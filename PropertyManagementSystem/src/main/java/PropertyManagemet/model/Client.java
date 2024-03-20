package PropertyManagemet.model;

import javax.persistence.*;

@Entity
public class Client {
    // Attributes
    @Id
    private int clientId;

    @OneToOne
    @JoinColumn(name = "UserId")
    private User user; // Foreign key referencing the User table

    private String requirement;

    private double budget;

    // Constructors
    public Client() {
    }

    public Client(User user, String requirement, double budget) {
        this.user = user;
        this.requirement = requirement;
        this.budget = budget;
    }

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", user=" + user +
                ", requirement='" + requirement + '\'' +
                ", budget=" + budget +
                '}';
    }
}
