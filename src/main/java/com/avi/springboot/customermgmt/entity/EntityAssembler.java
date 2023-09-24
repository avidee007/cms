package com.avi.springboot.customermgmt.entity;

import com.avi.springboot.customermgmt.domain.Customer;


public class EntityAssembler {
    public static Customer fromEntity(CustomerEntity entity) {
        return new Customer(entity.getId(), entity.getFirstName(), entity.getLastName());
    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.id());
        entity.setFirstName(customer.firstName());
        entity.setLastName(customer.lastName());
        return entity;
    }
}
