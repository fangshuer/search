package search.pojo;

public class TFIDFObject {
    private Integer id;

    private String keyword;

    private Integer docId;

    private Float tfidf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Float getTfidf() {
        return tfidf;
    }

    public void setTfidf(Float tfidf) {
        this.tfidf = tfidf;
    }
}