import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Database {
    Book[] books;
    Language[] languages;
    Countries[] countries;
    Author[] authors;
    EditorialGroup[] editorialGroups;
    PublishingBrand[] publishingBrands;
    PublishingRetailer[] publishingRetailers;

    int sizeBooks = 1;
    int sizeLanguages = 1;
    int sizeCountries = 1;
    int sizeAuthors = 1;
    int sizeEditorialGroup = 1;
    int sizePublishingBrands = 1;
    int sizePublishingRetailer = 1;

    public Book[] readBooks(File fileBooks) throws Exception {
        this.books = new Book[this.sizeBooks];
        int currSizeBooks = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileBooks))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 8);
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                String subtitle = arrOfStr[2];
                String ISBN = arrOfStr[3];
                int pageCount = Integer.parseInt(arrOfStr[4]);
                String keyWord = arrOfStr[5];
                int languageId = Integer.parseInt(arrOfStr[6]);
                String dataStr = arrOfStr[7];
                Date data = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse(dataStr);

                Book book = new Book(id, name, subtitle, ISBN, pageCount, keyWord, languageId, data);

                if (currSizeBooks >= this.sizeBooks) {
                    sizeBooks++;
                    this.books = Arrays.copyOf(books, this.sizeBooks);
                }
                this.books[currSizeBooks++] = book;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
        return this.books;
    }

    public Language[] readLanguages(File fileLanguage) throws Exception {
        this.languages = new Language[sizeLanguages];
        int currSizeLanguages = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileLanguage))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 3);
                int id = Integer.parseInt(arrOfStr[0]);
                String code = arrOfStr[1];
                String name = arrOfStr[2];
                Language language = new Language(id, code, name);

                if (currSizeLanguages >= this.sizeLanguages) {
                    sizeLanguages++;
                    this.languages = Arrays.copyOf(languages, this.sizeLanguages);
                }

                this.languages[currSizeLanguages++] = language;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        }
        catch (IOException e) {
            System.out.println("Eroare IO");
        }
        return this.languages;
    }

    public Countries[] readCountries(File fileCountry) throws Exception {
        this.countries = new Countries[sizeCountries];
        int currSizeCountries = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileCountry))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int id = Integer.parseInt(arrOfStr[0]);
                String code = arrOfStr[1];
                Countries country = new Countries(id, code);

                if (currSizeCountries >= this.sizeCountries) {
                    sizeCountries++;
                    this.countries = Arrays.copyOf(countries, this.sizeCountries);
                }
                this.countries[currSizeCountries++] = country;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
        return this.countries;
    }

    public Author[] readAuthors(File fileAuthors) throws Exception {
        this.authors = new Author[sizeAuthors];
        int currAuthorsSize = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileAuthors))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 3);
                int id = Integer.parseInt(arrOfStr[0]);
                String firstName = arrOfStr[1];
                String lastName = arrOfStr[2];
                Author author = new Author(id, firstName, lastName);

                if (currAuthorsSize >= this.sizeAuthors) {
                    sizeAuthors++;
                    this.authors = Arrays.copyOf(authors, this.sizeAuthors);
                }

                this.authors[currAuthorsSize++] = author;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }  catch (IOException e) {
            System.out.println("Eroare IO");
        }
        return this.authors;
    }

    public EditorialGroup[] readEditorialGroup(File fileEditorialGroup) throws Exception {
        this.editorialGroups = new EditorialGroup[sizeEditorialGroup];
        int currSizeEditorialGroup = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileEditorialGroup))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                EditorialGroup group = new EditorialGroup(id, name);

                if (currSizeEditorialGroup >= this.sizeEditorialGroup) {
                    sizeEditorialGroup++;
                    this.editorialGroups = Arrays.copyOf(editorialGroups, this.sizeEditorialGroup);
                }

                this.editorialGroups[currSizeEditorialGroup++] = group;
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch(IOException e) {
            System.out.println("Eroare IO");
        }
        return this.editorialGroups;
    }

    public PublishingBrand[] readPublishingBrands(File filePublishingBrands) throws Exception {
        this.publishingBrands = new PublishingBrand[sizePublishingBrands];
        int currSizePublishingBrand = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePublishingBrands))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                PublishingBrand brand = new PublishingBrand(id, name);

                if (currSizePublishingBrand >= this.sizePublishingBrands) {
                    sizePublishingBrands++;
                    this.publishingBrands = Arrays.copyOf(publishingBrands, this.sizePublishingBrands);
                }

                this.publishingBrands[currSizePublishingBrand++] = brand;
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch(IOException e) {
            System.out.println("Eroare IO");
        }
        return this.publishingBrands;
    }

    public PublishingRetailer[] readPublishingRetailer(File filePublishingRetailer) throws Exception {
        this.publishingRetailers = new PublishingRetailer[sizePublishingRetailer];
        int currSizePublishingRetailers = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePublishingRetailer))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int id = Integer.parseInt(arrOfStr[0]);
                String name = arrOfStr[1];
                PublishingRetailer retailer = new PublishingRetailer(id, name);

                if (currSizePublishingRetailers >= this.sizePublishingRetailer) {
                    sizePublishingRetailer++;
                    this.publishingRetailers = Arrays.copyOf(publishingRetailers, this.sizePublishingRetailer);
                }

                this.publishingRetailers[currSizePublishingRetailers++] = retailer;
            }
        } catch(FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch(IOException e) {
            System.out.println("Eroare IO");
        }
        return this.publishingRetailers;
    }

    public void readBooksAuthors(File fileReadBooksAuthors) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileReadBooksAuthors))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idBook = Integer.parseInt(arrOfStr[0]);
                int idAuthor = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizeBooks; i++) {
                    if (this.books[i].getID() == idBook) {

                        for (int j = 0; j < sizeAuthors; j++) {
                            if (this.authors[j].getID() == idAuthor) {

                                int ID = this.authors[j].getID();
                                String firstName = this.authors[j].getFirstName();
                                String lastName = this.authors[j].getLastName();
                                Author author = new Author(ID, firstName, lastName);
                                this.books[i].addAuthor(author);

                                break;
                            }
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public void readEditorialGroupsBooks(File fileEditorialGroupsBooks) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileEditorialGroupsBooks))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idEditorialGroup = Integer.parseInt(arrOfStr[0]);
                int idBook = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizeEditorialGroup; i++) {
                    if (this.editorialGroups[i].getID() == idEditorialGroup) {

                        for (int j = 0; j < sizeBooks; j++) {
                            if (this.books[j].getID() == idBook) {
                                int ID = this.books[j].getID();
                                String name = this.books[j].getName();
                                String subtitle = this.books[j].getSubtitle();
                                String ISBN = this.books[j].getISBN();
                                int pageCount = this.books[j].getPageCount();
                                String keywords = this.books[j].getKeywords();
                                int languageId = this.books[j].getLanguageId();
                                Date createdOn = this.books[j].getCreatedOn();
                                Author[] authors = this.books[j].getAuthors();

                                Book book = new Book(ID, name, subtitle, ISBN , pageCount,keywords, languageId,
                                            createdOn, authors);

                                this.editorialGroups[i].addBook(book);
                                break;
                            }
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }

    }

    public void readPublishingBrandsBooks(File filePublishingBrand) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePublishingBrand))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idPublishingBrand = Integer.parseInt(arrOfStr[0]);
                int idBook = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizePublishingBrands; i++) {
                    if (this.publishingBrands[i].getID() == idPublishingBrand) {

                        for (int j = 0; j < sizeBooks; j++) {
                            if (this.books[j].getID() == idBook) {
                                int ID = this.books[j].getID();
                                String name = this.books[j].getName();
                                String subtitle = this.books[j].getSubtitle();
                                String ISBN = this.books[j].getISBN();
                                int pageCount = this.books[j].getPageCount();
                                String keywords = this.books[j].getKeywords();
                                int languageId = this.books[j].getLanguageId();
                                Date createdOn = this.books[j].getCreatedOn();
                                Author[] authors = this.books[j].getAuthors();

                                Book book = new Book(ID, name, subtitle, ISBN , pageCount,keywords,
                                        languageId, createdOn, authors);
                                this.publishingBrands[i].addBook(book);
                                break;
                            }
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public void readPublishRetailerCountries(File filePublishRetailerCountries) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePublishRetailerCountries))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idPublicRetailer = Integer.parseInt(arrOfStr[0]);
                int idCountry = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizePublishingRetailer; i++) {
                    if (this.publishingRetailers[i].getID() == idPublicRetailer) {

                        for (int j = 0; j < sizeCountries; j++) {
                            if (this.countries[j].getID() == idCountry) {

                                int id = this.countries[j].getID();
                                String codeCountry = this.countries[j].getCountryCode();;

                                Countries country = new Countries(id, codeCountry);
                                this.publishingRetailers[i].addCountry(country);
                                break;
                            }
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public void readPublicRetailersBooks(File filePublicRetailersBooks) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePublicRetailersBooks))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idPublicRetailer = Integer.parseInt(arrOfStr[0]);
                int idBook = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizePublishingRetailer; i++) {
                    if (this.publishingRetailers[i].getID() == idPublicRetailer) {

                        for (int j = 0; j < sizeBooks; j++) {
                            if (this.books[j].getID() == idBook) {
                                int ID = this.books[j].getID();
                                String name = this.books[j].getName();
                                String subtitle = this.books[j].getSubtitle();
                                String ISBN = this.books[j].getISBN();
                                int pageCount = this.books[j].getPageCount();
                                String keywords = this.books[j].getKeywords();
                                int languageId = this.books[j].getLanguageId();
                                Date createdOn = this.books[j].getCreatedOn();
                                Author[] authors = this.books[j].getAuthors();

                                Book book = new Book(ID, name, subtitle, ISBN , pageCount,keywords,
                                            languageId, createdOn, authors);

                                this.publishingRetailers[i].addpublishingArtifactsBooks(book);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public void readPublicRetailersEditorialGroups(File filePublicRetailersEditorialGroups) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePublicRetailersEditorialGroups))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idPublicRetailer = Integer.parseInt(arrOfStr[0]);
                int idEditorialGroup = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < this.sizePublishingRetailer; i++) {
                    if (this.publishingRetailers[i].getID() == idPublicRetailer) {

                        for (int j = 0; j < sizeEditorialGroup; j++) {
                            if (this.editorialGroups[j].getID() == idEditorialGroup) {
                                int ID = this.editorialGroups[j].getID();
                                String name = this.editorialGroups[j].getName();
                                Book[] books = this.editorialGroups[j].getBooks();
                                EditorialGroup group = new EditorialGroup(ID, name, books);

                                this.publishingRetailers[i].addpublishingArtifactsEditorial(group);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public void readPublicRetailersPublishingBrands(File filePublicRetailersPublishingBrands) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePublicRetailersPublishingBrands))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] arrOfStr = line.split("###", 2);
                int idPublicRetailer = Integer.parseInt(arrOfStr[0]);
                int idPublishingBrand = Integer.parseInt(arrOfStr[1]);

                for (int i = 0; i < sizePublishingRetailer; i++) {
                    if (publishingBrands[i].getID() == idPublicRetailer) {

                        for (int j = 0; j < sizePublishingBrands; j++) {
                            if (publishingBrands[j].getID() == idPublishingBrand) {
                                int ID = publishingBrands[j].getID();
                                String name = publishingBrands[j].getName();
                                Book[] books = publishingBrands[j].getBooks();
                                PublishingBrand brand = new PublishingBrand(ID, name, books);

                                publishingRetailers[i].addpublishingArtifactsPublishingBrand(brand);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu exista");
        } catch (IOException e) {
            System.out.println("Eroare IO");
        }
    }

    public Book[] getBooks() {
        return books;
    }
    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Language[] getLanguages() {
        return languages;
    }
    public void setLanguages(Language[] languages) {
        this.languages = languages;
    }

    public Countries[] getCountries() {
        return countries;
    }
    public void setCountries(Countries[] countries) {
        this.countries = countries;
    }

    public Author[] getAuthors() {
        return authors;
    }
    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public EditorialGroup[] getEditorialGroups() {
        return editorialGroups;
    }
    public void setEditorialGroups(EditorialGroup[] editorialGroups) {
        this.editorialGroups = editorialGroups;
    }

    public PublishingBrand[] getPublishingBrands() {
        return publishingBrands;
    }
    public void setPublishingBrands(PublishingBrand[] publishingBrands) {
        this.publishingBrands = publishingBrands;
    }

    public PublishingRetailer[] getPublishingRetailers() {
        return publishingRetailers;
    }
    public void setPublishingRetailers(PublishingRetailer[] publishingRetailers) {
        this.publishingRetailers = publishingRetailers;
    }
}
