package chef;

import salad.Salad;
import salad.SaladBuilder;
import server.RemoteChef;
import vegetable.Vegetable;
import vegetable.VegetableCaloriesComparator;
import vegetable.VegetableParameters;

import java.rmi.RemoteException;
import java.util.ArrayList;

/** Class that represents a chef. Chef is responsible for building salads, counting calories and
 * sorting vegetables
 */
public class Chef implements RemoteChef {

    private final Object lock = new Object();

    private Salad salad;

    /**
     * Prepare Chef's salad with the given saladBuilder
     * @param saladBuilder
     *        SaladBuilder
     */
    @Override
    public Salad prepareSalad(SaladBuilder saladBuilder) {
        synchronized (lock) {
            salad = saladBuilder.getResult();

            return salad;
        }
    }

    @Override
    public void addVegetable(Vegetable vegetable) {
        synchronized (lock) {
            if (salad == null) {
                throw new NullPointerException("Salad is null");
            }

            salad.addVegetable(vegetable);
        }
    }

    @Override
    public boolean deleteVegetable(Vegetable vegetable) {
        synchronized (lock) {
            if (salad == null) {
                throw new NullPointerException("Salad is null");
            }

            return salad.deleteVegetable(vegetable);
        }
    }

    /**
     * Counts calories of the salad according to the calorie value of each vegetable
     * @return Calories
     */
    @Override
    public double countAllCalories() {
        double result = 0;

        synchronized (lock) {
            if (salad == null) {
                throw new NullPointerException("Salad is null");
            }

            for (Vegetable vegetable : salad.getVegetables())
                result += vegetable.getCaloriesInGramms();

            return result;
        }
    }

    /**
     * Returns a sorted by calories ArrayList of vegetables
     * @return ArrayList
     */
    @Override
    public ArrayList<Vegetable> sortedByCalories() {

        synchronized (lock) {
            if (salad == null) {
                throw new NullPointerException("Salad is null");
            }

            ArrayList<Vegetable> result = new ArrayList<>(salad.getVegetables());

            result.sort(new VegetableCaloriesComparator());

            return result;
        }

    }

    /**
     * Returns ArrayList of vegetables of the given parameters
     * @param vegetableParameters
     *        Parameters
     * @return ArrayList
     */
    @Override
    public ArrayList<Vegetable> findVegetablesWithParameters(VegetableParameters vegetableParameters) {

        synchronized (lock) {
            if (salad == null) {
                throw new NullPointerException("Salad is null");
            }

            ArrayList<Vegetable> result = new ArrayList<>();

            for (Vegetable vegetable: salad.getVegetables()) {
                if (Double.compare(vegetable.getCaloriesInGramms(), vegetableParameters.getCalories()) == 0 &&
                    vegetable.getVegetableState() == vegetableParameters.getVegetableState())
                    result.add(vegetable);
            }

            return result;
        }
    }

}
