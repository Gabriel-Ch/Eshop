
public class Pencil extends Item
{
    private double tipSize;
    private String[] type = {"H","B","HB"};
    private String typeSelected ;
    
    public Pencil(String name,double price,String description,int stock,int id,double tipSize,String typeSelected)
    {
        super(name,price,description,stock,id);
        this.tipSize = tipSize;
        
        switch(typeSelected)
        {
            case "H":
            this.typeSelected = type[0];
            break;
            case "B":
            this.typeSelected = type[1];
            break;
            case "HB":
            this.typeSelected = type[2];
            break;
            default:
            this.typeSelected = "Sorry but this is not an available pencil type!!!";
            break;
        }
    }
    
    public String getDetails()
    {
        return "  \tType:  " + typeSelected  + " \tTip Size:  " + tipSize;
    }
}
