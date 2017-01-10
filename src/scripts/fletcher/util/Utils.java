package scripts.fletcher.util;

import org.tribot.api2007.Game;
import org.tribot.api2007.Screen;

import java.awt.*;

/**
 * Created by Robin on 08/01/2017.
 */
public class Utils {

    public static boolean isItemSelected(String itemName) {
        return Game.getItemSelectionState() == 1
                && itemName.equals(Game.getSelectedItemName());
    }

    public static boolean enterAmountIsOpen() {
        return Screen.getColorAt(new Point(258, 425))
                .equals(new Color(0, 0, 128));
    }
}
