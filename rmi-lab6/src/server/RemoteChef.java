package server;

import salad.Salad;
import salad.SaladBuilder;
import vegetable.Vegetable;
import vegetable.VegetableParameters;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Remote interface for RMI client-server interaction
 */
public interface RemoteChef extends Remote {

    Salad prepareSalad(SaladBuilder saladBuilder) throws RemoteException;

    void addVegetable(Vegetable vegetable) throws RemoteException;

    boolean deleteVegetable(Vegetable vegetable) throws RemoteException;

    double countAllCalories() throws RemoteException;

    ArrayList<Vegetable> sortedByCalories() throws RemoteException;

    ArrayList<Vegetable> findVegetablesWithParameters(VegetableParameters vegetableParameters)
        throws RemoteException;

}
