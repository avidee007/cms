package com.avi.springboot.customermgmt.repository;

import com.avi.springboot.customermgmt.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    //Returns all customers with given firstName
    List<Customer> findCustomerByFirstName(String firstName);

    //Returns all customers with given lastName
    List<Customer> findCustomerByLastName(String lastName);

    //Returns all customers with given ID and firstName
    List<Customer> findCustomerByIdAndFirstName(Long id, String firstName);

    //Returns all customers with given ID and lastName
    List<Customer> findCustomerByIdAndLastName(Long id, String lastName);

    //Returns all customers having id greater than given ID
    List<Customer> findCustomerByIdGreaterThan(Long id);

    //Returns all customers having id lesser than given ID
    List<Customer> findCustomerByIdLessThan(Long id);

}
