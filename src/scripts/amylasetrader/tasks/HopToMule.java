package scripts.amylasetrader.tasks;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Game;
import org.tribot.api2007.Login;
import org.tribot.api2007.WorldHopper;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.ClanChat;

/**
 * Created by Robin on 10/05/2016.
 */
public class HopToMule implements Task {

    private MainScript script;

    private int muleWorld;

    public HopToMule(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        if(Login.getLoginState() == Login.STATE.INGAME) {
            muleWorld = ClanChat.getPlayerWorld(script.getMuleName());
            return muleWorld != Game.getCurrentWorld();
        }
        return false;
    }

    @Override
    public void execute() {
        if(WorldHopper.changeWorld(muleWorld)) {
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Game.getCurrentWorld() == muleWorld;
                }
            }, 30000);
        }


    }
}
