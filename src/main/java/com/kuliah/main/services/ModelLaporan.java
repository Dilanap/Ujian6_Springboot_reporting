package com.kuliah.main.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliah.main.entity.Laporan;
import com.kuliah.main.repository.LaporanRepository;

@Service
public class ModelLaporan  implements ModelLaporanInterface{
	
	@Autowired
	LaporanRepository laporanRepo;

	@Override
	public List<Laporan> getAllLaporan() {
		// TODO Auto-generated method stub
		return (List<Laporan>) this.laporanRepo.findAll();
	}

	

	@Override
	public Laporan addLaporan(Laporan laporan) {
		// TODO Auto-generated method stub
		
		return  this.laporanRepo.save(laporan);
	
	}

	@Override
	public Laporan cariLaporan(String id) {
		// TODO Auto-generated method stub
		return this.laporanRepo.findById(Long.parseLong(id)).get();
	}
	
	public List<Laporan> getHitung(){
		return laporanRepo.jumlahdata();
	}
	
	public void save(Laporan updateStatus) {
        // TODO Auto-generated method stub
		this.laporanRepo.save(updateStatus);
    }
	@Override
	public void deleteLaporan(String id) {
		// TODO Auto-generated method stub
		this.laporanRepo.deleteById(Long.parseLong(id));
		
	}

	public Laporan getLaporanByStatus(String status) {
		return laporanRepo.findbystatus(status);
	}
	


	@Override
	public Laporan getLaporanByName(String nama) {
		// TODO Auto-generated method stub
		return null;
	}

}
