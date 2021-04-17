package com.kuliah.main.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.main.entity.Karyawan;
import com.kuliah.main.repository.KaryawanRepository;

@Service
public class ModelKaryawan implements ModelKaryawanInterface{
	
	@Autowired
	KaryawanRepository karyawanRepo;

	@Override
	public List<Karyawan> getAllKaryawan() {
		// TODO Auto-generated method stub
		return (List<Karyawan>) this.karyawanRepo.findAll();
	}

	@Override
	public Karyawan getKaryawanByName(String nama) {
		// TODO Auto-generated method stub
		return this.karyawanRepo.findByNamaKaryawan(nama);
	}

	@Override
	public Karyawan addKaryawan(Karyawan karyawan) {
		// TODO Auto-generated method stub
		
		return  this.karyawanRepo.save(karyawan);
	
	}

	@Override
	public Karyawan getKaryawanById(String id) {
		// TODO Auto-generated method stub
		
		return 	((Karyawan)this.karyawanRepo.findByIdKaryawan(Long.parseLong(id)));
		
		
	}

	@Override
	public void deleteKaryawan(String id) {
		// TODO Auto-generated method stub
		this.karyawanRepo.deleteById(Long.parseLong(id));
		
	}

}
