package Caru4u.Caru4u.service;


import Caru4u.Caru4u.Utiles.Constants;
import Caru4u.Caru4u.model.RegistorCustomerModel;
import Caru4u.Caru4u.repository.RegistorCumstomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginCustomerServiceImpl implements LoginCustomerService {

    @Autowired
    private RegistorCumstomerRepository registorCumstomerRepository;

    @Override
    public String loginCustomer(String emailOrPhone, String password) {
        if (emailOrPhone == null || emailOrPhone.trim().isEmpty()) {
            return Constants.EMAIL_OR_PHONE_IS_REQUIRED;
        }
        if (password == null || password.trim().isEmpty()) {
            return Constants.PASSWORD_IS_REQUIRED;
        }
        Optional<RegistorCustomerModel> userOp = Optional.empty();
        if (isValidEmail(emailOrPhone)) {
            //Login By Email
            Optional<RegistorCustomerModel> userOpt = registorCumstomerRepository.findByEmail(emailOrPhone);
            if (userOpt.isEmpty()) {
                return Constants.USER_NOT_FOUND;
            }
            RegistorCustomerModel user = userOpt.get();
            if (!user.getPassword().equals(password)) {
                return Constants.INVALID_PASSWORD;
            }
            return Constants.LOGIN_SUCCESSFUL;
        } else if (isValidPhone(emailOrPhone)) {
            // Login by phone
            Optional<RegistorCustomerModel> userOpt = registorCumstomerRepository.findByPhone(emailOrPhone);
            if (userOpt.isEmpty()) {
                return Constants.USER_NOT_FOUND;
            }

            RegistorCustomerModel user = userOpt.get();
            if (!user.getPassword().equals(password)) {
                return Constants.INVALID_PASSWORD;
            }
            return Constants.LOGIN_SUCCESSFUL;
        } else {
            return Constants.INVALID_EMAIL_OR_PHONE_FORMAT;
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{10}$"; // Only 10 digits
        return phone != null && phone.matches(phoneRegex);
    }

}
