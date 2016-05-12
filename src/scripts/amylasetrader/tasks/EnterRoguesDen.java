package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;
import scripts.amylasetrader.MainScript;
import scripts.amylasetrader.data.Areas;
import scripts.api.framework.Task;

/**
 * Created by Robin on 10/05/2016.
 */
public class EnterRoguesDen implements Task {

    private MainScript script;

    public EnterRoguesDen(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        if (Login.getLoginState() == Login.STATE.INGAME) {
            return Areas.BURTHORPE_INN.contains(Player.getPosition());
        }
        return false;
    }

    @Override
    public void execute() {
            RSObject[] trapdoor = Objects.findNearest(10, "Trapdoor");
            if(trapdoor.length > 0) {
                if(trapdoor[0].isOnScreen()) {
                    if(trapdoor[0].click("Enter")) {
                        Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                General.sleep(200);
                                return Player.getPosition().getPlane() == 0;
                            }
                        }, 5000);
                    }
                } else {
                    Walking.walkTo(trapdoor[0].getPosition());
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(200);
                            return Player.isMoving();
                        }
                    }, 12000);
                }
            }






    }
}
