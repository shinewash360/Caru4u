package Caru4u.Caru4u.service;


import Caru4u.Caru4u.Utiles.Constants;
import Caru4u.Caru4u.model.RegistorCustomerModel;
import Caru4u.Caru4u.repository.RegistorCumstomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.nodes.CollectionNode;

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
            return Constants.EMAIL_ID_NOT_FOUND;
        }
        RegistorCustomerModel user = optionalUser.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusMinutes(30));

        registorCumstomerRepository.save(user);
        String resetLink = Constants.RESET_LINK_PASSWORD + token;
        forgotEmailService.sendEmail(
                email,
                Constants.RESET_YOUR_PASSWORD,
                Constants.CLICK_THE_LINK_TO_RESET_YOUR_PASSWORD + resetLink
        );

        return Constants.PASSWORD_RESET_LINK;
    }


    @Override
    public String updatePassword(String token, String newPassword) {

        Optional<RegistorCustomerModel> optionalUser =
                registorCumstomerRepository.findByResetToken(token);

        // Invalid token
        if (optionalUser.isEmpty()) {
            return Constants.INVALID_TOKEN;
        }

        RegistorCustomerModel user = optionalUser.get();

        // Token expired
        if (user.getTokenExpiry() != null &&
                user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            return Constants.INVALID_EXPIRED;
        }

        // Update password (add encryption if needed)
        user.setPassword(newPassword);

        // Clear reset token & expiry
        user.setResetToken(null);
        user.setTokenExpiry(null);

        // Save updated user
        registorCumstomerRepository.save(user);

        return Constants.PASSWORD_UPDATED_SUCCESSFULLY;
    }

}
