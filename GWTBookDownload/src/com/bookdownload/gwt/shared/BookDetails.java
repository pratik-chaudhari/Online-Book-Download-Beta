package com.bookdownload.gwt.shared;

import java.io.Serializable;
import java.sql.Date;

import sun.security.util.BigInt;

public class BookDetails implements java.io.Serializable {

	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getBook_Name() {
		return book_Name;
	}
	public void setBook_Name(String book_Name) {
		this.book_Name = book_Name;
	}
	public String getAuthor_Name() {
		return author_Name;
	}
	public void setAuthor_Name(String author_Name) {
		this.author_Name = author_Name;
	}
	public String getPublisher_Name() {
		return publisher_Name;
	}
	public void setPublisher_Name(String publisher_Name) {
		this.publisher_Name = publisher_Name;
	}
	public Date getDate_Of_Publication() {
		return Date_Of_Publication;
	}
	public void setDate_Of_Publication(Date date_Of_Publication) {
		Date_Of_Publication = date_Of_Publication;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getBook_Category_Code() {
		return book_Category_Code;
	}
	public void setBook_Category_Code(int book_Category_Code) {
		this.book_Category_Code = book_Category_Code;
	}
	public   int getTotal_Download() {
		return total_Download;
	}
	public  void setTotal_Download( int total_Download) {
		this.total_Download = total_Download;
	}
	private  long isbn;
	private  String book_Name;
	private  String author_Name;
	private  String publisher_Name;
	private  Date Date_Of_Publication;
	private  int price;
	private  int book_Category_Code;
	private  int total_Download;
}
