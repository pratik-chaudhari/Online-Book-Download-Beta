package com.bookdownload.gwt.client;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.ArrayList;

import Genral.Util.CriteriaForSearch;

import com.bookdownload.gwt.shared.BookDetails;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	 boolean AddBook(BookDetails bookDetails) throws IllegalArgumentException;
	 boolean UpdateBook(BookDetails bookDetails) throws IllegalArgumentException;
	 boolean DeleteBook(BookDetails bookDetails) throws IllegalArgumentException;
	ArrayList<BookDetails> SearchBook(String Field,String Value);
	 ArrayList<BookDetails> GetAllBook( )throws IllegalArgumentException;
	 BookDetails getBook(String isbn)throws IllegalArgumentException;
}
