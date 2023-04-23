import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;


// Servidor
public class BombillaRMIServerJNDI extends BombillaRMIServantJNDI
{
	public BombillaRMIServerJNDI() throws RemoteException {
	}
	private static Registry registry;
	private static InitialContext ctx;

	public static void initJNDI() {
		try {
			registry = LocateRegistry.createRegistry(1234);
			final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1234");
			ctx = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void bindJNDI(String name, Object obj) throws NamingException {
		ctx.bind(name, obj);
	}

	public static void unbindJNDI(String name) throws NamingException {
		ctx.unbind(name);
	}

	public static void unInitJNDI() throws NamingException {
		ctx.close();
	}

	public static void main(String args[])
	{
		System.out.println("Cargando Servicio RMI");
		
		try
		{ 		initJNDI();
				BombillaRMIServantJNDI servicioBombilla = new BombillaRMIServantJNDI();
				bindJNDI("/jndi/test01", servicioBombilla);
				System.err.println("Server ready");
				while(true){

				}
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