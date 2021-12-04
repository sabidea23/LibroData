import java.util.Arrays;

public class EditorialGroup implements IPublishingArtifact{
    int ID;
    String name;
    Book[] books;

    public EditorialGroup(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.books = new Book[0];
    }

    public EditorialGroup(int ID, String name, Book[] books) {
        this.ID = ID;
        this.name = name;
        this.books = books;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public void addBook(Book book) {
        int numberOfBooks = this.books.length;
        this.books = Arrays.copyOf(books, numberOfBooks + 1);
        this.books[numberOfBooks] = book;
    }

    @Override
    public String Publish() {
        String publishedBook = "<xml>\n" + "\t<editorialGroup>\n";
        String idEditorial =  "\t\t<ID>" + this.ID +  "</ID>\n";
        String name =  "\t\t<Name>" + this.name + "</Name>\n";

        publishedBook = publishedBook.concat(idEditorial + name + "\t</editorialGroup>\n");
        publishedBook = publishedBook.concat("\t<books>\n");

        for (Book book : books) {
            publishedBook = publishedBook.concat("\t\t<book>\n");

            String title = "\t\t\t<title>" + book.name + "</title>\n";
            String subtitle = "\t\t\t<subtitle>" + book.subtitle + "</subtitle>\n";
            String isbn = "\t\t\t<isbn>" + book.subtitle + "</isbn>\n";
            String pageCountBook = "\t\t\t<pageCount>" + book.pageCount + "</pageCount>\n";
            String keywordsBook = "\t\t\t<keywords>" + book.keywords + "</keywords>\n";
            String languageIDBook = "\t\t\t<languageID>" + book.languageId + "</languageID>\n";
            String createdOnBook = "\t\t\t<createdOn>" + book.createdOn + "</createdOn>\n";
            String authorsBook = "\t\t\t<authors>";

            for (Author author : book.authors) {
                String details = author.toString();
                authorsBook = authorsBook.concat(details);
            }
            authorsBook = authorsBook.concat("</authors>\n");

            publishedBook = publishedBook.concat(title + subtitle + isbn + pageCountBook +
                    pageCountBook + keywordsBook + languageIDBook + createdOnBook + authorsBook);
            publishedBook = publishedBook.concat("\t\t</book>\n");
        }

        publishedBook = publishedBook.concat("\t</books>\n" + "</xml>\n");
        return publishedBook;
    }

    @Override
    public String toString() {
        return "EditorialGroup{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", books=" + Arrays.toString(books) +
                '}';
    }
}
