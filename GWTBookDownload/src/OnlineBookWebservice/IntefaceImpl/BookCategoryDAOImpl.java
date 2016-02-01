package OnlineBookWebservice.IntefaceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bookdownload.gwt.Model.BookCategory;
import com.bookdownload.gwt.shared.DBConnecterUtil;

import OnlineBookWebservice.Inteface.BookCategoryDAO;

class BookCategoryDAOImpl implements BookCategoryDAO{
	BookCategoryDAOImpl(){
		try {
			con = DBConnecterUtil.getInstance().getConnection();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BookCategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(BookCategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
	public boolean AddBookCategory(BookCategory bookCategory) {
		String qry="INSERT INTO `onlinebooksdb`.`book_categories` (`Book_Category_Code`, `Description`) VALUES (NULL, ?)";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(qry);
			statement.setString(1, bookCategory.getDescription());
			return statement.execute();
		} catch (SQLException e) {
			Logger.getLogger(BookCategoryDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}


		return false;
	}

	@Override
	public boolean UpdateBookCategory(BookCategory bookCategory) {
		String qry="Update `onlinebooksdb`.`book_categories` set   `Description`=?  where `Book_Category_Code`=?";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(qry);
			statement.setString(1, bookCategory.getDescription());
			statement.setInt(2, bookCategory.getBook_Category_Code());
			return statement.execute();
		} catch (SQLException e) {
			Logger.getLogger(BookCategoryDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}


		return false;
	}

	@Override
	public boolean DeleteBookCategory(BookCategory bookCategory) {
		String qry="DELETE FROM `onlinebooksdb`.`book_categories` where `Book_Category_Code`=?";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(qry);
			statement.setInt(1, bookCategory.getBook_Category_Code());
			return statement.execute();
		} catch (SQLException e) {
			Logger.getLogger(BookCategoryDAOImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
		}

	@Override
	public  ArrayList<BookCategory> ViewBookCategory() {
		
		return null;
	}
	private static Connection con;
}
