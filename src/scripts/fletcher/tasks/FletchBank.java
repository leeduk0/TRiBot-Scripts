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
public class FletchBank implements Task {

    private FletchingTask fletchingTask;

    public FletchBank(FletchingTask fletchingTask) {
        this.fletchingTask = fletchingTask;
    }

    @Override
    public boolean validate() {
        return (Inventory.getCount(
                fletchingTask.getItem().getItemName()) > 0 &&
                Inventory.getCount(fletchingTask.getItem().getMaterialRequired()) == 0) || Inventory.getCount("Knife") == 0;
    }

    @Override
    public void execute() {
        if(!Banking.isBankScreenOpen()) {
            RSNPC[] banker = NPCs.findNearest("Banker");
            if(banker != null && banker.length > 0) {
                if(Clicking.click("FletchBank", banker)) {
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
}
