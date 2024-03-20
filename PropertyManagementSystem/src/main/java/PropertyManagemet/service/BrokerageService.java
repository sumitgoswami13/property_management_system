package PropertyManagemet.service;

import PropertyManagemet.DAO.BrokerageDaoInterface;
import PropertyManagemet.model.Brokerage;

public class BrokerageService implements BrokerageServiceInterface {
    private final BrokerageDaoInterface brokerageDao;

    public BrokerageService(BrokerageDaoInterface brokerageDao) {
        this.brokerageDao = brokerageDao;
    }

    @Override
    public void addBrokerage(Brokerage brokerage) {
        brokerageDao.addBrokerage(brokerage);
    }

    @Override
    public Brokerage getBrokerageByAgentId(int agentId) {
        return brokerageDao.getBrokerageByAgentId(agentId);
    }

    @Override
    public void updateBrokerage(Brokerage brokerage) {
        brokerageDao.updateBrokerage(brokerage);
    }

    @Override
    public void deleteBrokerage(int brokerageId) {
        brokerageDao.deleteBrokerage(brokerageId);
    }
}