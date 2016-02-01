package OnlineBookWebservice.IntefaceImpl;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bookdownload.gwt.shared.BookDetails;
import com.bookdownload.gwt.shared.DBConnecterUtil;

import Genral.Util.CriteriaForSearch;
import OnlineBookWebservice.Inteface.BookDetailsDAO;

class BookDetailsDAOImpl implements BookDetailsDAO{
	BookDetailsDAOImpl(){
		try {
			con = DBConnecterUtil.getInstance().getConnection();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BookDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(BookDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
	public boolean AddBook(BookDetails bookDetails) {
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
			Logger.getLogger(BookDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
        
		return false;
	}

	@Override
	public boolean UpdateBook(BookDetails bookDetails) {
	    String qry="Update `onlinebooksdb`.`books_details` set  `Book_Name`=?, `Author_Name`=?, `Publisher_Name`=?, `Date_Of_Publication`=?, `Price`=?, `Book_Category_Code`=?, `Total_Download`=?";
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
			Logger.getLogger(BookDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
        
		return false;
	
	}

	@Override
	public Boolean DeleteBook(BookDetails bookDetails) {
		 String qry="DELETE FROM `onlinebooksdb`.`books_details` WHERE `ISBN`=?";
		    try {
				PreparedStatement statement=con.prepareStatement(qry);
				statement.setLong(1, bookDetails.getIsbn());
				return statement.execute();
			} catch (SQLException e) {
				Logger.getLogger(BookDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, e);
			}
	        
			return false;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return list;
	}
	private static Connection con;

}
