public class ComputerList {
    String itemID, itemDescription, unitPrice, inventory;

    public ComputerList(String itemID, String itemDescription, 
        String unitPrice, String inventory) {
            this.itemID = itemID;
	    this.itemDescription = itemDescription;	
            this.unitPrice = unitPrice;
            this.inventory = inventory;
    }

    public String getItemID(){
	
	return itemID;
    }
    
    @Override
    public String toString(){
        String str = itemID + " " + itemDescription + " " + unitPrice + " " 
		+ inventory + "\n";
	return str;
    }

}
