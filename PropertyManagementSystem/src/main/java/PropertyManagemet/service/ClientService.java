package PropertyManagemet.service;

import java.util.List;
import PropertyManagemet.DAO.ClientDaoInterface;
import PropertyManagemet.DAO.UserDaoInterface;
import PropertyManagemet.model.Client;

public class ClientService implements ClientServiceInterface {
    private final ClientDaoInterface clientDao;
	private final UserDaoInterface userDao;
	
    public ClientService(ClientDaoInterface clientDao, UserDaoInterface userDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
    }

    // Create operation
    @Override
    public void addClient(Client client) {
        try {
            clientDao.addClient(client);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Read operation
    @Override
    public Client getClientById(int clientId) {
        try {
            return clientDao.getClientById(clientId);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }
    
    // Retrieve all clients
    @Override
    public List<Client> getAllClients() {
        try {
            return clientDao.getAllClients();
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }

    // Update operation
    @Override
    public void updateClient(Client client) {
        try {
            clientDao.updateClient(client);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Delete operation
    @Override
    public void deleteClient(int clientId) {
        try {
            Client client = clientDao.getClientById(clientId);
            if (client != null) {
                int userId = client.getUser().getUserId();
                userDao.deleteUser(userId);
            } else {
                System.out.println("Client with ID " + clientId + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting client: " + e.getMessage());
            // Handle exception as needed
        }
    }
}
