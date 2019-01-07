package is.larsen.ebbi.Util;


import is.larsen.ebbi.Dao.Impl.CustomerDaoImpl;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Model.responses.GetReportResponse;
import is.larsen.ebbi.Model.responses.ReportDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import is.larsen.ebbi.Dao.Impl.QuestionsDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.*;

public class ProcessReport {

    @Autowired
    QuestionsDaoImpl questionsDao;

    @Autowired
    CustomerDaoImpl customerDao;

    private static Median median = new Median();
    private static DecimalFormat percentFormatter = new DecimalFormat("##.##%");
    private static DecimalFormat scoreFormatter = new DecimalFormat("##.##");

    public GetReportResponse processReport(Integer customerId, List<AnswerResponse> answers) {
        GetReportResponse response = new GetReportResponse();
        Customer customer = customerDao.getCustomer(customerId);
        response.setCustomerName(customer.getCustomerName());
        response.setCustomerId(customerId);

        if ( answers.size() == 0) {
            response.setStatusCode(4000);
            response.setMessage("No data available");
            return response;
        }

        List<Question> questions = questionsDao.getQuestions().getQuestions();

        response.setReportItems(getReportItems(questions, answers, customer.getCustomerName()));
        response.setPromoterScore(createPromoterScore( questions, answers));

        response.setStatusCode(0);

        return response;
    }

    protected List<ReportItem> getReportItems(List<Question> questions, List<AnswerResponse> answers, String customerName) {
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
        return calculateValues(answerList, customerName);

    }

