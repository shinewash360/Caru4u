package Shinewash360.ShineWash360.service;

import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import Shinewash360.ShineWash360.repository.RegistorCumstomerRepository;
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
            return "Email or phone is required";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password is required";
        }
        Optional<RegistorCustomerModel> userOp = Optional.empty();
        if (isValidEmail(emailOrPhone)) {
            //Login By Email
            Optional<RegistorCustomerModel> userOpt = registorCumstomerRepository.findByEmail(emailOrPhone);
            if (userOpt.isEmpty()) {
                return "User not found";
            }
            RegistorCustomerModel user = userOpt.get();
            if (!user.getPassword().equals(password)) {
                return "Invalid password";
            }
            return "Login Successful!";
        } else if (isValidPhone(emailOrPhone)) {
            // Login by phone
            Optional<RegistorCustomerModel> userOpt = registorCumstomerRepository.findByPhone(emailOrPhone);
            if (userOpt.isEmpty()) {
                return "User not found";
            }

            RegistorCustomerModel user = userOpt.get();
            if (!user.getPassword().equals(password)) {
                return "Invalid password";
            }
            return "Login Successful!";
        } else {
            return "Invalid email or phone format";
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
