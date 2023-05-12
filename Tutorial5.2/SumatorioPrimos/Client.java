import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeSumService service = (PrimeSumService) registry.lookup("PrimeSumService");
            // Asynchronous call
            service.asyncPrimeSum(Integer.parseInt(args[0]));
            while(!service.isCompleted()) {
                System.out.println("Waiting for calculation to complete...");
                Thread.sleep(1000);
            }
            int asyncSum = service.getLatestSum();
            System.out.println("Asynchronous prime sum: " + asyncSum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
