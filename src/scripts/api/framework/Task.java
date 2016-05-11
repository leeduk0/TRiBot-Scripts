package scripts.api.framework;

/**
 * Created by Robin on 10/05/2016.
 */
public interface Task {
    boolean validate();
    void execute();
}
