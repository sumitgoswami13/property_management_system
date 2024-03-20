package PropertyManagemet.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import PropertyManagemet.model.Client;

public class ClientDao implements ClientDaoInterface {
    private final SessionFactory sessionFactory;

    public ClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    @Override
    public void addClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        }
    }

    // Read operation
    @Override
    public Client getClientById(int clientId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, clientId);
        }
    }

    // Read operation
    @Override
    public List<Client> getAllClients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        }
    }

    // Update operation
    @Override
    public void updateClient(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        }
    }

    // Delete operation
    @Override
    public void deleteClient(int clientId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.delete(client);
                transaction.commit();
            }
        }
    }
}
