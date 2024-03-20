package PropertyManagemet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Property {
    @Id
    private int propertyId;
    
    private int agentId;
    private int clientId;

    @OneToOne
    @JoinColumn(name = "listingId")
    private Listing listing;

    private String address;
    private int bathroom;
    private int bedroom;
    private double area;
    private int yearsOld;
    private String typeOfProperty;
    
    // Default constructor required by JPA
    public Property() {
    }

    // Constructor with parameters
    public Property(int propertyId, Listing listing, String address, int bathroom, int bedroom, double area, int yearsOld, String typeOfProperty, int agentId, int clientId) {
        this.propertyId = propertyId;
        this.listing = listing;
        this.address = address;
        this.bathroom = bathroom;
        this.bedroom = bedroom;
        this.area = area;
        this.yearsOld = yearsOld;
        this.typeOfProperty = typeOfProperty;
        this.agentId = agentId;
        this.clientId = clientId;
    }


    // Getters and setters...

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getTypeOfProperty() {
        return typeOfProperty;
    }

    public void setTypeOfProperty(String typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    
    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", agentId=" + agentId +
                ", clientId=" + clientId +
                ", listing=" + listing +
                ", address='" + address + '\'' +
                ", bathroom=" + bathroom +
                ", bedroom=" + bedroom +
                ", area=" + area +
                ", yearsOld=" + yearsOld +
                ", typeOfProperty='" + typeOfProperty + '\'' +
                '}';
    }
}
