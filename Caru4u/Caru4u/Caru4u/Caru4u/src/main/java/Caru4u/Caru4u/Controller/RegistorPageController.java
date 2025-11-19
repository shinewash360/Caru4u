package Shinewash360.ShineWash360.Controller;


import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Shinewash360.ShineWash360.service.RegistorCustomerService;


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
