package com.kuliah.main.services;

import java.util.List;

import com.kuliah.main.entity.Karyawan;

public interface ModelKaryawanInterface {
	
	public List<Karyawan> getAllKaryawan();
	public Karyawan getKaryawanByName(String name);
	
	public Karyawan addKaryawan(Karyawan karyawan);
	public Karyawan getKaryawanById(String id);
	public void deleteKaryawan(String id);

}
