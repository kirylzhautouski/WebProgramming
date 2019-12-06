package client;

import chef.Chef;
import salad.Salad;
import salad.SaladBuilder;
import server.RemoteChef;
import vegetable.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI client
 */
public class Client {

    public static final String SERVER_CHEF_NAME = "server.RemoteChef";

    public static final int SERVER_PORT = 1447;

    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(SERVER_PORT);

        RemoteChef chef = (RemoteChef)registry.lookup(SERVER_CHEF_NAME);

        VegetableFactory vegetableFactory = new VegetableFactory();

        Vegetable vegetable = vegetableFactory.getRawVegetable(Tomato.class, 100);

        SaladBuilder saladBuilder = new SaladBuilder();
        saladBuilder.setHasSalt();
        saladBuilder.setVegetables(new Vegetable[] {
            vegetableFactory.getRawVegetable(Tomato.class, 100),
            vegetableFactory.getBoiledVegetable(Carrot.class, 100),
            vegetableFactory.getRoastedVegetable(Cucumber.class, 100) });
        saladBuilder.setSourCream();

        Salad salad = chef.prepareSalad(saladBuilder);

        System.out.println("Calories: " + chef.countAllCalories());

        System.out.println();

        System.out.println(chef.sortedByCalories());

        System.out.println();

        VegetableParameters vegetableParameters = new VegetableParameters(18, VegetableState.RAW);

        System.out.println(chef.findVegetablesWithParameters(vegetableParameters));

        chef.deleteVegetable(vegetable);

        System.out.println();

        System.out.println(chef.sortedByCalories());

    }

}
