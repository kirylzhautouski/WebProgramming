package server;

import chef.Chef;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI server
 */
public class Server {

    public static final String CHEF_BINDING_NAME = "server.RemoteChef";

    public static final int REGISTRY_PORT = 1447;

    public static void main(String[] args) throws RemoteException {
        Chef chef = new Chef();

        Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);

        RemoteChef stub = (RemoteChef)UnicastRemoteObject.exportObject(chef, 0);
        registry.rebind(CHEF_BINDING_NAME, stub);

    }



}
