package is.larsen.ebbi.Service;

import is.larsen.ebbi.Model.requests.UpdateSurveyRequest;
import is.larsen.ebbi.Model.responses.*;
import is.larsen.ebbi.Model.requests.AnswerRequest;

import java.util.List;

public interface EbbiService {

    GetCustomersResponse getCustomers();

    GetQuestionsResponse getQuestions();

    GetCustomersResponse addCustomer(String customerName, String customerDescription);

    GetCustomersResponse deleteCustomer(Integer customerId);

    UpdateSurveyResponse updateSurvey(UpdateSurveyRequest request);

    GetReportResponse getReport(Integer customerId);

    GetAnswerResponse getAnswers(Integer customerId);

    DeleteSurveyResponse deleteAnswers(Integer customerId);
}
