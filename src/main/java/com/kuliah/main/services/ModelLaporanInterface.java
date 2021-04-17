package com.kuliah.main.services;

import java.util.List;
import java.util.Optional;

import com.kuliah.main.entity.Karyawan;
import com.kuliah.main.entity.Laporan;

public interface ModelLaporanInterface {
	
	public List<Laporan> getAllLaporan();
	
	public Laporan getLaporanByName(String nama);
	
	public void deleteLaporan(String id);
	
	public Laporan cariLaporan(String id);


public	Laporan addLaporan(Laporan laporan);

}