    protected PromoterScore createPromoterScore(List<Question> questions, List<AnswerResponse> answers) {
        PromoterScore promoterScore = new PromoterScore();

        List<ReportAnswers> answerList = new ArrayList<>();

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(3)) )
                    .collect(Collectors.toList());
            if ( filteredAnswers.size() > 0) {
                ReportAnswers reportAnswers = new ReportAnswers(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription(),a.getQuestionText(), filteredAnswers);
                answerList.add(reportAnswers);
            }
        });

        return promoterScore;
    }

    protected List<ReportItem> calculateValues(List<ReportAnswers> answerList, String customerName) {
        List<ReportItem> reportItems = new ArrayList<>();
        answerList.forEach(a -> {
            ReportItem reportItem = new ReportItem(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription().replaceAll("the company", customerName).replaceAll("The company", customerName), a.getQuestionText(), a.getAnswers().size());

            List<Integer> answerValues = new ArrayList<Integer>();
            a.getAnswers().stream()
                    .forEach(an -> answerValues.add(an.getAnswerValue()));

            reportItem.setReportScores(createReportScore(answerValues));
            reportItem.setReportDetails(createReportDetails(a));

            reportItems.add(reportItem);
        });
        return reportItems;
    }

    protected ReportDetails createReportDetails(ReportAnswers reportAnswers) {
        ReportDetails reportDetails = new ReportDetails();

        reportDetails.setMale(createDetailsData(reportAnswers.getAnswers().stream()
              .filter(a -> a.getGender().equals(1)).collect(Collectors.toList())));

        reportDetails.setFemale(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getGender().equals(2)).collect(Collectors.toList())));

        List<AnswerResponse> filteredList =reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(1) || a.getAge().equals(2))).collect(Collectors.toList());

        reportDetails.setAgeLessThan45(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(1) || a.getAge().equals(2))).collect(Collectors.toList())));

        reportDetails.setAge46to55(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(3) || a.getAge().equals(4))).collect(Collectors.toList())));

        reportDetails.setAge56to65(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(5) || a.getAge().equals(6))).collect(Collectors.toList())));

        reportDetails.setAgeOver66(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getAge().equals(7) || a.getAge().equals(8))).collect(Collectors.toList())));

        reportDetails.setIncome1(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getIncome().equals(1) || a.getIncome().equals(2))).collect(Collectors.toList())));

        reportDetails.setIncome2(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getIncome().equals(3) || a.getIncome().equals(4))).collect(Collectors.toList())));

        reportDetails.setEducation1(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getEducation().equals(1) || a.getEducation().equals(2) || a.getEducation().equals(3))).collect(Collectors.toList())));

        reportDetails.setEducation2(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getEducation().equals(3) || a.getEducation().equals(4) || a.getEducation().equals(5))).collect(Collectors.toList())));

        reportDetails.setDetractors(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(1) || a.getPromoterValue().equals(2) || a.getPromoterValue().equals(3) || a.getPromoterValue().equals(4) || a.getPromoterValue().equals(6) || a.getPromoterValue().equals(6))).collect(Collectors.toList())));

        reportDetails.setPassives(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(7) || a.getPromoterValue().equals(8))).collect(Collectors.toList())));

        reportDetails.setPromoters(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> (a.getPromoterValue().equals(9) || a.getPromoterValue().equals(10))).collect(Collectors.toList())));

        reportDetails.setSwitchSupplierNo(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getSwitchSuppliers().equals(1)).collect(Collectors.toList())));

        reportDetails.setSwitchSupplierYes(createDetailsData(reportAnswers.getAnswers().stream()
                .filter(a -> a.getSwitchSuppliers().equals(2)).collect(Collectors.toList())));

        return reportDetails;
    }

    protected ReportDetailsData createDetailsData(List<AnswerResponse> answerList) {
        ReportDetailsData reportDetails = new ReportDetailsData();
        Integer total = answerList.size();

        List<AnswerResponse> filteredList = answerList.stream()
                .filter(a -> a.getAnswerValue().equals(0))
        .collect(Collectors.toList());

        reportDetails.setWell(getPercent(answerList.stream()
                .filter(a -> a.getAnswerValue() .equals(5) || a.getAnswerValue() .equals(4))
                .collect(Collectors.toList()).size(), total, false));

        reportDetails.setModerate(getPercent(answerList.stream()
                .filter(a -> a.getAnswerValue().equals(3))
                .collect(Collectors.toList()).size(), total, false));

        reportDetails.setBadly(getPercent(answerList.stream()
                .filter(a -> a.getAnswerValue().equals(1) || a.getAnswerValue().equals(2))
                .collect(Collectors.toList()).size(), total, false));

        String temp = getScore(answerList);
        reportDetails.setScore(getScore(answerList));

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

    protected ReportScores createReportScore(List<Integer> answerValues) {
        ReportScores reportScore = new ReportScores();

        Integer totalAnswers = answerValues.size();
        reportScore.setTotalAnswers(new Score(totalAnswers, "100"));

        reportScore.setVeryWell(getScore(answerValues, 5, totalAnswers));
        reportScore.setWell(getScore(answerValues, 4, totalAnswers));
        reportScore.setModerate(getScore(answerValues, 3, totalAnswers));
        reportScore.setBadly(getScore(answerValues, 2, totalAnswers));
        reportScore.setVeryBadly(getScore(answerValues, 1, totalAnswers));

        reportScore.setDidNotAnswer(getScore(answerValues, 0, totalAnswers));

        Integer validAnswers = reportScore.getTotalAnswers().getValue() - reportScore.getDidNotAnswer().getValue();

        reportScore.setValidAnswers(new Score(validAnswers, getPercent(validAnswers, totalAnswers, true)));

        reportScore.setYourMedian(scoreFormatter.format(median.evaluate(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));
        reportScore.setYourMean(scoreFormatter.format(StatUtils.mean(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));

        return reportScore;
    }


    protected Score getScore(List<Integer> answerValues, Integer score, Integer total) {
        Integer value = answerValues.stream()
                .filter(v -> v.equals(score))
                .collect(Collectors.toList()).size();

        return new Score(value, getPercent(value, total, true));
    }

    protected String getPercent(Integer value, Integer total, Boolean numberFormat) {

        if ( value < total) {
            double percent = (double)value/(double)total;
            if ( numberFormat) {
                return scoreFormatter.format(percent);
            } else {
                return percentFormatter.format(percent);
            }
        } else {
            return "100%";
        }

    }
}
