package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.AnswersDao;
import is.larsen.ebbi.Util.ProcessReport;
import is.larsen.ebbi.Model.Question;
import is.larsen.ebbi.Model.ReportAnswers;
import is.larsen.ebbi.Model.ReportItem;
import is.larsen.ebbi.Model.requests.AnswerRequest;
import is.larsen.ebbi.Model.responses.*;
import is.larsen.ebbi.Model.AnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

import java.lang.Object;
import java.time.ZonedDateTime;

public class AnswersDaoImpl implements AnswersDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProcessReport reportProcessor;

    @Override
    public UpdateSurveyResponse addAnswers(Integer customerId, List<AnswerRequest> surveyResponses) {

        UpdateSurveyResponse response = new UpdateSurveyResponse();

        Integer[] count = {0};

        surveyResponses.forEach(r -> {

            if ( isValidResponse(r)) {
                String countryCode = r.getCountryCode().equals("") ? "0" : r.getCountryCode();
                String region = r.getRegion().equals("") ? "0" : r.getRegion();
                String switchSuppliers = r.getQuestion28().equals("") ? "0" : r.getQuestion28();
                String gender = r.getQuestion29().equals("") ? "0" : r.getQuestion29();
                String age = r.getQuestion30().equals("") ? "0" : r.getQuestion30();
                String education = r.getQuestion31().equals("") ? "0" : r.getQuestion31();
                String income = r.getQuestion32().equals("") ? "0" : r.getQuestion32();
                String promoterScore = r.getQuestion23().equals("") ? "0" : r.getQuestion23();

                List<Pair<String, String>> answers = getAnswerList(r);
                answers.forEach(a -> {
                    insertAnswer(customerId, countryCode, region, a.getKey(), a.getValue(), gender, age, income, education, switchSuppliers, promoterScore);
                    count[0] = count[0] + 1;
                });
            }

        });

        response.setRowsAffected(count[0]);
        response.setStatusCode(0);

        return response;

    }

    protected Boolean isValidResponse(AnswerRequest request) {
        if ( request.getQuestion28() == null || request.getQuestion29() == null || request.getQuestion30() == null || request.getQuestion31() == null || request.getQuestion32() == null) {
            return false;
        }

        return true;
    }


    @Override
    public GetAnswerResponse getAnswers(Integer customerId) {

        return new GetAnswerResponse(customerId, jdbcTemplate.query("Select Answers.question_id, Questions.question_type, Answers.customer_id, Answers.response, Answers.gender, Answers.age, Answers.income, Answers.education, Answers.promoter_score, Answers.switch_suppliers, Answers.country_code, Answers.region, Answers.time_stamp FROM Answers INNER JOIN Questions ON Answers.question_id=Questions.question_id where Answers.customer_id = " + customerId, new AnswersRowMapper()));

    }

    @Override
    public GetReportResponse getReport(Integer customerId) {

        List<AnswerResponse> answers = jdbcTemplate.query("Select Answers.question_id, Questions.question_type, Answers.customer_id, Answers.response, Answers.gender, Answers.age, Answers.income, Answers.education, Answers.promoter_score, Answers.switch_suppliers, Answers.country_code, Answers.region, Answers.time_stamp FROM Answers INNER JOIN Questions ON Answers.question_id=Questions.question_id where Answers.customer_id = " + customerId, new AnswersRowMapper());

        return reportProcessor.processReport(customerId, answers);

    }

    protected List<Pair<String, String>> getAnswerList(AnswerRequest answer) {
        List<Pair<String, String>> answers = new ArrayList<>();

        if (answer.getQuestion1() == null || answer.getQuestion1().equals("") ) {
            answer.setQuestion1("0");
        }
        answers.add(new Pair("1", answer.getQuestion1()));

        if (answer.getQuestion2() == null || answer.getQuestion2().equals("") ) {
            answer.setQuestion2("0");
        }
        answers.add(new Pair("2", answer.getQuestion2()));

        if (answer.getQuestion3() == null || answer.getQuestion3().equals("") ) {
            answer.setQuestion3("0");
        }
        answers.add(new Pair("3", answer.getQuestion3()));

        if (answer.getQuestion4() == null || answer.getQuestion4().equals("") ) {
            answer.setQuestion4("0");
        }
        answers.add(new Pair("4", answer.getQuestion4()));

        if (answer.getQuestion5() == null || answer.getQuestion5().equals("") ) {
            answer.setQuestion5("0");
        }
        answers.add(new Pair("5", answer.getQuestion5()));

        if (answer.getQuestion6() == null || answer.getQuestion6().equals("") ) {
            answer.setQuestion6("0");
        }
        answers.add(new Pair("6", answer.getQuestion6()));

        if (answer.getQuestion7() == null || answer.getQuestion7().equals("") ) {
            answer.setQuestion7("0");
        }
        answers.add(new Pair("7", answer.getQuestion7()));

        if (answer.getQuestion8() == null || answer.getQuestion8().equals("") ) {
            answer.setQuestion8("0");
        }
        answers.add(new Pair("8", answer.getQuestion8()));

        if (answer.getQuestion9() == null || answer.getQuestion9().equals("") ) {
            answer.setQuestion9("0");
        }
        answers.add(new Pair("9", answer.getQuestion9()));

        if (answer.getQuestion10() == null || answer.getQuestion10().equals("") ) {
            answer.setQuestion10("0");
        }
        answers.add(new Pair("10", answer.getQuestion10()));

        if (answer.getQuestion11() == null || answer.getQuestion11().equals("") ) {
            answer.setQuestion11("0");
        }
        answers.add(new Pair("11", answer.getQuestion11()));

        if (answer.getQuestion12() == null || answer.getQuestion12().equals("") ) {
            answer.setQuestion12("0");
        }
        answers.add(new Pair("12", answer.getQuestion12()));

        if (answer.getQuestion13() == null || answer.getQuestion13().equals("") ) {
            answer.setQuestion13("0");
        }
        answers.add(new Pair("13", answer.getQuestion13()));

        if (answer.getQuestion14() == null || answer.getQuestion14().equals("") ) {
            answer.setQuestion14("0");
        }
        answers.add(new Pair("14", answer.getQuestion14()));

        if (answer.getQuestion15() == null || answer.getQuestion15().equals("") ) {
            answer.setQuestion15("0");
        }
        answers.add(new Pair("15", answer.getQuestion15()));

        if (answer.getQuestion16() == null || answer.getQuestion16().equals("") ) {
            answer.setQuestion16("0");
        }
        answers.add(new Pair("16", answer.getQuestion16()));

        if (answer.getQuestion17() == null || answer.getQuestion17().equals("") ) {
            answer.setQuestion17("0");
        }
        answers.add(new Pair("17", answer.getQuestion17()));

        if (answer.getQuestion18() == null || answer.getQuestion18().equals("") ) {
            answer.setQuestion18("0");
        }
        answers.add(new Pair("18", answer.getQuestion18()));

        if (answer.getQuestion19() == null || answer.getQuestion19().equals("") ) {
            answer.setQuestion19("0");
        }
        answers.add(new Pair("19", answer.getQuestion19()));

        if (answer.getQuestion20() == null || answer.getQuestion20().equals("") ) {
            answer.setQuestion20("0");
        }
        answers.add(new Pair("20", answer.getQuestion20()));

        if (answer.getQuestion21() == null || answer.getQuestion21().equals("") ) {
            answer.setQuestion21("0");
        }
        answers.add(new Pair("21", answer.getQuestion21()));

        if (answer.getQuestion22() == null || answer.getQuestion22().equals("") ) {
            answer.setQuestion22("0");
        }
        answers.add(new Pair("22", answer.getQuestion22()));

        if (answer.getQuestion23() == null || answer.getQuestion23().equals("") ) {
            answer.setQuestion23("0");
        }
        answers.add(new Pair("23", answer.getQuestion23()));

        if (answer.getQuestion24() == null || answer.getQuestion24().equals("") ) {
            answer.setQuestion24("0");
        }
        answers.add(new Pair("24", answer.getQuestion24()));

        if (answer.getQuestion25() == null || answer.getQuestion25().equals("") ) {
            answer.setQuestion25("0");
        }
        answers.add(new Pair("25", answer.getQuestion25()));

        if (answer.getQuestion26() == null || answer.getQuestion26().equals("") ) {
            answer.setQuestion26("0");
        }
        answers.add(new Pair("26", answer.getQuestion26()));

        if (answer.getQuestion27() == null || answer.getQuestion27().equals("") ) {
            answer.setQuestion27("0");
        }
        answers.add(new Pair("27", answer.getQuestion27()));

        return answers;
    }

    protected Integer insertAnswer(Integer customerId,
                String countryCode,
                String region,
                String questionId,
                String answer,
                String gender,
                String age,
                String income,
                String education,
                String switch_suppliers,
                String promoterScore) {

        String query = "INSERT INTO Answers (question_id, customer_id, response, gender, age, income, education, promoter_score, switch_suppliers, country_code,region, time_stamp) " +
                       "VALUES (\"" + questionId + "\", \"" + customerId + "\", \"" + answer + "\", \"" + gender + "\", \"" + age +
                       "\", \"" + income + "\", \"" + education + "\", \"" + promoterScore + "\", \"" + switch_suppliers + "\", \"" + countryCode + "\", \"" + region + "\", \" "  + "\")";
        return jdbcTemplate.update(query);
    }

    @Override
    public DeleteSurveyResponse deleteAnswers(Integer customerId) {

        try {
            String query = "DELETE FROM Answers where customer_id = " + customerId + ";";

            Integer status = jdbcTemplate.update(query);
            return new DeleteSurveyResponse(0, "", status);
        } catch (Exception e) {
            return new DeleteSurveyResponse(4000, "Error deleting survey", 0);
        }

    }

    protected class AnswersRowMapper implements RowMapper {


        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            AnswerResponse answerResponse = new AnswerResponse();
            answerResponse.setQuestionId(rs.getInt("question_id"));
            answerResponse.setQuestionType(rs.getInt("question_type"));
            answerResponse.setAnswerValue(rs.getInt("response"));
            answerResponse.setGender(rs.getInt("gender"));
            answerResponse.setAge(rs.getInt("age"));
            answerResponse.setIncome(rs.getInt("income"));
            answerResponse.setEducation(rs.getInt("education"));
            answerResponse.setPromoterValue(rs.getInt("promoter_score"));
            answerResponse.setSwitchSuppliers(rs.getInt("switch_suppliers"));
            answerResponse.setCountryCode(rs.getString("country_code"));
            answerResponse.setRegion(rs.getString("region"));
            answerResponse.setTimeStamp(rs.getString("time_stamp"));

            return answerResponse;
        }
    }
}
