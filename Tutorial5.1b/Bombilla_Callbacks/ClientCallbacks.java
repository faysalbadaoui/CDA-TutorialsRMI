import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbacks extends Remote, Serializable
{
	public void tempModifyied(String val) throws RemoteException;
}

