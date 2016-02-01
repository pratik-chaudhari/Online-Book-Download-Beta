package com.bookdownload.gwt.client;


import java.util.ArrayList;
import Genral.Util.CriteriaForSearch;
import com.bookdownload.gwt.shared.BookDetails;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void AddBook(BookDetails details, AsyncCallback<Boolean> callback) throws IllegalArgumentException;


	void UpdateBook(BookDetails bookDetails, AsyncCallback<Boolean> callback) throws IllegalArgumentException;


	void DeleteBook(BookDetails bookDetails, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void GetAllBook(AsyncCallback<ArrayList<BookDetails>> callback);

	void getBook(String isbn, AsyncCallback<BookDetails> callback);

	void SearchBook(String Field, String Value,
			AsyncCallback<ArrayList<BookDetails>> callback);


	//void SearchBook(CriteriaForSearch criteriaForSearch,AsyncCallback<ArrayList<BookDetails>> callback);
}
