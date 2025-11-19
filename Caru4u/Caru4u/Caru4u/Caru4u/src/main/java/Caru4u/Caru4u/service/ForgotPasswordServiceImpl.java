package Caru4u.Caru4u.service;


import Caru4u.Caru4u.model.RegistorCustomerModel;
import Caru4u.Caru4u.repository.RegistorCumstomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    @Autowired
    private RegistorCumstomerRepository registorCumstomerRepository;

    @Autowired
    private ForgotEmailService forgotEmailService;

    @Override
    public String processForgotPassword(String email) {
        Optional<RegistorCustomerModel> optionalUser = registorCumstomerRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return "Email ID Not Found";
        }
        RegistorCustomerModel user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(30));

        registorCumstomerRepository.save(user);
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        forgotEmailService.sendEmail(
                email,
                "Reset Your Password",
                "Click the link to reset your password:\n" + resetLink
        );

        return "Password reset link sent!";
    }


    @Override
    public String updatePassword(String token, String newPassword) {

        Optional<RegistorCustomerModel> optionalUser =
                registorCumstomerRepository.findByResetToken(token);

        // Invalid token
        if (optionalUser.isEmpty()) {
            return "Invalid Token";
        }

        RegistorCustomerModel user = optionalUser.get();

        // Token expired
        if (user.getTokenExpiry() != null &&
                user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            return "Token expired!";
        }

        // Update password (add encryption if needed)
        user.setPassword(newPassword);

        // Clear reset token & expiry
        user.setResetToken(null);
        user.setTokenExpiry(null);

        // Save updated user
        registorCumstomerRepository.save(user);

        return "Password Updated Successfully";
    }

}
