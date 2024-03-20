package PropertyManagemet.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import PropertyManagemet.model.Agent;
import java.util.List;

public class AgentDao implements AgentDaoInterface {
    private final SessionFactory sessionFactory;

    public AgentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    @Override
    public void addAgent(Agent agent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(agent);
            transaction.commit();
        }
    }

    // Read operation
    @Override
    public Agent getAgentById(int agentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Agent.class, agentId);
        }
    }
    
    // Retrieve all agents
    @Override
    public List<Agent> getAllAgents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Agent", Agent.class).list();
        }
    }

    // Update operation
    @Override
    public void updateAgent(Agent agent) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(agent);
            transaction.commit();
        }
    }

    // Delete operation
    @Override
    public void deleteAgent(int agentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Agent agent = session.get(Agent.class, agentId);
            if (agent != null) {
                session.delete(agent);
                transaction.commit();
            }
        }
    }
}
