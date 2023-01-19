package nyt;

public class Results {

	private String list_name;
	private String published_date;
	private String published_date_description;
	private String updated;

	Books[] books;

	public Books[] getBooks() {
		return books;
	}

	public void setBooks(Books[] books) {
		this.books = books;
	}

	public String getList_name() {
		return list_name;
	}

	public void setList_name(String list_name) {
		this.list_name = list_name;
	}

	public String getPublished_date() {
		return published_date;
	}

	public void setPublished_date(String published_date) {
		this.published_date = published_date;
	}

	public String getPublished_date_description() {
		return published_date_description;
	}

	public void setPublished_date_description(String published_date_description) {
		this.published_date_description = published_date_description;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

}
