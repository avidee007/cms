package com.avi.springboot.jpademo.repository;

import com.avi.springboot.jpademo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

    //Returns all customers with given firstName
    public List<Customer> findCustomerByFirstName(String firstName);

    //Returns all customers with given lastName
    public List<Customer> findCustomerByLastName(String lastName);

    //Returns all customers with given ID and firstName
    public List<Customer> findCustomerByIdAndFirstName(Long id, String firstName);

    //Returns all customers with given ID and lastName
    public List<Customer> findCustomerByIdAndLastName(Long id, String lastName);

    //Returns all customers having id greater than given ID
    public List<Customer> findCustomerByIdGreaterThan(Long id);

    //Returns all customers having id lesser than given ID
    public List<Customer> findCustomerByIdLessThan(Long id);

}
