package OnlineBookWebservice.IntefaceImpl;

import java.sql.Date;

import com.bookdownload.gwt.shared.BookDetails;


public class test {
	public test(){
		BookDetails details=new BookDetails();
		details.setAuthor_Name("test");
		details.setBook_Category_Code(1);
		details.setBook_Name("test");
		details.setPrice(123);
		details.setPublisher_Name("test");
		details.setTotal_Download(12);
		BookDetailsDAOImpl bookDetailsDAOImpl=new BookDetailsDAOImpl();
		if(bookDetailsDAOImpl.AddBook(details)){
			System.out.println("success");
		}
	}
	
	
	
	public static void main(String a[]) {
 
	BookDetails details=new BookDetails();
	details.setAuthor_Name("test");
	details.setBook_Category_Code(1);
	details.setBook_Name("test");
	details.setPrice(123);
	details.setPublisher_Name("test");
	details.setTotal_Download(12);
	BookDetailsDAOImpl bookDetailsDAOImpl=new BookDetailsDAOImpl();
	if(bookDetailsDAOImpl.AddBook(details)){
		System.out.println("success");
	}
}
}
