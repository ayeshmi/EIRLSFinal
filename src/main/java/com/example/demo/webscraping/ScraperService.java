package com.example.demo.webscraping;

import java.io.IOException;
import java.util.List;


public interface ScraperService {

	List<ResponseDTO> getVehicleByModel();
	public List<BookIntegration> CSvFileReader() throws IOException;

	
	
}
