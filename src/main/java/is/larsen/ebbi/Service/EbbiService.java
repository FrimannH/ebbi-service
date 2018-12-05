package is.larsen.ebbi.Service;

import is.larsen.ebbi.Model.*;

import java.util.List;

public interface EbbiService {

    List<CustomerResponse> getCustomers();

    GetQuestionsResponse getQuestions();

    BaseResponse addCustomer(String customerName, String customerDescription);

    BaseResponse updateSurvey(Integer customerId, List<Answer> input);
}
