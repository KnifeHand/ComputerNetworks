public class ComputerList {
    String itemID, itemDescription, unitPrice, inventory;

    public ComputerList(String itemID, String itemDescription, 
        String unitPrice, String inventory) {
            this.itemID = itemID;
            this.itemDescription = itemDescription;
            this.unitPrice = unitPrice;
            this.inventory = inventory;
    }
    
    @Override
    public String toString() {
        return itemID + itemDescription + unitPrice + inventory;
    }

}