package com.avi.springboot.jpademo.dao;

import com.avi.springboot.jpademo.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public List<Customer> findCustomerByLastName(String lastName);
    public List<Customer> findCustomerByFirstName(String firstName);
    public List<Customer> findCustomerByIdAndFirstName(Long id,String firstName);
    public List<Customer> findCustomerByIdGreaterThan(Long id);

}
