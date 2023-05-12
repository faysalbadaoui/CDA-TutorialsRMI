import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServerDist {
    public static void main(String[] args) {
        try {
            PrimeSumServiceDist service = new PrimeSumServiceImplDist();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeSumService", service);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Press any key to start calculation when all workers have registered...");
            scanner.nextLine();
            service.asyncPrimeSum(10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
