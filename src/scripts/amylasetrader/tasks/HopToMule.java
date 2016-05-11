package scripts.amylasetrader.tasks;

import org.tribot.api2007.Game;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.ClanChat;

/**
 * Created by Robin on 10/05/2016.
 */
public class HopToMule implements Task {

    private MainScript script;

    public HopToMule(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        return script.isReadyToTrade()
                && ClanChat.isInClanChat()
                && ClanChat.getPlayerWorld(script.getMuleName()) != Game.getCurrentWorld();
    }

    @Override
    public void execute() {



    }
}
