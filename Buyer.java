
public class Buyer extends User
{
    private static int bonus = 0;
    private static String[] buyerCategory = {"BRONZE","SILVER","GOLD"};
    private ShoppingCart shoppingBasket = new ShoppingCart();
    private static String buyerStatus = buyerCategory[0];
    
    public Buyer(String name,String email)
    {
        super(name,email);
    }
    
    public static void awardBonus(int extra)
    {
      bonus += extra;
    }
    
    public void setbuyerCategory()
    {
       if(bonus > 100 && bonus < 200)
       {
           buyerStatus = buyerCategory[1];
       }
       else if(bonus > 200)
       {
           buyerStatus = buyerCategory[2];
       }
    }
    
    public void placeOrder(ItemOrdered itemList) throws NoStockAvailableException
    {
        try
        {
           shoppingBasket.addItemOrdered(itemList);
        }
        catch(NoStockAvailableException e)
        {
            throw new NoStockAvailableException();
        }
    }
    
    public String toString()
    {
        return "Name: " + super.getName() + "\n" + "Points: " + bonus + "\n"  + "Category: " + buyerStatus;
    }
    
    
    public String getBuyerStatus()
    {
        return buyerStatus;
    }
    
    public ShoppingCart getShoppingBasket()
    {
        return shoppingBasket;
    }
    
    public String getStatus()
    {
        return "Name:  " + super.getName() + "   " + "Points: " + bonus + "   "  + "Category:  " + buyerStatus;
    }
}
