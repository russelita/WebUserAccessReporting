package com.ujian.main.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ujian.main.entity.Laporan;
import com.ujian.main.services.ModelLaporan;
import com.ujian.main.utility.FileUtility;

@Controller
public class LaporanPage {
	
	@Autowired
	ModelLaporan modelLaporan;
	
	@GetMapping("/ujian/laporan/view")
	public String viewIndexLaporan(Model model) {
		
		model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
		model.addAttribute("active",1);
		
		return "view_laporan";
	}
	
	@GetMapping("/ujian/dashboard/view")
	public String viewIndexReport(Model model) {
		
		model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
		model.addAttribute("active",3);
		
		return "dashboard";
	}
	
	@GetMapping("/ujian/laporan/add")
	public String viewAddLaporan(Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("laporan",new Laporan());
		
		return "add_laporan";
	}
	
	@PostMapping("/ujian/dashboard/view")
	  public String addLaporan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Laporan laporan, Model model) throws IOException { {
		
		// buat penampung data mahasiswa di halaman htmlnya
		  String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
	        
	        String uploadDir = "user-photos/" ;

	        FileUtility.saveFile(uploadDir, fileName, file);
	 
	      laporan.setGambar("/"+uploadDir + fileName);
		this.modelLaporan.addLaporan(laporan);
		model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
		
		
		return "redirect:/ujian/dashboard/view";
	}
	}
	
	@GetMapping("/ujian/laporan/update/{id}")
	public String viewUpdateLaporan(@PathVariable String id,@RequestParam String status, Model model) {
		
		Laporan laporan = modelLaporan.getLaporanById(status);
		laporan.setStatus(status);
		this.modelLaporan.addLaporan(laporan);
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("laporan",laporan);
		
		return "redirect:/ujian/laporan/view";
	}
	
	

}
