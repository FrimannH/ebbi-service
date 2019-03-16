package is.larsen.ebbi.Util;


import is.larsen.ebbi.Dao.Impl.CustomerDaoImpl;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Model.responses.*;
import org.apache.commons.math3.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import is.larsen.ebbi.Dao.Impl.QuestionsDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import javafx.util.Pair;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.*;
import java.lang.Math;

public class ProcessReport {

    @Autowired
    QuestionsDaoImpl questionsDao;

    @Autowired
    CustomerDaoImpl customerDao;

    private static Median median = new Median();
    private static DecimalFormat percentFormatter = new DecimalFormat("##.##%");
    private static DecimalFormat scoreFormatter = new DecimalFormat("##.##");

    public GetReportResponse processReport(Integer customerId,
                                           List<AnswerResponse> answers,
                                           String customerName,
                                           String customerDescription,
                                           String startDate,
                                           String endDate,
                                           String ageGapFrom,
                                           String ageGapTo) {

        GetReportResponse response = new GetReportResponse();
        response.setCustomerName(customerName);
        response.setCustomerId(customerId);
        response.setReportStartDate(startDate);
        response.setReportEndDate(endDate);
        response.setAgeRangeFrom(ageGapFrom);
        response.setAgeRaneTo(ageGapTo);


        if ( answers.size() == 0) {
            response.setStatusCode(4000);
            response.setMessage("No data available");
            return response;
        }

        List<Question> questions = questionsDao.getQuestions().getQuestions();
        HashMap<Integer, Double> ebbiScores = questionsDao.GetEbbiScore();

        response.setReportItems(getReportItems(questions, answers, customerName, ebbiScores));
        response.setPromoterScore(createPromoterScoreData(answers));
        response.setCustomerImportance(createCustomerImportance(questions, answers, customerName));
        response.setCustomerImportanceItems(createCustomerImportanceItems(questions, answers));

        response.setSegmentation(getScoreFormulation(answers.stream()
                .filter(an -> (an.getScoreFormulation().equals(1)))
                .collect(Collectors.toList())));

        response.setDifferentiation(getScoreFormulation(answers.stream()
                .filter(an -> (an.getScoreFormulation().equals(2)))
                .collect(Collectors.toList())));

        response.setImageAndPerception(getScoreFormulation(answers.stream()
                .filter(an -> (an.getScoreFormulation().equals(3)))
                .collect(Collectors.toList())));

        response.setRespondentCount(getRespondentCount(answers));

        response.setStatusCode(0);

        return response;
    }

    protected String getRespondentCount(List<AnswerResponse> answers) {
        //get respondent count by checking answers to first question
       List<AnswerResponse> filteredAnswers =  answers.stream()
                .filter(a -> a.getQuestionId().equals(1))
                .collect(Collectors.toList());

        return Integer.toString(filteredAnswers.size());
    }

    protected String getScoreFormulation(List<AnswerResponse> answers) {
        List<Integer> answerValues = new ArrayList<>();
        answers.stream()
                .forEach(a -> answerValues.add(a.getAnswerValue()));

        Double mean = StatUtils.mean(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray());

        return getPercent(mean, 5.0, true);

    }

