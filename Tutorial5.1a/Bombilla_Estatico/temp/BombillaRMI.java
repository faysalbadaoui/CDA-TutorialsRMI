
import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote
{
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;
		
}

