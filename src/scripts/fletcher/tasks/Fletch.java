package scripts.fletcher.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Player;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSItem;
import scripts.api.framework.Task;
import scripts.fletcher.FletchingTask;
import scripts.fletcher.util.Conditions;
import scripts.fletcher.util.Utils;

public class Fletch implements Task {

    private FletchingTask fletchingTask;

    private static final int FLETCHING_INTERFACE_ID = 306;
    public static final int ARROW_CHILD_ID = 7;
    public static final int SHORTBOW_INTERFACE_ID = 15;
    private final int LONG_BOW_INTERFACE_ID = 19;


    public Fletch(FletchingTask fletchingTask) {
        this.fletchingTask = fletchingTask;
    }

    @Override
    public boolean validate() {
        return hasRequiredItems();
    }

    @Override
    public void execute() {

        RSItem[] knife = Inventory.find("Knife");
        RSItem[] log = Inventory.find(fletchingTask.getItem().getMaterialRequired());

        if (knife.length > 0 && log.length > 0) {
            if (!Utils.isItemSelected("Knife")
                    && Clicking.click("Use", knife)) {
                Timing.waitCondition(Conditions.itemSelected("Knife"), General.random(500, 1000));
            }

            if (Utils.isItemSelected("Knife")) {

                Clicking.click(log);
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Interfaces.get(306) != null;
                    }
                }, 1200);

                int itemTypeInterfaceChildID = getItemTypeInterfaceChildID();
                RSInterface child = Interfaces.get(FLETCHING_INTERFACE_ID, itemTypeInterfaceChildID);

                if (child != null && child.click("Make X")) {

                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Utils.enterAmountIsOpen();
                        }
                    }, 1200);

                    if (Utils.enterAmountIsOpen()) {
                        General.println("Enter amount interface not null");
                        Keyboard.typeSend("99");
                        General.println("Sleeping while fletching");
                        General.sleep(2000);
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                General.sleep(200);
                                return Player.getAnimation() == -1;
                            }
                        }, 60000);

                    }

                }

            }

        }

    }

    private int getItemTypeInterfaceChildID() {
        switch (fletchingTask.getItem().getItemType()) {
            case ARROWS:
                return ARROW_CHILD_ID;
            case SHORTBOW:
                return 0;
            // TODO: Add more cases after testing
        }
        return 0;
    }

    private boolean hasRequiredItems() {
        String requiredLog = fletchingTask.getItem().getMaterialRequired();
        return Inventory.getCount("Knife") > 0 &&
                Inventory.getCount(requiredLog) > 0;
    }

}
