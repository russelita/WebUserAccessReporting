package com.ujian.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujian.main.entity.Laporan;
import com.ujian.main.repository.LaporanRepository;

@Service
public class ModelLaporan implements ModelLaporanInterface{
	
	@Autowired
	LaporanRepository laporRepo;

	@Override
	public List<Laporan> getAllLaporan() {
		// TODO Auto-generated method stub
		return (List<Laporan>) this.laporRepo.findAll();
	}

	@Override
	public Laporan addLaporan(Laporan laporan) {
		// TODO Auto-generated method stub
		return this.laporRepo.save(laporan);
	}

	@Override
	public Laporan getLaporanById(String id) {
		// TODO Auto-generated method stub
		return this.laporRepo.findById(Long.parseLong(id)).get();
	}

	@Override
	public void deleteKejadian(String id) {
		// TODO Auto-generated method stub
		this.laporRepo.deleteById(Long.parseLong(id));
	}

}
