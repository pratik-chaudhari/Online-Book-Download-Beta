package OnlineBookWebservice.Model;

import sun.security.util.BigInt;

public class BooksTag {

	public BigInt getIsbn() {
		return isbn;
	}
	public void setIsbn(BigInt isbn) {
		this.isbn = isbn;
	}
	public String getTagName() {
		return TagName;
	}
	public void setTagName(String tagName) {
		TagName = tagName;
	}
	private BigInt isbn;
	private String TagName;
}
