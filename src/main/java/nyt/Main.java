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
//		insertIntoAuthorsTable();
		insertIntoSectionsTable();
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
		for (int i = 0; i < prettyJsonString.length(); i++) {
			String publisher = records.getResults().getBooks()[i].getPublisher();
			String name = records.getResults().getBooks()[i].getBuy_links()[0].getName();
			String title = records.getResults().getBooks()[i].getTitle();
			String author = records.getResults().getBooks()[i].getAuthor();
			String list_name = records.getResults().getList_name();
			String published_date_description = records.getResults().getPublished_date_description();
			String published_date = records.getResults().getPublished_date();
			String updated = records.getResults().getUpdated();

			String sqlQueryToInsert = " insert into authors (" + "publisher," + "name, " + "title," + "author,"
					+ "list_name ," + "published_date_description," + "published_date ," + "updated ) Values ('"
					+ publisher + "','" + name + "','" + title + "','" + author + "','" + list_name + "','"
					+ published_date_description + "','" + published_date + "','" + updated + "'" + ")";
			executingOfQurey(sqlQueryToInsert);

		}
	}

	public static void insertIntoSectionsTable() throws IOException, InterruptedException {
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
					+ "','" + pub_date + "','" + document_type + "','" + lead_paragraph + "','" + status + "'" + ")";
			executingOfQurey(sqlQueryToInsert);
		}

	}
}
