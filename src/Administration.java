import java.io.File;
import java.util.*;

public class Administration  {
    Database dataBase;

    public Database databaseInitialization() throws Exception {
        Database dataBase = new Database();

        File fileBooks = new File("init\\books.in");
        dataBase.setBooks(dataBase.readBooks(fileBooks));

        File fileLanguage = new File("init\\languages.in");
        dataBase.setLanguages(dataBase.readLanguages(fileLanguage));

        File fileCountry = new File("init\\countries.in");
        dataBase.setCountries(dataBase.readCountries(fileCountry));

        File fileAuthors = new File("init\\authors.in");
        dataBase.setAuthors(dataBase.readAuthors(fileAuthors));

        File fileEditorialGroup = new File("init\\editorial-groups.in");
        dataBase.setEditorialGroups(dataBase.readEditorialGroup(fileEditorialGroup));

        File filePublishingBrands = new File("init\\publishing-brands.in");
        dataBase.setPublishingBrands(dataBase.readPublishingBrands(filePublishingBrands));

        File filePublishingRetailer = new File("init\\publishing-retailers.in");
        dataBase.setPublishingRetailers(dataBase.readPublishingRetailer(filePublishingRetailer));

        File fileReadBooksAuthors = new File("init\\books-authors.in");
        dataBase.readBooksAuthors(fileReadBooksAuthors);

        File fileEditorialGroupsBooks = new File("init\\editorial-groups-books.in");
        dataBase.readEditorialGroupsBooks(fileEditorialGroupsBooks);

        File filePublishingBrandsBooks = new File("init\\publishing-brands-books.in");
        dataBase.readPublishingBrandsBooks(filePublishingBrandsBooks);

        File filePublishRetailerCountries = new File("init\\publishing-retailers-countries.in");
        dataBase.readPublishRetailerCountries(filePublishRetailerCountries);

        File filePublicRetailersBooks = new File("init\\publishing-retailers-books.in");
        dataBase.readPublicRetailersBooks(filePublicRetailersBooks);

        File filePublicRetailersEditorialGroups = new File("init\\publishing-retailers-editorial-groups.in");
        dataBase.readPublicRetailersEditorialGroups(filePublicRetailersEditorialGroups);

        File filePublicRetailersPublishingBrands = new File("init\\publishing-retailers-publishing-brands.in");
        dataBase.readPublicRetailersPublishingBrands(filePublicRetailersPublishingBrands);

        return dataBase;
    }

    public Database getDataBase() {
        return dataBase;
    }

    public void setDataBase(Database dataBase) {
        this.dataBase = dataBase;
    }

    public boolean searchSameBook(Book[] booksForPublishingRetailer, IPublishingArtifact publishingArtifact) {
        for (Book book : booksForPublishingRetailer) {
            if (publishingArtifact instanceof Book) {
                if (book.name.equals(((Book) publishingArtifact).name))
                    return false;
            }
            if (publishingArtifact instanceof EditorialGroup) {
                if (book.name.equals(((EditorialGroup) publishingArtifact).books[0].name))
                    return false;
            } else if (publishingArtifact instanceof PublishingBrand) {
                if (book.name.equals(((PublishingBrand) publishingArtifact).books[0].name))
                    return false;
            }
        }
        return true;
    }

