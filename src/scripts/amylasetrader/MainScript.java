package scripts.amylasetrader;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import scripts.amylasetrader.tasks.*;
import scripts.api.framework.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Robin on 09/05/2016.
 */

@ScriptManifest(authors = "Cypher", category = "Tools", name = "Amylase Trader")
public class MainScript extends Script implements MessageListening07 {

    private boolean readyToTrade = false;
    private boolean needToTeleport = false;
    private String muleName = "Sphonix";
    private String clanName = "Sphonix";

    private ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public void run() {
        Collections.addAll(tasks,
                new JoinClan(this),
                new TeleportToGamesRoom(this),
                new LeaveGamesRoom(this),
                new WalkToInn(this),
                new EnterRoguesDen(this),
                new WalkToGrace(this),
                new TradeGrace(this),
                new BuyAmylasePacks(this),
                new OpenAmylasePacks(this),
                new HopToMule(this),
                new TradeMule(this),
                new UseTeletab(this)
        );

        while(true) {
            for (Task task : tasks) {
                if (task.validate()) {
                    task.execute();
                }
            }
            General.sleep(200);
        }
    }

    public String getMuleName() {
        return muleName;
    }

    public void setMuleName(String muleName) {
        this.muleName = muleName;
    }

    public boolean isReadyToTrade() {
        return readyToTrade;
    }

    public void setReadyToTrade(boolean readyToTrade) {
        this.readyToTrade = readyToTrade;
    }

    public boolean isNeedToTeleport() {
        return needToTeleport;
    }

    public void setNeedToTeleport(boolean needToTeleport) {
        this.needToTeleport = needToTeleport;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    @Override
    public void personalMessageReceived(String s, String s1) {

    }

    @Override
    public void serverMessageReceived(String s) {

    }

    @Override
    public void clanMessageReceived(String s, String s1) {
       // if(s.contains("a") && s1.contains("a")) {
      //      Vars.get().readyToTrade = true;
      //  }
    }

    @Override
    public void tradeRequestReceived(String s) {

    }

    @Override
    public void duelRequestReceived(String s, String s1) {

    }

    @Override
    public void playerMessageReceived(String s, String s1) {

    }
}
