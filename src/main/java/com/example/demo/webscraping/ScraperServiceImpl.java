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
import com.example.demo.repository.VideoRepository;

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
	
	@Autowired
	private VideoRepository videoRepository;

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
			
			
			for(int i=2;i<4;i++) {
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
	
	@Override
	public List<VideoDTO> getVideos() {
		Document document;
		List<VideoDTO> items=new ArrayList<VideoDTO>();
		try {
			
			document = Jsoup.connect("https://www.amazon.com/gp/video/search/ref=atv_mv_hom_4_c_dr69uw_3_smr?queryToken=eyJ0eXBlIjoicXVlcnkiLCJuYXYiOnRydWUsInBpIjoiZGVmYXVsdCIsInNlYyI6ImNlbnRlciIsInN0eXBlIjoic2VhcmNoIiwicXJ5Ijoibm9kZT0yMTIxMDI1MTAxMSZmaWVsZC13YXlzX3RvX3dhdGNoPTEyMDA3ODY3MDExJnBfbl9lbnRpdHlfdHlwZT0xNDA2OTE4NDAxMSZhZHVsdC1wcm9kdWN0PTAmYmJuPTIxMjEwMjUxMDExJmZpZWxkLWlzX3ByaW1lX2JlbmVmaXQ9MCZzZWFyY2gtYWxpYXM9aW5zdGFudC12aWRlbyZxcy1hdl9yZXF1ZXN0X3R5cGU9NCZxcy1pcy1wcmltZS1jdXN0b21lcj0xIiwicnQiOiJkUjY5dXdzbXIiLCJ0eHQiOiJOZXcgcmVsZWFzZSBtb3ZpZXMiLCJzdWJ0eHQiOiJSZW50Iiwib2Zmc2V0IjowLCJucHNpIjowLCJvcmVxIjoiNzBiMTdhZmUtNjcwNC00MjVlLTg1ZTQtN2Y3OWM5MzY5NmM4OjE2NDI4MzU0ODcwMDAiLCJzdHJpZCI6IjE6MU81U1lGN1JGVTY1WSMjTVpRV0daTFVNVlNFR1lMU041MlhHWkxNIiwib3JlcWsiOiJMaTYrby9nc2gwaEdDY1RnYVRnS0x6bWJBenR6bmdvc29lSTA2emFoZmRJPSIsIm9yZXFrdiI6MX0%3D&pageId=default&queryPageType=browse&ie=UTF8").get();	
            
			Element element = document.getElementById("av-search");
			Elements elements = element.getElementsByClass("_38SAO3 tst-hover-container");
			for(Element adss : elements) {
				VideoDTO videoDTO = new VideoDTO();
				Elements movieName=adss.getElementsByClass("_2MiS8F tst-hover-title");
				videoDTO.setName(movieName.text());
				
				Elements image = adss.getElementsByTag("img");
				videoDTO.setImage(image.get(0).attr("src"));
				System.out.println("image is"+image.get(0).attr("src"));
				Elements image1 = adss.getElementsByTag("a");
				videoDTO.setImage(image1.get(0).attr("href"));
				System.out.println("sfsfsfs"+image1.get(0).attr("href"));
				
				
				Document document2=Jsoup.connect("https://www.amazon.com"+image1.get(0).attr("href")).get();
				Elements element4 = document2.getElementsByClass("_2hu-aV");
				for(Element pds : element4) {
					Elements element5=pds.getElementsByTag("h1");	
					System.out.println("dsdsds"+element5.text());
					Elements movieName12=pds.getElementsByClass("XqYSS8");
					System.out.println("dsdsssttds"+movieName12.get(0).text());
					videoDTO.setDuration(movieName12.get(0).text());
					System.out.println("dsdsssttds"+movieName12.get(1).text());
					videoDTO.setYear(Integer.parseInt(movieName12.get(1).text()));
					Elements movieName123=pds.getElementsByClass("_3qsVvm _1wxob_");
					System.out.println("dsdsdsds12"+movieName123.text());
					videoDTO.setDescription(movieName123.text());
					Elements price12=pds.getElementsByClass("_36qUej");
					
					System.out.println("price"+price12.get(12).text());
					
					videoDTO.setYeart1(price12.get(12).text());
					Elements price14=pds.getElementsByClass("_1NNx6V");
					System.out.println("price"+price14.get(0).text());
					videoDTO.setAuthor(price14.get(0).text());
					System.out.println("price"+price14.get(4).text());
					videoDTO.setCategory(price14.get(4).text());
					
					
				}
	
				
				if (videoDTO.getDuration() != null) {
					items.add(videoDTO);
					System.out.println("size is"+items.size());
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

		 final URL url = new URL("https://www.tutorialspoint.com/spring_boot/spring_boot_scheduling.html");

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
	 
	
	@Override
	 public List<VideoDTO> CSvFileReaderVideo() throws IOException {

	       final String csvFile = "C:\\Users\\ayesh\\OneDrive\\Desktop\\ayeshmi12.csv";

	        final Reader reader  = new FileReader(csvFile);


	        List<VideoDTO> books = new ArrayList<>();

	        CSVFormat csvFormat = CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withQuote(null);

	        try (CSVParser csvParser = CSVParser.parse(reader, csvFormat)) {

	            for (CSVRecord csvRecord : csvParser) {

	            	VideoDTO book=new VideoDTO();
	                book.setAgeLimitation(csvRecord.get("ageLimitation"));
	                book.setAuthor( csvRecord.get("author"));
	                book.setCategory(csvRecord.get("category"));
	                book.setDate(csvRecord.get("date"));
	                book.setDescription(csvRecord.get("description"));
	                book.setImage(csvRecord.get("imageOfVideo"));
	                book.setName(csvRecord.get("name"));
	                book.setNumberOfCopies(Integer.parseInt( csvRecord.get("numberOfCopies")));
	                book.setPrice(Integer.parseInt( csvRecord.get("price")));
	                book.setYear(Integer.parseInt( csvRecord.get("year")));
	                
	                books.add(book);
                  
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         for(int i=0;i<books.size();i++) {
	        	 Boolean status=videoRepository.existsByTitle(books.get(i).getName());
	        	 if(status == true) {
	        		books.get(i).setStatus("exist"); 
	        	 }
	        	 else {
	        		 books.get(i).setStatus("new"); 
	        	 }
	        	 
	         }
	         System.out.println(books.get(0).getStatus());
           System.out.println(books.size());
           System.out.println(books.get(0).getDate());
	        return books;
	    }


}
