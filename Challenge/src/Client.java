public class Client {

    private String name;
    private BankOperation operation;

    public Client(String name, BankOperation operation) {
        this.name = name;
        this.operation = operation;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankOperation getOperation() {
        return operation;
    }

    public void setOperation(BankOperation operation) {
        this.operation = operation;
    }
}
