package is.larsen.ebbi.Service.Impl;

import is.larsen.ebbi.Dao.QuestionsDao;
import is.larsen.ebbi.Service.EbbiService;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EbbiServiceImpl implements EbbiService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private QuestionsDao questionsDao;

    public EbbiServiceImpl() {

    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerDao.getCustomers();
    }

    @Override
    public GetQuestionsResponse getQuestions() { return questionsDao.getQuestions(); }

    @Override
    public BaseResponse addCustomer(String customerName, String customerDescription) {

        Integer status = customerDao.addCustomer(customerName, customerDescription);

        return new BaseResponse(status, "");
    }

    @Override
    public BaseResponse updateSurvey(Integer customerId, List<Answer> answers) {
        BaseResponse response = new BaseResponse();
        //do something

        return response;
    }


}
