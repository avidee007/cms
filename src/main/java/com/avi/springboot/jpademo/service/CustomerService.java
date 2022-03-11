package com.avi.springboot.jpademo.service;

import com.avi.springboot.jpademo.dao.CustomerRepository;
import com.avi.springboot.jpademo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;


    public Customer getById(Long id){
       return repository.findById(id).orElse(null);
    }

    public List<Customer> getAll(){
        List<Customer> customerList= new ArrayList<>();
        Iterable<Customer> customers=  repository.findAll();
        customers.forEach(customerList::add);
        return customerList;
    }

    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Customer> getCustomersByLastName(String lastname){
        return repository.findCustomerByLastName(lastname);
    }
    public List<Customer> getCustomersByIDAndFirstName(Long id,String firstName){
        return repository.findCustomerByIdAndFirstName(id,firstName);
    }

    public List<Customer> getCustomersByFirstName(String firstName){
        return repository.findCustomerByFirstName(firstName);
    }

    public List<Customer> getCustomerGreaterThanID(Long id){
        return repository.findCustomerByIdGreaterThan(id);
    }

}
