package scripts.amylasetrader.data;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

/**
 * Created by Robin on 10/05/2016.
 */
public class Areas {
    public static final RSArea GAMES_ROOM_BOTTOM_FLOOR =
            new RSArea(new RSTile(0, 0, 0), new RSTile(0, 0, 0));
    public static final RSArea GAMES_ROOM_MIDDLE_FLOOR =
            new RSArea(new RSTile(0, 0, 0), new RSTile(0, 0, 0));
    public static final RSArea GAMES_ROOM_TOP_FLOOR =
            new RSArea(new RSTile(2890, 3555, 0), new RSTile(2907, 3572, 0));
    public static final RSArea BURTHORPE_INN =
            new RSArea(new RSTile(2905, 3543, 0), new RSTile(2915, 3536, 0));
    public static final RSArea ROGUES_DEN =
            new RSArea(new RSTile(3048, 4981, 1), new RSTile(2915, 3536, 0));

}
