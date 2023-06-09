
import java.rmi.*;


// Implementacion Servidor
public class BombillaRMIServantCallbacks implements BombillaRMICallbacks
{
	private static final long serialVersionUID = 1;
	
	private boolean luzOn;
	private int temp = 0;
	private int consumo = 0;
	private ClientCallbacks cl;
	// Constructor.
	public BombillaRMIServantCallbacks() throws RemoteException
	{
		// Asignar valor por defecto = off
		setBombilla(false);
	}

	// Metodo remoto -> Enciende la Bombilla.
	public void on() throws RemoteException
	{
		// Encender Bombilla.
		setBombilla(true);
	}

	// Metodo remoto -> Apagar la Bombilla.	
	public void off() throws RemoteException
	{
		// Apagar Bombilla.
		setBombilla(false);
	}

	// Metodo remoto -> Devuelve el estado de la Bombilla.	
	public boolean isOn() throws RemoteException
	{
		return getBombilla();
	}

	@Override
	public String getTemp() throws RemoteException {
		return(Integer.toString(temp));
	}

	@Override
	public String getCons() throws RemoteException {
		return(Integer.toString(consumo));
	}

	@Override
	public void modifyCons(int val) throws RemoteException {
		consumo = val;
	}

	@Override
	public void modifyTemp(int val) throws RemoteException {
		temp = val;
		cl.tempModifyied(Integer.toString(temp));
	}

	@Override
	public void subscribe(ClientCallbacks cli) throws RemoteException {
		cl = cli;
	}


	// Metodo local -> Modificar el estado de la bombilla.
	public void setBombilla(boolean valor)
	{
		luzOn = valor;
	}
	
	// Metodo local -> Devovler el estado de la bombilla.
	public boolean getBombilla()
	{
		return(luzOn);
	}

}
