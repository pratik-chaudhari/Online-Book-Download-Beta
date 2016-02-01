package com.bookdownload.gwt.client;

//import java.sql.Date;
import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.bookdownload.gwt.server.GreetingServiceImpl;
import com.bookdownload.gwt.shared.BookDetails;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("deprecation")
public class GWTBookDownload implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	// protected static final String DateTimeFormat = null;

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final String USER_AGENT = "Mozilla/5.0";

	// final Button btn_submit = new Button("Submit");
	// final TextBox txt_author_name = new TextBox();
	final DialogBox box = new DialogBox();
	final TextBox txt_author = new TextBox();
	final TextBox txt_book_name = new TextBox();
	final TextBox txt_publisher_name = new TextBox();
	final Button btn_date_picker = new Button("SET DATE");
	final DatePicker date_picker = new DatePicker();
	final Label txt_date_picker = new Label();
	final ListBox list_book_category = new ListBox();
	final Button closeButton = new Button("Close");
	final ListBox update_list_isbn = new ListBox();
	final ListBox update_list_book_category = new ListBox();
	final Button update_btn_book_reset = new Button("Reset");
	final Button update_btn_book_submit = new Button("Submit");
	final TextBox search_txt_keyword = new TextBox();
	final ListBox search_list_criteria = new ListBox();
	final Button search_btn_submit = new Button("Search");
	final Button btn_book_reset = new Button("Reset");
	// btn_book_submit
	final Button btn_book_submit = new Button("Submit");
	final TextBox update_txt_author = new TextBox();
	// final TextBox update_txt_isbn = new TextBox();
	final TextBox update_txt_book_name = new TextBox();
	final TextBox update_txt_publisher_name = new TextBox();
	final DatePicker update_date_picker = new DatePicker();
	// btn_update_refresh_list
	final Button btn_update_refresh_list = new Button("Refresh List");

	final ArrayList<BookDetails> booklist = new ArrayList<BookDetails>();

	@Override
	public void onModuleLoad() {

		// BOOKS TAB ADDING

		// adding llist_author
		// adding txt_isbn

		RootPanel.get("txt_author").add(txt_author);
		RootPanel.get("txt_book_name").add(txt_book_name);
		RootPanel.get("txt_publisher_name").add(txt_publisher_name);

		// adding date_picker
		// ============================================
		// ADDING date and ITS CHANGED VALUE HANDLER
		// ============================================

		RootPanel.get("date_picker").add(date_picker);

		btn_date_picker.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				date_picker.setVisible(true);
			}
		});

		// Set the value in the text box when the user selects a date
		date_picker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {

				Date date = event.getValue();

				DateTimeFormat dateTimeFormat = DateTimeFormat
						.getFormat("dd-MMM-yyyy");
				String strFormat = dateTimeFormat.format(date);

				txt_date_picker.setText(strFormat);

			}
		});

		// load isbn
		greetingService.GetAllBook(new AsyncCallback<ArrayList<BookDetails>>() {

			@Override
			public void onSuccess(ArrayList<BookDetails> result) {
				// TODO Auto-generated method stub
				System.out.println("\n\n\n");
				System.out.println("adding to booklist");
				for (BookDetails bookDetails : result) {
					update_list_isbn.addItem("" + bookDetails.getIsbn());
					System.out.println("booklist"
							+ bookDetails.getAuthor_Name());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);

			}
		});

		// Set the default value
		date_picker.setValue(new Date(), true);

		// Add the widgets to the page
		RootPanel.get("date_picker").add(txt_date_picker);
		RootPanel.get("date_picker").add(date_picker);
		RootPanel.get("date_picker").add(btn_date_picker);

		// ============================================
		// COMPLETED date and ITS CHANGED VALUE HANDLER
		// ============================================

		// adding list_book_category

		list_book_category.addItem("1. Business, Investing and Management ");
		list_book_category.addItem("2. College Text & Reference");
		list_book_category.addItem("3. Comics & Graphic Novels");
		list_book_category.setVisibleItemCount(3);
		RootPanel.get("list_book_category").add(list_book_category);

		RootPanel.get("btn_book_reset").add(btn_book_reset);

		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setTitle("Add Book Message");
		dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		box.setWidget(dialogVPanel);

		RootPanel.get("btn_book_submit").add(btn_book_submit);
		btn_book_submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				BookDetails bookDetails = new BookDetails();
				bookDetails.setAuthor_Name(txt_author.getValue());
				bookDetails.setBook_Name(txt_book_name.getValue());
				bookDetails.setDate_Of_Publication(new java.sql.Date(
						new java.util.Date().getTime()));
				bookDetails.setPrice(1000);
				bookDetails.setBook_Category_Code(list_book_category
						.getSelectedIndex());
				bookDetails.setPublisher_Name(txt_publisher_name.getValue());
				bookDetails.setTotal_Download(10);
				System.out.println("\n\n\n\n\n\n");

				greetingService.AddBook(bookDetails,
						new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								box.setText(" FAIL");
								box.setAnimationEnabled(true);
								box.center();
								box.setVisible(true);
							}

							public void onSuccess(Boolean result) {
								box.setText(result.toString());
								box.setAnimationEnabled(true);
								box.center();
								box.setVisible(true);
								// can reset the list of books here
							}
						});

			}
		});

		btn_book_reset.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				reset_add();
			}
		});

		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				box.hide();
			}
		});

		// ============================================
		// ============================================
		// UPDATE
		// ============================================
		// ============================================

		// BY SELECTING ISBN FROM THE LIST WE CAN EDIT THE BOOK

		RootPanel.get("btn_update_refresh_list").add(btn_update_refresh_list);
		// update_list_isbn

		btn_update_refresh_list.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				/*
				 * if (booklist.isEmpty() == false) { String str = (new
				 * Long((booklist.get(0)).getIsbn())) .toString();
				 * load_updatetab(str); } else { reset_list();
				 * update_list_isbn.clear(); Long temp_long = new Long(1); for
				 * (BookDetails bookdetail : booklist) { temp_long =
				 * bookdetail.getIsbn();
				 * update_list_isbn.addItem(temp_long.toString()); }
				 * 
				 * }
				 */

				greetingService
						.GetAllBook(new AsyncCallback<ArrayList<BookDetails>>() {

							@Override
							public void onSuccess(ArrayList<BookDetails> result) {
								// TODO Auto-generated method stub
								update_list_isbn.clear();
								System.out.println("\n\n\n");
								System.out.println("adding to booklist");
								for (BookDetails bookDetails : result) {
									update_list_isbn.addItem(""
											+ bookDetails.getIsbn());
									System.out.println("booklist"
											+ bookDetails.getAuthor_Name());
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								box.setText(" FAIL");
								box.setAnimationEnabled(true);
								box.setHTML("<br>Update Not Successful<br>");
								box.center();
								box.setVisible(true);
							}
						});
			}
		});

		RootPanel.get("update_list_isbn").add(update_list_isbn);
		RootPanel.get("update_txt_author").add(update_txt_author);
		RootPanel.get("update_txt_book_name").add(update_txt_book_name);
		RootPanel.get("update_txt_publisher_name").add(
				update_txt_publisher_name);

		RootPanel.get("update_date_picker").add(update_date_picker);

		// update_list_book_category.addItem("Sci Fi");
		// update_list_book_category.addItem("Spiritual");
		update_list_book_category.setVisibleItemCount(3);
		RootPanel.get("update_list_book_category").add(
				update_list_book_category);

		RootPanel.get("update_btn_book_reset").add(update_btn_book_reset);

		RootPanel.get("update_btn_book_submit").add(update_btn_book_submit);

		update_list_isbn.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				greetingService.getBook(update_list_isbn
						.getValue(update_list_isbn.getSelectedIndex()),
						new AsyncCallback<BookDetails>() {

							@Override
							public void onSuccess(BookDetails result) {
								update_txt_author.setText(result
										.getAuthor_Name());
								update_txt_publisher_name.setText(result
										.getPublisher_Name());
								update_txt_book_name.setText(result
										.getBook_Name());
								update_date_picker.setValue(
										result.getDate_Of_Publication(), false);
								update_list_book_category
										.addItem("1 Business, Investing and Management ");
								update_list_book_category
										.addItem("2 College Text & Reference");
								update_list_book_category
										.addItem("3 Comics & Graphic Novels");
								update_list_book_category
										.setSelectedIndex(result
												.getBook_Category_Code());
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								txt_author.setText("fail");

							}
						});
				String selected_item = event.toString();
				load_updatetab(selected_item);

			}
		});

		update_btn_book_submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				BookDetails bookDetails = new BookDetails();
				bookDetails.setIsbn(Long.parseLong(update_list_isbn
						.getItemText(update_list_isbn.getSelectedIndex())));
				bookDetails.setAuthor_Name(update_txt_author.getValue());
				bookDetails.setBook_Name(update_txt_book_name.getValue());
				bookDetails.setDate_Of_Publication(new java.sql.Date(
						new java.util.Date().getTime()));
				bookDetails.setPrice(1000);
				bookDetails.setBook_Category_Code(update_list_book_category
						.getSelectedIndex());
				bookDetails.setPublisher_Name(update_txt_publisher_name
						.getValue());
				bookDetails.setTotal_Download(10);

				System.out.println("\n\n\n\n\n\n");

				greetingService.UpdateBook(bookDetails,
						new AsyncCallback<Boolean>() {
							public void onFailure(Throwable caught) {
								box.setText(" FAIL");
								box.setAnimationEnabled(true);
								box.center();
								box.setVisible(true);
							}

							public void onSuccess(Boolean result) {
								box.setText(result.toString());
								box.setAnimationEnabled(true);
								box.center();
								box.setVisible(true);
								// can reset the list of books here
							}
						});

			}
		});

		// =============================
		// SEARCH FUNCTIONALITY
		// =============================

		RootPanel.get("search_txt_keyword").add(search_txt_keyword);
		RootPanel.get("search_list_criteria").add(search_list_criteria);
		RootPanel.get("search_btn_submit").add(search_btn_submit);

		search_list_criteria.addItem("ISBN");
		search_list_criteria.addItem("Book_Name");
		search_list_criteria.addItem("Author_Name");
		search_list_criteria.addItem("Publisher_Name");
		// search_list_criteria.addItem("Date_Of_Publication");

		search_btn_submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String field = search_list_criteria
						.getItemText(search_list_criteria.getSelectedIndex());
				

				greetingService.SearchBook(field, search_txt_keyword.getValue(),
						new AsyncCallback<ArrayList<BookDetails>>() {

							@Override
							public void onSuccess(ArrayList<BookDetails> result) {

								RootPanel.get("search_grid").clear();
								
								
								
								for (BookDetails bookDetails : result) {
									Grid grid=new Grid(1,6);
									//grid.setWidget(2, 2, new Button("Button"));

								    // You can use the CellFormatter to affect the layout of the grid's cells.
								    //grid.getCellFormatter().setWidth(0, 2, "256px");
								    
									Long ln=bookDetails.getIsbn();
									grid.setText(0,0 ,("   ,"+ ln.toString() ));
									grid.setText(0,1 ,("   ,"+ bookDetails.getBook_Name()));
									grid.setText(0,2 ,("   ,"+ bookDetails.getAuthor_Name()));
									grid.setText(0,3 ,("   ,"+ bookDetails.getPublisher_Name()));
									
									Integer in=bookDetails.getBook_Category_Code();
									grid.setText(0,4 , ("  ,"+in.toString()));
									Date dt= bookDetails.getDate_Of_Publication();
									grid.setText(0,5 ,("   ,"+ dt.toString()));
									
									RootPanel.get("search_grid").add(grid);
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								System.out
										.println("The search was not properly made");
							}
						});

			}
		});

		// <td id="search_grid"></td>
		// get the list through query..
		// see the number of elements in the list. and set the grid accordingly
		// run the for loop to add the element to the grid
		// /add the grid to the root panel"search_btn_submit"
	}

	public void reset_list() {
		// Reload the update side with array

		booklist.clear();

		greetingService.GetAllBook(new AsyncCallback<ArrayList<BookDetails>>() {

			@Override
			public void onSuccess(ArrayList<BookDetails> result) {
				// TODO Auto-generated method stub
				System.out.println("\n\n\n");
				System.out.println("adding to booklist");
				for (BookDetails bookDetails : result) {
					booklist.add(bookDetails);
					System.out.println("booklist"
							+ bookDetails.getAuthor_Name());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void load_updatetab(String isbn) {
		// get the selected bookdetail and load it
		// getting the details
		reset_list();
		BookDetails book = new BookDetails();

		for (BookDetails bookdetail : booklist) {
			if (isbn.equals(bookdetail.getIsbn())) {
				book = bookdetail;
			}
		}

		update_txt_author.setText(book.getAuthor_Name());
		update_txt_book_name.setText(book.getBook_Name());
		update_txt_publisher_name.setText(book.getPublisher_Name());
		update_date_picker.setValue(book.getDate_Of_Publication(), false);

		// check the list and set the selected index to that count.
		int count_index = 0;

		list_book_category.setSelectedIndex(book.getBook_Category_Code());

	}

	public void reset_update() {
		update_list_isbn.clear();
		update_list_book_category.clear();
		update_list_book_category
				.addItem("1 Business, Investing and Management ");
		update_list_book_category.addItem("2 College Text & Reference");
		update_list_book_category.addItem("3 Comics & Graphic Novels");
	}

	public void reset_add() {
		// remove the values in add field
		txt_author.setText("");
		txt_book_name.setText("");
	}

}
