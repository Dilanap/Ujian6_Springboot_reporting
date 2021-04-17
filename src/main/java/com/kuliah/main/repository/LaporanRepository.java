package com.kuliah.main.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.kuliah.main.entity.Laporan;


public interface LaporanRepository extends CrudRepository<Laporan, Long>{
	
	
	@Query(value="Select *from laporan where status= null", nativeQuery = true)
	Laporan findbystatus(String status);
    
	@Query(value = "Select Count(*) From laporan", nativeQuery = true)
	public List<Laporan> jumlahdata();
}
