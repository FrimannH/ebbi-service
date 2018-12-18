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

    @RequestMapping(method =  RequestMethod.POST, value = "/customer" )
    public BaseResponse addCustomer(@RequestBody AddCustomerRequest request) {
        return ebbiService.addCustomer(request.getCustomerName(), request.getCustomerDescription());
    }

    @RequestMapping(method =  RequestMethod.DELETE, value = "/customer" )
    public BaseResponse deleteCustomer(@RequestBody AddCustomerRequest request) {
        return ebbiService.addCustomer(request.getCustomerName(), request.getCustomerDescription());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public GetCustomersResponse getCustomers() {

        return ebbiService.getCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/questions")
    public GetQuestionsResponse getQuestions() {

        return ebbiService.getQuestions();

    }

    @RequestMapping(method =  RequestMethod.POST, value = "/update/survey" )
    public BaseResponse updateSurvey(@RequestBody  UpdateSurveyRequest request) {
        return ebbiService.updateSurvey(request.getCustomerId(), request.getAnswers());
    }

}
