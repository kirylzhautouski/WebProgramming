package text;

import java.util.ArrayList;

/**
 * Basic abstract class for classes that should implement TextUnit
 * and contain many TextUnit
 */
public abstract class BaseComplexTextUnit implements TextUnit {

    /** Units of TextUnit */
    protected ArrayList<TextUnit> units = new ArrayList<>();

    /**
     * Add unit to the units
     * @param unit
     *        TextUnit
     */
    public void addUnit(TextUnit unit) {
        units.add(unit);
    }
}
