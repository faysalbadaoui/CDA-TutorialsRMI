import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbacksJNDI extends Remote
{
	public void tempModifyied(String val) throws RemoteException;
}

