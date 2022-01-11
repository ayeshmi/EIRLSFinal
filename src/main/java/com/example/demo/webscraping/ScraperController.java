package com.example.demo.webscraping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/auth")
public class ScraperController {
	@Autowired
    ScraperService scraperService;

    @GetMapping(path = "/OtherWebsitesDetails")
    public List<ResponseDTO> getVehicleByModel() {
    	System.out.println("called");
        return  scraperService.getVehicleByModel();
    }
    
    @GetMapping(path = "/readCSV")
    public List<BookIntegration> readCSVFile() {
    	System.out.println("called");
    	List<BookIntegration> books=null;
         try {
        	 books=scraperService.CSvFileReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return books;
    }
    
    
   
}
