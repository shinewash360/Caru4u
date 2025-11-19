package Shinewash360.ShineWash360.service;

public interface ForgotPasswordService {

String processForgotPassword(String message);

String updatePassword(String token,String newPassword);
}
