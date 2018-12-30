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
    public AddAnswersResponse addAnswers(Integer customerId, List<AnswerRequest> surveyResponses) {

        AddAnswersResponse response = new AddAnswersResponse();

        surveyResponses.forEach(r -> {
            String countryCode = r.getCountryCode().equals("") ? "0" : r.getCountryCode();
            String region = r.getRegion().equals("") ? "0" : r.getRegion();
            String switchSuppliers = r.getQuestion28().equals("") ? "0" : r.getQuestion28();
            String gender = r.getQuestion29().equals("") ? "0" : r.getQuestion29();
            String age = r.getQuestion30().equals("") ? "0" : r.getQuestion30();
            String education = r.getQuestion31().equals("") ? "0" : r.getQuestion31();
            String income = r.getQuestion32().equals("") ? "0" : r.getQuestion32();

            List<Pair<String, String>> answers = getAnswerList(r);
            answers.forEach(a -> {
                insertAnswer(customerId, countryCode, region, a.getKey(), a.getValue(), gender, age, income, education, switchSuppliers);
            });

        });

        response.setStatusCode(0);

        return response;

    }

    @Override
    public GetAnswerResponse getAnswers(Integer customerId) {

        return new GetAnswerResponse(customerId, jdbcTemplate.query("Select Answers.question_id, Questions.question_type, Answers.customer_id, Answers.response, Answers.gender, Answers.age, Answers.income, Answers.education, Answers.switch_suppliers, Answers.country_code, Answers.region, Answers.time_stamp FROM Answers INNER JOIN Questions ON Answers.question_id=Questions.question_id where Answers.customer_id = " + customerId, new AnswersRowMapper()));

    }

    @Override
    public GetReportResponse getReport(Integer customerId) {

        List<AnswerResponse> answers = jdbcTemplate.query("Select Answers.question_id, Questions.question_type, Answers.customer_id, Answers.response, Answers.gender, Answers.age, Answers.income, Answers.education, Answers.switch_suppliers, Answers.country_code, Answers.region, Answers.time_stamp FROM Answers INNER JOIN Questions ON Answers.question_id=Questions.question_id where Answers.customer_id = " + customerId, new AnswersRowMapper());

        return reportProcessor.processReport(customerId, answers);

    }

    protected List<Pair<String, String>> getAnswerList(AnswerRequest answer) {
        List<Pair<String, String>> answers = new ArrayList<>();

        if (answer.getQuestion1() != null && !answer.getQuestion1().equals("") ) {
            Pair pair = new Pair("1", answer.getQuestion1());
            answers.add(pair);
        }

        if (answer.getQuestion2() != null && !answer.getQuestion2().equals("") ) {
            Pair pair = new Pair("2", answer.getQuestion2());
            answers.add(pair);
        }

        if (answer.getQuestion3() != null && !answer.getQuestion3().equals("") ) {
            Pair pair = new Pair("3", answer.getQuestion3());
            answers.add(pair);
        }

        if (answer.getQuestion4() != null && !answer.getQuestion4().equals("") ) {
            Pair pair = new Pair("4", answer.getQuestion4());
            answers.add(pair);
        }

        if (answer.getQuestion5() != null && !answer.getQuestion5().equals("") ) {
            Pair pair = new Pair("5", answer.getQuestion5());
            answers.add(pair);
        }

        if (answer.getQuestion6() != null && !answer.getQuestion6().equals("") ) {
            Pair pair = new Pair("6", answer.getQuestion6());
            answers.add(pair);
        }

        if (answer.getQuestion7() != null && !answer.getQuestion7().equals("") ) {
            Pair pair = new Pair("7", answer.getQuestion7());
            answers.add(pair);
        }

        if (answer.getQuestion8() != null && !answer.getQuestion8().equals("") ) {
            Pair pair = new Pair("8", answer.getQuestion8());
            answers.add(pair);
        }

        if (answer.getQuestion9() != null && !answer.getQuestion9().equals("") ) {
            Pair pair = new Pair("9", answer.getQuestion9());
            answers.add(pair);
        }

        if (answer.getQuestion10() != null && !answer.getQuestion10().equals("") ) {
            Pair pair = new Pair("10", answer.getQuestion10());
            answers.add(pair);
        }

        if (answer.getQuestion11() != null && !answer.getQuestion11().equals("") ) {
            Pair pair = new Pair("11", answer.getQuestion11());
            answers.add(pair);
        }

        if (answer.getQuestion12() != null && !answer.getQuestion12().equals("") ) {
            Pair pair = new Pair("12", answer.getQuestion12());
            answers.add(pair);
        }

        if (answer.getQuestion13() != null && !answer.getQuestion13().equals("") ) {
            Pair pair = new Pair("13", answer.getQuestion13());
            answers.add(pair);
        }

        if (answer.getQuestion14() != null && !answer.getQuestion14().equals("") ) {
            Pair pair = new Pair("14", answer.getQuestion14());
            answers.add(pair);
        }

        if (answer.getQuestion15() != null && !answer.getQuestion15().equals("") ) {
            Pair pair = new Pair("15", answer.getQuestion15());
            answers.add(pair);
        }

        if (answer.getQuestion16() != null && !answer.getQuestion16().equals("") ) {
            Pair pair = new Pair("16", answer.getQuestion16());
            answers.add(pair);
        }

        if (answer.getQuestion17() != null && !answer.getQuestion17().equals("") ) {
            Pair pair = new Pair("17", answer.getQuestion17());
            answers.add(pair);
        }

        if (answer.getQuestion18() != null && !answer.getQuestion18().equals("") ) {
            Pair pair = new Pair("18", answer.getQuestion18());
            answers.add(pair);
        }

        if (answer.getQuestion19() != null && !answer.getQuestion19().equals("") ) {
            Pair pair = new Pair("19", answer.getQuestion19());
            answers.add(pair);
        }

        if (answer.getQuestion20() != null && !answer.getQuestion20().equals("") ) {
            Pair pair = new Pair("20", answer.getQuestion20());
            answers.add(pair);
        }

        if (answer.getQuestion21() != null && !answer.getQuestion21().equals("") ) {
            Pair pair = new Pair("21", answer.getQuestion21());
            answers.add(pair);
        }

        if (answer.getQuestion22() != null && !answer.getQuestion22().equals("") ) {
            Pair pair = new Pair("22", answer.getQuestion22());
            answers.add(pair);
        }

        if (answer.getQuestion23() != null && !answer.getQuestion23().equals("") ) {
            Pair pair = new Pair("23", answer.getQuestion23());
            answers.add(pair);
        }

        if (answer.getQuestion24() != null && !answer.getQuestion24().equals("") ) {
            Pair pair = new Pair("24", answer.getQuestion24());
            answers.add(pair);
        }

        if (answer.getQuestion25() != null && !answer.getQuestion25().equals("") ) {
            Pair pair = new Pair("25", answer.getQuestion25());
            answers.add(pair);
        }
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
                String switch_suppliers) {

        String query = "INSERT INTO Answers (question_id, customer_id, response, gender, age, income, education, switch_suppliers, country_code,region, time_stamp) " +
                       "VALUES (\"" + questionId + "\", \"" + customerId + "\", \"" + answer + "\", \"" + gender + "\", \"" + age +
                       "\", \"" + income + "\", \"" + education + "\", \"" + switch_suppliers + "\", \"" + countryCode + "\", \"" + region + "\", \" "  + "\")";
        return jdbcTemplate.update(query);
    }

    @Override
    public BaseResponse deleteAnswers(Integer customerId) {

        String query = "DELETE FROM Answers where customer_id = " + customerId + ";";

        Integer status = jdbcTemplate.update(query);

        return new BaseResponse(status, "");
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
            answerResponse.setSwitchSuppliers(rs.getInt("switch_suppliers"));
            answerResponse.setCountryCode(rs.getString("country_code"));
            answerResponse.setRegion(rs.getString("region"));
            answerResponse.setTimeStamp(rs.getString("time_stamp"));

            return answerResponse;
        }
    }
}
