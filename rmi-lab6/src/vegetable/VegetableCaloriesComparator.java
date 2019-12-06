package vegetable;

import java.util.Comparator;

public class VegetableCaloriesComparator implements Comparator<Vegetable> {

    @Override
    public int compare(Vegetable o1, Vegetable o2) {
        return (int)Math.signum(o1.getCaloriesInGramms() - o2.getCaloriesInGramms());
    }
}
