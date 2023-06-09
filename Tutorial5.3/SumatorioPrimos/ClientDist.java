import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ClientDist extends UnicastRemoteObject implements Worker {
    private PrimeSumServiceDist service;

    public ClientDist(PrimeSumServiceDist service) throws RemoteException {
        super();
        this.service = service;
    }

    @Override
    public int processSubtask(int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        System.out.println("Calculated: "+sum);
        return sum;
    }

    @Override
    public void notifyCompletion() {
        System.out.println("Calculation completed");
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeSumServiceDist service = (PrimeSumServiceDist) registry.lookup("PrimeSumService");

            ClientDist client = new ClientDist(service);
            service.registerWorker(client);
            while(true){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
