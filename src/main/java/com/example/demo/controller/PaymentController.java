package com.example.demo.controller;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Checking;
import com.example.demo.dto.MessageResponse;
import com.example.demo.model.PaymentRequest;
import com.example.demo.model.Paymentdto;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.checkingRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@RestController

@RequestMapping("/api/auth")
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private checkingRepository checkRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping("/paymentDetails/{email}")
	public Paymentdto  getPaymentDetailsByEmail(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		Paymentdto payment=paymentRepository.findPaymentDetailsByEmail(email);
		
		return payment;
	}
	
	@GetMapping("/lendingPaymentDetails/{email}")
	public Paymentdto  getLendingPaymentDetailsByEmail(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		Paymentdto payment=paymentRepository.findLendingPaymentDetailsByEmail(email);
		
		return payment;
	}

	
	@PostMapping("/addPayment")
	public ResponseEntity<Object> addPayment(@RequestBody PaymentRequest paymentdto) {
		System.out.println("Payment is called45"+paymentdto.getCardNumber());
		MessageResponse message=null;
		int number=paymentdto.getCardNumber().length();
		int number1=paymentdto.getCvv().length();
		if(number !=16) {
			 
			 return ResponseEntity.badRequest().body(new MessageResponse("Error: Card Number should contain 16 numbers!"));
		}
		else if(number1 !=3){
			
			return ResponseEntity.badRequest().body(new MessageResponse("Error: CVV Number should contain 3 numbers!"));
		}
		else {
			Paymentdto payment=paymentRepository.findPaymentDetailsByEmail(paymentdto.getEmail());
			payment.setCardType(paymentdto.getCardType());
			payment.setCardHolderName(paymentdto.getCardHolderName());
			payment.setExpiryDate(paymentdto.getExpiryDate());
			payment.setPaymentStatus("paid");
			paymentRepository.save(payment);
			sendReport(150,"dsdsd",150,150);
			System.out.println("Payment is added");
			message = new MessageResponse("Your payment is successfully completed!");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		
		
	
	}
	
	@PostMapping("/checking")
	public void checking(@RequestBody Checking check) {
		checkRepository.save(check);
		System.out.println(""+check.getNum1());
		
	
	}
	
	@GetMapping("/allUnpaidPayments/{email}")
	public List<Paymentdto>  viewAllPaymentsForUser(@PathVariable("email") String email) {
		System.out.println("Payment is called");
		List<Paymentdto> payment=paymentRepository.findAllPaymentDetailsByEmail(email);
		
		return payment;
	}
	
	@PostMapping("/addBookOrder")
	public List<Paymentdto>  addBookOrder(@RequestBody Paymentdto paymentdto) {
		paymentdto.setReason("OrderFee");
		paymentRepository.save(paymentdto);
		return null;
	}
	
	public void sendReport(int price, String email, int deliveryFee, int totalFee) {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();

		/* next, get the Template */
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		ve.init();
		Template t = ve.getTemplate("templates/home.jsp");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("email", email);	
		context.put("orderPrice", price);	
		context.put("deliveryPrice",deliveryFee);
		context.put("totalPrice", totalFee);
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
	
	
	
}
