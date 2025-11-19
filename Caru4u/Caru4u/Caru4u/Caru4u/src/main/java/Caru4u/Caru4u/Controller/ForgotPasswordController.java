package Caru4u.Caru4u.Controller;


import Caru4u.Caru4u.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customer")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public String forgotCustomer(@RequestBody String email) {
        return forgotPasswordService.processForgotPassword(email);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,@RequestParam String newPassword){
        return forgotPasswordService.updatePassword(token,newPassword);
    }

}
