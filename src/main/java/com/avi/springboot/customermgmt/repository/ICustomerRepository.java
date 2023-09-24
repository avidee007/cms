package com.avi.springboot.customermgmt.repository;

import com.avi.springboot.customermgmt.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    //Returns all customers with given firstName
    List<CustomerEntity> findCustomerByFirstName(String firstName);

    //Returns all customers with given lastName
    List<CustomerEntity> findCustomerByLastName(String lastName);

    //Returns all customers with given ID and firstName
    List<CustomerEntity> findCustomerByIdAndFirstName(Long id, String firstName);

    //Returns all customers with given ID and lastName
    List<CustomerEntity> findCustomerByIdAndLastName(Long id, String lastName);

    //Returns all customers having id greater than given ID
    List<CustomerEntity> findCustomerByIdGreaterThan(Long id);

    //Returns all customers having id lesser than given ID
    List<CustomerEntity> findCustomerByIdLessThan(Long id);

}
