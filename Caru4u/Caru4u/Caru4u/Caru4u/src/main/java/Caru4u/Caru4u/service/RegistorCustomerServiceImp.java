package Shinewash360.ShineWash360.service;

import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import Shinewash360.ShineWash360.repository.RegistorCumstomerRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistorCustomerServiceImp implements RegistorCustomerService {

    @Autowired
    private RegistorCumstomerRepository registorCumstomerRepository;

    @Override
    public String CustomerSave(RegistorCustomerModel registorCustomerModel) {

        String passwordCheck = checkPassword(registorCustomerModel.getPassword());
        if (!passwordCheck.equals("Ok")) {
            return passwordCheck;
        }

        String emailDeatile = EmailCheck(registorCustomerModel.getEmail());
        if (!emailDeatile.equals("OK")) {
            return emailDeatile;
        }
        if (registorCumstomerRepository.findByEmail(registorCustomerModel.getEmail()).isPresent()) {
            return "Email Id already Registered";
        }

        String PhoneNumberValidation = phoneNumberCheck(registorCustomerModel.getPhone());
        if (PhoneNumberValidation.equals("OK")) {
            return PhoneNumberValidation;
        }

        if (registorCumstomerRepository.findByPhone(registorCustomerModel.getPhone()).isPresent()) {
            return "Phone Number Already Registered";
        }
        registorCumstomerRepository.save(registorCustomerModel);
        return "Registration SuccessFull";


    }

    private String checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return "Password Not Empty";
        }
        if (password.length() >= 4) {
            return "Password Should be Atlest 4";
        }
        return "OK";
    }

    private String EmailCheck(String Email) {
        if (Email == null || Email.trim().isEmpty()) {
            return "Email Not Empty";
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!emailRegex.matches(emailRegex)) {
            return "InValid Email";
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
}
