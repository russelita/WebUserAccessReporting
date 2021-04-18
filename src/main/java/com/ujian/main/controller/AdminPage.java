package com.ujian.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ujian.main.entity.Admin;
import com.ujian.main.services.ModelAdmin;

@Controller
public class AdminPage {
	
	@Autowired
	ModelAdmin modelAdmin;
	
	@GetMapping("/admin")
	public String viewIndexAdmin(Model model) {
		
		model.addAttribute("listAdmin",modelAdmin.getAllAdmin());
		
		return "admin";
	}
	
	@GetMapping("/admin/add")
	public String viewAddAdmin(Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("admin",new Admin());
		
		return "add_admin";
	}
	
	@PostMapping("/admin")
	  public String addAdmin(@ModelAttribute Admin admin, Model model) {
		
		// buat penampung data mahasiswa di halaman htmlnya
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = admin.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
        admin.setPassword(encodedPassword);	
		this.modelAdmin.addAdmin(admin);
		model.addAttribute("listAdmin",modelAdmin.getAllAdmin());
		
		
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/update/{id}")
	public String viewUpdateAdmin(@PathVariable String id, Model model) {
		
		Admin admin = modelAdmin.getAdminById(id);
		// buat penampung data mahasiswa di halaman htmlnya
		model.addAttribute("admin",admin);
		
		return "add_admin";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteAdmin(@PathVariable String id, Model model) {
		
		this.modelAdmin.deleteAdmin(id);
		model.addAttribute("listAdmin",modelAdmin.getAllAdmin());
		
		
		return "redirect:/admin";
	}

}
