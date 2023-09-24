package com.avi.springboot.customermgmt.controller;

import com.avi.springboot.customermgmt.domain.Customer;
import com.avi.springboot.customermgmt.exception.CustomerNotFoundException;
import com.avi.springboot.customermgmt.service.CustomerService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

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
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getAll());
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerByID(@Positive @PathParam("id") Long id) throws CustomerNotFoundException {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getById(id));
    }

    @GetMapping("/customers/greater")
    public ResponseEntity<List<Customer>> getCustomerByGreaterThanId(@RequestParam("id") Long id) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomerGreaterThanID(id));
    }

    @GetMapping("/customers/lesser")
    public ResponseEntity<List<Customer>> getCustomerByLesserThanId(@RequestParam("id") Long id) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomerLesserThanID(id));
    }

    public ResponseEntity<List<Customer>> getCustomerByFirstName(String firstName) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomersByFirstName(firstName));
    }

    public ResponseEntity<List<Customer>> getCustomerByLastName(String name) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomersByLastName(name));
    }

    public ResponseEntity<List<Customer>> getCustomerByIDAndFirstName(Long id, String firstName) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomersByIDAndFirstName(id, firstName));
    }

    public ResponseEntity<List<Customer>> getCustomerByIDAndLastName(Long id, String lastName) {
        return ResponseEntity.ok().headers(addHeader()).body(customerService.getCustomersByIDAndLastName(id, lastName));
    }

    /*================================ POST APIs=================================================*/

    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), addHeader(), HttpStatus.CREATED);
    }

    /*================================ PUT APIs=================================================*/

    @PutMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), addHeader(), HttpStatus.OK);
    }

    /*================================ DELETE APIs=================================================*/

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteCustomers() {
        customerService.deleteAll();
        return ResponseEntity.ok().headers(addHeader()).body("All Customers Deleted Successfully");
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteCustomer(@Positive @PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().headers(addHeader()).body("Customer id:" + id + " Deleted Successfully");
    }


}
