package scripts.fletcher.util;

import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;


/**
 * Created by Robin on 08/01/2017.
 */
public class Conditions {

    public static Condition itemSelected(String itemName) {
        return new Condition() {
            @Override
            public boolean active() {
                return Utils.isItemSelected(itemName);
            }
        };
    }
}
