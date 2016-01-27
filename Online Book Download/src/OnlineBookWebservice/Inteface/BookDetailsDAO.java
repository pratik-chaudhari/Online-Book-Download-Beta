package OnlineBookWebservice.Inteface;

import java.util.ArrayList;
import Genral.Util.CriteriaForSearch;
import OnlineBookWebservice.Model.BookDetails;

public interface BookDetailsDAO {
	public boolean AddBook(BookDetails bookDetails);
	public boolean UpdateBook(BookDetails bookDetails);
	public Boolean DeleteBook(BookDetails bookDetails);
	public ArrayList<BookDetails> SearchBook(CriteriaForSearch criteriaForSearch);
}
