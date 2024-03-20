package PropertyManagemet.service;

import java.util.List;

import PropertyManagemet.model.Property;

public interface PropertyServiceInterface {
    void addProperty(Property property);
    Property getPropertyById(int propertyId);
    void updateProperty(Property property);
    void deleteProperty(int propertyId);
    List<Property> getAllProperties();
}