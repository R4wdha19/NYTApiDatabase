package nyt;

public class BookApiDataInfo {

	private String status;
	private String num_results;
	Results results;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNum_results() {
		return num_results;
	}

	public void setNum_results(String num_results) {
		this.num_results = num_results;
	}

	public Results getResults() {
		return results;
	}

	public void setResults(Results results) {
		this.results = results;
	}

}
