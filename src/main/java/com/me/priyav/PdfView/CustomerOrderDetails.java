package com.me.priyav.PdfView;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.me.priyav.pojo.CartItem;
import com.me.priyav.pojo.Customer;

public class CustomerOrderDetails extends AbstractPdfView{
	
	private Customer customer;
	
	public CustomerOrderDetails(Customer customer) {
		this.customer = customer;
		
	}
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
		Paragraph title = new Paragraph("List of Products", titleFont );
		Font prodTitleFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
		try {
			 document.add(new Phrase(""+customer.getCustFName(),prodTitleFont)) ;
			 document.add(new Phrase(" "+customer.getCustLName(),prodTitleFont)) ;
			 document.add( Chunk.NEWLINE );
			 document.add(new Phrase("Address: ",prodTitleFont)) ;
			 document.add( Chunk.NEWLINE );
			 document.add(new Phrase(" "+customer.getBillingAddress().getStreetName()+ ", "+customer.getBillingAddress().getAptNo(),prodTitleFont)) ;
			 document.add( Chunk.NEWLINE );
			 document.add(new Phrase(" "+ customer.getBillingAddress().getCity()+ ", "+customer.getBillingAddress().getState()+ ", " +customer.getBillingAddress().getZipCode(),prodTitleFont)) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
