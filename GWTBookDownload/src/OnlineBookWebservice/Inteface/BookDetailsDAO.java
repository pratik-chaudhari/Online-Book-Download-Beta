package OnlineBookWebservice.Inteface;

import java.util.ArrayList;

import com.bookdownload.gwt.shared.BookDetails;

import Genral.Util.CriteriaForSearch;

public interface BookDetailsDAO {
	public boolean AddBook(BookDetails bookDetails);
	public boolean UpdateBook(BookDetails bookDetails);
	public Boolean DeleteBook(BookDetails bookDetails);
	public ArrayList<BookDetails> SearchBook(CriteriaForSearch criteriaForSearch);
}
