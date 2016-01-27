package OnlineBookWebservice.Inteface;

import java.util.ArrayList;

import sun.security.util.BigInt;

import OnlineBookWebservice.Model.BooksTag;

public interface BookTagDAO {
	public boolean AddBookTag(BooksTag booksTag);
	public boolean UpdateBookTag(BooksTag booksTag);
	public boolean DeteleBookTag(BooksTag booksTag);
	public ArrayList<BooksTag> GetBookTag(BigInt isbn);
}
