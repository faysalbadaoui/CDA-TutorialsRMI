import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrimeSumService extends Remote {
    int syncPrimeSum(int m) throws RemoteException;
    void asyncPrimeSum(int m) throws RemoteException, InterruptedException;
    int getLatestSum() throws RemoteException;
    boolean isCompleted() throws RemoteException;
}