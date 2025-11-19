package Shinewash360.ShineWash360.service;

import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import Shinewash360.ShineWash360.repository.ForgotPasswordResetRepository;
import Shinewash360.ShineWash360.repository.RegistorCumstomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    private ForgotPasswordResetRepository forgotPasswordResetRepository;

    @Autowired
    private ForgotEmailService forgotEmailService;
    @Override
    public String processForgotPassword(String mail) {
        RegistorCustomerModel registorCustomerModel= forgotPasswordResetRepository.findByEmail(mail);
        if(forgotPasswordResetRepository == null){
            return "Email Id Not Found";
        }
      String token =UUID.randomUUID().toString();
        registorCustomerModel.setResetToken(token);
        registorCustomerModel.setTokenExpiry(LocalDateTime.now().plusMinutes(30));
        forgotPasswordResetRepository.save(registorCustomerModel);

        String resetLink = "http://localhost:8080/reset-password?token=" + token;

        forgotEmailService.sendEmail(
                mail,
                "Reset Your Password",
                "Click the link to reset your password:\n" + resetLink
        );
       return  "Password reset link sent!";
    }

    @Override
    public String updatePassword(String token, String newPassword) {
        RegistorCustomerModel registorCustomerModel =forgotPasswordResetRepository.findByResetToken(token);
        if (registorCustomerModel == null){
            return "Invalid Token";
        }
        if (registorCustomerModel.getTokenExpiry().isBefore(LocalDateTime.now())) {
            return "Token expired!";
        }
        registorCustomerModel.setPassword(newPassword);
        registorCustomerModel.setResetToken(null);
        registorCustomerModel.setTokenExpiry(null);
        return "Password Updated Successfully";
    }

}
