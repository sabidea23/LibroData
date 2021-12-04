import java.util.Arrays;
import java.util.Date;

public class Book implements IPublishingArtifact {
    int ID;
    String name;
    String subtitle;
    String ISBN;
    int pageCount;
    String keywords;
    int languageId;
    Date createdOn;
    Author[] authors;

    public Book(int ID, String name, String subtitle, String ISBN, int pageCount, String keywords, int languageId,
                Date createdOn) {
        this.ID = ID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.keywords = keywords;
        this.languageId = languageId;
        this.createdOn = createdOn;
        this.authors = new Author[0];
    }

    public Book(int ID, String name, String subtitle, String ISBN, int pageCount, String keywords, int languageId,
                Date createdOn, Author[] authors) {
        this.ID = ID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.keywords = keywords;
        this.languageId = languageId;
        this.createdOn = createdOn;
        this.authors = authors;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getLanguageId() {
        return languageId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        int numberOfAuthors = this.authors.length;
        this.authors = Arrays.copyOf(authors, numberOfAuthors + 1);
        this.authors[numberOfAuthors] = author;
    }

    @Override
    public String Publish() {
        String title = "\t<title>" + this.name + "</title>\n";
        String subtitle =  "\t<subtitle>" +  this.subtitle + "</subtitle>\n";
        String isbn = "\t<isbn>" + this.ISBN + "</isbn>\n";
        String pageCountBook =  "\t<pageCount>" + this.pageCount + "</pageCount>\n";
        String keywordsBook =  "\t<keywords>" + this.keywords + "</keywords>\n";
        String  languageIDBook =  "\t<languageID>" + this.languageId + "</languageID>\n";
        String createdOnBook =  "\t<createdOn>"  + this.createdOn + "</createdOn>\n";
        String authorsBook =  "\t<authors>";

        for (Author author : authors) {
            String details = author.toString();
            authorsBook = authorsBook.concat(details);
        }

        authorsBook = authorsBook.concat("</authors>\n");
        String publishedBook = "<xml>\n" +  title + subtitle + isbn + pageCountBook + pageCountBook +
                keywordsBook + languageIDBook + createdOnBook + authorsBook + "</xml>\n";

        return publishedBook;
    }
}
