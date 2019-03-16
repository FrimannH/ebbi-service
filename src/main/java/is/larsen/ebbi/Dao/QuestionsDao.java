package is.larsen.ebbi.Dao;



import is.larsen.ebbi.Model.responses.GetQuestionsResponse;

import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;

public interface QuestionsDao {

    public Integer addQuestion(String questionName, String questionShortDescription, String questionLongDescription);

    public GetQuestionsResponse getQuestions();

    public HashMap<Integer, Double> GetEbbiScore();

}
