package PropertyManagemet;

import PropertyManagemet.DAO.AgentDao;
import PropertyManagemet.DAO.AgentDaoInterface;
import PropertyManagemet.DAO.BrokerageDao;
import PropertyManagemet.DAO.ClientDao;
import PropertyManagemet.DAO.ClientDaoInterface;
import PropertyManagemet.DAO.ListingDao;
import PropertyManagemet.DAO.ListingDaoInterface;
import PropertyManagemet.DAO.PropertyDao;
import PropertyManagemet.DAO.PropertyDaonterface;
import PropertyManagemet.DAO.UserDao;
import PropertyManagemet.DAO.UserDaoInterface;
import PropertyManagemet.Enums.Role;
import PropertyManagemet.model.Agent;
import PropertyManagemet.model.Brokerage;
import PropertyManagemet.model.Client;
import PropertyManagemet.model.Listing;
import PropertyManagemet.model.Property;
import PropertyManagemet.model.User;
import PropertyManagemet.service.AgentService;
import PropertyManagemet.service.ClientService;
import PropertyManagemet.service.ListingService;
import PropertyManagemet.service.PropertyService;
import PropertyManagemet.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
	 public static void main(String[] args) {
	        try {
	            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	            // Create DAO instances
	            UserDaoInterface userDao = new UserDao(sessionFactory);
	            AgentDaoInterface agentDao = new AgentDao(sessionFactory);
	            ClientDaoInterface clientDao = new ClientDao(sessionFactory);
	            ListingDaoInterface listingDao = new ListingDao(sessionFactory);
	            PropertyDaonterface propertyDao = new PropertyDao(sessionFactory);
	            // Create Service instances
	            
	            UserService userService = new UserService(userDao);
	            AgentService agentService = new AgentService(agentDao,userDao);
	            ClientService clientService = new ClientService(clientDao,userDao);
	            ListingService listingService = new ListingService(listingDao);
	            PropertyService propertyService = new PropertyService(propertyDao); 

	            Scanner scanner = new Scanner(System.in);
	            int choice;
	            do {
	                System.out.println("Menu:");
	                System.out.println("1. Agent Management");
	                System.out.println("2. Client Management");
	                System.out.println("3. Listing Management");
	                System.out.println("4. User Management");
	                System.out.println("5. Exit");
	                System.out.print("Enter your choice: ");
	                choice = scanner.nextInt();

	                switch (choice) {
	                    case 1:
	                        agentManagementMenu(userService, agentService, scanner);
	                        break;
	                    case 2:
	                        clientManagementMenu(userService, clientService, scanner);
	                        break;
	                    case 3:
	                        propertyManagementMenu(listingService, propertyService, clientService, agentService, scanner);
	                        break;
	                    case 4:
	                        userManagementMenu(userService, scanner);
	                        break;
	                    case 5:
	                        System.out.println("Exiting...");
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please enter again.");
	                }
	            } while (choice != 5);

	            // Close SessionFactory
	            sessionFactory.close();
	        } catch (Exception e) {
	            System.out.print(e);
	        }
	 }

	 private static void userManagementMenu(UserService userService, Scanner scanner) {
		    int choice;
		    do {
		        System.out.println("User Management Menu:");
		        System.out.println("1. Create User");
		        System.out.println("2. Update User");
		        System.out.println("3. Delete User");
		        System.out.println("4. Find User by ID");
		        System.out.println("5. Show All Users");
		        System.out.println("6. Back to Main Menu");
		        System.out.print("Enter your choice: ");
		        choice = scanner.nextInt();

		        switch (choice) {
		            case 1:
		                createUser(userService, scanner);
		                break;
		            case 2:
		                updateUser(userService, scanner);
		                break;
		            case 3:
		                deleteUser(userService, scanner);
		                break;
		            case 4:
		                findUserById(userService, scanner);
		                break;
		            case 5:
		                showAllUsers(userService);
		                break;
		            case 6:
		                System.out.println("Returning to main menu...");
		                break;
		            default:
		                System.out.println("Invalid choice. Please enter again.");
		        }
		    } while (choice != 6);
		}

		private static void showAllUsers(UserService userService) {
		    List<User> users = userService.getAllUsers();
		    if (users.isEmpty()) {
		        System.out.println("No users found.");
		    } else {
		        System.out.println("All Users:");
		        for (User user : users) {
		            System.out.println(user);
		        }
		    }
		}

	    
	    

	    private static void createUser(UserService userService, Scanner scanner) {
	        System.out.print("Enter user ID: ");
	        int userId = scanner.nextInt();
	        System.out.print("Enter user name: ");
	        String userName = scanner.next();
	        System.out.print("Enter user email: ");
	        String userEmail = scanner.next();
	        System.out.print("Enter user address: ");
	        String userAddress = scanner.next();
	        Role role;
	        System.out.println("Select Role:");
	        System.out.println("1. ADMIN");
	        System.out.println("2. AGENT");
	        System.out.println("3. CLIENT");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                role = Role.ADMIN;
	                break;
	            case 2:
	                role = Role.AGENT;
	                break;
	            case 3:
	                role = Role.CLIENT;
	                break;
	            default:
	                System.out.println("Invalid choice. Defaulting to USER role.");
	                role = Role.USER;
	                break;
	        }
	        User user = new User(userId, userName, userEmail, userAddress,role);
	        userService.addUser(user);
	        System.out.println("User created successfully.");
	    }

	    private static void updateUser(UserService userService, Scanner scanner) {
	        System.out.print("Enter user ID to update: ");
	        int userId = scanner.nextInt();
	        
	        User existingUser = userService.getUserById(userId);
	        if (existingUser != null) {
	            System.out.print("Enter new user name: ");
	            String newName = scanner.next();
	            System.out.print("Enter new user contact: ");
	            String contact = scanner.next();
	            System.out.print("Enter new user address: ");
	            String newAddress = scanner.next();
	            Role role;
		        System.out.println("Select Role:");
		        System.out.println("1. ADMIN");
		        System.out.println("2. AGENT");
		        System.out.println("3. CLIENT");
		        System.out.print("Enter your choice: ");
		        int choice = scanner.nextInt();
		        switch (choice) {
		            case 1:
		                role = Role.ADMIN;
		                break;
		            case 2:
		                role = Role.AGENT;
		                break;
		            case 3:
		                role = Role.CLIENT;
		                break;
		            default:
		                System.out.println("Invalid choice. Defaulting to USER role.");
		                role = Role.USER;
		                break;
		        }

	            existingUser.setName(newName);
	            existingUser.setAddress(newAddress);
	            existingUser.setRole(role);

	            userService.updateUser(existingUser);
	            System.out.println("User updated successfully.");
	        } else {
	            System.out.println("User with ID " + userId + " not found.");
	        }
	    }

	    private static void deleteUser(UserService userService, Scanner scanner) {
	        System.out.print("Enter user ID to delete: ");
	        int userId = scanner.nextInt();
	        
	        userService.deleteUser(userId);
	        System.out.println("User deleted successfully.");
	    }

	    private static void findUserById(UserService userService, Scanner scanner) {
	        System.out.print("Enter user ID to find: ");
	        int userId = scanner.nextInt();
	        
	        User user = userService.getUserById(userId);
	        if (user != null) {
	            System.out.println("User found: " + user);
	        } else {
	            System.out.println("User with ID " + userId + " not found.");
	        }
	    }

	    private static void agentManagementMenu(UserService userService, AgentService agentService, Scanner scanner) {
	        int choice;
	        do {
	            System.out.println("Agent Management Menu:");
	            System.out.println("1. Create Agent");
	            System.out.println("2. Update Agent");
	            System.out.println("3. Delete Agent");
	            System.out.println("4. Find Agent by ID");
	            System.out.println("5. Show All Agents");
	            System.out.println("6. Back to Main Menu");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    createAgent(userService, agentService, scanner);
	                    break;
	                case 2:
	                	updateAgent(agentService,userService, scanner);
	                    break;
	                case 3:
	                    deleteAgent(agentService, scanner);
	                    break;
	                case 4:
	                    findAgentById(agentService, scanner);
	                    break;
	                case 5:
	                    showAllAgents(agentService);
	                    break;
	                case 6:
	                    System.out.println("Returning to main menu...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter again.");
	            }
	        } while (choice != 6);
	    }

	    private static void clientManagementMenu(UserService userService, ClientService clientService, Scanner scanner) {
	        int choice;
	        do {
	            System.out.println("Client Management Menu:");
	            System.out.println("1. Create Client");
	            System.out.println("2. Update Client");
	            System.out.println("3. Delete Client");
	            System.out.println("4. Find Client by ID");
	            System.out.println("5. Show All Clients");
	            System.out.println("6. Back to Main Menu");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    createClient(userService, clientService, scanner);
	                    break;
	                case 2:
	                    updateClient(clientService, scanner);
	                    break;
	                case 3:
	                    deleteClient(clientService, scanner);
	                    break;
	                case 4:
	                    findClientById(clientService, scanner);
	                    break;
	                case 5:
	                	showClients(clientService);
	                    break;
	                case 6:
	                    System.out.println("Returning to main menu...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter again.");
	            }
	        } while (choice != 6);
	    }
	    private static void showClients(ClientService clientService) {
	        List<Client> clients = clientService.getAllClients();
	        if (clients.isEmpty()) {
	            System.out.println("No clients found.");
	        } else {
	        	System.out.println(clients);
	        }
	    }
	    private static void createAgent(UserService userService, AgentService agentService, Scanner scanner) {
	        // Collect user information
	        System.out.print("Enter user ID: ");
	        int userId = scanner.nextInt();
	        System.out.print("Enter user name: ");
	        String userName = scanner.next();
	        System.out.print("Enter user email: ");
	        String userEmail = scanner.next();
	        System.out.print("Enter user address: ");
	        String userAddress = scanner.next();
	        Role role;
	        System.out.println("Select Role:");
	        System.out.println("1. ADMIN");
	        System.out.println("2. AGENT");
	        System.out.println("3. CLIENT");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                role = Role.ADMIN;
	                break;
	            case 2:
	                role = Role.AGENT;
	                break;
	            case 3:
	                role = Role.CLIENT;
	                break;
	            default:
	                System.out.println("Invalid choice. Defaulting to USER role.");
	                role = Role.USER;
	                break;
	        }

	        // Create user object
	        User user = new User(userId, userName, userEmail, userAddress, role);

	        // Collect agent information
	        System.out.print("Enter agent ID: ");
	        int agentId = scanner.nextInt();
	        System.out.print("Enter joining date (YYYY-MM-DD): ");
	        String joiningDate = scanner.next();
	        System.out.print("Enter Brokerage ID: ");
	        int BrokerageId = scanner.nextInt();
	        System.out.print("Enter commission rate: ");
	        double commissionRate = scanner.nextDouble();
	        System.out.print("Enter total sales: ");
	        double totalSales = scanner.nextDouble();

	        // Create agent object
	        Agent agent = new Agent(agentId, user, joiningDate, commissionRate, totalSales);

	        // Save user and agent
	        userService.addUser(user);
	        agentService.addAgent(agent);

	        // Create brokerage object
	        Brokerage brokerage = new Brokerage();
	        brokerage.setAgent(agent);
	        brokerage.setCommissionPercentage(commissionRate);
	        brokerage.setBrokerageId(BrokerageId);

	        // Save brokerage using BrokerageDao
	        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	        BrokerageDao brokerageDao = new BrokerageDao(sessionFactory);
	        brokerageDao.addBrokerage(brokerage);
	        

	        // Close SessionFactory
	        sessionFactory.close();

	        System.out.println("Agent and associated brokerage created successfully.");
	    }


	    private static void updateAgent(AgentService agentService, UserService userService, Scanner scanner) {
	        System.out.print("Enter agent ID to update: ");
	        int agentId = scanner.nextInt();

	        Agent existingAgent = agentService.getAgentById(agentId);
	        if (existingAgent != null) {
	            // Update user information
	            System.out.print("Enter user ID: ");
	            int userId = scanner.nextInt();
	            System.out.print("Enter user name: ");
	            String userName = scanner.next();
	            System.out.print("Enter user email: ");
	            String userEmail = scanner.next();
	            System.out.print("Enter user address: ");
	            String userAddress = scanner.next();
	            Role role;
		        System.out.println("Select Role:");
		        System.out.println("1. ADMIN");
		        System.out.println("2. AGENT");
		        System.out.println("3. CLIENT");
		        System.out.print("Enter your choice: ");
		        int choice = scanner.nextInt();
		        switch (choice) {
		            case 1:
		                role = Role.ADMIN;
		                break;
		            case 2:
		                role = Role.AGENT;
		                break;
		            case 3:
		                role = Role.CLIENT;
		                break;
		            default:
		                System.out.println("Invalid choice. Defaulting to USER role.");
		                role = Role.USER;
		                break;
		        }
	            User user = new User(userId, userName, userEmail, userAddress, role);
	            existingAgent.setUser(user);

	            System.out.print("Enter new joining date (YYYY-MM-DD): ");
	            String newJoiningDate = scanner.next();
	            existingAgent.setDateOfJoining(newJoiningDate);
	            
	            // Update sales information
	            System.out.print("Enter new total monthly sales: ");
	            double newTotalMonthlySales = scanner.nextDouble();
	            existingAgent.setTotalMonthlySales(newTotalMonthlySales);
	            
	            System.out.print("Enter new total sales: ");
	            double newTotalSales = scanner.nextDouble();
	            existingAgent.setTotalSales(newTotalSales);

	            // Update both user and agent
	            userService.updateUser(user);
	            agentService.updateAgent(existingAgent);

	            System.out.println("Agent and associated user updated successfully.");
	        } else {
	            System.out.println("Agent with ID " + agentId + " not found.");
	        }
	    }


	    private static void deleteAgent(AgentService agentService, Scanner scanner) {
	        System.out.print("Enter agent ID to delete: ");
	        int agentId = scanner.nextInt();
	        
	        agentService.deleteAgent(agentId);
	        System.out.println("Agent deleted successfully.");
	    }

	    private static void findAgentById(AgentService agentService, Scanner scanner) {
	        System.out.print("Enter agent ID to find: ");
	        int agentId = scanner.nextInt();
	        
	        Agent agent = agentService.getAgentById(agentId);
	        if (agent != null) {
	            System.out.println("Agent found: " + agent);
	        } else {
	            System.out.println("Agent with ID " + agentId + " not found.");
	        }
	    }

	    private static void showAllAgents(AgentService agentService) {
	        List<Agent> agents = agentService.getAllAgents();
	        if (agents != null && !agents.isEmpty()) {
	            System.out.println("All Agents:");
	            for (Agent agent : agents) {
	                System.out.println(agent);
	            }
	        } else {
	            System.out.println("No agents found.");
	        }
	    }

	    private static void createClient(UserService userService, ClientService clientService, Scanner scanner) {
	        System.out.print("Enter user ID: ");
	        int userId = scanner.nextInt();
	        System.out.print("Enter user name: ");
	        String userName = scanner.next();
	        System.out.print("Enter user email: ");
	        String userEmail = scanner.next();
	        System.out.print("Enter user address: ");
	        String userAddress = scanner.next();
	        Role role;
	        System.out.println("Select Role:");
	        System.out.println("1. ADMIN");
	        System.out.println("2. AGENT");
	        System.out.println("3. CLIENT");
	        System.out.print("Enter your choice: ");
	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                role = Role.ADMIN;
	                break;
	            case 2:
	                role = Role.AGENT;
	                break;
	            case 3:
	                role = Role.CLIENT;
	                break;
	            default:
	                System.out.println("Invalid choice. Defaulting to USER role.");
	                role = Role.USER;
	                break;
	        }

	        User user = new User(userId, userName, userEmail, userAddress, role);
	        userService.addUser(user);

	        System.out.print("Enter client ID: ");
	        int clientId = scanner.nextInt();
	        System.out.print("Enter client requirement: ");
	        String requirement = scanner.next();
	        System.out.print("Enter client budget: ");
	        double budget = scanner.nextDouble();

	        Client client = new Client(user, requirement, budget);
	        client.setClientId(clientId);
	        clientService.addClient(client);
	    }

	    private static void updateClient(ClientService clientService, Scanner scanner) {
	        System.out.println("Updating client:");
	        System.out.print("Enter client ID to update: ");
	        int clientId = scanner.nextInt();

	        Client existingClient = clientService.getClientById(clientId);
	        if (existingClient != null) {
	            // Update user information
	            System.out.print("Enter user ID: ");
	            int userId = scanner.nextInt();
	            System.out.print("Enter user name: ");
	            String userName = scanner.next();
	            System.out.print("Enter user email: ");
	            String userEmail = scanner.next();
	            System.out.print("Enter user address: ");
	            String userAddress = scanner.next();
	            Role role;
		        System.out.println("Select Role:");
		        System.out.println("1. ADMIN");
		        System.out.println("2. AGENT");
		        System.out.println("3. CLIENT");
		        System.out.print("Enter your choice: ");
		        int choice = scanner.nextInt();
		        switch (choice) {
		            case 1:
		                role = Role.ADMIN;
		                break;
		            case 2:
		                role = Role.AGENT;
		                break;
		            case 3:
		                role = Role.CLIENT;
		                break;
		            default:
		                System.out.println("Invalid choice. Defaulting to USER role.");
		                role = Role.USER;
		                break;
		        }

	            User user = new User(userId, userName, userEmail, userAddress, role);
	            existingClient.setUser(user);

	            // Update client information
	            System.out.print("Enter new requirement: ");
	            String requirement = scanner.next();
	            System.out.print("Enter new budget: ");
	            double newBudget = scanner.nextDouble();

	            existingClient.setRequirement(requirement);
	            existingClient.setBudget(newBudget);

	            clientService.updateClient(existingClient);
	            System.out.println("Client updated successfully.");
	        } else {
	            System.out.println("Client with ID " + clientId + " not found.");
	        }
	    }

	    private static void deleteClient(ClientService clientService, Scanner scanner) {
	        System.out.print("Enter client ID to delete: ");
	        int clientId = scanner.nextInt();

	        clientService.deleteClient(clientId);
	        System.out.println("Client deleted successfully.");
	    }

	    private static void findClientById(ClientService clientService, Scanner scanner) {
	        System.out.print("Enter client ID to find: ");
	        int clientId = scanner.nextInt();

	        Client client = clientService.getClientById(clientId);
	        if (client != null) {
	            System.out.println("Client found: " + client);
	        } else {
	            System.out.println("Client with ID " + clientId + " not found.");
	        }
	    }

	    private static void propertyManagementMenu(ListingService listingService, PropertyService propertyService, ClientService clientService, AgentService agentService, Scanner scanner) {
	        int choice;
	        do {
	            System.out.println("Property Management Menu:");
	            System.out.println("1. Add Property");
	            System.out.println("2. Show All Properties");
	            System.out.println("3. Find Property by ID");
	            System.out.println("4. Update Property");
	            System.out.println("5. Delete Property");
	            System.out.println("6. Back to Main Menu");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    createProperty(listingService, propertyService, clientService, agentService, scanner);
	                    break;
	                case 2:
	                    showAllProperties(propertyService);
	                    break;
	                case 3:
	                    findPropertyById(propertyService, scanner);
	                    break;
	                case 4:
	                    updateProperty(propertyService, scanner);
	                    break;
	                case 5:
	                    deleteProperty(propertyService, scanner);
	                    break;
	                case 6:
	                    System.out.println("Returning to main menu...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter again.");
	            }
	        } while (choice != 6);
	    }

	    
	    
	    private static void showAllProperties(PropertyService propertyService) {
	        List<Property> properties = propertyService.getAllProperties();
	        if (properties != null && !properties.isEmpty()) {
	            System.out.println("All Properties:");
	            for (Property property : properties) {
	                System.out.println(property);
	            }
	        } else {
	            System.out.println("No properties found.");
	        }
	    }
	    
	    private static void showAllListings(ListingService listingService) {
	        List<Listing> listings = listingService.getAllListings();
	        if (listings != null && !listings.isEmpty()) {
	            System.out.println("All Listings:");
	            for (Listing listing : listings) {
	                System.out.println(listing);
	            }
	        } else {
	            System.out.println("No listings found.");
	        }
	    }

	    private static void createProperty(ListingService listingService, PropertyService propertyService, ClientService clientService, AgentService agentService, Scanner scanner) {
	        System.out.print("Enter listing ID: ");
	        int listingId = scanner.nextInt();
	        System.out.print("Enter listing name: ");
	        String listingName = scanner.next();
	        System.out.print("Enter listing description: ");
	        String description = scanner.next();
	        System.out.print("Enter listing price: ");
	        double askPrice = scanner.nextDouble();
	        System.out.print("Enter date of listing (YYYY-MM-DD): ");
	        String dateOfListingStr = scanner.next(); // Read as string first
	        Date dateOfListing = parseDate(dateOfListingStr); // Convert string to Date
	        System.out.print("Enter status: ");
	        String status = scanner.next();
	        System.out.print("Enter sold date (YYYY-MM-DD): ");
	        String soldDateStr = scanner.next(); // Read as string first
	        Date soldDate = parseDate(soldDateStr); // Convert string to Date
	        System.out.print("Enter sold price: ");
	        double soldPrice = scanner.nextDouble();
	        System.out.print("Enter property ID: ");
	        int propertyId = scanner.nextInt();
	        System.out.print("Enter address: ");
	        String address = scanner.next();
	        System.out.print("Enter number of bathrooms: ");
	        int bathroom = scanner.nextInt();
	        System.out.print("Enter number of bedrooms: ");
	        int bedroom = scanner.nextInt();
	        System.out.print("Enter area: ");
	        double area = scanner.nextDouble();
	        System.out.print("Enter years old: ");
	        int yearsOld = scanner.nextInt();
	        System.out.print("Enter type of property: ");
	        String typeOfProperty = scanner.next();
	        System.out.print("Enter agent ID: ");
	        int agentId = scanner.nextInt();
	        System.out.print("Enter client ID: ");
	        int clientId = scanner.nextInt();

	        // Fetch Agent and Client objects from the database
	        Agent agent = agentService.getAgentById(agentId);
	        Client client = clientService.getClientById(clientId);

	        // Create Listing object
	        Listing listing = new Listing(listingId, askPrice, dateOfListing, status, soldDate, soldPrice);
	        listing.setClient(client);

	        // Create Property object with agent and client
	        Property property = new Property(propertyId, listing, address, bathroom, bedroom, area, yearsOld, typeOfProperty, agent.getAgentId(), client.getClientId());

	        // Save Listing and Property
	        listingService.addListing(listing);
	        propertyService.addProperty(property);

	        System.out.println("Listing and property added successfully.");
	    }

	    
	    private static void findPropertyById(PropertyService propertyService, Scanner scanner) {
	        System.out.print("Enter property ID: ");
	        int propertyId = scanner.nextInt();
	        Property property = propertyService.getPropertyById(propertyId);
	        if (property != null) {
	            System.out.println("Property found:");
	            System.out.println(property);
	        } else {
	            System.out.println("Property with ID " + propertyId + " not found.");
	        }
	    }

	    private static void updateProperty(PropertyService propertyService, Scanner scanner) {
	        System.out.print("Enter property ID to update: ");
	        int propertyId = scanner.nextInt();
	        Property existingProperty = propertyService.getPropertyById(propertyId);
	        if (existingProperty != null) {
	            // Proceed with updating the property fields
	            // You can prompt the user to enter new values for each property field
	            // Then update the existing property object using setters
	            // Finally, call the updateProperty method in PropertyService to save the changes
	            System.out.println("Property found. You can now update its details.");
	            // Example:
	            // existingProperty.setAddress("New Address");
	            // propertyService.updateProperty(existingProperty);
	        } else {
	            System.out.println("Property with ID " + propertyId + " not found.");
	        }
	    }

	    private static void deleteProperty(PropertyService propertyService, Scanner scanner) {
	        System.out.print("Enter property ID to delete: ");
	        int propertyId = scanner.nextInt();
	        Property property = propertyService.getPropertyById(propertyId);
	        if (property != null) {
	            propertyService.deleteProperty(propertyId);
	            System.out.println("Property with ID " + propertyId + " deleted successfully.");
	        } else {
	            System.out.println("Property with ID " + propertyId + " not found.");
	        }
	    }


	    private static Date parseDate(String dateStr) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            return dateFormat.parse(dateStr);
	        } catch (ParseException e) {
	            System.out.println("Error parsing date: " + e.getMessage());
	            return null;
	        }
	    }
	}


