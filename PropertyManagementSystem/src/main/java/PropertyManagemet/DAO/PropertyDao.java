package PropertyManagemet.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import PropertyManagemet.model.Property;
import java.util.List;

public class PropertyDao implements PropertyDaonterface {
    private final SessionFactory sessionFactory;

    public PropertyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    @Override
    public void addProperty(Property property) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(property);
            transaction.commit();
        }
    }

    // Read operation
    @Override
    public Property getPropertyById(int propertyId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Property.class, propertyId);
        }
    }

    // Update operation
    @Override
    public void updateProperty(Property property) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(property);
            transaction.commit();
        }
    }

    // Delete operation
    @Override
    public void deleteProperty(int propertyId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Property property = session.get(Property.class, propertyId);
            if (property != null) {
                session.delete(property);
                transaction.commit();
            }
        }
    }

    // Retrieve all properties
    @Override
    public List<Property> getAllProperties() {
        try (Session session = sessionFactory.openSession()) {
            Query<Property> query = session.createQuery("FROM Property", Property.class);
            return query.list();
        }
    }
}
