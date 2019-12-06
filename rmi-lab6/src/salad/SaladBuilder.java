package salad;

import vegetable.Vegetable;

import java.io.Serializable;
import java.util.Arrays;

/** Builder used for constructing Salads */
public class SaladBuilder implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Array of vegetables */
    private Vegetable[] vegetables;

    /** If this salad will contain sour cream */
    private boolean hasSourCream;

    /** If this salad will contain vegetable oil */
    private boolean hasVegetableOil;

    /** If this salad will contain salt */
    private boolean hasSalt;

    public SaladBuilder() {

    }

    /**
     * Sets vegetables for building salad
     * @param vegetables
     *        Vegetables for the salad
     */
    public void setVegetables(Vegetable[] vegetables) {
        this.vegetables = Arrays.copyOf(vegetables, vegetables.length);
    }

    /**
     * Sets sour cream
     */
    public void setSourCream() {
        this.hasSourCream = true;
    }

    /**
     * Sets vegetable oil
     */
    public void setHasVegetableOil() {
        this.hasVegetableOil = true;
    }

    /**
     * Sets salt
     */
    public void setHasSalt() {
        this.hasSalt = true;
    }

    /**
     * Builds configured Salad and returns it
     * @return Salad
     */
    public Salad getResult() {
        return new Salad(vegetables, hasSourCream, hasVegetableOil, hasSalt);
    }

}
