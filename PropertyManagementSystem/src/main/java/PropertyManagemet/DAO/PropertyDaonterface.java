package PropertyManagemet.DAO;

import java.util.List;

import PropertyManagemet.model.Property;

public interface PropertyDaonterface {
    void addProperty(Property property);
    Property getPropertyById(int propertyId);
    void updateProperty(Property property);
    void deleteProperty(int propertyId);
    List<Property> getAllProperties();
}
