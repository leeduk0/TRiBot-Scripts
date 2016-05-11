package scripts.amylasetrader.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.*;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.Shopping;

/**
 * Created by Robin on 10/05/2016.
 */
public class TradeGrace implements Task {

    private MainScript script;

    public TradeGrace(MainScript script) {
        this.script = script;
    }

    private RSNPC[] grace;

    @Override
    public boolean validate() {
        return Player.getPosition().distanceTo(new RSTile(3049, 4973, 1)) < 20
                && !Shopping.isShopOpen()
                && Inventory.getCount("Mark of grace") > 9;
    }

    @Override
    public void execute() {
        General.println("TradeGrace");
        grace = NPCs.findNearest("Grace");
        if (grace.length > 0) {
            if(grace[0].isOnScreen()) {
                Clicking.click("Trade", grace[0]);

            } else {
                Walking.walkTo(grace[0].getPosition());
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Player.isMoving();
                    }
                },12000);
            }
        } else {
            DPathNavigator dPathNavigator = new DPathNavigator();
            if(dPathNavigator.traverse(new RSTile(3049, 4973, 1))) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Player.isMoving();
                    }
                },12000);
            }
        }
    }
    // Grace is in loaded region and inventory has > 9 marks
}
