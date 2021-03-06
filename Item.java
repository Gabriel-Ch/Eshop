
public abstract class Item
{
    private String name;
    private double price;
    private String description;
    private int stock;
    private int id;
    
    public Item(String name,double price,String description,int stock,int id)
    {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.id = id;
    }
    
    public String getBasicInfo()
    {
        return "Name: " + name + "\tPrice: " + price + "€" + "\tDescription: " + description + "  \tStock: " + stock +  "\tID Code: " + id;
    }
    
    public abstract String getDetails();
    
    public String toString()
    {
        return  getBasicInfo()  +  getDetails(); 
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setStock(int newStock)
    {
        stock = newStock;
    }
    
    public int getStock()
    {
        return stock;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String showProducts()
    {
        return "Code: " + id +  "   " +"Name: " + name ;
    }
}
