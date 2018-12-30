package is.larsen.ebbi.Dao;


import is.larsen.ebbi.Model.requests.AnswerRequest;
import is.larsen.ebbi.Model.responses.AddAnswersResponse;
import is.larsen.ebbi.Model.AnswerResponse;
import is.larsen.ebbi.Model.responses.BaseResponse;
import is.larsen.ebbi.Model.responses.GetAnswerResponse;
import is.larsen.ebbi.Model.responses.GetReportResponse;

import java.util.List;

public interface AnswersDao {

    public AddAnswersResponse addAnswers(Integer customerId, List<AnswerRequest> surveyResponses);

    public GetAnswerResponse getAnswers(Integer customerId);

    public BaseResponse deleteAnswers(Integer customerId);

    public GetReportResponse getReport(Integer customerId);
}
