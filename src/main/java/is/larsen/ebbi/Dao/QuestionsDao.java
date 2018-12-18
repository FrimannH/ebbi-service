package is.larsen.ebbi.Dao;



import is.larsen.ebbi.Model.responses.GetQuestionsResponse;

public interface QuestionsDao {

    public Integer addQuestion(String questionName, String questionShortDescription, String questionLongDescription);

    public GetQuestionsResponse getQuestions();

}
