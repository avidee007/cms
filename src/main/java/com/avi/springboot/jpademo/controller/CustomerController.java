package com.avi.springboot.jpademo.controller;

import com.avi.springboot.jpademo.entity.Customer;
import com.avi.springboot.jpademo.exception.CustomerNotFoundException;
import com.avi.springboot.jpademo.exception.CustomerUpdateFailedExecption;
import com.avi.springboot.jpademo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Validated
public class CustomerController {

    @Autowired
    private CustomerService service;

    private HttpHeaders addHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("app-name", "JPA-DEMO-APP");
        headers.add("controller-name", "Customer-Controller");
        return headers;
    }

    /*================================ GET APIs=================================================*/

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer(
            @RequestParam(value = "lastname", required = false) String lastname,
            @RequestParam(value = "firstname", required = false) String firstname,
            @Positive @RequestParam(value = "id", required = false) Long id) {

        if (id != null && firstname != null) {
            return getCustomerByIDAndFirstName(id, firstname);
        }
        if (id != null && lastname != null) {
            return getCustomerByIDAndLastName(id, lastname);
        }
        if (lastname != null) {
            return getCustomerByLastName(lastname);
        }
        if (firstname != null) {
            return getCustomerByFirstName(firstname);
        }
        return ResponseEntity.ok().headers(addHeader()).body(service.getAll());
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerByID(@Positive @PathParam("id") Long id) throws CustomerNotFoundException {
        return ResponseEntity.ok().headers(addHeader()).body(service.getById(id));
    }

    @GetMapping("/customers/greater")
    public ResponseEntity<List<Customer>> getCustomerByGreaterThanId(@RequestParam("id") Long id) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomerGreaterThanID(id));
    }

    @GetMapping("/customers/lesser")
    public ResponseEntity<List<Customer>> getCustomerByLesserThanId(@RequestParam("id") Long id) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomerLesserThanID(id));
    }

    public ResponseEntity<List<Customer>> getCustomerByFirstName(String firstName) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomersByFirstName(firstName));
    }

    public ResponseEntity<List<Customer>> getCustomerByLastName(String name) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomersByLastName(name));
    }

    public ResponseEntity<List<Customer>> getCustomerByIDAndFirstName(Long id, String firstName) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomersByIDAndFirstName(id, firstName));
    }

    public ResponseEntity<List<Customer>> getCustomerByIDAndLastName(Long id, String lastName) {
        return ResponseEntity.ok().headers(addHeader()).body(service.getCustomersByIDAndLastName(id, lastName));
    }

    /*================================ POST APIs=================================================*/

    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(service.save(customer), addHeader(), HttpStatus.CREATED);
    }

    /*================================ PUT APIs=================================================*/

    @PutMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        Customer existing = service.getById(customer.getId());
        if (existing != null) {
            try {
                customer.setId(existing.getId());
                return new ResponseEntity<>(service.save(customer), addHeader(), HttpStatus.CREATED);
            } catch (Exception e) {
                throw new CustomerUpdateFailedExecption("Updated Failed with error " + e.getMessage());
            }
        } else {
            throw new CustomerNotFoundException("Customer Id: " + customer.getId() + " Not Found");
        }
    }

    /*================================ DELETE APIs=================================================*/

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteCustomers() {
        service.deleteAll();
        return ResponseEntity.ok().headers(addHeader()).body("All Customers Deleted Successfully");
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@Positive @PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().headers(addHeader()).body("Customer id:" + id + " Deleted Successfully");
    }


}
