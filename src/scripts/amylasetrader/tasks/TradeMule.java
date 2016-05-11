package scripts.amylasetrader.tasks;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Players;
import org.tribot.api2007.Trading;
import org.tribot.api2007.types.RSPlayer;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;

/**
 * Created by Robin on 10/05/2016.
 */
public class TradeMule implements Task {

    private MainScript script;
    private RSPlayer[] mule;

    public TradeMule(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        mule = Players.findNearest(script.getMuleName());
        return mule.length > 0;
    }

    @Override
    public void execute() {
        script.setReadyToTrade(true);
        if (!isTradeWindowOpen()) {
            if (mule[0].click("Trade")) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return isTradeWindowOpen();
                    }
                }, 10000);
            }
        } else if (Trading.getWindowState() == Trading.WINDOW_STATE.FIRST_WINDOW) {
            if (Trading.offer(Inventory.getCount("Amylase crystal"), "Amylase crystal")) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.getCount("Amylase crystal") == 0;
                    }
                }, 3000);
            }

            if (Trading.hasAccepted(true)) {
                if (Trading.accept()) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Trading.getWindowState() == Trading.WINDOW_STATE.SECOND_WINDOW;
                        }
                    }, 10000);
                }
            }

        } else if (Trading.getWindowState() == Trading.WINDOW_STATE.SECOND_WINDOW) {
            if (Trading.hasAccepted(true)) {
                if (Trading.accept()) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return !isTradeWindowOpen();
                        }
                    }, 10000);
                    script.setReadyToTrade(false);
                }
            }
        }
    }

    private boolean isTradeWindowOpen() {
        return Trading.getWindowState() != null;
    }
    //
}
