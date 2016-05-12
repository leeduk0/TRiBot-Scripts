package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.Minigames;

/**
 * Created by Robin on 10/05/2016.
 */
public class TeleportToGamesRoom implements Task {

    private MainScript script;

    public TeleportToGamesRoom(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        if (Login.getLoginState() == Login.STATE.INGAME) {
            return script.isNeedToTeleport();
        }
        return false;
    }

    @Override
    public void execute() {
        General.println("TeleportToGamesRoom");
        if(Minigames.selectMinigame(Minigames.MINIGAMES.BURTHORPE_GAMES_ROOM)) {
            if(Minigames.teleport()) {
                script.setNeedToTeleport(false);
            }
        }

    }
}
