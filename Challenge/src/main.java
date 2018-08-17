import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main (String[] args){
        System.out.printf("begin{main}---\n\r");


        createClients(15);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.attend(clients);


        System.out.printf("---end{main}\n\r");
    }

    private static List<Client> clients=new ArrayList<>();

    /**
     * This function creates the clients with a random bank operation
     * @param numberOfClients Number of clients to want to create
     */

    public static void createClients(int numberOfClients) {
        for(int count = 1; count <= numberOfClients; count++) {
            clients.add(new Client("Client #" + count,getOperation() ));
        }
    }

    /**
     *
     * @return A random job position between Cashier, Supervisor and Director
     */

    private static BankOperation getOperation(){
        switch ((int)(Math.random()*3)+1) {
            case 1:
                return BankOperation.DEPOSITS;
            case 2:
                return BankOperation.WITHDRAWALS;
            case 3:
                return BankOperation.CUSTOMERISSUES;
            default:
                return BankOperation.DEPOSITS;
        }
    }

}
