package Caru4u.Caru4u.Controller;



import Caru4u.Caru4u.model.LoginCustomerModel;
import Caru4u.Caru4u.service.LoginCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
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
