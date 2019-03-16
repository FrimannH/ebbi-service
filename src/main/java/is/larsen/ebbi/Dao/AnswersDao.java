package is.larsen.ebbi.Dao;


import is.larsen.ebbi.Model.requests.AnswerRequest;
import is.larsen.ebbi.Model.requests.UpdateSurveyRequest;
import is.larsen.ebbi.Model.responses.*;
import is.larsen.ebbi.Model.AnswerResponse;

import java.util.List;

public interface AnswersDao {

    public UpdateSurveyResponse addAnswers(UpdateSurveyRequest request);

    public GetAnswerResponse getAnswers(Integer customerId);

    public DeleteSurveyResponse deleteAnswers(Integer customerId);

    public GetReportResponse getReport(Integer customerId);
}
