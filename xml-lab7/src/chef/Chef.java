package chef;

import salad.Salad;
import salad.SaladBuilder;
import vegetable.Vegetable;
import vegetable.VegetableCaloriesComparator;
import vegetable.VegetableParameters;

import java.util.ArrayList;

/** Class that represents a chef. Chef is responsible for building salads, counting calories and
 * sorting vegetables
 */
public class Chef {

    /** Chef's salad */
    private Salad salad;

    public Chef(SaladBuilder saladBuilder) {
        salad = saladBuilder.getResult();
    }

    /**
     * Getter for Chef's salad
     * @return Salad
     */
    public Salad getSalad() {
        return salad;
    }

    /**
     * Setter for Chef's salad
     * @param salad
     *        Chef's salad
     */
    public void setSalad(Salad salad) {
        this.salad = salad;
    }

    /**
     * Prepare Chef's salad with the given saladBuilder
     * @param saladBuilder
     *        SaladBuilder
     */
    public void prepareSalad(SaladBuilder saladBuilder) {
        this.salad = saladBuilder.getResult();
    }

    /**
     * Counts calories of the salad according to the calorie value of each vegetable
     * @return Calories
     */
    public double countAllCalories() {
        double result = 0;

        for (Vegetable vegetable : salad.getVegetables())
            result += vegetable.getCaloriesInGramms();

        return result;
    }

    /**
     * Returns a sorted by calories ArrayList of vegetables
     * @return ArrayList
     */
    public ArrayList<Vegetable> sortedByCalories() {
        ArrayList<Vegetable> result = new ArrayList<>(salad.getVegetables());

        result.sort(new VegetableCaloriesComparator());

        return result;
    }

    /**
     * Returns ArrayList of vegetables of the given parameters
     * @param vegetableParameters
     *        Parameters
     * @return ArrayList
     */
    public ArrayList<Vegetable> findVegetablesWithParameters(VegetableParameters vegetableParameters) {
        ArrayList<Vegetable> result = new ArrayList<>();

        for (Vegetable vegetable: salad.getVegetables()) {
            if (Double.compare(vegetable.getCaloriesInGramms(), vegetableParameters.getCalories()) == 0 &&
                vegetable.getVegetableState() == vegetableParameters.getVegetableState())
                result.add(vegetable);
        }

        return result;
    }


}
