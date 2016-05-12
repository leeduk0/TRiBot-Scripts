package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Login;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.ClanChat;

/**
 * Created by Robin on 10/05/2016.
 */
public class JoinClan implements Task {

    private MainScript script;

    public JoinClan(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {

        if (Login.getLoginState() == Login.STATE.INGAME) {
            return !ClanChat.isInClanChat();
        }
        return false;
    }

    @Override
    public void execute() {
        if(!ClanChat.isTabOpen()) {
            ClanChat.openTab();
        }

        ClanChat.join(script.getClanName());
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                    return ClanChat.isInClanChat(); // Breaks when true
                }
            }, 2000);




    }
}
