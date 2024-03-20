package PropertyManagemet.DAO;

import PropertyManagemet.model.Brokerage;

public interface BrokerageDaoInterface {
    void addBrokerage(Brokerage brokerage);
    Brokerage getBrokerageByAgentId(int agentId);
    void updateBrokerage(Brokerage brokerage);
    void deleteBrokerage(int brokerageId);
}