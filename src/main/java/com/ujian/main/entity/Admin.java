package com.ujian.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAdmin;
	private String username;
	private String password;
	private String role;
	
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="id_kejadian")
//    private List<Kejadian> lstKejadian = new ArrayList<Kejadian>();
//	

}
