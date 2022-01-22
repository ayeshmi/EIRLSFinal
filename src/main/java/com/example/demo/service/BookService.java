package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.dto.MessageResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public List<Book> getAllBooks() {
		List<Book> books=null;
		try {
			books=bookRepository.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	@Transactional
	public MessageResponse addbook(Book book) {
		try {
			if (bookRepository.existsByTitle(book.getTitle())) {
				return new MessageResponse("Error: This book is already registered in this system.");
			}
			else if (bookRepository.existsByInumber(book.getInumber())) {
				return new MessageResponse("Error: This book is already registered in this system.");
			}else {
				bookRepository.save(book);
				List<User> users=userRepository.getUsers();
				for(int i=0;i<users.size();i++) {
					users.get(i).getEmail();
					emailSender.sendEmailNewBook();
					sendReport(book.getTitle(),book.getAuthor(),book.getCategory(),book.getInumber().toString(),Integer.toString(book.getNumberOfPages()),book.getBookDescription());	
			}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new MessageResponse("Book is successfully registered!");

	}

	public Book getBookById(Long id) {
		Book book = null;
		try {
			book = bookRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}

	public List<Book> getBooksByCategory(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		System.out.println("Array Size1" + list.size());
		return list;
	}

	public List<Book> getBooksByRomance(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size2" + list.size());
		return list;
	}

	public List<Book> getBooksByComic(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size3" + list.size());
		return list;
	}

	public List<Book> getBooksByAction(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size4" + list.size());
		return list;
	}

	public List<Book> getBooksByFantasy(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size5" + list.size());
		return list;
	}

	public List<Book> getBooksByHorror(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		System.out.println("Array Size123" + list.size());
		return list;
	}

	public List<Book> getBooksByDrama(String category) {

		List<Book> list = new ArrayList<>(2);
		List<Book> array = bookRepository.findByCategory(category);
		// array.get(1);
		list.add(array.get(0));
		list.add(array.get(1));
		list.add(array.get(2));
		list.add(array.get(3));
		return list;
	}

	public MessageResponse deleteBook(Long id) {
		MessageResponse messageResponse=null;
		
		try {
			Book book = bookRepository.findById(id).orElseThrow();
			bookRepository.delete(book);
			messageResponse=new MessageResponse("Book is successfully deleted!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return messageResponse;
	}

	public List<Book> advanceSearch(String specs) {
		List<Book> book = bookRepository.search(specs);
		return book;
	}

	public List<Book> getAllBooksByCategory(String category) {
		List<Book> books = bookRepository.findByCategory(category);
		return books;
	}

	public MessageResponse updateBook(Long id, Book book) {
MessageResponse messageResponse=null;
		
		try {
			Book book1 = bookRepository.findById(id).orElseThrow();
			book1.setAuthor(book.getAuthor());
			book1.setBookDescription(book.getBookDescription());
			book1.setBookExcerpt(book.getBookExcerpt());
			book1.setNumberOfCopies(book.getNumberOfCopies());
			book1.setNumberOfPages(book.getNumberOfPages());
			bookRepository.save(book1);
			messageResponse=new MessageResponse("Book is successfully updated!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return messageResponse;
	}
	
	
	public void sendReport(String title,String author,String category,String isbn,String pages,String description) {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/home2.jsp");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("title", title);	
		context.put(" author", author);	
		context.put("category",category);
		context.put("isbn", isbn);
		context.put("pages", pages);
		context.put("description", description);
		context.put("date", LocalDateTime.now().toString());
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		baos = generatePdf(writer.toString());
	
		String filename = "C:\\Users\\ayesh\\eclipse-workspace\\EEAassignmentFinal\\src\\main\\resources\\myfile.pdf";
		try {
			FileOutputStream output = new FileOutputStream(filename);
			output.write(baos.toByteArray());
			output.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		attachReportToEmail();
		
	}
	
	public void attachReportToEmail() {
MimeMessage msg = javaMailSender.createMimeMessage();

        
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
		
        helper.setTo("domsellanaka@gmail.com");

        helper.setSubject("Testing from Spring Boot");

         //default = text/plain
        helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);
         
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();
        
        String file = "C:\\Users\\ayesh\\eclipse-workspace\\EEAassignmentFinal\\src\\main\\resources\\myfile.pdf";
        String fileName = "myfile.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
 
        javaMailSender.send(msg);
        
        
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ByteArrayOutputStream generatePdf(String html) {

		PdfWriter pdfWriter = null;

		// create a new document
		Document document = new Document();
		try {

			document = new Document();
			// document header attributes
			document.addAuthor("Kinns");
			document.addAuthor("Kinns123");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("kinns123.github.io");
			document.addTitle("HTML to PDF using itext");
			document.setPageSize(PageSize.LETTER);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

			// open document
			document.open();

			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.getDefaultCssResolver(true);
			xmlWorkerHelper.parseXHtml(pdfWriter, document, new StringReader(
					html));
			// close the document
			document.close();
			System.out.println("PDF generated successfully");

			return baos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public MessageResponse addBookCopy(Book book) {
		Book book1=bookRepository.findByInumber(book.getInumber());
		int total=book1.getNumberOfCopies() +book.getNumberOfCopies();
		book1.setNumberOfCopies(total);
		bookRepository.save(book1);
		MessageResponse messageResponse=new MessageResponse("Book copies are increased!");
		return messageResponse;
	}



	

}
