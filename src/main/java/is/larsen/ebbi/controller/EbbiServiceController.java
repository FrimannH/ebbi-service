package is.larsen.ebbi.controller;

import is.larsen.ebbi.Service.EbbiService;
import is.larsen.ebbi.Model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EbbiServiceController {

    private final EbbiService ebbiService;

    @Autowired
    public EbbiServiceController(EbbiService ebbiService) {
        this.ebbiService = ebbiService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<Customer> getCustomers() {

        return ebbiService.getCustomers();

    }

    @RequestMapping(method =  RequestMethod.POST, value = "/update/survey" )
    public Response updateCustomerQuery(@RequestBody  UpdateSurveyRequest request) {
        return ebbiService.updateCustomerSurvey(request.getCustomerId(), request.getInput());
    }
}
