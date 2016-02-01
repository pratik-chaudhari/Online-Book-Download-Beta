package com.bookdownload.gwt.Model;

import sun.security.util.BigInt;

public class BooksTag {

	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTagName() {
		return TagName;
	}
	public void setTagName(String tagName) {
		TagName = tagName;
	}
	private long isbn;
	private String TagName;
}
