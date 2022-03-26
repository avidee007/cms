package com.avi.springboot.jpademo.service;

import com.avi.springboot.jpademo.entity.Customer;
import com.avi.springboot.jpademo.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository repository;


    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();
        customers.forEach(customerList::add);
        return customerList;
    }

    public Customer getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Customer> getCustomersByLastName(String lastname) {
        return repository.findCustomerByLastName(lastname);
    }

    public List<Customer> getCustomersByIDAndFirstName(Long id, String firstName) {
        return repository.findCustomerByIdAndFirstName(id, firstName);
    }

    public List<Customer> getCustomersByIDAndLastName(Long id, String lastName) {
        return repository.findCustomerByIdAndLastName(id, lastName);
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return repository.findCustomerByFirstName(firstName);
    }

    public List<Customer> getCustomerGreaterThanID(Long id) {
        return repository.findCustomerByIdGreaterThan(id);
    }

    public List<Customer> getCustomerLesserThanID(Long id) {
        return repository.findCustomerByIdLessThan(id);
    }

}
