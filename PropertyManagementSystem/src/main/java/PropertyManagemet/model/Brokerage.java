package PropertyManagemet.model;


import javax.persistence.*;

@Entity
public class Brokerage {
    @Id
    
    private int brokerageId;

    @OneToOne
    @JoinColumn(name = "agentId")
    private Agent agent;

    private double commissionPercentage;

    // Getters
    public int getBrokerageId() {
        return brokerageId;
    }

    public Agent getAgent() {
        return agent;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    // Setters
    public void setBrokerageId(int brokerageId) {
        this.brokerageId = brokerageId;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
    @Override
    public String toString() {
        return "Brokerage{" +
                "brokerageId=" + brokerageId +
                ", agent=" + (agent != null ? agent.getAgentId() : "null") +
                ", commissionPercentage=" + commissionPercentage +
                '}';
    }
}
