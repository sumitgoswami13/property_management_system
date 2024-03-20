package PropertyManagemet.service;

import PropertyManagemet.model.Listing;
import java.util.List;

public interface ListingServiceInterface {
    void addListing(Listing listing);
    Listing getListingById(int listingId);
    void updateListing(Listing listing);
    void deleteListing(int listingId);
    List<Listing> getAllListings(); // New method to retrieve all listings
}
