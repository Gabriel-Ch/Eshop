
public class ItemOrdered
{
    private Item item;
    private int quantity;
    
    public ItemOrdered(Item item,int quantity)
    {
       this.item = item;
       this.quantity = quantity;
    }
    
    public Item getItem()
    {
        return item;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public String toString()
    {
        return  "Item: " + item.getName() + "  " + " Code: " + item.getID() + "  " + " Category: " + item.getClass().getName()   +  "  " + " Quantity: " +  quantity;
         
    }
    
    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
}
