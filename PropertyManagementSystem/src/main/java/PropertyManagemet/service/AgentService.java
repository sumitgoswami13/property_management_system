package PropertyManagemet.service;

import PropertyManagemet.DAO.AgentDaoInterface;
import PropertyManagemet.DAO.UserDao;
import PropertyManagemet.DAO.UserDaoInterface;
import PropertyManagemet.model.Agent;
import PropertyManagemet.model.Client;

import org.hibernate.SessionFactory;
import java.util.List;

public class AgentService implements AgentServiceInterface {
	private final AgentDaoInterface agentDao;
	private final UserDaoInterface userDao;

	public AgentService(AgentDaoInterface agentDao, UserDaoInterface userDao) {
	    this.agentDao = agentDao;
	    this.userDao = userDao; // Corrected field name
	}

    // Create operation
    @Override
    public void addAgent(Agent agent) {
        try {
            agentDao.addAgent(agent);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Read operation
    @Override
    public Agent getAgentById(int agentId) {
        try {
            return agentDao.getAgentById(agentId);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }

    // Retrieve all agents
    @Override
    public List<Agent> getAllAgents() {
        try {
            return agentDao.getAllAgents();
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }

    // Update operation
    @Override
    public void updateAgent(Agent agent) {
        try {
            agentDao.updateAgent(agent);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Delete operation
    @Override
    public void deleteAgent(int agentId) {
        try {
            Agent agent = agentDao.getAgentById(agentId);
            if (agent != null) {
                UserService userService = new UserService(userDao);
                userService.deleteUser(agent.getUser().getUserId()); // accessing user object in the agent entity
            } else {
                System.out.println("Agent with ID " + agentId + " not found.");
            }
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

}
