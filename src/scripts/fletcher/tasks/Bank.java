package scripts.fletcher.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import scripts.api.framework.Task;
import scripts.fletcher.FletchingTask;

/**
 * Created by Robin on 07/01/2017.
 */
public class Bank implements Task {

    private FletchingTask fletchingTask;

    public Bank(FletchingTask fletchingTask) {
        this.fletchingTask = fletchingTask;
    }

    @Override
    public boolean validate() {
        return !Banking.isBankScreenOpen() &&
                fletchingTask.getMethod().needsToBank(fletchingTask.getItem());
    }

    @Override
    public void execute() {
        RSNPC[] banker = NPCs.findNearest("Banker");
        if (banker != null && banker.length > 0) {
            if (Clicking.click("Bank", banker)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Banking.isBankScreenOpen();
                    }
                }, 2000);
            }
        }
    }
}
