package search.pojo;

public class DocObject {
	private Integer id;

	private String name;

	private String authors;

	private String docAbstract;

	private String ref;

	private String keywords;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors == null ? null : authors.trim();
	}

	public String getDocAbstract() {
		return docAbstract;
	}

	public void setDocAbstract(String docAbstract) {
		this.docAbstract = docAbstract == null ? null : docAbstract.trim();
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref == null ? null : ref.trim();
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords == null ? null : keywords.trim();
	}
}