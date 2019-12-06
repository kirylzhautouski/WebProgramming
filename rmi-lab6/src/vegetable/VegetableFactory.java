package vegetable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory for creating vegetables of different types and conditions
 */
public class VegetableFactory {

    /**
     * Creates a raw vegetable of the given type with the proper weight
     * @param vegetableClass
     *        Type of a created vegetable
     * @param weightInGramms
     *        Weight in gramms of a vegetable
     * @return
     *        A raw vegetable of the given type with the proper weight
     */
    public Vegetable getRawVegetable(Class<?> vegetableClass, double weightInGramms) {
        return getVegetable(vegetableClass, weightInGramms, VegetableState.RAW);
    }

    /**
     * Creates a boiled vegetable of the given type with the proper weight
     * @param vegetableClass
     *        Type of a created vegetable
     * @param weightInGramms
     *        Weight in gramms of a vegetable
     * @return
     *        A boiled vegetable of the given type with the proper weight
     */
    public Vegetable getBoiledVegetable(Class<?> vegetableClass, double weightInGramms) {
        return getVegetable(vegetableClass, weightInGramms, VegetableState.BOILED);
    }

    /**
     * Creates a roasted vegetable of the given type with the proper weight
     * @param vegetableClass
     *        Type of a created vegetable
     * @param weightInGramms
     *        Weight in gramms of a vegetable
     * @return
     *        A roasted vegetable of the given type with the proper weight
     */
    public Vegetable getRoastedVegetable(Class<?> vegetableClass, double weightInGramms) {
        return getVegetable(vegetableClass, weightInGramms, VegetableState.ROASTED);
    }

    /**
     * Creates a vegetable of the given type with the proper weight and state
     * @param vegetableClass
     *        Type of a created vegetable
     * @param weightInGramms
     *        Weight in gramms of a vegetable
     * @param vegetableState
     *        State of a vegetable
     * @return
     *        A vegetable of the given type with the proper weight and state
     */
    public Vegetable getVegetable(Class<?> vegetableClass, double weightInGramms,
                                  VegetableState vegetableState) {
        try {
            Constructor constructor = vegetableClass.getConstructor(double.class, VegetableState.class);

            return (Vegetable)constructor.newInstance(weightInGramms, vegetableState);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
            InvocationTargetException exception) {
            return null;
        }
    }


}
