package is.larsen.ebbi.Util;


import is.larsen.ebbi.Dao.Impl.CustomerDaoImpl;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Model.responses.GetReportResponse;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.springframework.beans.factory.annotation.Autowired;
import is.larsen.ebbi.Dao.Impl.QuestionsDaoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.*;

public class ProcessReport {

    @Autowired
    QuestionsDaoImpl questionsDao;

    @Autowired
    CustomerDaoImpl customerDao;

    public GetReportResponse processReport(Integer customerId, List<AnswerResponse> answers) {
        GetReportResponse response = new GetReportResponse();

        if ( answers.size() == 0) {
            response.setStatusCode(0);
            response.setMessage("No data available");
            return response;
        }

        List<Question> questions = questionsDao.getQuestions().getQuestions();
        Customer customer = customerDao.getCustomer(customerId);
        response.setCustomerName(customer.getCustomerName());
        response.setCustomerId(customerId);
        List<ReportAnswers> answerList = new ArrayList<>();

        questions.forEach(a -> {
            List<AnswerResponse> filteredAnswers = answers.stream()
                    .filter(an -> a.getQuestionId().equals(an.getQuestionId()))
                    .filter(an -> (an.getQuestionType().equals(1) ||an.getQuestionType().equals(2)) )
                    .collect(Collectors.toList());
            if ( filteredAnswers.size() > 0) {
                ReportAnswers reportAnswers = new ReportAnswers(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription(),a.getQuestionText(), filteredAnswers);
                answerList.add(reportAnswers);
            }
        });

        calculateValues(answerList, response, customer.getCustomerName());
        response.setStatusCode(200);

        return response;
    }


    protected void calculateValues(List<ReportAnswers> answerList, GetReportResponse response, String customerName) {
        List<ReportItem> reportItems = new ArrayList<>();
        answerList.forEach(a -> {
            ReportItem reportItem = new ReportItem(a.getQuestionId(), a.getQuestionName(), a.getQuestionShortDescription().replaceAll("the company", customerName).replaceAll("The company", customerName), a.getQuestionText(), a.getAnswers().size());

            List<Integer> answerValues = new ArrayList<Integer>();
            a.getAnswers().stream()
                    .forEach(an -> answerValues.add(an.getAnswerValue()));

            reportItem.setReportScore(createReportScore(answerValues));
            reportItem.setReportDetails(createReportDetails(answerValues));

            reportItems.add(reportItem);
        });
        response.setReportItems(reportItems);
        String temp = "";
    }

    protected ReportDetails createReportDetails(List<Integer> answerValues) {
        ReportDetails reportDetails = new ReportDetails();

        return reportDetails;
    }

    protected ReportScore createReportScore(List<Integer> answerValues) {
        Median median = new Median();
        ReportScore reportScore = new ReportScore();

        reportScore.setValidAnswers(answerValues.size());
        reportScore.setTotalAnswers(answerValues.size());

        reportScore.setVeryWell(getScore(answerValues, 5));
        reportScore.setWell(getScore(answerValues, 4));
        reportScore.setModerate(getScore(answerValues, 3));
        reportScore.setBadly(getScore(answerValues, 2));
        reportScore.setVeryBadly(getScore(answerValues, 1));

        reportScore.setYourMedian(Double.toString(median.evaluate(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));
        reportScore.setYourMean(Double.toString(StatUtils.mean(answerValues.stream().mapToDouble(v -> v.doubleValue()).toArray())));

        return reportScore;
    }


    protected Integer getScore(List<Integer> answerValues, Integer value) {
        return answerValues.stream()
                .filter(v -> v.equals(value))
                .collect(Collectors.toList()).size();
    }

}
