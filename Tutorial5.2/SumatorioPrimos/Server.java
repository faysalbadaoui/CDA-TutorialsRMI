import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            PrimeSumService service = new PrimeSumServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeSumService", service);
            System.out.println("Server is ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}