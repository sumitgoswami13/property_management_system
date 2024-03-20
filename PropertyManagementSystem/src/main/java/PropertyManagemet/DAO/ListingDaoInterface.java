package PropertyManagemet.DAO;

import java.util.List;

import PropertyManagemet.model.Listing;

 public interface ListingDaoInterface {
    void addListing(Listing listing);
    Listing getListingById(int listingId);
    void updateListing(Listing listing);
    void deleteListing(int listingId);
    List<Listing> getAllListings();
}
