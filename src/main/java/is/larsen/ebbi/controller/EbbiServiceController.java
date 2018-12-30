package is.larsen.ebbi.controller;

import is.larsen.ebbi.Model.requests.*;
import is.larsen.ebbi.Model.responses.*;
import is.larsen.ebbi.Model.responses.GetQuestionsResponse;
import is.larsen.ebbi.Service.EbbiService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class EbbiServiceController {

    private final EbbiService ebbiService;

    @Autowired
    public EbbiServiceController(EbbiService ebbiService) {
        this.ebbiService = ebbiService;
    }

    //Add a customer and return a list all customers from customer db
    @RequestMapping(method =  RequestMethod.POST, value = "/customer" )
    public GetCustomersResponse addCustomer(@RequestBody AddCustomerRequest request) {
        return ebbiService.addCustomer(request.getCustomerName(), request.getCustomerDescription());
    }

    //Delete a customer from customer db and return a list of remaining customers
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}" )
    public GetCustomersResponse deleteCustomer(@PathVariable("id") Integer customerId) {
        return ebbiService.deleteCustomer(customerId);
    }

    //Get list of all customers from customer db
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public GetCustomersResponse getCustomers() {

        return ebbiService.getCustomers();
    }

    //get a list of all questions in db
    @RequestMapping(method = RequestMethod.GET, value = "/questions")
    public GetQuestionsResponse getQuestions() {

        return ebbiService.getQuestions();

    }

    //Update answers db with a survey results for one customer
    @RequestMapping(method =  RequestMethod.POST, value = "/update/survey" )
    public BaseResponse updateSurvey(@RequestBody  UpdateSurveyRequest request) {
        return ebbiService.updateSurvey(request.getCustomerId(), request.getSurveyResponses());
    }

    //Get report
    @RequestMapping(method = RequestMethod.GET, value = "/answers/{id}")
    public GetAnswerResponse getAnswers(@PathVariable("id") Integer customerId) {

        return ebbiService.getAnswers(customerId);
    }

    //Get list of answers in db for a customer
    @RequestMapping(method = RequestMethod.GET, value = "/report/{id}")
    public GetReportResponse getReport(@PathVariable("id") Integer customerId) {

        return ebbiService.getReport(customerId);
    }

    //Delete answers for a customer
    @RequestMapping(method = RequestMethod.DELETE, value = "/survey/{id}" )
    public BaseResponse deleteQuestions(@PathVariable("id") Integer customerId) {
        return ebbiService.deleteAnswers(customerId);
    }

}
