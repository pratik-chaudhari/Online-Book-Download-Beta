package OnlineBookWebservice.Inteface;

import java.util.ArrayList;

import com.bookdownload.gwt.Model.BookCategory;


public interface BookCategoryDAO {
	public boolean AddBookCategory(BookCategory bookCategory);
	public boolean UpdateBookCategory(BookCategory bookCategory);
	public boolean DeleteBookCategory(BookCategory bookCategory);
	public  ArrayList<BookCategory>  ViewBookCategory();
}
