package com.kuliah.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Karyawan;


public interface KaryawanRepository extends CrudRepository<Karyawan, Long>{
	
	public Karyawan findByNamaKaryawan(String nama);
	public Karyawan findByIdKaryawan(Long id);

}
