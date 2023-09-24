package com.avi.springboot.customermgmt.service;

import com.avi.springboot.customermgmt.domain.Customer;
import com.avi.springboot.customermgmt.entity.CustomerEntity;
import com.avi.springboot.customermgmt.entity.EntityAssembler;
import com.avi.springboot.customermgmt.exception.CustomerNotFoundException;
import com.avi.springboot.customermgmt.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private final ICustomerRepository repository;


    public List<Customer> getAll() {
        return repository.findAll().stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public Customer getById(Long id) throws CustomerNotFoundException {
        return repository.findById(id)
                .map(EntityAssembler::fromEntity)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID : " + id + " not found"));
    }

    public Customer updateCustomer(Customer customer) {
        return repository.findById(customer.id())
                .map(entity -> updateExistingCustomer(entity, customer))
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID : " + customer.id() + " not found"));
    }

    private Customer updateExistingCustomer(CustomerEntity existingCustomer, Customer newCustomer) {
        existingCustomer.setId(newCustomer.id());
        existingCustomer.setFirstName(newCustomer.firstName());
        existingCustomer.setLastName(newCustomer.lastName());
        return EntityAssembler.fromEntity(repository.save(existingCustomer));
    }

    public Customer createCustomer(Customer customer) {
        return EntityAssembler.fromEntity(repository.save(EntityAssembler.toEntity(customer)));
    }

    public void deleteCustomerById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Customer> getCustomersByLastName(String lastname) {
        return repository.findCustomerByLastName(lastname).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public List<Customer> getCustomersByIDAndFirstName(Long id, String firstName) {
        return repository.findCustomerByIdAndFirstName(id, firstName).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public List<Customer> getCustomersByIDAndLastName(Long id, String lastName) {
        return repository.findCustomerByIdAndLastName(id, lastName).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public List<Customer> getCustomersByFirstName(String firstName) {
        return repository.findCustomerByFirstName(firstName).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public List<Customer> getCustomerGreaterThanID(Long id) {
        return repository.findCustomerByIdGreaterThan(id).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

    public List<Customer> getCustomerLesserThanID(Long id) {
        return repository.findCustomerByIdLessThan(id).stream()
                .map(EntityAssembler::fromEntity)
                .toList();
    }

}
