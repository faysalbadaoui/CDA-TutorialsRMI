

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class BombillaRMIClientJNDI implements ClientCallbacksJNDI
{
	public static void main(String args[])
	{
		System.out.println("Buscar el servicio BombillaRMI");
		
		try
		{
			// Comprobar si se ha especificado la direccion del servicio de registros
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1234");

			InitialContext ctx = new InitialContext(jndiProperties);
			BombillaRMIServantJNDI servicioBombilla = (BombillaRMIServantJNDI) ctx.lookup("/jndi/test01");

			servicioBombilla.subscribe((ClientCallbacksJNDI) UnicastRemoteObject.exportObject(new BombillaRMIClientJNDI(), 0));
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
		} catch (RemoteException re)
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