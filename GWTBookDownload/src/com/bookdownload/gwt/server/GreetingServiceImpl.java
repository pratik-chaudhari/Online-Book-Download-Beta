package com.bookdownload.gwt.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


import com.bookdownload.gwt.client.GreetingService;
import com.bookdownload.gwt.shared.BookDetails;
import com.bookdownload.gwt.shared.DBConnecterUtil;
import com.bookdownload.gwt.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	public GreetingServiceImpl(){
		try {
			con = DBConnecterUtil.getInstance().getConnection();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public boolean AddBook(BookDetails bookDetails) throws IllegalArgumentException{
		System.out.println("\n\n\n\n\n\n");
		System.out.println(bookDetails.getAuthor_Name());
		System.out.println("\n\n\n\n\n\n");
		/*bookDetails.setAuthor_Name("AJAY Hirapara");
		bookDetails.setBook_Category_Code(1);
		bookDetails.setBook_Name("Master in java");
		bookDetails.setPrice(123);
		bookDetails.setPublisher_Name("Tata");
		bookDetails.setTotal_Download(12);
		*/
		String  qry="insert into `onlinebooksdb`.`books_details` (`ISBN`, `Book_Name`, `Author_Name`, `Publisher_Name`, `Date_Of_Publication`, `Price`, `Book_Category_Code`, `Total_Download`) values (NULL, ?, ?, ?,  ?, ?, ?, ?)";
        try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setString(1, bookDetails.getBook_Name());
			statement.setString(2,bookDetails.getAuthor_Name());
			statement.setString(3, bookDetails.getPublisher_Name());
			statement.setDate(4, new Date(new java.util.Date().getTime()));
			statement.setInt(5, bookDetails.getPrice());
			statement.setInt(6, bookDetails.getBook_Category_Code());
			statement.setInt(7, bookDetails.getTotal_Download());
			return statement.execute();
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
			System.out.println(e);
		}
        
		return false;
	}

	@Override
	public boolean UpdateBook(BookDetails bookDetails)throws IllegalArgumentException {
	    String qry="Update `onlinebooksdb`.`books_details` set  `Book_Name`=?, `Author_Name`=?, `Publisher_Name`=?, `Date_Of_Publication`=?, `Price`=?, `Book_Category_Code`=?, `Total_Download`=? Where `ISBN`=?";
	    try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setString(1, bookDetails.getBook_Name());
			statement.setString(2,bookDetails.getAuthor_Name());
			statement.setString(3, bookDetails.getPublisher_Name());
			statement.setDate(4, new Date(new java.util.Date().getTime()));
			statement.setInt(5, bookDetails.getPrice());
			statement.setInt(6, bookDetails.getBook_Category_Code());
			statement.setInt(7, bookDetails.getTotal_Download());
			statement.setLong(8, bookDetails.getIsbn());
			return statement.execute();
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
        
		return false;
	
	}

	@Override
	public boolean DeleteBook(BookDetails bookDetails)throws IllegalArgumentException {
		 String qry="DELETE FROM `onlinebooksdb`.`books_details` WHERE `ISBN`=?";
		    try {
				PreparedStatement statement=con.prepareStatement(qry);
				statement.setLong(1, bookDetails.getIsbn());
				return statement.execute();
			} catch (SQLException e) {
				Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
			}
	        
			return false;
	}
	/*
	@Override
	public ArrayList<BookDetails> SearchBook(CriteriaForSearch criteriaForSearch) {
		ArrayList<BookDetails> list=new ArrayList<BookDetails>();
		String qry="select `ISBN`, `Book_Name`, `Author_Name`, `Publisher_Name`, `Date_Of_Publication`, `Price`, `Book_Category_Code`, `Total_Download`" +
				" from `onlinebooksdb`.`books_details` ";
		if(!((CriteriaForSearch) criteriaForSearch).getCriteria().equals("")){
			qry+=" where "+criteriaForSearch.getCriteria();
		}
		try {
			PreparedStatement statement=con.prepareStatement(qry);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
				BookDetails bookDetails=new BookDetails();
				bookDetails.setIsbn(resultSet.getLong(1));
				bookDetails.setBook_Name(resultSet.getString(2));
				bookDetails.setAuthor_Name(resultSet.getString(3));
				bookDetails.setPublisher_Name(resultSet.getString(4));
				bookDetails.setDate_Of_Publication(resultSet.getDate(5));
				bookDetails.setIsbn(resultSet.getInt(6));
				bookDetails.setBook_Category_Code(resultSet.getInt(7));
				bookDetails.setTotal_Download(resultSet.getInt(8));
				list.add(bookDetails);
			}
			
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
			
		return list;
	}
	*/		
	private static Connection con;
	@Override
	public ArrayList<BookDetails> GetAllBook() throws IllegalArgumentException {
		ArrayList<BookDetails> list=new ArrayList<BookDetails>();
		String qry="select `ISBN`, `Book_Name`, `Author_Name`, `Publisher_Name`, `Date_Of_Publication`, `Price`, `Book_Category_Code`, `Total_Download`" +
				" from `onlinebooksdb`.`books_details` ";
		
		try {
			PreparedStatement statement=con.prepareStatement(qry);
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
				BookDetails bookDetails=new BookDetails();
				bookDetails.setIsbn(resultSet.getLong(1));
				bookDetails.setBook_Name(resultSet.getString(2));
				bookDetails.setAuthor_Name(resultSet.getString(3));
				bookDetails.setPublisher_Name(resultSet.getString(4));
				bookDetails.setDate_Of_Publication(resultSet.getDate(5));
				bookDetails.setPrice(resultSet.getInt(6));
				bookDetails.setBook_Category_Code(resultSet.getInt(7));
				bookDetails.setTotal_Download(resultSet.getInt(8));
				list.add(bookDetails);
			}
			
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
			
		return list;
	}
	public static void main(String a[]){
		System.out.println(new GreetingServiceImpl().GetAllBook().get(16).getAuthor_Name());
	}
	@Override
	public BookDetails getBook(String isbn) throws IllegalArgumentException {
		String qry="select `ISBN`, `Book_Name`, `Author_Name`, `Publisher_Name`, `Date_Of_Publication`, `Price`, `Book_Category_Code`, `Total_Download`" +
				" from `onlinebooksdb`.`books_details` where `ISBN`=?";
		
		BookDetails bookDetails=new BookDetails();
		try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setLong(1, Long.parseLong(isbn));
			ResultSet resultSet=statement.executeQuery();
		
				if(resultSet.next()){
				bookDetails.setIsbn(resultSet.getLong(1));
				bookDetails.setBook_Name(resultSet.getString(2));
				bookDetails.setAuthor_Name(resultSet.getString(3));
				bookDetails.setPublisher_Name(resultSet.getString(4));
				bookDetails.setDate_Of_Publication(resultSet.getDate(5));
				bookDetails.setPrice(resultSet.getInt(6));
				bookDetails.setBook_Category_Code(resultSet.getInt(7));
				bookDetails.setTotal_Download(resultSet.getInt(8));
				}
			
			
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return bookDetails;
	}
	@Override
	public ArrayList<BookDetails> SearchBook(String Field, String Value) {
		ArrayList<BookDetails> list=new ArrayList<BookDetails>();
		String qry="select `ISBN`, `Book_Name`, `Author_Name`, `Publisher_Name`, `Date_Of_Publication`, `Price`, `Book_Category_Code`, `Total_Download`" +
				" from `onlinebooksdb`.`books_details` Where ? = ?";
		System.out.println(qry);
		try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setString(1, Field);
			statement.setString(2, Value );
			ResultSet resultSet=statement.executeQuery();
			
			while(resultSet.next()){
				BookDetails bookDetails=new BookDetails();
				bookDetails.setIsbn(resultSet.getLong(1));
				bookDetails.setBook_Name(resultSet.getString(2));
				bookDetails.setAuthor_Name(resultSet.getString(3));
				bookDetails.setPublisher_Name(resultSet.getString(4));
				bookDetails.setDate_Of_Publication(resultSet.getDate(5));
				bookDetails.setPrice(resultSet.getInt(6));
				bookDetails.setBook_Category_Code(resultSet.getInt(7));
				bookDetails.setTotal_Download(resultSet.getInt(8));
				list.add(bookDetails);
			}
			
		} catch (SQLException e) {
			Logger.getLogger(GreetingServiceImpl.class.getName()).log(Level.SEVERE, null, e);
		}
			
		return list;
	}
	
}
