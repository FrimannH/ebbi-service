package is.larsen.ebbi.Service;

import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Model.responses.*;

import javax.lang.model.type.IntersectionType;
import java.util.List;

public interface EbbiService {

    GetCustomersResponse getCustomers();

    GetQuestionsResponse getQuestions();

    BaseResponse addCustomer(String customerName, String customerDescription);

    BaseResponse deleteCustomer(Integer customerId);

    BaseResponse updateSurvey(Integer customerId, List<Answer> input);
}
