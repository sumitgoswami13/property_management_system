package PropertyManagemet.service;

import java.util.List;

import PropertyManagemet.DAO.PropertyDaonterface;
import PropertyManagemet.model.Property;

public class PropertyService implements PropertyServiceInterface {
    private final PropertyDaonterface propertyDao;

    public PropertyService(PropertyDaonterface propertyDao) {
        this.propertyDao = propertyDao;
    }

    @Override
    public void addProperty(Property property) {
        propertyDao.addProperty(property);
    }

    @Override
    public Property getPropertyById(int propertyId) {
        return propertyDao.getPropertyById(propertyId);
    }

    @Override
    public void updateProperty(Property property) {
        propertyDao.updateProperty(property);
    }

    @Override
    public void deleteProperty(int propertyId) {
        propertyDao.deleteProperty(propertyId);
    }
    public List<Property> getAllProperties() {
        return propertyDao.getAllProperties();
    }
}

