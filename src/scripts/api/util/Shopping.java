package scripts.api.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

public class Shopping {

    private static final int PARENT = 300;
    private static final int MAIN_CHILD = 1;
    private static final int ITEMS_CHILD = 2;
    private static final int EXIT_BUTTON_COMPONENT = 11;
    private static final int SHOP_TITLE_COMPONENT = 1;

    public static boolean isShopOpen() {
        return Interfaces.get(PARENT) != null;
    }

    public static RSItem[] getAllItems() {
        ArrayList<RSItem> items = new ArrayList<>();
        RSInterfaceChild itemsChild = Interfaces.get(PARENT, ITEMS_CHILD);
        if(itemsChild != null) {
            RSInterfaceComponent[] components = itemsChild.getChildren();
            for(RSInterfaceComponent component : components) {
                if(component.getComponentItem() == 6512) {
                    break;
                }
                items.add(new RSItem(component.getComponentName(),
                        component.getActions(),
                        component.getIndex(),
                        component.getComponentItem(),
                        component.getComponentStack(),
                        RSItem.TYPE.OTHER));
                // Adds location of square to item.
                items.get(component.getIndex()).setArea(component.getAbsoluteBounds());
            }
            return items.toArray(new RSItem[items.size()]);
        }
        return null;
    }


    public static String getShopName(){
        if (isShopOpen()) {
            return Interfaces.get(PARENT, MAIN_CHILD).getChild(SHOP_TITLE_COMPONENT).getText();
        }
        return null;
    }
    public static boolean close(){
        if (isShopOpen()) {
            return Interfaces.get(PARENT, MAIN_CHILD).getChild(EXIT_BUTTON_COMPONENT).click("Close");
        }
        return false;
    }


    public static RSItem[] get(String... name){
        ArrayList<RSItem> items = new ArrayList<>();
        for (RSItem item : getAllItems()) {
            RSItemDefinition definition = item.getDefinition();
            if(definition != null && Arrays.asList(name).contains(definition.getName())){
                items.add(item);
            }
        }
        return items.toArray(new RSItem[items.size()]);
    }

    public static RSItem[] get(int... ids){
        ArrayList<RSItem> items = new ArrayList<>();
        for (RSItem i : getAllItems()) {
            for(int id : ids) {
                if(i.getID() == id || i.getID() == id - 1) {
                    items.add(i);
                }
            }
        }
        return items.toArray(new RSItem[items.size()]);
    }

    public static int getCount(String... name){
        int i = 0;
        for (RSItem item : get(name)) {
            i += item.getStack();
        }
        return i;
    }
    public static int getCount(int... id){
        int i = 0;
        for (RSItem item : get(id)) {
            i += item.getStack();
        }
        return i;
    }

    public static boolean buy(int count, String... names){
        //Buy an item from the shop
        for(RSItem item : get(names)){
            if(item.getStack() > 0){
                if(count >= 10){
                    return item.click("Buy 10");
                }else if(count >= 5){
                    return item.click("Buy 5");
                }else{
                    return item.click("Buy 1");
                }
            }
        }
        return false;
    }
    public static boolean buy(int count, int... ids){
        //Buy an item from the shop
        for(RSItem item : get(ids)){
            if(item.getStack() > 0){
                if(count >= 10){
                    return item.click("Buy 10");
                }else if(count >= 5){
                    return item.click("Buy 5");
                }else{
                    return item.click("Buy 1");
                }
            }
        }
        return false;
    }
    public static boolean sell(int count, String... names){
        RSItem[] items = Inventory.find(names);
        if(items.length > 0){
            if(count >= 10){
                return items[0].click("Sell 10");
            }else if(count >= 5){
                return items[0].click("Sell 5");
            }else{
                return items[0].click("Sell 1");
            }
        }
        return false;
    }
    public static boolean sell(int count, int id){
        RSItem[] items = Inventory.find(id);
        if(items.length > 0) {
            int amountSold = 0;
            int leftToSell = 0;
            RSItem toSell = items[0];
            while(amountSold < count && Inventory.find(id).length > 0)
            {
                leftToSell = (count - amountSold);
                if(leftToSell >= 10 && toSell.click("Sell 10"))
                {
                    amountSold += 10;
                }
                else if(leftToSell >= 5 && toSell.click("Sell 5"))
                {
                    amountSold += 5;
                }
                else if(leftToSell >= 1 && toSell.click("Sell 1"))
                {
                    amountSold += 1;
                }

                General.sleep(General.random(600, 800));
            }
        }
        return false;
    }
}