package Caru4u.Caru4u.service;

public interface ForgotPasswordService {

String processForgotPassword(String message);

String updatePassword(String token,String newPassword);
}
