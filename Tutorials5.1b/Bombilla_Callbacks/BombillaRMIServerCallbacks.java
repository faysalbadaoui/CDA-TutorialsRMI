
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;


// Servidor
public class BombillaRMIServerCallbacks extends BombillaRMIServantCallbacks
{
	public BombillaRMIServerCallbacks() throws RemoteException {
	}

	public static void main(String args[])
	{
		System.out.println("Cargando Servicio RMI");
		
		try
		{
				// Cargar el servicio.
				BombillaRMIServantCallbacks servicioBombilla = new BombillaRMIServantCallbacks();

				// Imprimir la ubicacion del servicio.
				
				// Comprobar si se ha expecificado un registro (arg[0])

				BombillaRMICallbacks bombilla = (BombillaRMICallbacks) UnicastRemoteObject.exportObject(servicioBombilla, 0);
				// Crear la URL del registro.
				Registry registry = LocateRegistry.getRegistry();
				registry.bind("BombillaRMI", bombilla);
				System.err.println("Server ready");
		}
		catch (RemoteException re)
		{
			System.err.println("Remote Error - " + re);
		}
		catch (Exception e)
		{
			System.err.println("Error - " + e);
		}
	}
}