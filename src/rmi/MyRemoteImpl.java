package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	protected MyRemoteImpl() throws RemoteException {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String sayHello() throws RemoteException {
		return null;
	}



}
