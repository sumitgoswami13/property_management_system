package PropertyManagemet.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Listing {
    @Id
    private int listingId;

    private double askPrice;
    private Date dateOfListing;
    private String status;
    private Date soldDate;
    private double soldPrice;

    @ManyToOne
    @JoinColumn(name = "clientId") // assuming this is the foreign key column in the Listing table
    private Client client;

    // Default constructor
    public Listing() {
    }

    public Listing(int listingId, double askPrice, Date dateOfListing, String status, Date soldDate, double soldPrice) {
        this.listingId = listingId;
        this.askPrice = askPrice;
        this.dateOfListing = dateOfListing;
        this.status = status;
        this.soldDate = soldDate;
        this.soldPrice = soldPrice;
    }

    // Getters
    public int getListingId() {
        return listingId;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public Date getDateOfListing() {
        return dateOfListing;
    }

    public String getStatus() {
        return status;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public Client getClient() {
        return client;
    }

    // Setters
    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public void setDateOfListing(Date dateOfListing) {
        this.dateOfListing = dateOfListing;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "listingId=" + listingId +
                ", askPrice=" + askPrice +
                ", dateOfListing=" + dateOfListing +
                ", status='" + status + '\'' +
                ", soldDate=" + soldDate +
                ", soldPrice=" + soldPrice +
                '}';
    }
}
