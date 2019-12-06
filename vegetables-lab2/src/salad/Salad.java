package salad;

import vegetable.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/** Class representing a Salad of Vegetables */
public class Salad {

    /** Vegetables */
    private ArrayList<Vegetable> vegetables;

    /** If this salad will contain sour cream */
    private boolean hasSourCream;

    /** If this salad will contain vegetable oil */
    private boolean hasVegetableOil;

    /** If this salad will contain salt */
    private boolean hasSalt;

    /** Creates and initializes a newly created salad with the given values */
    public Salad(Vegetable[] vegetables, boolean hasSourCream, boolean hasVegetableOil, boolean hasSalt) {
        this.vegetables = new ArrayList<>(Arrays.asList(vegetables));
        this.hasSourCream = hasSourCream;
        this.hasVegetableOil = hasVegetableOil;
        this.hasSalt = hasSalt;
    }

    /**
     * Getter for vegetables
     * @return Vegetables
     */
    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    /**
     * Add vegetable to the salad
     * @param vegetable
     */
    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    /**
     * Delete vegetable from the salad
     * @param vegetable
     *        Vegetable to be deleted
     * @return The result of deletion
     */
    public boolean deleteVegetable(Vegetable vegetable) {
        return vegetables.removeAll(Collections.singleton(vegetable));
    }

    @Override
    public String toString() {
        return "Salad{" +
            "vegetables=" + vegetables +
            ", hasSourCream=" + hasSourCream +
            ", hasVegetableOil=" + hasVegetableOil +
            ", hasSalt=" + hasSalt +
            '}';
    }
}
