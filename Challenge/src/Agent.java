import java.util.function.Supplier;

public class Agent implements Supplier<String> {

    private String name;
    private boolean availability;
    private Client assignedClient;
    private JobPosition position;

    private int timeAttention;

    public Agent(String name, boolean availability, Client assignedClient, JobPosition position) {
        this.name = name;
        this.availability = availability;
        this.assignedClient = assignedClient;
        this.position = position;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Client getAssignedClient() {
        return assignedClient;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public JobPosition getPosition() {
        return position;
    }

    public void setPosition(JobPosition position) {
        this.position = position;
    }

    public int getTimeAttention() {
        return timeAttention;
    }

    /**
     * This simulates the attention time
     * @return The agent who attended the client, the client and the operation that the client did
     */


    @Override
    public String get() {

        timeAttention=(int)(Math.random()*6)+10;
        try {
            Thread.sleep(timeAttention*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return name + position + " attended: " + assignedClient.getName()+ " who want to do: " + assignedClient.getOperation() + " ";
    }
}
