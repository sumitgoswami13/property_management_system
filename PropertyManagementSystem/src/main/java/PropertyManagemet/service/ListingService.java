package PropertyManagemet.service;

import PropertyManagemet.DAO.ListingDaoInterface;
import PropertyManagemet.model.Listing;
import java.util.List;

public class ListingService implements ListingServiceInterface {
    private final ListingDaoInterface listingDao;

    public ListingService(ListingDaoInterface listingDao) {
        this.listingDao = listingDao;
    }

    @Override
    public void addListing(Listing listing) {
        listingDao.addListing(listing);
    }

    @Override
    public Listing getListingById(int listingId) {
        return listingDao.getListingById(listingId);
    }

    @Override
    public void updateListing(Listing listing) {
        listingDao.updateListing(listing);
    }

    @Override
    public void deleteListing(int listingId) {
        listingDao.deleteListing(listingId);
    }

    @Override
    public List<Listing> getAllListings() {
        return listingDao.getAllListings();
    }
}
