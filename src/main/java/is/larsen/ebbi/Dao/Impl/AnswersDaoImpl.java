package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.AnswersDao;
import is.larsen.ebbi.Model.responses.AddAnswersResponse;
import is.larsen.ebbi.Model.Answer;
import is.larsen.ebbi.Model.responses.GetAnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswersDaoImpl implements AnswersDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public AddAnswersResponse addAnswers(Integer customerId, List<Answer> answers) {

        AddAnswersResponse response = new AddAnswersResponse();

        answers.forEach(a -> {
            String query = "INSERT INTO Questions ( question_id, customer_id, response, " +
                    "gender, age, income, education, switch_suppliers, " +
                    "country_code, region, time_stamp)) " +
                    "VALUES(" + a.getQuestionId() + ", " + customerId + a.getAnswerValue() + ", " +
                                a.getGender() + ", " + a.getAge() + ", " + a.getIncome() + ", " +
                                a.getEducation() + ", " + a.getSwitchSuppliers() + ", " +
                                a.getCountryCode() + ", " + a.getRegion() + ", " + a.getTimeStamp() +
                                ");";

            Integer result = jdbcTemplate.update(query);

            response.setStatusCode(result);

        });

        return response;

    }

    @Override
    public GetAnswerResponse getAnswers(Integer customerId) {
        GetAnswerResponse answerResponse = new GetAnswerResponse();

        return new GetAnswerResponse(customerId, jdbcTemplate.query("SELECT * FROM Answers where customer_id = " + customerId, new AnswersRowMapper()));

    }

    protected class AnswersRowMapper implements RowMapper {


        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answer answer = new Answer();
            answer.setQuestionId(rs.getInt("question_id"));
            answer.setAnswerValue(rs.getInt("response"));
            answer.setGender(rs.getInt("gender"));
            answer.setAge(rs.getInt("age"));
            answer.setIncome(rs.getInt("income"));
            answer.setEducation(rs.getInt("education"));
            answer.setSwitchSuppliers(rs.getInt("switch_suppliers"));
            answer.setCountryCode(rs.getString("country_code"));
            answer.setRegion(rs.getString("region"));
            answer.setTimeStamp(rs.getString("time_stamp"));

            return answer;
        }
    }
}
