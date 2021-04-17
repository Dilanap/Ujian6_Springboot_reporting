package com.kuliah.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.main.entity.AdminUser;
import com.kuliah.main.repository.AdminUserRepository;

@Service
public class ModelAdminUser  implements ModelAdminUserInterface{
	
	@Autowired
	AdminUserRepository adminUserRepo;

	@Override
	public List<AdminUser> getAllAdminUser() {
		// TODO Auto-generated method stub
		return (List<AdminUser>) this.adminUserRepo.findAll();
	}

	

	@Override
	public AdminUser addAdminUser(AdminUser adminUser) {
		// TODO Auto-generated method stub
		
		return  this.adminUserRepo.save(adminUser);
	
	}

	@Override
	public AdminUser getAdminUserById(String id) {
		// TODO Auto-generated method stub
		
		return this.adminUserRepo.findById(Long.parseLong(id)).get();
		
		
	}

	@Override
	public void deleteAdminUser(String id) {
		// TODO Auto-generated method stub
		this.adminUserRepo.deleteById(Long.parseLong(id));
		
	}

}
