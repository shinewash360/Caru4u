package Shinewash360.ShineWash360.repository;

import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPasswordResetRepository extends JpaRepository<RegistorCustomerModel,Long> {

    RegistorCustomerModel findByEmail(String email);
    RegistorCustomerModel findByResetToken(String phone);
}