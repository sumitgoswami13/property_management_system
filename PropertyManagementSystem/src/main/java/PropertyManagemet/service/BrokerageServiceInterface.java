package PropertyManagemet.service;

import PropertyManagemet.model.Brokerage;

public interface BrokerageServiceInterface {
    void addBrokerage(Brokerage brokerage);
    Brokerage getBrokerageByAgentId(int agentId);
    void updateBrokerage(Brokerage brokerage);
    void deleteBrokerage(int brokerageId);
}