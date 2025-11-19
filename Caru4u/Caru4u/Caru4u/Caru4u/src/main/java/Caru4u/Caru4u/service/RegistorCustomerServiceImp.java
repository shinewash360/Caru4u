package Caru4u.Caru4u.service;


import Caru4u.Caru4u.model.RegistorCustomerModel;
import Caru4u.Caru4u.repository.RegistorCumstomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistorCustomerServiceImp implements RegistorCustomerService {

    @Autowired
    private RegistorCumstomerRepository registorCumstomerRepository;

    @Override
    public String CustomerSave(RegistorCustomerModel registorCustomerModel) {

        String passwordCheck = checkPassword(registorCustomerModel.getPassword());
        if (!passwordCheck.equals("OK")) {
            return passwordCheck;
        }

        String emailDeatile = emailCheck(registorCustomerModel.getEmail());
        if (!emailDeatile.equals("OK")) {
            return emailDeatile;
        }
        if (registorCumstomerRepository.findByEmail(registorCustomerModel.getEmail()).isPresent()) {
            return "Email Id already Registered";
        }

        String PhoneNumberValidation = phoneNumberCheck(registorCustomerModel.getPhone());
        if (!PhoneNumberValidation.equals("OK")) {
            return PhoneNumberValidation;
        }

        if (registorCumstomerRepository.findByPhone(registorCustomerModel.getPhone()).isPresent()) {
            return "Phone Number Already Registered";
        }
//        String PasswordCheck = passwordValidation(registorCustomerModel.getPassword());
//        if (PasswordCheck.equals("OK")) {
//            return PasswordCheck;
//        }

        String token = UUID.randomUUID().toString();
        registorCustomerModel.setResetToken(token);
        registorCustomerModel.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

        registorCumstomerRepository.save(registorCustomerModel);
        return "Registration SuccessFull";


    }

    private String checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return "Password Not Empty";
        }
        if (password.length() < 4) {
            return "Password Should be Atlest 4";
        }
        return "OK";
    }

    private String emailCheck(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty";
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            return "Invalid Email";
        }
        return "OK";
    }

    private String phoneNumberCheck(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "PhoneNumber Not Empty";
        }
        String phoneRegex = "^[0-9]{10}$";
        if (!phoneNumber.matches(phoneRegex)) {
            return "Invalid Number";
        }
        return "OK";
    }

//    private String passwordValidation(String password) {
//
//        if (password == null || password.trim().isEmpty()) {
//            return "Password must not be empty";
//        }
//
//        String passwordRegex =
//                "^(?=.*[A-Z])" +        // At least one uppercase
//                        "(?=.*[a-z])" +        // At least one lowercase
//                        "(?=.*\\d)" +          // At least one digit
//                        "(?=.*[@$!%*?&])" +    // At least one special character
//                        "[A-Za-z\\d@$!%*?&]{8,}$"; // Min 8 chars, allowed characters only
//
//        if (!password.matches(passwordRegex)) {
//            return "Password must contain minimum 8 characters";
//        }
//
//        return "OK";
//    }
}
