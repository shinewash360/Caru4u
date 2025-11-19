package Shinewash360.ShineWash360.Controller;


import Shinewash360.ShineWash360.model.LoginCustomerModel;
import Shinewash360.ShineWash360.service.LoginCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer")
public class LoginPageController {

    @Autowired
    private LoginCustomerService loginCustomerService;

    @PostMapping("/login")
    public String LoginCustomer(@RequestBody LoginCustomerModel loginCustomerModel) {
        String LoginDeatile = loginCustomerService.loginCustomer(loginCustomerModel.getEmailOrPhone(), loginCustomerModel.getPassword());
        return LoginDeatile;
    }

}
