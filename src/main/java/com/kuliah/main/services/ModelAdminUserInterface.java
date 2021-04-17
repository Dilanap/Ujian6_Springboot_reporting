package com.kuliah.main.services;

import java.util.List;

import com.kuliah.main.entity.AdminUser;

public interface ModelAdminUserInterface {
	
	public List<AdminUser> getAllAdminUser();
	
	public AdminUser addAdminUser(AdminUser mahasiswa);
	public AdminUser getAdminUserById(String id);
	public void deleteAdminUser(String id);

}
