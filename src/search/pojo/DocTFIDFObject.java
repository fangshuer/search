package search.pojo;

public class DocTFIDFObject implements Comparable<DocTFIDFObject> {
	private Integer id;
	private Float tfidf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getTfidf() {
		return tfidf;
	}

	public void setTfidf(Float tfidf) {
		this.tfidf = tfidf;
	}

	public int compareTo(DocTFIDFObject o) {
		// TODO Auto-generated method stub
		return this.getTfidf().compareTo(o.getTfidf());
	}

}
