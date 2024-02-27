package com.ezycart.ezycart.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezycart.ezycart.Entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{
    
}
