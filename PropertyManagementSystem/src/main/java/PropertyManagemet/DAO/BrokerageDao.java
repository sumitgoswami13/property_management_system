package PropertyManagemet.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import PropertyManagemet.model.Brokerage;

public class BrokerageDao implements BrokerageDaoInterface {
    private final SessionFactory sessionFactory;

    public BrokerageDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    @Override
    public void addBrokerage(Brokerage brokerage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(brokerage);
            transaction.commit();
        }
    }

    // Read operation
    @Override
    public Brokerage getBrokerageByAgentId(int agentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Brokerage WHERE agent.agentId = :agentId", Brokerage.class)
                          .setParameter("agentId", agentId)
                          .uniqueResult();
        }
    }

    // Update operation
    @Override
    public void updateBrokerage(Brokerage brokerage) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(brokerage);
            transaction.commit();
        }
    }

    // Delete operation
    @Override
    public void deleteBrokerage(int brokerageId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Brokerage brokerage = session.get(Brokerage.class, brokerageId);
            if (brokerage != null) {
                session.delete(brokerage);
                transaction.commit();
            }
        }
    }
}