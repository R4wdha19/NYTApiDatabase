package nyt;

public class Books {
	private String publisher;
	private String title;
	private String author;
	Buylinks[] buy_links;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Buylinks[] getBuy_links() {
		return buy_links;
	}

	public void setBuy_links(Buylinks[] buy_links) {
		this.buy_links = buy_links;
	}

}
