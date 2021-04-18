package com.kuliah.main.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.kuliah.main.entity.Laporan;

import com.kuliah.main.services.ModelLaporan;
import com.kuliah.main.utility.FileUtility;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class LaporanPage {

	@Autowired
	ModelLaporan modelLaporan;

    private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

	@GetMapping("/laporan/view")
	public String viewIndexLaporan(Model model) {
		model.addAttribute("listlaporan",modelLaporan.getAllLaporan());
		model.addAttribute("active",4);
		return "view_laporan";
	}
	
	@GetMapping("/laporan/add")
	public String viewAddLaporan(Model model) {
		model.addAttribute("laporan",new Laporan());
		return "add_laporan1";
	}
	
	
	@GetMapping("/laporan/proses/{id}")
    public String prosesLaporan(@PathVariable String id, Model model) {

        Laporan updateStatus = modelLaporan.cariLaporan(id);
        updateStatus.setStatus("Telah Diproses");
        modelLaporan.save(updateStatus);
        
        return "redirect:/laporan/view";
    }
	
	@GetMapping("/laporan/reject/{id}")
    public String rejectLaporan(@PathVariable String id, Model model) {

        Laporan updateStatus = modelLaporan.cariLaporan(id);
        updateStatus.setStatus("Reject");
        modelLaporan.save(updateStatus);
        
        return "redirect:/laporan/view";
    }
	
	@PostMapping("/laporan/view")
	public String addLaporan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Laporan laporan, Model model) throws IOException { {
		   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		  
	         
	        String uploadDir = "user-photos/" ;
	
	        FileUtility.saveFile(uploadDir, fileName, file);
	 
          laporan.setKeterangan("/"+uploadDir + fileName);
           this.modelLaporan.addLaporan(laporan);

		model.addAttribute("listlaporan",modelLaporan.getAllLaporan());
		
		return "redirect:/laporan/view";
	  }
	}
	
	@GetMapping("/laporan/delete/{id}")
	public String deleteLaporan(@PathVariable String id, Model model) {
		
		this.modelLaporan.deleteLaporan(id);
		
		
		return "redirect:/laporan/view";
	}
	
	@GetMapping("/laporan/update/{id}")
	public String viewUpdateLaporan(@PathVariable String id, Model model) {
		
		Laporan laporan = modelLaporan.cariLaporan(id);
	
		model.addAttribute("laporan",laporan);
		
		return "add_laporan";
	}
	
	@GetMapping("/laporan/update1/{id}")
	public String viewUpdateLaporan1(@PathVariable String id, Model model) {
		
		Laporan laporan = modelLaporan.cariLaporan(id);
	
		model.addAttribute("laporan",laporan);
		
		return "add_laporan";
	}
	
	@GetMapping("/laporan/view1/{status}")
	public String findbystatus(@PathVariable String status, Model model) {
		Laporan laporan = modelLaporan.getLaporanByStatus(status);
		
		model.addAttribute("laporan",laporan);
		
		return "redirect:/laporan/view";
	}
	
	@GetMapping("/laporan/print")
	public String exportPDF() {
		try {
		File file = ResourceUtils.getFile("classpath:templatereport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		List<Laporan> lstLaporan = modelLaporan.getAllLaporan();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstLaporan);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy","Juaracoding");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        String path = "E:\\laporan.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,path);
        
       
		
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return "redirect:/laporan/view";
				
	}
	
	@GetMapping("/laporanhitung")
	public List<Laporan> hitung(){
		return modelLaporan.getHitung();
	}
}