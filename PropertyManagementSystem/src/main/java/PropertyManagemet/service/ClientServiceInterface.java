package PropertyManagemet.service;

import java.util.List;
import PropertyManagemet.model.Client;

public interface ClientServiceInterface {
    // Create operation
    void addClient(Client client);

    // Read operation
    Client getClientById(int clientId);

    // Update operation
    void updateClient(Client client);

    // Delete operation
    void deleteClient(int clientId);
    
    // Read all clients
    List<Client> getAllClients();
}