    public Book[] getBooksForPublishingRetailerID(int publishingRetailerID) {
        Book[] booksForPublishingRetailer= new Book[0];
        int numberOfBooksForPublishingRetailer = 0;

        for (int i = 0; i < dataBase.sizePublishingRetailer; i++) {
            if (dataBase.publishingRetailers[i].ID == publishingRetailerID) {
                for (int j = 0; j < dataBase.publishingRetailers[i].publishingArtifacts.length; j++) {
                    if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof Book) {
                        if (searchSameBook(booksForPublishingRetailer,
                                                    dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            booksForPublishingRetailer = Arrays.copyOf(booksForPublishingRetailer,
                                    numberOfBooksForPublishingRetailer + 1);
                            booksForPublishingRetailer[numberOfBooksForPublishingRetailer++] =
                                    (Book) dataBase.publishingRetailers[i].publishingArtifacts[j];
                        }
                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof EditorialGroup) {
                        if (searchSameBook(booksForPublishingRetailer,
                                                        dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            booksForPublishingRetailer = Arrays.copyOf(booksForPublishingRetailer ,
                                            numberOfBooksForPublishingRetailer + 1);
                            booksForPublishingRetailer[numberOfBooksForPublishingRetailer++] = (Book) ((EditorialGroup)
                                            dataBase.publishingRetailers[i].publishingArtifacts[j]).books[0];
                        }
                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof PublishingBrand) {
                        if (searchSameBook(booksForPublishingRetailer,
                                                            dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            booksForPublishingRetailer = Arrays.copyOf(booksForPublishingRetailer ,
                                            numberOfBooksForPublishingRetailer + 1);
                            booksForPublishingRetailer[numberOfBooksForPublishingRetailer++] = (Book) ((PublishingBrand)
                                             dataBase.publishingRetailers[i].publishingArtifacts[j]).books[0];
                        }
                    }
                }
                break;
            }
        }
        return booksForPublishingRetailer;
    }

    public Language getLanguageFromBook(Book book) {
        for (int i = 0; i < dataBase.sizeLanguages; i++) {
            if (book.languageId == dataBase.languages[i].ID)
                return dataBase.languages[i];
        }
        return null;
    }

    public boolean searchSameLanguage(Language[] languagesForPublishRetailer,
                                      IPublishingArtifact publishingArtifact) {
        for (Language language : languagesForPublishRetailer) {
            if (publishingArtifact instanceof  Book) {
                if (language.ID == ((Book) publishingArtifact).languageId)
                    return false;

            } else if (publishingArtifact instanceof EditorialGroup) {
                if (language.ID == ((EditorialGroup) publishingArtifact).books[0].languageId)
                    return false;

            } else if (publishingArtifact instanceof PublishingBrand) {
                if (language.ID == ((PublishingBrand) publishingArtifact).books[0].languageId)
                    return false;
            }
        }
        return true;
    }

    public Language[] getLanguagesForPublishingRetailerID(int publishingRetailerID) {
        Language[] languagesForPublishRetailer = new Language[0];
        int numberOflanguagesForPublishRetailer = 0;

        for (int i = 0; i < dataBase.sizePublishingRetailer; i++) {
            if (dataBase.publishingRetailers[i].ID == publishingRetailerID) {
                for (int j = 0; j < dataBase.publishingRetailers[i].publishingArtifacts.length; j++) {
                    if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof Book) {
                        if (searchSameLanguage(languagesForPublishRetailer,
                                                            dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            languagesForPublishRetailer = Arrays.copyOf(languagesForPublishRetailer,
                                                    numberOflanguagesForPublishRetailer + 1);
                            Book bookFromPublishRetailer = (Book) dataBase.publishingRetailers[i].publishingArtifacts[j];
                            languagesForPublishRetailer[numberOflanguagesForPublishRetailer++] =
                                                            getLanguageFromBook(bookFromPublishRetailer);
                        }
                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof EditorialGroup) {
                        if (searchSameLanguage(languagesForPublishRetailer,
                                                            dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            languagesForPublishRetailer = Arrays.copyOf(languagesForPublishRetailer ,
                                                    numberOflanguagesForPublishRetailer + 1);
                            EditorialGroup group =
                                                (EditorialGroup) dataBase.publishingRetailers[i].publishingArtifacts[j];
                            Book bookFromPublishRetailer = group.books[0];
                            languagesForPublishRetailer[numberOflanguagesForPublishRetailer++] =
                                                        getLanguageFromBook(bookFromPublishRetailer);
                        }

                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof PublishingBrand) {
                        if (searchSameLanguage(languagesForPublishRetailer,
                                                            dataBase.publishingRetailers[i].publishingArtifacts[j])) {
                            languagesForPublishRetailer = Arrays.copyOf(languagesForPublishRetailer ,
                                                        numberOflanguagesForPublishRetailer + 1);
                            PublishingBrand brand =
                                                (PublishingBrand) dataBase.publishingRetailers[i].publishingArtifacts[j];
                            Book bookFromPublishRetailer = brand.books[0];
                            languagesForPublishRetailer[numberOflanguagesForPublishRetailer++] =
                                                    getLanguageFromBook(bookFromPublishRetailer);
                        }
                    }
                }
                break;
            }
        }
        return languagesForPublishRetailer;
    }

    public boolean searchSameCountry(Countries[] countriesForPublishRetailer,
                                      PublishingRetailer publishingRetailer) {
        for (Countries country : countriesForPublishRetailer) {
            if (publishingRetailer.countries[0].ID == country.ID)
                return false;
        }
        return true;
    }

    public Countries[] getCountriesForBookID(int bookID) {
        Countries []countriesForForBookID = new Countries[0];
        int numberOfcountriesForForBookID = 0;
        for (int i = 0; i < dataBase.sizePublishingRetailer; i++) {
            for (int j = 0; j < dataBase.publishingRetailers[i].publishingArtifacts.length; j++) {
                if (searchSameCountry(countriesForForBookID, dataBase.publishingRetailers[i])) {
                    if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof Book book) {
                        if (book.getID() == bookID) {
                            countriesForForBookID = Arrays.copyOf(countriesForForBookID,
                                    numberOfcountriesForForBookID + 1);
                            countriesForForBookID[numberOfcountriesForForBookID++] =
                                    dataBase.publishingRetailers[i].countries[0];
                        }
                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof EditorialGroup group) {
                        Book book = group.books[0];
                        if (book.getID() == bookID) {
                            countriesForForBookID = Arrays.copyOf(countriesForForBookID,
                                    numberOfcountriesForForBookID + 1);
                            countriesForForBookID[numberOfcountriesForForBookID++] =
                                    dataBase.publishingRetailers[i].countries[0];
                        }
                    } else if (dataBase.publishingRetailers[i].publishingArtifacts[j] instanceof PublishingBrand brand) {
                        Book book = brand.books[0];
                        if (book.getID() == bookID) {
                            countriesForForBookID = Arrays.copyOf(countriesForForBookID,
                                    numberOfcountriesForForBookID + 1);
                            countriesForForBookID[numberOfcountriesForForBookID++] =
                                    dataBase.publishingRetailers[i].countries[0];
                        }
                    }
                }
            }
        }
        return countriesForForBookID;
    }

    public boolean findSameBook(IPublishingArtifact artifact1, IPublishingArtifact artifact2) {
        if (artifact1 instanceof Book) {
            if (artifact2 instanceof Book) {
                return ((Book) artifact1).name.equals(((Book) artifact2).name);
            } else if (artifact2 instanceof EditorialGroup) {
                return  ((Book) artifact1).name.equals(((EditorialGroup) artifact2).books[0].name);
             } else if (artifact2 instanceof PublishingBrand) {
                return ((Book) artifact1).name.equals(((PublishingBrand) artifact2).books[0].name);
            }

        } else if (artifact1 instanceof EditorialGroup) {
            if (artifact2 instanceof Book) {
                return ((EditorialGroup) artifact1).books[0].name.equals(((Book) artifact2).name);
            } else if (artifact2 instanceof EditorialGroup) {
                return  ((EditorialGroup) artifact1).books[0].name.equals(((EditorialGroup) artifact2).books[0].name);
            } else if (artifact2 instanceof PublishingBrand) {
                return  ((EditorialGroup) artifact1).books[0].name.equals(((PublishingBrand) artifact2).books[0].name);
            }

        } else if (artifact1 instanceof PublishingBrand) {
            if (artifact2 instanceof Book) {
                return ((PublishingBrand) artifact1).books[0].name.equals(((Book) artifact2).name);
            } else if (artifact2 instanceof EditorialGroup) {
                return ((PublishingBrand) artifact1).books[0].name.equals(((EditorialGroup) artifact2).books[0].name);
            } else if (artifact2 instanceof PublishingBrand) {
                return ((PublishingBrand) artifact1).books[0].name.equals(((PublishingBrand) artifact2).books[0].name);
            }
        }
        return false;
    }

    public Book[] getCommonBooksForRetailer(PublishingRetailer publishingRetailer1,
                                            PublishingRetailer publishingRetailer2) {

        Book[] commonBooksForRetailerIDs = new Book[0];
        int numberOfCommonBooks = 0;

        for (int i = 0; i < publishingRetailer1.publishingArtifacts.length; i++) {
            for (int j = 0; j < publishingRetailer2.publishingArtifacts.length; j++) {
                if (findSameBook(publishingRetailer1.publishingArtifacts[i],
                                                                        publishingRetailer2.publishingArtifacts[j])) {
                    commonBooksForRetailerIDs = Arrays.copyOf(commonBooksForRetailerIDs,
                                                                        numberOfCommonBooks + 1);

                    if (publishingRetailer1.publishingArtifacts[i] instanceof Book) {
                        commonBooksForRetailerIDs[numberOfCommonBooks++] =
                                                (Book) publishingRetailer1.publishingArtifacts[i];
                    } else if (publishingRetailer1.publishingArtifacts[i] instanceof EditorialGroup) {
                        commonBooksForRetailerIDs[numberOfCommonBooks++] =
                                                ((EditorialGroup) publishingRetailer1.publishingArtifacts[i]).books[0];
                    } else if (publishingRetailer1.publishingArtifacts[i] instanceof PublishingBrand) {
                        commonBooksForRetailerIDs[numberOfCommonBooks++] =
                                                ((PublishingBrand) publishingRetailer1.publishingArtifacts[i]).books[0];
                    }

                }
            }
        }
        return commonBooksForRetailerIDs;
    }

    public Book[] getCommonBooksForRetailerIDs(int retailerID1, int retailerID2) {
        int i = 0, j = 0;

        for (; i < dataBase.sizePublishingRetailer; i++) {
            if (dataBase.publishingRetailers[i].getID() == retailerID1)
                break;
        }

        for (; j < dataBase.sizePublishingRetailer; j++) {
            if (dataBase.publishingRetailers[j].getID() == retailerID2)
                break;
        }

        return getCommonBooksForRetailer(dataBase.publishingRetailers[i], dataBase.publishingRetailers[j]);
    }

    public Book[] getAllBooksForRetailer(PublishingRetailer publishingRetailer1,
                                         PublishingRetailer publishingRetailer2) {
        Book[] allBooksForRetailer = new Book[0];
        int numberOfBooks = 0, j, k;

        for (int i = 0; i < publishingRetailer1.publishingArtifacts.length; i++) {
            for (j = 0; j < publishingRetailer2.publishingArtifacts.length; j++) {
                if (findSameBook(publishingRetailer1.publishingArtifacts[i],
                        publishingRetailer2.publishingArtifacts[j])) {
                    break;
                }
            }
            if (j == publishingRetailer2.publishingArtifacts.length) {
                for (k = 0; k < numberOfBooks; k++) {
                    if (findSameBook(publishingRetailer1.publishingArtifacts[i],allBooksForRetailer[k]))
                        break;
                }

                if (numberOfBooks == k) {
                    allBooksForRetailer = Arrays.copyOf(allBooksForRetailer, numberOfBooks + 1);

                    if (publishingRetailer1.publishingArtifacts[i] instanceof Book) {
                        allBooksForRetailer[numberOfBooks++] = (Book) publishingRetailer1.publishingArtifacts[i];
                    } else if (publishingRetailer1.publishingArtifacts[i] instanceof EditorialGroup) {
                        allBooksForRetailer[numberOfBooks++] =
                                                ((EditorialGroup) publishingRetailer1.publishingArtifacts[i]).books[0];
                    } else if (publishingRetailer1.publishingArtifacts[i] instanceof PublishingBrand) {
                        allBooksForRetailer[numberOfBooks++] =
                                                ((PublishingBrand) publishingRetailer1.publishingArtifacts[i]).books[0];
                    }
                }
            }
        }
        return allBooksForRetailer;
    }

    public Book[] getAllBooksForRetailerIDs(int retailerID1, int retailerID2) {
        int i = 0, j = 0;

        for (; i < dataBase.sizePublishingRetailer; i++) {
            if (dataBase.publishingRetailers[i].getID() == retailerID1)
                break;
        }

        for (; j < dataBase.sizePublishingRetailer; j++) {
            if (dataBase.publishingRetailers[j].getID() == retailerID2)
                break;
        }

        Book[] uniqueBooksRetailer1 = getAllBooksForRetailer(dataBase.publishingRetailers[i],
                                    dataBase.publishingRetailers[j]);
        Book[] uniqueBooksRetailer2 = getAllBooksForRetailer(dataBase.publishingRetailers[j],
                                    dataBase.publishingRetailers[i]);
        Book[] commonBooksForRetailers = getCommonBooksForRetailer(dataBase.publishingRetailers[i],
                                    dataBase.publishingRetailers[j]);

        int finalLength = uniqueBooksRetailer1.length + uniqueBooksRetailer2.length + commonBooksForRetailers.length;
        Book[] allBooksForRetailer = new Book[finalLength];

        System.arraycopy(uniqueBooksRetailer1, 0,allBooksForRetailer,0, uniqueBooksRetailer1.length);
        System.arraycopy(uniqueBooksRetailer2, 0,allBooksForRetailer,uniqueBooksRetailer1.length,
                        uniqueBooksRetailer2.length);
        System.arraycopy(commonBooksForRetailers, 0,allBooksForRetailer,uniqueBooksRetailer2.length,
                        commonBooksForRetailers.length);

        return allBooksForRetailer;
    }

    public Book[] getCommonBooksForRetailerIDsOptimized(int retailerID1, int retailerID2) {
        int i = 0, j = 0;
        for (; i < dataBase.publishingRetailers.length; i++) {
            if (dataBase.publishingRetailers[i].getID() == retailerID1)
                break;
        }

        for (; j < dataBase.publishingRetailers.length; j++) {
            if (dataBase.publishingRetailers[j].getID() == retailerID2)
                break;
        }

        Book[] booksOptimized1 = getBooksForPublishingRetailerID(i);
        Book[] booksOptimized2 = getBooksForPublishingRetailerID(j);

        HashSet<Book> setRetailer1 = new HashSet<Book>();
        HashSet<Book> setRetailer2 = new HashSet<Book>();

        Collections.addAll(setRetailer1, booksOptimized1);
        Collections.addAll(setRetailer2, booksOptimized2);

        setRetailer1.retainAll(setRetailer2);
        Book[] intersection = new Book[setRetailer1.size()];
        int  k = 0;
        for (Book book:setRetailer1) {
            intersection[k++] = book;
        }

        return intersection;
    }

    public Book[] getAllBooksForRetailerIDsOptimized(int retailerID1, int retailerID2) {
        int i, j;
        for (i = 0; i < dataBase.publishingRetailers.length; i++) {
            if (dataBase.publishingRetailers[i].getID() == retailerID1)
                break;
        }

        for (j = 0; j < dataBase.publishingRetailers.length; j++) {
            if (dataBase.publishingRetailers[j].getID() == retailerID2)
                break;
        }

        Book[] booksOptimized1 = getBooksForPublishingRetailerID(i);
        Book[] booksOptimized2 = getBooksForPublishingRetailerID(j);

        HashSet<Book> setRetailer1 = new HashSet<Book>();
        HashSet<Book> setRetailer2 = new HashSet<Book>();

        Collections.addAll(setRetailer1, booksOptimized1);
        Collections.addAll(setRetailer2, booksOptimized2);
        setRetailer1.addAll(setRetailer2);

        Book[] union = new Book[setRetailer1.size()];
        int  k = 0;
        for (Book book:setRetailer1) {
            union[k++] = book;
        }

        return union;
    }

    public static void main(String[] args) throws Exception {
        Administration administration = new Administration();
        administration.setDataBase(administration.databaseInitialization());

        int[] idsForBooksForPublishingRetailer = {1, 10, 13, 20, 21};
        for (int id : idsForBooksForPublishingRetailer) {
            System.out.println("\nCartile unice ale retailerului " + id);
            Book[] booksForPublishingRetailerID = administration.getBooksForPublishingRetailerID(id);

            System.out.println("Numarul de carti unice " + booksForPublishingRetailerID.length);
            for (Book book : booksForPublishingRetailerID) {
                System.out.println(book.Publish());
            }
        }

        int[] idLanguages = {2, 23, 17, 19, 31};
        for (int idLanguage : idLanguages) {
            System.out.println("\nLimbile in care sunt publicate cartile retailerului " + idLanguage);
            Language[] languagesForPublishRetailer = administration.getLanguagesForPublishingRetailerID(idLanguage);

            for (Language language : languagesForPublishRetailer) {
                System.out.println(language);
            }
        }

        int[] bookIdsForCountrie = {513 ,9374, 10172, 12405, 14456};
        for (int booksId : bookIdsForCountrie) {
            System.out.println("\n" +
                    "Cartea cu ID-ul " + booksId + " a ajuns in tarile:");
            Countries[] countriesBookID = administration.getCountriesForBookID(booksId);
            for (Countries value : countriesBookID) {
                System.out.println(value);
            }
        }

        int[] publishRetailerIds1 = {1, 4, 14, 20, 32};
        int[] publishRetailerIds2 = {4, 9, 12, 25, 3};

        for (int i = 0, j = 0; i < publishRetailerIds1.length && j < publishRetailerIds2.length; i++, j++) {
            System.out.println("\nCartile comune pentru retailerii cu ID-urile " +
                    publishRetailerIds1[i] + " si " + publishRetailerIds2[j]);

            Book[] commonBooksForRetailerIDs = administration.getCommonBooksForRetailerIDs(publishRetailerIds1[i],
                                                publishRetailerIds2[j]);
            if (commonBooksForRetailerIDs.length == 0)
                System.out.println("Retailerii nu au carti comune");
            for (Book commonBooksForRetailerID : commonBooksForRetailerIDs) {
                System.out.println(commonBooksForRetailerID.Publish());
            }
        }

        int[] allPublishRetailerId1 = {4, 8, 12, 25, 3};
        int[] allPublishRetailerId2 = {1, 10, 14, 20, 30};

        for (int i = 0, j = 0; i < allPublishRetailerId1.length && j < allPublishRetailerId2.length; i++, j++) {

            System.out.println("\nUniunea cartilor pentru retailerii cu ID-urile " +
                    allPublishRetailerId1[i] + " si " + allPublishRetailerId2[j]);

            Book[] allBooksForRetailersIds = administration.getAllBooksForRetailerIDs(allPublishRetailerId1[i],
                                            allPublishRetailerId2[j]);
            System.out.println("are lungimea " + allBooksForRetailersIds.length);
            for (int k = 0; k < allBooksForRetailersIds.length; k++) {
                System.out.println(allBooksForRetailersIds[i].Publish());
            }
        }

        int[] optimizedPublishRetailerId1 = {6, 8, 13, 27, 3};
        int[] optimizedPublishRetailerId2 = {1, 12, 18, 21, 32};

        for (int i = 0, j = 0; i < optimizedPublishRetailerId1.length && j < optimizedPublishRetailerId2.length;
             i++, j++) {

            Book[] intersectionOptimized = administration.getCommonBooksForRetailerIDsOptimized(i, j);
            Book[] unionSectionOptimized = administration.getAllBooksForRetailerIDsOptimized(i, j);
        }
    }
}