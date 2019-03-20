package is.larsen.ebbi.Dao.Impl;


import is.larsen.ebbi.Dao.QuestionsDao;
import is.larsen.ebbi.Model.responses.GetQuestionsResponse;
import is.larsen.ebbi.Model.Question;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;;

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

        return new GetQuestionsResponse(jdbcTemplate.query("SELECT Questions.question_id, Questions.question_name, Questions.question_overview_name, Questions.question_short_description, Questions.question_long_description, Questions.question_text, QuestionType.type_name, Questions.question_score_formulation FROM Questions INNER JOIN QuestionType ON Questions.question_type=QuestionType.type_id;", new QuestionsRowMapper()));
    }

    protected class QuestionsRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setQuestionId(rs.getInt("question_id"));
            question.setQuestionName(rs.getString("question_name"));
            question.setQuestionOverviewName(rs.getString("question_overview_name"));
            question.setQuestionShortDescription(rs.getString("question_short_description"));
            question.setQuestionLongDescription(rs.getString("question_long_description"));
            question.setQuestionText(rs.getString("question_text"));
            question.setQuestionType(rs.getString("type_name"));
            question.setQuestionScoreFormulation(rs.getString("question_score_formulation"));
            return question;
        }
    }

    @Override
    public HashMap<Integer, Double> GetEbbiScore() {
        HashMap scores = new HashMap();
        List<Pair<Integer, Double>> scoreList = new ArrayList<>(jdbcTemplate.query("Select * from EbbiScore;", new EbbiScoreRowMapper()));

        scoreList.stream()
                .forEach(s -> scores.put(s.getKey(), s.getValue()));

        return scores;
    }

    protected class EbbiScoreRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pair(rs.getInt("question_id"), rs.getDouble("score"));
        }
    }

}
