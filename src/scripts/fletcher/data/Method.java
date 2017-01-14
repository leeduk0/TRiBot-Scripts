package scripts.fletcher.data;

import org.tribot.api2007.Inventory;

/**
 * Created by Robin on 07/01/2017.
 */
public enum Method {
    FLETCH {
        @Override
        public boolean needsToBank(Item item) {
            return Inventory.getCount(item.getMaterialRequired()) == 0
                    || Inventory.getCount("Knife") == 0;
        }

        @Override
        public boolean hasInventoryResources(Item item) {
            return false;
        }
    },
    STRING {
        @Override
        public boolean needsToBank(Item item) {
            return Inventory.getCount(item.getMaterialRequired()) == 0
                    || Inventory.getCount("Bow string") == 0;
        }

        @Override
        public boolean hasInventoryResources(Item item) {
            return false;
        }
    };

    public abstract boolean needsToBank(Item item);

    public abstract boolean hasInventoryResources(Item item);
}
