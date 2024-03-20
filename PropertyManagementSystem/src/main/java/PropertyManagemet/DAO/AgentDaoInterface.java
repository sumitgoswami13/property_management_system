package PropertyManagemet.DAO;

import PropertyManagemet.model.Agent;
import java.util.List;

public interface AgentDaoInterface {
    // Create operation
    void addAgent(Agent agent);

    // Read operation
    Agent getAgentById(int agentId);
    
    // Retrieve all agents
    List<Agent> getAllAgents();

    // Update operation
    void updateAgent(Agent agent);

    // Delete operation
    void deleteAgent(int agentId);
}
