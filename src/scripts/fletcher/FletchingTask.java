package scripts.fletcher;

import org.tribot.api.General;
import org.tribot.api2007.Skills;
import scripts.api.framework.Task;
import scripts.fletcher.data.Goal;
import scripts.fletcher.data.Item;
import scripts.fletcher.data.Method;
import scripts.fletcher.tasks.Bank;
import scripts.fletcher.tasks.Fletch;
import scripts.fletcher.tasks.Restock;
import scripts.fletcher.tasks.StringBows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FletchingTask {

    private Item item;
    private Goal goal;
    private Method method;

    public Method getMethod() {
        return method;
    }

    private int goalTarget;

    private List<Task> tasks = new ArrayList<>();

    public FletchingTask(Item item, Goal goal, int goalTarget) {

        this.item = item;
        this.goal = goal;
        this.goalTarget = goalTarget;

        addSubTasks();

    }

    public Item getItem() {
        return item;
    }

    public Goal getGoal() {
        return goal;
    }

    public int getGoalTarget() {
        return goalTarget;
    }

    private void addSubTasks() {
        switch (item.getMethod()) {
            case FLETCH:
                Collections.addAll(
                        tasks,
                        new Fletch(this),
                        new Bank(this));
                break;
            case STRING:
                Collections.addAll(
                        tasks,
                        new StringBows(this),
                        new Bank(this));
        }

        if (goal == goal.GRAND_EXCHANGE) {
            tasks.add(new Restock(this));
        }
    }

    public boolean isComplete() {
        switch(goal) {
            case LEVEL:
                return Skills.getActualLevel(Skills.SKILLS.FLETCHING) >= goalTarget;
            case AMOUNT:
                return 0 == goalTarget; // TODO: Edit 0 with variable which stores total made
            case GRAND_EXCHANGE:
                return false;
        }
        return false;
    }

    public void execute() {
        for (Task task : tasks) {
            if (task.validate()) {
                task.execute();
            }
        }
        General.sleep(100);
    }

    @Override
    public java.lang.String toString() {
        return "FletchingTask{" +
                " Item=" + item +
                ", Goal=" + goal +
                ", goalTarget=" + goalTarget +
                '}';
    }
}
