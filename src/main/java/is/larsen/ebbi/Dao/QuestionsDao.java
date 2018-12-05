package is.larsen.ebbi.Dao;



import is.larsen.ebbi.Model.GetQuestionsResponse;
import is.larsen.ebbi.Model.Question;

import java.util.List;

public interface QuestionsDao {

    public Integer addQuestion(String questionName, String questionShortDescription, String questionLongDescription);

    public GetQuestionsResponse getQuestions();

}
