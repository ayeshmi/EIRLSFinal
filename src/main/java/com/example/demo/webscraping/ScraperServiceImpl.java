package com.example.demo.webscraping;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BookRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScraperServiceImpl implements ScraperService {
	
	@Autowired
	private BookRepository bookRepository;

	@Value("#{'${website.urls}'.split(',')}")
	List<String> urls;

	@Override
	public List<ResponseDTO> getVehicleByModel() {

		List<ResponseDTO> responseDTOS = null;

		for (String url : urls) {

			if (url.contains("ikman")) {


			} else if (url.contains("booklending")) {
				
				responseDTOS=getHello(responseDTOS, url);
				
				
			}

		}

		return responseDTOS;
	}
	
	private List<ResponseDTO> getHello(List<ResponseDTO> responseDTOS, String url) {
		Document document;
		List<ResponseDTO> items=new ArrayList<ResponseDTO>();
		try {
			
			
			for(int i=1;i<4;i++) {
				document = Jsoup.connect("https://www.booklender.com/bestsellers/all/page/"+i).get();	
				
				Element element = document.getElementById("browseDataContainer");
				
				Elements elements = element.getElementsByClass("row thumbnail");
				for(Element ads : elements) {
					ResponseDTO responseDTO = new ResponseDTO();

					Elements element1 = ads.select(".quick-box");
					Elements element2 = ads.getElementsByTag("a");
					Elements elementC = ads.getElementsByTag("img");
					responseDTO.setImage(elementC.get(0).attr("src"));
					responseDTO.setUrl(element2.get(0).attr("href"));
					
					Element element3 = ads.getElementById("title-link");
					responseDTO.setTitle(element3.text());
					
					
					Document document2=Jsoup.connect(element2.get(0).attr("href")).get();
					Elements element4 = document2.getElementsByClass("col-xs-12 col-sm-4 col-md-5 col-lg-6 t16");
					for(Element pds : element4) {
						Elements element5=pds.getElementsByTag("a");	
						responseDTO.setName(element5.get(0).text());
						Elements element6=pds.getElementsByClass("no-margin-bottom bold");
						responseDTO.setYear(element6.get(2).text());
						responseDTO.setCategory(element6.get(3).text());
						responseDTO.setPrice(element6.get(4).text());
						
						
					}
					if (responseDTO.getUrl() != null) {
						items.add(responseDTO);
						//csvPrinter.printRecord(responseDTO.getTitle(), responseDTO.getUrl(), responseDTO.getName(),responseDTO.getCategory(),responseDTO.getPrice(),responseDTO.getImage());
						System.out.println("processing");
					}
					

					System.out.println("Completed");	
				}	
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
		
	}
	
	public List<BookIntegration> CSvFileReader() throws IOException {

	       final String csvFile = "C:\\Users\\ayesh\\OneDrive\\Desktop\\BookPurchasingWebiste.csv";

	        final Reader reader  = new FileReader(csvFile);


	        List<BookIntegration> books = new ArrayList<>();

	        CSVFormat csvFormat = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withQuote(null);

	        try (CSVParser csvParser = CSVParser.parse(reader, csvFormat)) {

	            for (CSVRecord csvRecord : csvParser) {

	            	BookIntegration book=new BookIntegration();
	                book.setTitle(csvRecord.get("title"));
	                book.setAuthor( csvRecord.get("author"));
	                book.setBookDescription(csvRecord.get("book_description"));
	                book.setBookExcerpt(csvRecord.get("book_excerpt"));
	                book.setCategory(csvRecord.get("category"));
	                book.setDate(csvRecord.get("date"));
	                book.setInumber(Long.parseLong(csvRecord.get("inumber")));
	                book.setNumberOfCopies(Integer.parseInt( csvRecord.get("number_of_copies")));
	                book.setNumberOfPages(Integer.parseInt( csvRecord.get("number_of_pages")));
	                book.setPrice(Integer.parseInt( csvRecord.get("price")));
	                book.setYear(Integer.parseInt( csvRecord.get("year")));
	                books.add(book);
                    
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         for(int i=0;i<books.size();i++) {
	        	 Boolean status=bookRepository.existsByInumber(books.get(i).getInumber());
	        	 if(status == true) {
	        		books.get(i).setStatus("exist"); 
	        	 }
	        	 else {
	        		 books.get(i).setStatus("new"); 
	        	 }
	        	 
	         }
	         System.out.println(books.get(0).getStatus());
             System.out.println(books.size());
             System.out.println(books.get(1).getDate());
	        return books;
	    }
	
	 @Scheduled(cron = "0 2 3 * * *", zone = "Asia/Calcutta")
	    public void CSvFileWriter() throws IOException {

		 final URL url = new URL("https://www.tutorialspoint.com/spring_boot/spring_boot_scheduling.htm");

	        final Reader reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
	        CSVFormat csvFormat = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withQuote(null);
		 
	      final  String csvFile = "C:\\Users\\ayesh\\OneDrive\\Desktop\\BookPurchasingWebiste.csv";
	        
			FileWriter fileWriter = new FileWriter(csvFile);
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, csvFormat);

	        try (CSVParser csvParser = CSVParser.parse(reader, csvFormat)) {

	            for (CSVRecord csvRecord : csvParser) {

	                String title = csvRecord.get("title");
	                String author=csvRecord.get("author");
	                String bookDescription=csvRecord.get("book_description");
	                String bookExcerpt=csvRecord.get("book_excerpt");
	                String category=csvRecord.get("category");
	                String date=csvRecord.get("date");
	                String inumber=csvRecord.get("inumber");
	                String numberOfCopies=csvRecord.get("number_of_copies");
	                String numberOfPages=csvRecord.get("number_of_pages");
	                String price=csvRecord.get("price");
	                String year=csvRecord.get("year"); 

	               csvPrinter.printRecord(title,author,bookDescription,bookExcerpt,category,date,inumber,numberOfCopies,numberOfPages,price,year);

	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        csvPrinter.flush();
	        csvPrinter.close();

	    }


}
