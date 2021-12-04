import java.util.Arrays;

public class PublishingRetailer {
    int ID;
    String name;
    IPublishingArtifact[] publishingArtifacts;
    Countries[] countries;

    public PublishingRetailer(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.publishingArtifacts = new IPublishingArtifact[0];
        this.countries = new Countries[0];
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

    public IPublishingArtifact[] getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void setPublishingArtifacts(IPublishingArtifact[] publishingArtifacts) {
        this.publishingArtifacts = publishingArtifacts;
    }

    public Countries[] getCountries() {
        return countries;
    }

    public void setCountries(Countries[] countries) {
        this.countries = countries;
    }

    public void addCountry(Countries country) {
        int numberOfCountries = this.countries.length;
        this.countries = Arrays.copyOf(countries, numberOfCountries + 1);
        this.countries[numberOfCountries] = country;
    }

    public void addpublishingArtifactsBooks(Book book) {
        int numberOfpublishingArtifacts = this.publishingArtifacts.length;
        this.publishingArtifacts = Arrays.copyOf(publishingArtifacts, numberOfpublishingArtifacts + 1);
        this.publishingArtifacts[numberOfpublishingArtifacts] = book;
    }

    public void addpublishingArtifactsEditorial(EditorialGroup group) {
        int numberOfpublishingArtifacts = this.publishingArtifacts.length;
        this.publishingArtifacts = Arrays.copyOf(publishingArtifacts, numberOfpublishingArtifacts + 1);
        this.publishingArtifacts[numberOfpublishingArtifacts] = group;
    }

    public void addpublishingArtifactsPublishingBrand(PublishingBrand brand) {
        int numberOfpublishingArtifacts = this.publishingArtifacts.length;
        this.publishingArtifacts = Arrays.copyOf(publishingArtifacts, numberOfpublishingArtifacts + 1);
        this.publishingArtifacts[numberOfpublishingArtifacts] = brand;
    }

    @Override
    public String toString() {
        return "PublishingRetailer{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", publishingArtifacts=" + Arrays.toString(publishingArtifacts) +
                ", countries=" + Arrays.toString(countries) +
                '}';
    }
}
