import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {


    private List<Agent> agents = new ArrayList<>();

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * This function creates the agents with a random role
     * @param numberOfAgents Number of agents to want to create
     */
    private void createAgent(int numberOfAgents)
    {
        for(int count = 0;count < numberOfAgents; count++){
            agents.add(new Agent("Agent ",true,null, getJobPosition() ));
        }
    }

    /**
     *
     * @return A random job position between Cashier, Supervisor and Director
     */
    private static JobPosition getJobPosition(){
        switch ((int)(Math.random()*3)+1) {
            case 1:
                return JobPosition.CASHIER;
            case 2:
                return JobPosition.SUPERVISOR;
            case 3:
                return JobPosition.DIRECTOR;
            default:
                return JobPosition.CASHIER;
        }
    }

    /**
     *
     * @return An agent available, takes first the cashiers than the supervisors and for the last the directors
     */

    private Agent getAgentAvailable() {
        return agents.stream()
                .filter(Agent::isAvailability)
                .filter(agent->agent.getPosition()==JobPosition.CASHIER)
                .findFirst()
                .orElse(
                        agents.stream()
                                .filter(Agent::isAvailability)
                                .filter(agent->agent.getPosition()==JobPosition.SUPERVISOR)
                                .findFirst()
                        .orElse(
                                agents.stream().filter(Agent::isAvailability)
                                        .filter(agent->agent.getPosition()==JobPosition.DIRECTOR)
                                        .findFirst()
                                .orElse(null)
                        )
                );
    }

    /**
     * This function creates the threads to attend one by one the client
     * @param clients A list of clients to attend
     */

    public void attend(List<Client> clients)
    {
        createAgent(10);
        int count = 0;
        while(count < clients.size()) {

            Agent agentAvailable = getAgentAvailable();
            if (agentAvailable != null) {
                agentAvailable.setAssignedClient(clients.get(count));
                agentAvailable.setAvailability(false);
                CompletableFuture
                        .supplyAsync(agentAvailable, executorService)
                        .thenAccept(r ->{System.out.println(r + "time: " + agentAvailable.getTimeAttention());
                            agentAvailable.setAvailability(true);agentAvailable.setAssignedClient(null);
                        });
                count++;

            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        executorService.shutdown();
    }

}
