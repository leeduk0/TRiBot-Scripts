package scripts.amylasetrader.tasks;

import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;

/**
 * Created by Robin on 10/05/2016.
 */
public class TradeMule implements Task {

    private MainScript script;

    public TradeMule(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        return script.isReadyToTrade() ;
    }

    @Override
    public void execute() {

    }
    //
}
