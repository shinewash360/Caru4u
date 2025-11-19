package Caru4u.Caru4u.repository;



import Caru4u.Caru4u.model.RegistorCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistorCumstomerRepository extends JpaRepository<RegistorCustomerModel,Long> {


    Optional<RegistorCustomerModel> findByEmail(String email);

    Optional<RegistorCustomerModel> findByPhone(String phone);

    Optional<RegistorCustomerModel> findByResetToken(String resetToken);
}
