package com.ujian.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.ujian.main.entity.Laporan;

public interface LaporanRepository extends CrudRepository<Laporan, Long> {
	
	public Laporan findByNama(String nama);

}
