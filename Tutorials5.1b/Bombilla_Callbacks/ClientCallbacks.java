import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbacks extends Remote
{
	public void tempModifyied(String val) throws RemoteException;
}