    protected CustomerImportance createCustomerImportance(List<Question> questions, List<AnswerResponse> answers, String customerName) {
        List<CustomerImportanceSummary> summary = new ArrayList<>();
        CustomerImportance customerImportance = new CustomerImportance();

        int[] summaryCount = new int[]{0};

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(4)))
                    .collect(Collectors.toList());
            if (filteredAnswers.size() > 0) {
               summaryCount[0]++;
               summary.add(createSummary(filteredAnswers, a.getQuestionName(), summaryCount[0]));
            }
        });

        customerImportance.setCustomerImportanceSummary(summary);

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(3) ))
                    .collect(Collectors.toList());
            if ( filteredAnswers.size() > 0) {
                ReportDetails reportDetails = createReportDetails(new ReportAnswers(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription(), a.getQuestionText(), filteredAnswers), 10.0);
                customerImportance.setCustomerImportanceDetails(reportDetails);
                customerImportance.setCustomerImportanceQuestion(a.getQuestionLongDescription().replaceAll("the company", customerName).replaceAll("The company", customerName));
            }
        });

        return customerImportance;
    }

    protected CustomerImportanceSummary createSummary(List<AnswerResponse> answers, String questionName, Integer count) {
        CustomerImportanceSummary summary = new CustomerImportanceSummary();

        summary.setQuestionName(questionName);
        if ( answers.size() > 0) {
            summary.setQuestionId(answers.get(0).getQuestionId());
        }

        List<Integer> answerValues = new ArrayList<Integer>();
        answers.stream()
                .forEach(an -> answerValues.add(an.getAnswerValue()));

        Double mean = StatUtils.mean(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray());
        summary.setQuestionScore(scoreFormatter.format(mean));
        summary.setQuestionScorePercentage(getPercent(mean, 5.0, false));
        summary.setQuestionCount(count);

        return summary;
    }

    protected List<ReportItem> createCustomerImportanceItems(List<Question> questions, List<AnswerResponse> answers) {
        List<ReportItem> items = new ArrayList<>();

        List<ReportAnswers> answerList = new ArrayList<>();

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(4)) )
                    .collect(Collectors.toList());
            if ( filteredAnswers.size() > 0) {
                ReportAnswers reportAnswers = new ReportAnswers(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription(),a.getQuestionText(), filteredAnswers);
                answerList.add(reportAnswers);
            }
        });
        return calculateImportanceValues(answerList);

    }

    protected List<ReportItem> calculateImportanceValues(List<ReportAnswers> answerList) {
        List<ReportItem> reportItems = new ArrayList<>();
        answerList.forEach(a -> {
            ReportItem reportItem = new ReportItem(a.getQuestionId(), a.getQuestionName());

            List<Integer> answerValues = new ArrayList<Integer>();
            a.getAnswers().stream()
                    .forEach(an -> answerValues.add(an.getAnswerValue()));

//            reportItem.setReportScores(createReportScore(answerValues));
            reportItem.setReportDetails(createReportDetails(a, 4.0));

            reportItems.add(reportItem);
        });
        return reportItems;
    }

    protected List<ReportItem> getReportItems(List<Question> questions, List<AnswerResponse> answers, String customerName, HashMap<Integer, Double> ebbScores) {
        List<ReportAnswers> answerList = new ArrayList<>();

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(1) || an.getQuestionType().equals(2)) )
                    .collect(Collectors.toList());
            if ( filteredAnswers.size() > 0) {
                ReportAnswers reportAnswers = new ReportAnswers(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription(),a.getQuestionText(), filteredAnswers);
                answerList.add(reportAnswers);
            }
        });
        return calculateValues(answerList, customerName, ebbScores);

    }

    protected PromoterScore createPromoterScoreData(List<AnswerResponse> answers) {
        PromoterScore promoterScore = new PromoterScore();

        List<AnswerResponse> filteredAnswers = answers.stream()
               .filter(an -> (an.getQuestionType().equals(3)))
               .collect(Collectors.toList());

        Integer total = filteredAnswers.size();

        promoterScore.setDetractors(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(0) || a.getAnswerValue().equals(1)
                             || a.getAnswerValue().equals(2) || a.getAnswerValue().equals(3)
                             || a.getAnswerValue().equals(4) || a.getAnswerValue().equals(5)
                             || a.getAnswerValue().equals(6)))
        .collect(Collectors.toList()), total));

        promoterScore.setPassives(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(7) || a.getAnswerValue().equals(8)))
                .collect(Collectors.toList()), total));

        promoterScore.setPromoters(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(9) || a.getAnswerValue().equals(10)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore0(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(0)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore1(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(1)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore2(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(2)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore3(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(3)))
                .collect(Collectors.toList()), total));


        promoterScore.setScore4(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(4)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore5(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(5)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore6(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(6)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore7(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(7)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore8(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(8)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore9(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(9)))
                .collect(Collectors.toList()), total));

        promoterScore.setScore10(getPromoterScore(filteredAnswers.stream()
                .filter(a -> (a.getAnswerValue().equals(10)))
                .collect(Collectors.toList()), total));

        return promoterScore;
    }

    protected Score getPromoterScore(List<AnswerResponse> answers, Integer total) {
        Integer value = answers.size();

        return new Score(value, getPercent(value, total, false), getPercent(value * 3, total, false), "", "", "", "");
    }

    protected List<ReportItem> calculateValues(List<ReportAnswers> answerList, String customerName, HashMap<Integer, Double> ebbiScores) {
        List<ReportItem> reportItems = new ArrayList<>();
        answerList.forEach(a -> {
            ReportItem reportItem = new ReportItem(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription().replaceAll("the company", customerName).replaceAll("The company", customerName), a.getQuestionText(), a.getAnswers().size());

            List<Integer> answerValues = new ArrayList<Integer>();
            a.getAnswers().stream()
                    .forEach(an -> answerValues.add(an.getAnswerValue()));

            reportItem.setReportScores(createReportScore(answerValues, ebbiScores.get(a.getQuestionId())));
            reportItem.setReportDetails(createReportDetails(a, 5.0));

            reportItems.add(reportItem);
        });
        return reportItems;
    }

    protected ReportDetails createReportDetails(ReportAnswers reportAnswers, Double totalScore) {
        ReportDetails reportDetails = new ReportDetails();

        reportDetails.setMale(createDetailsData(reportAnswers.getAnswers().stream()
              .filter(a -> a.getGender().equals(1)).collect(Collectors.toList()), totalScore));

        reportDetails.setFemale(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getGender().equals(2)).collect(Collectors.toList()), totalScore));

        reportDetails.setAgeLessThan45(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(1) || a.getAge().equals(2))).collect(Collectors.toList()), totalScore));

        reportDetails.setAge46to55(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(3) || a.getAge().equals(4))).collect(Collectors.toList()), totalScore));

        reportDetails.setAge56to65(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(5) || a.getAge().equals(6))).collect(Collectors.toList()), totalScore));

        reportDetails.setAgeOver66(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(7) || a.getAge().equals(8))).collect(Collectors.toList()), totalScore));

        reportDetails.setIncome1(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getIncome().equals(1) || a.getIncome().equals(2))).collect(Collectors.toList()), totalScore));

        reportDetails.setIncome2(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getIncome().equals(3) || a.getIncome().equals(4))).collect(Collectors.toList()), totalScore));

        reportDetails.setEducation1(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getEducation().equals(1) || a.getEducation().equals(2) || a.getEducation().equals(3))).collect(Collectors.toList()), totalScore));

        reportDetails.setEducation2(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getEducation().equals(3) || a.getEducation().equals(4) || a.getEducation().equals(5))).collect(Collectors.toList()), totalScore));

        reportDetails.setDetractors(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(1) || a.getPromoterValue().equals(2) || a.getPromoterValue().equals(3) || a.getPromoterValue().equals(4) || a.getPromoterValue().equals(6) || a.getPromoterValue().equals(6))).collect(Collectors.toList()), totalScore));

        reportDetails.setPassives(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(7) || a.getPromoterValue().equals(8))).collect(Collectors.toList()), totalScore));

        reportDetails.setPromoters(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(9) || a.getPromoterValue().equals(10))).collect(Collectors.toList()), totalScore));

        reportDetails.setSwitchSupplierNo(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getSwitchSuppliers().equals(1)).collect(Collectors.toList()), totalScore));

        reportDetails.setSwitchSupplierYes(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getSwitchSuppliers().equals(2)).collect(Collectors.toList()), totalScore));

        return reportDetails;
    }

    protected ReportDetailsData createDetailsData(List<AnswerResponse> answerList, Double totalScore) {
        ReportDetailsData reportDetails = new ReportDetailsData();
        Integer total = answerList.size();

        if (totalScore > 6 ) {
            reportDetails.setWell(getPercent(answerList.stream()
                    .filter(a -> (a.getAnswerValue().equals(9) || a.getAnswerValue() .equals(10)))
                    .collect(Collectors.toList()).size(), total, false));

            reportDetails.setModerate(getPercent(answerList.stream()
                    .filter(a -> (a.getAnswerValue().equals(7) || a.getAnswerValue().equals(8)))
                    .collect(Collectors.toList()).size(), total, false));

            reportDetails.setBadly(getPercent(answerList.stream()
                    .filter(a -> (a.getAnswerValue().equals(1) || a.getAnswerValue().equals(2) || a.getAnswerValue().equals(3) || a.getAnswerValue().equals(4) || a.getAnswerValue().equals(5) || a.getAnswerValue().equals(6)))
                    .collect(Collectors.toList()).size(), total, false));

        } else {
            reportDetails.setWell(getPercent(answerList.stream()
                    .filter(a -> a.getAnswerValue().equals(5) || a.getAnswerValue().equals(4))
                    .collect(Collectors.toList()).size(), total , false));

            reportDetails.setModerate(getPercent(answerList.stream()
                    .filter(a -> a.getAnswerValue().equals(3))
                    .collect(Collectors.toList()).size(), total , false));

            reportDetails.setBadly(getPercent(answerList.stream()
                    .filter(a -> a.getAnswerValue().equals(1) || a.getAnswerValue().equals(2))
                    .collect(Collectors.toList()).size() , total , false));

        }

        String score = getScore(answerList);

        reportDetails.setScore(score);
        reportDetails.setScorePercentage(getPercent(Double.valueOf(score) -1, totalScore -1, false));

        return reportDetails;
    }

   protected String getScore(List<AnswerResponse> answerList) {
       //remove missing answers
       List<AnswerResponse> filteredList = answerList.stream()
               .filter(a -> a.getAnswerValue() != 0)
               .collect(Collectors.toList());

       List<Integer> valueList = new ArrayList<>();

       filteredList.stream()
               .forEach(a -> valueList.add(a.getAnswerValue()));

       return scoreFormatter.format(StatUtils.mean(valueList.stream().mapToDouble(v -> v.doubleValue()).toArray()));

   }

    protected ReportScores createReportScore(List<Integer> answerValues, Double ebbiScore) {
        ReportScores reportScore = new ReportScores();

        Integer totalAnswers = answerValues.size();
        reportScore.setTotalAnswers(new Score(totalAnswers, "100", "", "", "", "", ""));

        Double strokeOffset = 25.0;

        reportScore.setVeryWell(getScore(answerValues, 5, totalAnswers, strokeOffset));

        reportScore.setDidNotAnswer(getScore(answerValues, 0, totalAnswers, strokeOffset));
        strokeOffset = strokeOffset + Double.parseDouble(reportScore.getDidNotAnswer().getPercentage());

        reportScore.setVeryBadly(getScore(answerValues, 1, totalAnswers, strokeOffset));
        strokeOffset = strokeOffset + Double.parseDouble(reportScore.getVeryBadly().getPercentage());

        reportScore.setBadly(getScore(answerValues, 2, totalAnswers, strokeOffset));
        strokeOffset = strokeOffset + Double.parseDouble(reportScore.getBadly().getPercentage());

        reportScore.setModerate(getScore(answerValues, 3, totalAnswers, strokeOffset));
        strokeOffset = strokeOffset + Double.parseDouble(reportScore.getModerate().getPercentage());

        reportScore.setWell(getScore(answerValues, 4, totalAnswers, strokeOffset));
        strokeOffset = strokeOffset + Double.parseDouble(reportScore.getWell().getPercentage());

        Integer validAnswers = reportScore.getTotalAnswers().getValue() - reportScore.getDidNotAnswer().getValue();

        reportScore.setValidAnswers(new Score(validAnswers, getPercent(validAnswers, totalAnswers, true), "", "", "", "", ""));

        reportScore.setYourMedian(scoreFormatter.format(median.evaluate(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));
        reportScore.setMedianPercentage(getPercent(Double.parseDouble(reportScore.getYourMedian()) - 1.03, 4.0, false));
        reportScore.setYourMean(scoreFormatter.format(StatUtils.mean(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));
        reportScore.setMeanPercentage(getPercent(Double.parseDouble(reportScore.getYourMean()) - 1.13, 4.0, false));
        reportScore.setStandardDeviation(scoreFormatter.format(Math.sqrt(StatUtils.variance(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray()))));

        reportScore.setEbbiScore(ebbiScore.toString());
        reportScore.setEbbiPercentage(getPercent(ebbiScore - 1.25, 4.0, false));

        return reportScore;
    }


    protected Score getScore(List<Integer> answerValues, Integer score, Integer total, Double dashOffset) {
        Integer value = answerValues.stream()
                .filter(v -> v.equals(score))
                .collect(Collectors.toList()).size();


        String strokeDashStart = getPercent(value, total, true);
        String strokeDashEnd = String.valueOf(100.0 - Double.parseDouble(strokeDashStart));
        if ( score != 5 ) {
            dashOffset = dashOffset + Double.parseDouble(getPercent(value, total, true));
        }

        return new Score(value, getPercent(value, total, true), "", strokeDashStart, strokeDashEnd, dashOffset.toString(), strokeDashStart + " " + strokeDashEnd);
    }

    protected String getPercent(Double value, Double total, Boolean numberFormat) {
        if ( value < total) {
            double percent = (double)value/(double)total;
            if ( numberFormat) {
                return scoreFormatter.format(percent * 100);
            } else {
                return percentFormatter.format(percent);
            }
        } else {
            return "100%";
        }

    }

    protected String getPercent(Integer value, Integer total, Boolean numberFormat) {

        if ( value < total) {
            double percent = ((double)value/(double)total);
            if ( numberFormat) {
                return scoreFormatter.format(percent * 100);
            } else {
                return percentFormatter.format(percent);
            }
        } else {
            return "100%";
        }

    }
}
