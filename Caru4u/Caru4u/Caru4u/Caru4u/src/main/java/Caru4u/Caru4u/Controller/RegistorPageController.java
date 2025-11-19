package Caru4u.Caru4u.Controller;



import Caru4u.Caru4u.model.RegistorCustomerModel;
import Caru4u.Caru4u.service.RegistorCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/User")
public class RegistorPageController {

    @Autowired
    private RegistorCustomerService registorCustomerService;

    @PostMapping("/registor")
    public String Registor(@RequestBody RegistorCustomerModel registorCustomerModel) {

        return registorCustomerService.CustomerSave(registorCustomerModel);
    }

}
