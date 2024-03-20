package PropertyManagemet.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import PropertyManagemet.model.Listing;

public class ListingDao implements ListingDaoInterface {
    private final SessionFactory sessionFactory;

    public ListingDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addListing(Listing listing) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(listing);
            transaction.commit();
        }
    }

    @Override
    public Listing getListingById(int listingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Listing.class, listingId);
        }
    }

    @Override
    public void updateListing(Listing listing) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(listing);
            transaction.commit();
        }
    }

    @Override
    public void deleteListing(int listingId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Listing listing = session.get(Listing.class, listingId);
            if (listing != null) {
                session.delete(listing);
                transaction.commit();
            }
        }
    }

    @Override
    public List<Listing> getAllListings() {
        try (Session session = sessionFactory.openSession()) {
            Query<Listing> query = session.createQuery("FROM Listing", Listing.class);
            return query.list();
        }
    }
}
