package com.ujian.main.services;

import java.util.List;

import com.ujian.main.entity.Laporan;

public interface ModelLaporanInterface {
	
	public List<Laporan> getAllLaporan();
	
	public Laporan addLaporan(Laporan laporan);
	public Laporan getLaporanById(String status);
	public void deleteKejadian(String id);

}
