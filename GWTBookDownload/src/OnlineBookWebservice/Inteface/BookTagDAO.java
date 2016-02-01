package OnlineBookWebservice.Inteface;

import java.util.ArrayList;

import com.bookdownload.gwt.Model.BooksTag;

import sun.security.util.BigInt;


public interface BookTagDAO {
	public boolean AddBookTag(BooksTag booksTag);
	public boolean UpdateBookTag(BooksTag booksTag);
	public boolean DeteleBookTag(BooksTag booksTag);
	public ArrayList<BooksTag> GetBookTag(long isbn);
}
