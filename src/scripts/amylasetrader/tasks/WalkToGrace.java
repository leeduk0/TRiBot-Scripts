package scripts.amylasetrader.tasks;

import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;

/**
 * Created by Robin on 11/05/2016.
 */
public class WalkToGrace implements Task {

    private MainScript script;

    public WalkToGrace(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void execute() {

    }
}
