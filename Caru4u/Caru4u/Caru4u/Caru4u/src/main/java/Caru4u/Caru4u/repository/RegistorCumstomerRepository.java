package Shinewash360.ShineWash360.repository;

import Shinewash360.ShineWash360.model.RegistorCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistorCumstomerRepository extends JpaRepository<RegistorCustomerModel,Long> {


    Optional<RegistorCustomerModel> findByEmail(String email);

    Optional<RegistorCustomerModel> findByPhone(String phone);
}
