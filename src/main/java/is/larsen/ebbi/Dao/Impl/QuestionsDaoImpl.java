package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.QuestionsDao;
import is.larsen.ebbi.Model.responses.GetQuestionsResponse;
import is.larsen.ebbi.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionsDaoImpl implements QuestionsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer addQuestion(String questionName, String questionShortDescription, String questionLongDescription) {

        String query = "INSERT INTO Questions (question_name, question_short_description, question_long_description) VALUES(" + questionName + ", " + questionShortDescription + ", " + questionLongDescription + ");";

        return jdbcTemplate.update(query);
    }

    @Override
    public GetQuestionsResponse getQuestions() {

        return new GetQuestionsResponse(jdbcTemplate.query("SELECT Questions.question_id, Questions.question_name, Questions.question_short_description, Questions.question_long_description, Questions.question_text, QuestionType.type_name FROM Questions INNER JOIN QuestionType ON Questions.question_type=QuestionType.type_id;", new QuestionsRowMapper()));
    }

    protected class QuestionsRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setQuestionId(rs.getInt("question_id"));
            question.setQuestionName(rs.getString("question_name"));
            question.setQuestionShortDescription(rs.getString("question_short_description"));
            question.setQuestionLongDescription(rs.getString("question_long_description"));
            question.setQuestionText(rs.getString("question_text"));
            question.setQuestionType(rs.getString("type_name"));
            return question;
        }
    }

}
