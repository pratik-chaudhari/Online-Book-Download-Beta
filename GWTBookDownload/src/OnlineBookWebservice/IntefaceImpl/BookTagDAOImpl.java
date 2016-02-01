package OnlineBookWebservice.IntefaceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bookdownload.gwt.Model.BooksTag;
import com.bookdownload.gwt.shared.DBConnecterUtil;

import OnlineBookWebservice.Inteface.BookTagDAO;

class BookTagDAOImpl implements BookTagDAO {
	BookTagDAOImpl(){
		try {
			con = DBConnecterUtil.getInstance().getConnection();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BookTagDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(BookTagDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	@Override
	public boolean AddBookTag(BooksTag booksTag) {
	    String qry="insert into `onlinebooksdb`.`books_tag` (`ISBN`, `TagName`) values (?, ?)";
	    try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setLong(1, booksTag.getIsbn());
			statement.setString(2, booksTag.getTagName());
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateBookTag(BooksTag booksTag) {
		String qry="Update `onlinebooksdb`.`books_tag`  set `ISBN`=?, `TagName`=?  where `ISBN`=?, `TagName`=?";
	    try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setLong(1, booksTag.getIsbn());
			statement.setString(2, booksTag.getTagName());
			statement.setLong(3, booksTag.getIsbn());
			statement.setString(4, booksTag.getTagName());
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean DeteleBookTag(BooksTag booksTag) {
        String qry="DELETE FROM `onlinebooksdb`.`books_tag` WHERE `ISBN`=?, `TagName`=? ";
	    try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setLong(1, booksTag.getIsbn());
			statement.setString(2, booksTag.getTagName());
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<BooksTag> GetBookTag(long isbn) {
		ArrayList<BooksTag> booksTaList=new ArrayList<BooksTag>();
		String qry="select * from  `onlinebooksdb`.`books_tag`  WHERE `ISBN`= ?)";
	    try {
			PreparedStatement statement=con.prepareStatement(qry);
			statement.setLong(1, isbn);
			ResultSet resultSet=statement.getResultSet();
			while(resultSet.next()){
				BooksTag booksTag=new BooksTag();
				booksTag.setIsbn(resultSet.getLong(1));
				booksTag.setTagName(resultSet.getString(2));
				booksTaList.add(booksTag);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksTaList;
	}
	private static Connection con;
}
