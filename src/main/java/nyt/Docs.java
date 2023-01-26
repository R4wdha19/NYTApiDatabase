package nyt;

public class Docs {

	private String source;
	private String section_name;
	private String subsection_name;
	private String pub_date;
	private String document_type;
	private String lead_paragraph;

	public keyword[] getKeywords() {
		return keywords;
	}

	public void setKeywords(keyword[] keywords) {
		this.keywords = keywords;
	}

	keyword[] keywords;

	public String getLead_paragraph() {
		return lead_paragraph;
	}

	public void setLead_paragraph(String lead_paragraph) {
		this.lead_paragraph = lead_paragraph;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	public String getSubsection_name() {
		return subsection_name;
	}

	public void setSubsection_name(String subsection_name) {
		this.subsection_name = subsection_name;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

}
