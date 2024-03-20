package PropertyManagemet.DAO;

import java.util.List;
import PropertyManagemet.model.Client;

public interface ClientDaoInterface {
    // Create operation
    void addClient(Client client);

    // Read operation
    Client getClientById(int clientId);
    
    // Read operation
    List<Client> getAllClients(); // Method to retrieve all clients
    
    // Update operation
    void updateClient(Client client);

    // Delete operation
    void deleteClient(int clientId);
}
