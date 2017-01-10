package scripts.fletcher.data;

/**
 * Created by Robin on 07/01/2017.
 */
public enum Item {
    ARROW_SHAFTS(1, "Arrows shafts",  "Logs", Method.FLETCH, ItemType.ARROWS),
    NORMAL_SHORTBOW_U(5, "Shortbow (u)", "Logs", Method.FLETCH, ItemType.SHORTBOW),
    NORMAL_SHORTBOW(5, "Shortbow", "Shortbow (u)", Method.STRING, ItemType.SHORTBOW);


    private int requiredLevel;
    private String itemName;
    private String materialRequired;
    private Method method;
    private ItemType itemType;

    Item(int requiredLevel, String itemName, String materialRequired, Method method, ItemType itemType) {
        this.requiredLevel = requiredLevel;
        this.itemName = itemName;
        this.materialRequired = materialRequired;
        this.method = method;
        this.itemType = itemType;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public String getItemName() {
        return itemName;
    }

    public String getMaterialRequired() {
        return materialRequired;
    }

    public Method getMethod() {
        return method;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
