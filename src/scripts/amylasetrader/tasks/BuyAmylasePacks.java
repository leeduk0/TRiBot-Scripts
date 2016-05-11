package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;
import scripts.api.util.Shopping;


/**
 * Created by Robin on 10/05/2016.
 */
public class BuyAmylasePacks implements Task {

    private MainScript script;

    public BuyAmylasePacks(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        return Shopping.isShopOpen()
                && Inventory.getCount("Mark of grace") > 9
                && !Inventory.isFull();
    }

    @Override
    public void execute() {
        General.println("BuyAmylasePacks");
        if (Shopping.buy(10, "Amylase pack")) {
            Timing.waitChooseOption("Buy", 2000);
        }

    }
}
