package Caru4u.Caru4u.Controller;



import Caru4u.Caru4u.model.LoginCustomerModel;
import Caru4u.Caru4u.service.LoginCustomerService;
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
