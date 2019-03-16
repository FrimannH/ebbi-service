package is.larsen.ebbi.Service.Impl;

import is.larsen.ebbi.Dao.QuestionsDao;
import is.larsen.ebbi.Model.requests.AnswerRequest;
import is.larsen.ebbi.Model.requests.UpdateSurveyRequest;
import is.larsen.ebbi.Model.responses.*;
import is.larsen.ebbi.Service.EbbiService;
import is.larsen.ebbi.Model.*;
import is.larsen.ebbi.Dao.CustomerDao;
import is.larsen.ebbi.Dao.AnswersDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

public class EbbiServiceImpl implements EbbiService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private QuestionsDao questionsDao;

    @Autowired
    private AnswersDao answersDao;

    public EbbiServiceImpl() {

    }

    @Override
    public GetCustomersResponse getCustomers() {

        return customerDao.getCustomers();
    }

    @Override
    public GetQuestionsResponse getQuestions() { return questionsDao.getQuestions(); }

    @Override
    public GetCustomersResponse addCustomer(String customerName, String customerDescription) {

        Integer status = customerDao.addCustomer(customerName, customerDescription);
        if ( status.equals(1)) {
            return customerDao.getCustomers();
        } else {
            return new GetCustomersResponse(status, "", new ArrayList<Customer>());
        }

    }

    @Override
    public GetCustomersResponse deleteCustomer(Integer customerId) {

        Integer status = customerDao.deleteCustomer(customerId);
        if ( status.equals(1)) {
            return customerDao.getCustomers();
        } else {
            return new GetCustomersResponse(status, "", new ArrayList<Customer>());
        }

    }

    @Override
    public UpdateSurveyResponse updateSurvey(UpdateSurveyRequest request) {

        return answersDao.addAnswers(request);

    }

    @Override
    public GetReportResponse getReport(Integer customerId) {

        return answersDao.getReport(customerId);
    }

    @Override
    public GetAnswerResponse getAnswers(Integer customerId) {

        return answersDao.getAnswers(customerId);
    }

    @Override
    public DeleteSurveyResponse deleteAnswers(Integer customerId) {

        return answersDao.deleteAnswers(customerId);
    }

}
