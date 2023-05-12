import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Worker extends Remote {
    int processSubtask(int start, int end) throws RemoteException;
    void notifyCompletion() throws RemoteException;
}