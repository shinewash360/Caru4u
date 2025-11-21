package Caru4u.Caru4u.service;


import Caru4u.Caru4u.Utiles.Constants;
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
        if (!passwordCheck.equals(Constants.OK)) {
            return passwordCheck;
        }

        String emailDeatile = emailCheck(registorCustomerModel.getEmail());
        if (!emailDeatile.equals(Constants.OK)) {
            return emailDeatile;
        }
        if (registorCumstomerRepository.findByEmail(registorCustomerModel.getEmail()).isPresent()) {
            return Constants.EMAIL_ID_ALREADY_REGISTERED;
        }

        String PhoneNumberValidation = phoneNumberCheck(registorCustomerModel.getPhone());
        if (!PhoneNumberValidation.equals(Constants.OK)) {
            return PhoneNumberValidation;
        }

        if (registorCumstomerRepository.findByPhone(registorCustomerModel.getPhone()).isPresent()) {
            return Constants.PHONE_NUMBER_ALREADY_REGISTEDRED;
        }
//        String PasswordCheck = passwordValidation(registorCustomerModel.getPassword());
//        if (PasswordCheck.equals("OK")) {
//            return PasswordCheck;
//        }

        String token = UUID.randomUUID().toString();
        registorCustomerModel.setResetToken(token);
        registorCustomerModel.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

        registorCumstomerRepository.save(registorCustomerModel);
        return Constants.REGISTRATION_SUCCESFULL;


    }

    private String checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return Constants.PASSWORD_NOT_EMPTY;
        }
        if (password.length() < 4) {
            return Constants.PASSWORD_SHOULD_BE_ATLEAST;
        }
        return Constants.OK;
    }

    private String emailCheck(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Constants.EMAIL_CONNOT_BE_EMPTY;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            return Constants.INVALID_EMAIL;
        }
        return Constants.OK;
    }

    private String phoneNumberCheck(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return Constants.PHONENUMBER_NOT_EMPTY;
        }
        String phoneRegex = "^[0-9]{10}$";
        if (!phoneNumber.matches(phoneRegex)) {
            return Constants.INVALID_NUMBER;
        }
        return Constants.OK;
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
