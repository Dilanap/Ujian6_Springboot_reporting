package com.kuliah.main.controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Karyawan;
import com.kuliah.main.services.ModelKaryawan;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class KaryawanPage {
	
	@Autowired
	ModelKaryawan modelKaryawan;
	
	
	@GetMapping("/karyawan/view")
	public String viewIndexKaryawan(Model model) {
		
		model.addAttribute("listKaryawan",modelKaryawan.getAllKaryawan());
		model.addAttribute("active",1);
		model.addAttribute("test","Test Aja");
		
		return "view_karyawan";
	}
	
	
	@GetMapping("/karyawan/add")
	public String viewAddKaryawan(Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("karyawan",new Karyawan());
		
		return "add_karyawan";
	}
	
	@PostMapping("/karyawan/view")
	  public String addKaryawan(@ModelAttribute Karyawan karyawan, Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = karyawan.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
		karyawan.setPassword(encodedPassword);		
		
		this.modelKaryawan.addKaryawan(karyawan);
		model.addAttribute("listKaryawan",modelKaryawan.getAllKaryawan());
		
		
		return "redirect:/karyawan/view";
	}
	
	
	@GetMapping("/karyawan/update/{id}")
	public String viewUpdateKaryawan(@PathVariable String id, Model model) {
		
		Karyawan karyawan = modelKaryawan.getKaryawanById(id);
		// buat penampung data karyawan di halaman htmlnya
		model.addAttribute("karyawan",karyawan);
		
		return "add_karyawan";
	}
	
	@GetMapping("/karyawan/delete/{id}")
	public String deleteKaryawan(@PathVariable String id, Model model) {
		
		this.modelKaryawan.deleteKaryawan(id);
		model.addAttribute("listKaryawan",modelKaryawan.getAllKaryawan());
		
		
		return "redirect:/karyawan/view";
	}
	
	@GetMapping("/karyawan/report/pdf")
	public void exportPDF() {
		try {
		File file = ResourceUtils.getFile("classpath:mahasiswa.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		List<Karyawan> lstKaryawan = modelKaryawan.getAllKaryawan();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstKaryawan);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","Juaracoding");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = "D:\\karyawan.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,path);
        
       
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		
				
	}

}
