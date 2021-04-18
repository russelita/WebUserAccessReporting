package com.ujian.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.ujian.main.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	public Admin findByUsername(String username); 

}
