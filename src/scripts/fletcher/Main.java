package scripts.fletcher;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.fletcher.data.Goal;
import scripts.fletcher.data.Item;

import java.util.ArrayList;
import java.util.List;

@ScriptManifest(authors = {"Cypher"}, name = "AIO Fletcher", category = "Fletching")
public class Main extends Script {

    private List<FletchingTask> fletchingTasks = new ArrayList<>();

    @Override
    public void run() {

        fletchingTasks.add(new FletchingTask(
                Item.ARROW_SHAFTS,
                Goal.LEVEL,
                99));

        while (!fletchingTasks.isEmpty() ) {
            General.println("Fletching tasks not empty");
            for(FletchingTask fletchingTask : fletchingTasks) {
                General.println("Looping through letching tasks");
                if(!fletchingTask.isComplete()) {
                    General.println("Executing: " + fletchingTask.toString());
                    fletchingTask.execute();
                } else {
                    General.println(fletchingTask.toString() + " - Complete! Removing...");
                    fletchingTasks.remove(fletchingTask);
                }
            }
            General.sleep(100);
        }
    }
}
