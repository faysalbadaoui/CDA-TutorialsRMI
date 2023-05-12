import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrimeSumServiceDist extends Remote {
    void registerWorker(Worker worker) throws RemoteException;
    void unregisterWorker(Worker worker) throws RemoteException;
    void asyncPrimeSum(int m) throws RemoteException, InterruptedException;
    int getLatestSum() throws RemoteException;
    boolean isCompleted() throws RemoteException;
}
