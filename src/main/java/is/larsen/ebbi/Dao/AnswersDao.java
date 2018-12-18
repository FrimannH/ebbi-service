package is.larsen.ebbi.Dao;


import is.larsen.ebbi.Model.responses.AddAnswersResponse;
import is.larsen.ebbi.Model.Answer;
import is.larsen.ebbi.Model.responses.GetAnswerResponse;

import java.util.List;

public interface AnswersDao {

    public AddAnswersResponse addAnswers(Integer customerId, List<Answer> answers);

    public GetAnswerResponse getAnswers(Integer customerId);
}
