package Caru4u.Caru4u.repository;



import Caru4u.Caru4u.model.RegistorCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForgotPasswordResetRepository extends JpaRepository<RegistorCustomerModel,Long> {

    RegistorCustomerModel findByEmail(String email);
    RegistorCustomerModel findByResetToken(String phone);
}