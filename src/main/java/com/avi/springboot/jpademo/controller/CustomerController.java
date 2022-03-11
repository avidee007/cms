package com.avi.springboot.jpademo.controller;

import com.avi.springboot.jpademo.model.Customer;
import com.avi.springboot.jpademo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    private HttpHeaders addHeader(){
        HttpHeaders headers=new HttpHeaders();
        headers.add("application-name","JPA-DEMO-APP");
        headers.add("Controller-Name","Customer-Controller");
        return headers;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        //return new ResponseEntity<>(service.getAll(),addHeader(),HttpStatus.OK);
        return ResponseEntity.ok().headers(addHeader()).body(service.getAll());
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") Long id) throws Exception{
        Customer customer=service.getById(id);
        if(customer!=null){
            return new ResponseEntity<>(customer,addHeader(),HttpStatus.OK);
        }else{
            throw new Exception();
        }
    }

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(service.save(customer),addHeader(),HttpStatus.CREATED);
    }

    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Customer customer1= service.save(customer);
        if(customer1!=null){
            return new ResponseEntity<>(customer,addHeader(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity("Update Failed",addHeader(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().headers(addHeader()).body("Customer Deleted Successfully");
    }

    @GetMapping("/getByFirstName/{firstName}")
    public List<Customer> getCustomerByFirstName(@PathVariable("firstName") String firstName){
        return service.getCustomersByFirstName(firstName);
    }

    @GetMapping("/getByLastname/{name}")
    public List<Customer> getCustomerByLastName(@PathVariable  String name){
        return service.getCustomersByLastName(name);
    }

    @GetMapping("/get2")
    public List<Customer> getCustomerByIDAndFirstName(@RequestParam Long id,
                                                      @RequestParam String firstName){
        return service.getCustomersByIDAndFirstName(id,firstName);
    }

    @GetMapping("/getGT")
    public List<Customer> getCustomerByGreaterThanId(@RequestParam Long id){
        return service.getCustomerGreaterThanID(id);
    }

}
