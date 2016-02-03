package OnlineBookWebservice.Inteface;

import OnlineBookWebservice.Model.BookCategory;

public interface BookCategoryDAO {
	public boolean AddBookCategory(BookCategory bookCategory);
	public boolean UpdateBookCategory(BookCategory bookCategory);
	public boolean DeleteBookCategory(BookCategory bookCategory);
	public boolean ViewBookCategory();
}
