
public class Pen extends Item
{
    private String color;
    private double tipSize;
    
    public Pen(String name,double price,String description,int stock,int id,String color,double tipSize)
    {
        super(name,price,description,stock,id);
        this.color = color;
        this.tipSize = tipSize;
    }
    
    public String getDetails()
    {
        return "  \tColor: " + color + " \tTip Size: " + tipSize; 
    }
    
}
