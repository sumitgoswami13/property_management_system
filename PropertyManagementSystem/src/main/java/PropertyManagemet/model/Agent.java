package PropertyManagemet.model;

import javax.persistence.*;

@Entity
public class Agent {
    // Attributes
    @Id
    private int agentId;

    @OneToOne
    @JoinColumn(name = "userId") 
    private User user;

    @OneToOne(mappedBy = "agent", cascade = CascadeType.ALL)
    private Brokerage brokerage;

    private String dateOfJoining;
    private double totalMonthlySales;
    private double totalSales;

    // Constructors
    public Agent() {
    }

    public Agent(int agentId, User user, String dateOfJoining, double totalMonthlySales, double totalSales) {
        this.agentId = agentId;
        this.user = user;
        this.dateOfJoining = dateOfJoining;
        this.totalMonthlySales = totalMonthlySales;
        this.totalSales = totalSales;
    }

    // Getters and Setters
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Brokerage getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(Brokerage brokerage) {
        this.brokerage = brokerage;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getTotalMonthlySales() {
        return totalMonthlySales;
    }

    public void setTotalMonthlySales(double totalMonthlySales) {
        this.totalMonthlySales = totalMonthlySales;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId=" + agentId +
                ", user=" + user +
                ", brokerage=" + brokerage +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", totalMonthlySales=" + totalMonthlySales +
                ", totalSales=" + totalSales +
                '}';
    }
}
