
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class BombillaRMIClientCallbacks implements ClientCallbacks
{
	public static void main(String args[])
	{
		System.out.println("Buscar el servicio BombillaRMI");
		
		try
		{
			// Comprobar si se ha especificado la direccion del servicio de registros
			String registry = "localhost";
			if (args.length >=1)
				registry = args[0];
				
			// Formatear la url del registro
			String registro ="rmi://" + registry + "/BombillaRMI";
			
			// Buscar el servicio en el registro.
			Remote servicioRemoto = Naming.lookup(registro);
			
			// Convertir a un interfaz
			BombillaRMICallbacks servicioBombilla = (BombillaRMICallbacks) servicioRemoto;
			servicioBombilla.subscribe((ClientDist) UnicastRemoteObject.exportObject(new BombillaRMIClientCallbacks(), 0));
			// Encender la bombilla
			System.out.println("Invocando servicioBombilla.on()");
			servicioBombilla.on();
			
			// Mirar si el estado ha cambiado
			System.out.println("Estado bombilla: " + servicioBombilla.isOn() );
			
			// Ahorrar energica -> Apagar la bombilla
			System.out.println("Invocando servicioBombilla.off()");
			servicioBombilla.off();
			
			// Mirar si el estado ha cambiado
			System.out.println("Estado bombilla: " + servicioBombilla.isOn() );

			System.out.println("Modifying temperature to 20");
			servicioBombilla.modifyTemp(20);
			System.out.println("Temperature: "+servicioBombilla.getTemp());

			System.out.println("Modifying consum to 20");
			servicioBombilla.modifyCons(20);
			System.out.println("Consum: "+servicioBombilla.getCons());
		}
		catch (NotBoundException nbe)
		{
			System.err.println("No existe el servicio de bombilla en el registro!");
		}
		catch (RemoteException re)
		{
			System.err.println("Error Remoto - " + re);
		}
		catch (Exception e)
		{
			System.err.println("Error - " + e);
		}		
	}

	@Override
	public void tempModifyied(String val) throws RemoteException {
		System.out.println("Temperature changed: " + val);
	}
}