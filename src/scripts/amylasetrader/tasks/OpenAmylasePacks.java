package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSItem;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.Shopping;

/**
 * Created by Robin on 10/05/2016.
 */
public class OpenAmylasePacks implements Task {

    private MainScript script;

    public OpenAmylasePacks(MainScript script) {
        this.script = script;
    }
    @Override
    public boolean validate() {
        return Inventory.isFull()
                && Inventory.getCount("Amylase pack") > 0;
    }

    @Override
    public void execute() {
        General.println("OpenAmylasePacks");
        if(Shopping.isShopOpen()) {
            if(Shopping.close()) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Shopping.isShopOpen();
                    }
                }, 3000);
            }
        }

        RSItem[] packs = Inventory.find("Amylase pack");
        if(packs.length > 0) {
            for(RSItem pack : packs) {
                if(pack.click("Open")) {
                    Timing.waitChooseOption("Open", 2000);
                }
            }
        }
    }
}
