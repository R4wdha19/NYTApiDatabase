package nyt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {
	public static final String url = "jdbc:sqlserver://localhost:1433;databaseName=ApiDB;encrypt=true;trustServerCertificate=true";
	public static final String user = "sa";
	public static final String pass = "root";
	public static Connection con;
	static {
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closingConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static HttpClient client = HttpClient.newHttpClient();

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please Choose An Option ! ");
		System.out.println("1 : Insert Into Authors");
		System.out.println("2 : Insert Into Sections ");
		System.out.println("3 : Insert Into Articles ");
		System.out.println("4 : The Top 5 Sections With The Most Articles ");
		System.out.println("5 : Articles Were Written By Each Author ");
		System.out.println("6 : The Top 10 Articles With The Most Views");
		System.out.println("7 : Articles Were Published Each Month In The Year 2021 ");
		System.out.println("8 : Section Had The Most Articles Published On A Particular Day");

		int option = sc.nextInt();

		switch (option) {
		case 1:
			insertIntoAuthorsTable();
			break;
		case 2:
			insertIntoSectionsTable();
			break;
		case 3:

			insertIntoArticlesTable();
			break;
		}
	}

	public static void executingOfQurey(String sql) {

		try {
			Statement st = con.createStatement();
			int executing = st.executeUpdate(sql);
			System.out.println(sql);
			if (executing > 0) {
				System.out.println("Successful : " + sql);
			} else {
				System.out.println("Failed");
			}
		} catch (Exception ex) {

			System.err.println(ex);
		}

	}

	public static void insertIntoAuthorsTable() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=6BTGeQylDDCLiqIs9Z1ffNLyuJKTNNWl"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String responseJsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responseJsonString);
		String prettyJsonString = gson.toJson(je);
		AuthorApiDataInfo records = gson.fromJson(prettyJsonString, AuthorApiDataInfo.class);
		for (int i = 0; i < records.getResults().getBooks().length; i++) {
			String publisher = records.getResults().getBooks()[i].getPublisher();
			String name = records.getResults().getBooks()[i].getBuy_links()[0].getName();
			String title = records.getResults().getBooks()[i].getTitle();
			String author = records.getResults().getBooks()[i].getAuthor();
			String list_name = records.getResults().getList_name();
			String published_date_description = records.getResults().getPublished_date_description();
			String published_date = records.getResults().getPublished_date();
			String updated = records.getResults().getUpdated();
			Integer rank = records.getResults().getBooks()[i].getRank();
			String sqlQueryToInsert = " insert into authors (" + "publisher," + "name, " + "title," + "author,"
					+ "list_name ," + "published_date_description," + "published_date ," + "rank ,"
					+ "updated) Values ('" + publisher + "','" + name + "','" + title + "','" + author + "','"
					+ list_name + "','" + published_date_description + "','" + published_date + "'," + rank + ",'"
					+ updated + "'" + ")";
			executingOfQurey(sqlQueryToInsert);

		}
	}

	public static void insertIntoSectionsTable() throws IOException, InterruptedException {

		System.out.println(" What Is The Name Of The Author ? ");
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=6BTGeQylDDCLiqIs9Z1ffNLyuJKTNNWl"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String responseJsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responseJsonString);
		String prettyJsonString = gson.toJson(je);
		SectionApiDataInfo records = gson.fromJson(prettyJsonString, SectionApiDataInfo.class);
		for (int r = 0; r < records.getResponse().getDocs().length; r++) {
			Integer hits = records.getResponse().getMeta().getHits();
			String source = records.getResponse().getDocs()[r].getSource();
			String section_name = records.getResponse().getDocs()[r].getSection_name();
			String subsection_name = records.getResponse().getDocs()[r].getSubsection_name();
			String pub_date = records.getResponse().getDocs()[r].getPub_date();
			String document_type = records.getResponse().getDocs()[r].getDocument_type();
			String lead_paragraph = records.getResponse().getDocs()[r].getLead_paragraph();
			String status = records.getStatus();

			String sqlQueryToInsert = " insert into Sections (" + "hits," + "source," + "section_name,"
					+ "subsection_name," + "pub_date," + "document_type," + "lead_paragraph" + ","
					+ "status ) Values ('" + hits + "','" + source + "','" + section_name + "','" + subsection_name
					+ "','" + pub_date + "','" + document_type + "','" + lead_paragraph + "','" + status + "'"
					+ ") where ";
			executingOfQurey(sqlQueryToInsert);
		}

	}

	public static void insertIntoArticlesTable() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=6BTGeQylDDCLiqIs9Z1ffNLyuJKTNNWl"))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String responseJsonString = response.body();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(responseJsonString);
		String prettyJsonString = gson.toJson(je);
		SectionApiDataInfo records = gson.fromJson(prettyJsonString, SectionApiDataInfo.class);
		for (int x = 0; x < records.getResponse().getDocs().length; x++) {
			Integer hits = records.getResponse().getMeta().getHits();
			String source = records.getResponse().getDocs()[x].getSource();
			String section_name = records.getResponse().getDocs()[x].getSection_name();
			String subsection_name = records.getResponse().getDocs()[x].getSubsection_name();
			String pub_date = records.getResponse().getDocs()[x].getPub_date();
			String document_type = records.getResponse().getDocs()[x].getDocument_type();
			String lead_paragraph = records.getResponse().getDocs()[x].getLead_paragraph();
			String status = records.getStatus();
			String name = records.getResponse().getDocs()[x].getKeywords()[0].getName();
			String value = records.getResponse().getDocs()[x].getKeywords()[0].getValue();
			Integer rank = records.getResponse().getDocs()[x].getKeywords()[0].getRank();
			String sqlQueryToInsert = " insert into Articles (" + "hits," + "source," + "section_name,"
					+ "subsection_name," + "pub_date," + "document_type," + "lead_paragraph" + "," + "status " + ","
					+ "name," + "value," + "rank) Values ('" + hits + "','" + source + "','" + section_name + "','"
					+ subsection_name + "','" + pub_date + "','" + document_type + "','" + lead_paragraph + "','"
					+ status + "','" + name + "','" + value + "'," + rank + ")";
			executingOfQurey(sqlQueryToInsert);
		}

	}

}
