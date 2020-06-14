# Eshop


public class Main
{
    public static void main()
    {
        Owner owner = new Owner("Aris","arisgialenios@gmail.com");
        EShop eshop = new EShop("Gwnia tou Vivliou",owner);
        Buyer b1 = new Buyer("Giannis","giannisgialenios@gmail.com");
        Buyer b2 = new Buyer("Panagiwths","pgialenios@gmail.com");
        
        try
        {
            eshop.addBuyer(b1);
            eshop.addBuyer(b2);
        }
        catch(AddTheSameBuyerException e)
        {
            
        }
        
        Pen pen1 = new Pen("PILOT",1.5,"red pen",20,1250,"red",0.5);
        Pen pen2 = new Pen("BIC",1.5,"blue pen",17,2032,"blue",0.5);
        Pen pen3 = new Pen("BIC",1.5,"black pen",25,4531,"black",0.5);
        Pencil p1 = new Pencil("Faber Castell",1.8,"BEST SALE",30,2357,12,"HB");
        Pencil p2 = new Pencil("Staedtler",1.3,"yellow pencil",15,3502,11,"B");
        Pencil p3 = new Pencil("Faber Castell",1.5,"Blue pencil",20,6432,12,"H");
        Notebook note1 = new Notebook("Salco Paper",3.75,"Color Line",20,7834,3);
        Notebook note2 = new Notebook("Typotrast",4.55,"Black Edition",15,4379,4);
        Notebook note3 = new Notebook("Scag",2.86,"Math Edition",20,5603,2);
        Paper paper1 = new Paper("HP",2.85,"A4",40,1259,80,200);
        Paper paper2 = new Paper("Fabriano",3.95,"A3",30,4312,120,250);
        Paper paper3 = new Paper("Mondi",2.50,"A4",35,6792,100,200);
        
        
        try
        {
            eshop.addItem(pen1);
            eshop.addItem(pen2);
            eshop.addItem(pen3);
            eshop.addItem(p1);
            eshop.addItem(p2);
            eshop.addItem(p3);
            eshop.addItem(note1);
            eshop.addItem(note2);
            eshop.addItem(note3);
            eshop.addItem(paper1);
            eshop.addItem(paper2);
            eshop.addItem(paper3);
        }
        catch(AddTheSameItemException e)
        {  
            
        }
        catch(AddTheSameIDException e)
        {
            
        }
        
        Menu menu = new Menu(eshop);
        menu.pack();
        menu.resize(5000,5000);
        menu.setVisible(true);
        
    }
}








public abstract class User
{
    private String name;
    private String email;
    
    public User(String name,String email)
    {
        this.name = name;
        this.email = email;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getEmail()
    {
        return email;
    }
}






public class Owner extends User
{
   private boolean isAdmin = true;  
   
   public Owner(String name,String email)
   {
       super(name,email);
   }
   
   public boolean getIsAdmin()
   {
       return isAdmin;
   }
   
   public String toString()
   {
       return "Name: " + super.getName() + "\n" + "Email: " + super.getEmail() + "\n" + "Admin: " + isAdmin;
   }
}





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



public class Notebook extends Item
{
    private int sections;
    
    public Notebook(String name,double price,String description,int stock,int id,int sections)
    {
        super(name,price,description,stock,id);
        this.sections = sections;
    }
    
    public String getDetails()
    {
        return "  \tSections: " + sections;
    }
}





public class Paper extends Item
{
    private int weight;
    private int pages;
    
    public Paper(String name,double price,String description,int stock,int id,int weight,int pages)
    {
        super(name,price,description,stock,id);
        this.weight = weight;
        this.pages = pages;
    }
    
    public String getDetails()
    {
        return "  \tWeight: " + weight  + " \tPages: " + pages;
    }
}








import java.util.ArrayList;

public class EShop
{
    private String name;
    private static Owner owner;
    private boolean emailChecking;
    private boolean ownerChecking;
    private int index;
    static ArrayList<Item> itemsList = new ArrayList<Item>();
    static ArrayList<Buyer> buyersList = new ArrayList<Buyer>();
    ArrayList<Object>  pencilList = new ArrayList<Object>();
    ArrayList<Object>  paperList = new ArrayList<Object>();
    ArrayList<Object> penList = new ArrayList<Object>();
    ArrayList<Object> noteList = new ArrayList<Object>();
    ArrayList<Object> items = new ArrayList<Object>();
    ArrayList<Object> buyers = new ArrayList<Object>();
    
    
    public EShop(String name,Owner owner)
    {
        this.name = name;
        this.owner = owner;
        
    }
 
    public void addItem(Item item) throws AddTheSameItemException,AddTheSameIDException
    {
       if(itemsList.contains(item))
       {
          throw new AddTheSameItemException(item,itemsList);
       }
       
       for(Item items: itemsList)
       {
           if(item.getID() == items.getID())
           {
               throw new AddTheSameIDException(item,items,itemsList);
           }
       }
        
       itemsList.add(item);
    } 
    
    public Item getItemByld(int id) throws WrongIDException
    {
       try
       { 
         for(Item items: itemsList)
         {
             if(id == items.getID())
             {
                 id = itemsList.indexOf(items);
             }
         }
          
         return itemsList.get(id);
       }
       catch(IndexOutOfBoundsException e)
       {
           throw new WrongIDException();
       }
    }
    
    public void removeItem(Item item)
    {
        itemsList.remove(item);
    }
    
    public static void addBuyer(Buyer buyer) throws AddTheSameBuyerException 
    {
        if(buyersList.contains(buyer))
        {
            throw new AddTheSameBuyerException();
        }
        
        buyersList.add(buyer);
    }
    
    public void removeBuyer(Buyer buyer)
    {
        buyersList.remove(buyer);
    }
    
    public void updateItemStock(Item item,int newStock) throws NotAQuantityException 
    {
        if(newStock < 0)
        {
            throw new NotAQuantityException();
        }
        
        item.setStock(newStock);
    }
    
    public void showCategories()
    {
        for(Object items: itemsList)
        {
           System.out.println(items.getClass().getName());
        }
    }
    
    public void showProductsInCategory(String epilogh)
    {
    
        for(Object items: itemsList)
        {
            String category = items.getClass().getName(); 
            
            switch(category)
            {
                case "Pen": 
                penList.add(items);
                break;
                
                case "Pencil":
                pencilList.add(items);
                break;
                
                case "Paper":
                paperList.add(items);
                break;
                
                case "Notebook":
                noteList.add(items);
                break;
            }
        }
        
        switch(epilogh)
        {
            case "3.Pen":
            if(! penList.isEmpty())
            {
              for(Object pen: penList)
              {
                 String item = (showProduct((Pen)pen));
                 items.add(item);
              }
            }
            else
            {
                String item ="This Category has no products";
                items.add(item);
            }
            break;
            
            case "4.Pencil":
            if(! pencilList.isEmpty())
            {
              for(Object pencil: pencilList)
              {
                 String item = (showProduct((Pencil)pencil));
                 items.add(item);
              }
            }
            else
            {
               String item ="This Category has no products";
               items.add(item);
            }
            break;
            
            case "2.Paper":
            if(! paperList.isEmpty())
            {
              for(Object paper: paperList)
              {
                String item = (showProduct((Paper)paper));
                items.add(item);
              }
            }
            else
            {
               String item ="This Category has no products";
               items.add(item);
            }
            break;
            
            case "1.Notebook":
            if(! noteList.isEmpty())
            {
              for(Object notebook: noteList)
              {
                String item = (showProduct((Notebook)notebook));
                items.add(item);
              }
            }
            else
            {
               String item ="This Category has no products";
               items.add(item);
            }
            break;
        }
    }
     
    public String showProduct(Item item)
    {
       int orderNumber = itemsList.indexOf(item);
       
       String product;
       
       if(orderNumber > 9)
       {
          product = "Order Number: " + "00" + orderNumber + "   " + itemsList.get(orderNumber).showProducts();
       }
       else if(orderNumber > 99)
       {
           product = "Order Number: " + "0" + orderNumber + "   " + itemsList.get(orderNumber).showProducts();
       }
       else if(orderNumber > 999)
       {
           product = "Order Number: " + orderNumber + "   " + itemsList.get(orderNumber).showProducts();
       }
       else
       {
           product = "Order Number: " + "000" + orderNumber + "   " + itemsList.get(orderNumber).showProducts();
       }
       
       return product;
    }
    
    public void checkStatus()
    {
        for(Buyer buyer: buyersList)
        {
            if( ! buyersList.isEmpty() )
            {
                String status = buyer.getStatus();
                buyers.add(status);
            }
            else
            {
                 String status = "The shop has none registrations yet ";
                 buyers.add(status);
            }
        }
    }
    
    public ArrayList getBuyersList()
    {
        return buyersList;
    }
    
    public ArrayList getItemsList()
    {
        return itemsList;
    }
    
     public ArrayList getPaperList()
    {
        return paperList;
    }
    
    public ArrayList getPencilList()
    {
        return pencilList;
    }
    
    public ArrayList getPenList()
    {
        return penList;
    }
    
    public ArrayList getNoteList()
    {
        return noteList;
    }
    
    public ArrayList getItems()
    {
        return items;
    }
    
    public String getEshopName()
    {
        return name;
    }
    
    public Owner getOwner()
    {
        return owner;
    }
    
    public boolean emailCheck(String email)
    {
        
        for(Buyer buyer: buyersList)
        {               
            if (email.equals(buyer.getEmail()))
            {
                emailChecking = true;
            }
            else
            {
                emailChecking = false;
            }
        }
        
        return emailChecking;
    }
    
    public boolean ownerCheck(String email)
    {        
       if (email.equals(owner.getEmail()))
       {
           ownerChecking = true;
       }
       else
       {
           ownerChecking = false;
       }
        
        
       return ownerChecking;
    }
    
    public Buyer getBuyer(String email)
    {
        for(Buyer buyer: buyersList)
         {   
            if (email.equals(buyer.getEmail()))
            {
                index = buyersList.indexOf(buyer);
            }
        }
         
        return buyersList.get(index);
    }
    
    public ArrayList getBuyers()
    {
        return buyers;
    }
}




import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
 
public class ShoppingCart
{
    ArrayList<ItemOrdered> orderList = new ArrayList<ItemOrdered>();
    private static double total = 0;
    private double transport;
    private Scanner eisodos = new Scanner(System.in);
    ArrayList<String> List1 = new ArrayList<String>();
    static ArrayList<String> List2 = new ArrayList<String>();
    
    public void addItemOrdered(ItemOrdered itemList) throws NoStockAvailableException
    {
        if(itemList.getQuantity() < itemList.getItem().getStock() )
        {
           int newStock = itemList.getItem().getStock() - itemList.getQuantity();
           itemList.getItem().setStock(newStock);
        }
        else
        {
            throw new NoStockAvailableException(); 
        }
        
        if(orderList.contains(itemList.getItem()))
        {
            int newQuantity = itemList.getQuantity() + orderList.get(orderList.indexOf(itemList.getItem())).getQuantity();
        } 
        
        orderList.add(itemList);
    }
    
    public void removeItemOrdered(ItemOrdered itemList)
    {
        orderList.remove(itemList);
        
        int newStock = itemList.getItem().getStock() + itemList.getQuantity();
        itemList.getItem().setStock(newStock);
    }
    
    public void changeItemOrderedQuantity(EShop eshop,ItemOrdered itemList,int newQuantity) throws NotAQuantityException
    {
            ItemOrdered order = orderList.get(orderList.indexOf(itemList));
        
            if(newQuantity < order.getItem().getStock())
            {  
              int newStock;
              
              if(newQuantity >= order.getQuantity() )
              {
                  newStock = order.getItem().getStock() - (newQuantity - order.getQuantity());
              }
              else 
              {
                  newStock = order.getItem().getStock() + (order.getQuantity() - newQuantity);
              }
               
              order.setQuantity(newQuantity); 
              
              try
              {
                 eshop.updateItemStock(order.getItem(),newStock);
              }
              catch(NotAQuantityException e)
              {
                   throw new NotAQuantityException();  
              } 
            }
    }
    
    public void showCart(Buyer buyer)
    {
           for(ItemOrdered itemList: orderList)
           {
            String list = " " + itemList;
            List1.add(list); 
           }
    }
    
    public void clearCart()
    {
        int j=0;
       
        for(int i=0;i < (orderList.size() + j);i++)
        {             
            removeItemOrdered(orderList.get(i - j));
            j++;
        }
    }
    
    public void checkout(Buyer buyer,int list,int answer)
    {
       int bonus;
        
        if(list == JOptionPane.YES_OPTION)
        {
            for(ItemOrdered itemList: orderList)
            {
              String line = itemList + "\n" + "Price: " + itemList.getItem().getPrice() + " x " + itemList.getQuantity();
              List2.add(line);
            
              total += itemList.getItem().getPrice() * itemList.getQuantity();
            }
        
            String gap = ".................";
            String cost = "Cost: " + total + " euro" + "\n" + "Courier's cost: " + calculateCourierCost(buyer) + " euro " + "(" + buyer.getBuyerStatus() + ")" + "\n" + "Total: " + calculateNet();
            List2.add(gap);
            List2.add(cost);
       }
        
       if(answer == JOptionPane.YES_OPTION)
        {
            bonus = (int)getTotal() / 10 ;
            buyer.awardBonus(bonus);
            
            orderList.removeAll(orderList);
            total = 0;
       }
    }
    
    public double calculateNet()
    {
        double sum = total + transport;
        return sum;
    }
    
    public double calculateCourierCost(Buyer buyer)
    {
        transport = total / 50;
        
        if ( transport < 3)
        {
            transport = 3;
        }
        
        buyer.setbuyerCategory();
        
        switch(buyer.getBuyerStatus())
        {
            case "GOLD":
            
            transport = 0;
            break;
            
            case "SILVER":
            
            transport /= 2;
            break;
            
            case "BRONZE":            
            break;
        }
        
        return transport;
    }
    
    public ArrayList getList1()
    {
        return List1;
    }
    
    public ArrayList getOrderList()
    {
        return orderList;
    }
    
    public ArrayList getList2()
    {
        return List2;
    }
    
    public double getTotal()
    {
        return total;
    }
}






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




import java.util.ArrayList;

public class AddTheSameItemException extends Exception
{ 
    public AddTheSameItemException(Item item,ArrayList itemsList)
    {
       System.out.println("You have already add the product " + item.getID() + " once");
    }
}




public class AddTheSameBuyerException extends Exception
{    
}



public class NoStockAvailableException extends Exception
{    
}




public class NoStockAvailableException extends Exception
{    
}



public class NoStockAvailableException extends Exception
{    
}



import java.util.Scanner;
import java.util.ArrayList;

public class AddTheSameIDException extends Exception
{
   public AddTheSameIDException(Item item1,Item item2,ArrayList itemList)
   {
      Scanner eisodos = new Scanner(System.in);
       
      System.out.println("An other product in the shop has the same ID Code!!!");
      System.out.println("Do you want to replace it;(1=YES,2=NO)");
      int apanthsh = eisodos.nextInt();
      
      if(apanthsh == 1)
      {
          itemList.set(itemList.indexOf(item2),item1);
      }
      else
      {
          System.out.println("You choose not to replace the specific product");
      }
   }
}






import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame  
{
    private String email = " ";
    private Buyer B1;
    private int check = 0;
    public  Menu(EShop eshop)
    {
        super(eshop.getEshopName());  
               
        while(email != null)
        {
            email = JOptionPane.showInputDialog(null,"Enter your email: ","USER INPUT",JOptionPane.INFORMATION_MESSAGE);
        
            if (!eshop.ownerCheck(email)  &&  !eshop.emailCheck(email))
            {
               int registration = JOptionPane.showConfirmDialog(null,"Do you want to register to our shop? ","NEW BUYER!!!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            
               if (registration == JOptionPane.YES_OPTION)
               {
                   try
                   {
                    String newName = JOptionPane.showInputDialog(null,"Enter your name: ","USER INPUT",JOptionPane.INFORMATION_MESSAGE);
                    String newEmail = JOptionPane.showInputDialog(null,"Enter your email: ","USER INPUT",JOptionPane.INFORMATION_MESSAGE);
                    
                    B1 = new Buyer(newName,newEmail);
                    
                    eshop.addBuyer(B1);
                    
                    JOptionPane.showMessageDialog(null,"You saccesfully registered to our shop!!!","NEW BUYER",JOptionPane.INFORMATION_MESSAGE);
                   }
                   catch(AddTheSameBuyerException e)
                   {
                     String sameBuyer = B1.getName() + " has already been registered in the shop as a buyer!!!";  
                     
                     JOptionPane.showMessageDialog(null,sameBuyer,"USER INPUT",JOptionPane.INFORMATION_MESSAGE);
                   }
               }
               else
               {
                   JOptionPane.showMessageDialog(null,"As a guest you can only browze in the shop and see products","GUEST",JOptionPane.INFORMATION_MESSAGE);
                   int guest = JOptionPane.showConfirmDialog(null,"Are you sure you want to enter as a guest? ","GUEST",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                   
                   if (guest == JOptionPane.YES_OPTION)
                   {
                        email = null;
                   }
               }
            }
            else if ( eshop.ownerCheck(email) )
            {
               String status = " " + eshop.getOwner() ;
               String greetings = "Welcome " + eshop.getOwner().getName();
               
               
               JOptionPane.showMessageDialog(null,status,greetings,JOptionPane.INFORMATION_MESSAGE);
               
               email = null;
               check++;
            }
            else if( !eshop.ownerCheck(email)  &&  eshop.emailCheck(email))
            {
                
               B1 = eshop.getBuyer(email);
               String status = " "  + B1;
               String greetings = "Welcome to our shop!!!!";
            
               JOptionPane.showMessageDialog(null,status,greetings,JOptionPane.INFORMATION_MESSAGE);
               
               email = null;
            }
        }
         
        
        if(check != 1)
        {
           Font f1 = new Font("Helvetica",Font.BOLD,14);
           setFont(f1);
           BorderLayout lay = new BorderLayout(20,0);
           FlowLayout lay1 = new FlowLayout(FlowLayout.LEFT,10,50);
           FlowLayout lay2 = new FlowLayout(FlowLayout.LEFT);
           FlowLayout lay3 = new FlowLayout(FlowLayout.LEFT,10,200);
           FlowLayout lay4 = new FlowLayout(FlowLayout.CENTER);
           FlowLayout lay5 = new FlowLayout(FlowLayout.LEFT,100,0);
        
        
           JButton b1 = new JButton();
           JButton b2 = new JButton("View Cart");
           JButton b3 = new JButton();
           JButton b4 = new JButton();
           JButton b5 = new JButton();
           JButton b6 = new JButton("Check Out");
           JButton b7 = new JButton("Back");
           JButton b8 = new JButton("Sign Out");
           JButton b9 = new JButton("Exit");
           JTextArea intro = new JTextArea("Select a Category to see products",20,15);
           JLabel l1 = new JLabel("Choose Category: ");
           JComboBox categories = new JComboBox();
           categories.addItem("1.Notebook");
           categories.addItem("2.Paper");
           categories.addItem("3.Pen");
           categories.addItem("4.Pencil");
           JPanel frame = new JPanel();
           JPanel frame1 = new JPanel();
           JPanel frame2 = new JPanel();
           JPanel frame3 = new JPanel();
           JPanel frame4 = new JPanel();
           JPanel frame5 = new JPanel();
           JPanel frame6 = new JPanel();
           JLabel l2 = new JLabel();
              
          categories.setEditable(false);
          intro.setEditable(false);
            
          ItemListener category = new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
            {
              eshop.getItems().removeAll(eshop.getItems());
              eshop.getPencilList().removeAll(eshop.getPencilList());
              eshop.getPenList().removeAll(eshop.getPenList());
              eshop.getPaperList().removeAll(eshop.getPaperList());
              eshop.getNoteList().removeAll(eshop.getNoteList());
                
              Object epilogh = e.getItem();
              eshop.showProductsInCategory(((String)epilogh));
              
              intro.setText(" ");
              
              if(eshop.getItemsList().isEmpty())
              {
                  intro.setText(((String)eshop.getItems().get(0)));
              }
              
              intro.setText(((String)eshop.getItems().get(0)) + "\n");

              for(int i=1;i < eshop.getItems().size();i++)
              {
                 intro.append(((String)eshop.getItems().get(i)) + "\n");
              }
             
              switch(((String)epilogh))
              {
                case "3.Pen":
                l2.setText("Products:" + "(" + eshop.getPenList().size() + ")");
                break;
                       
                case "2.Paper":
                l2.setText("Products:" + "(" + eshop.getPaperList().size() + ")");
                break;
                       
                case "1.Notebook":
                l2.setText("Products:" + "(" + eshop.getNoteList().size() + ")");
                break;
                       
                case "4.Pencil":
                l2.setText("Products:" + "(" + eshop.getPencilList().size() + ")");
                break;
              }
             
              frame2.add(b1);
              b1.setText("View Details");
             
             
              ActionListener back = new ActionListener()
              {
                 public void actionPerformed(ActionEvent x)
                 {
                     l2.setText(" ");
                     b1.setText(null);
                     
                     categories.setSelectedItem("1.Notebook");
                     
                     intro.setText("Select a Category to see products");
                 }
              };
              b7.addActionListener(back);
             
             
              ActionListener info = new ActionListener()
              {
                 public void actionPerformed(ActionEvent z)
                 {
                     String id = JOptionPane.showInputDialog(frame,"Enter the 4-digit ID Code or Order Number: ","VIEW PRODUCT",JOptionPane.INFORMATION_MESSAGE);
                     frame2.remove(b1);
                     
                     try
                     {
                        intro.setText(" " + eshop.getItemByld(Integer.parseInt(id)));
                         
                        int answer = JOptionPane.showConfirmDialog(frame,"Do you want to add this product in your shopping basket? ","ADD PRODUCT!!!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                         
                        if(answer == JOptionPane.YES_OPTION)
                        {
                           String quantity = JOptionPane.showInputDialog(frame,"Enter quantity: ","QUANTITY INPUT",JOptionPane.INFORMATION_MESSAGE);
                             
                           ItemOrdered itemList = new ItemOrdered(eshop.getItemByld(Integer.parseInt(id)),Integer.parseInt(quantity)); 
                           
                           try
                           { 
                             B1.placeOrder(itemList);
                             
                             String product = " " + itemList + "\nYou saccesfully add this product in your shopping basket";
                             JOptionPane.showMessageDialog(frame,product,"ADD PRODUCT",JOptionPane.INFORMATION_MESSAGE);
                           }
                           catch(NoStockAvailableException e)
                           {
                              String stock = "Sorry but the requested quantity of product " + itemList.getItem().getID() + " is not available!!!";
        
                              JOptionPane.showMessageDialog(null,stock,"INPUT QUANTITY",JOptionPane.INFORMATION_MESSAGE);
                           }
                        }
                     }
                     catch(WrongIDException w)
                     {
                        intro.setText("Sorry but the product you demanded does not exist in the shop or maybe you entered a wrong id code!!!");
                     }
                 }
              };
              b1.addActionListener(info); 
            }
          };
          categories.addItemListener(category);
        
        
        
          ActionListener showCart = new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              frame2.remove(b1);
              
              b3.setText("Delete");
              b4.setText("Edit");
              b5.setText("Clear Cart");
               
              B1.getShoppingBasket().getList1().removeAll(B1.getShoppingBasket().getList1());
              
              
              intro.setText(" ");
              
              B1.getShoppingBasket().showCart(B1);
              
              if(B1.getShoppingBasket().getOrderList().isEmpty())
              {
                  intro.setText("The Shopping Basket is Empty");
              }
                
              intro.setText( "0." + " " + ((String)B1.getShoppingBasket().getList1().get(0)) + "\n");
              
              for(int i=1; i < B1.getShoppingBasket().getList1().size() ; i++)
              {
                    intro.append(i + "." + " " + ((String)B1.getShoppingBasket().getList1().get(i)) + "\n");
              }

              ActionListener delete = new ActionListener()
              {
                  public void actionPerformed(ActionEvent e)
                  { 
                    String line = JOptionPane.showInputDialog(frame,"Enter the number of line of the list you want to delete: ","DELETE ORDER",JOptionPane.INFORMATION_MESSAGE);
                     
                    B1.getShoppingBasket().removeItemOrdered(((ItemOrdered)B1.getShoppingBasket().getOrderList().get(Integer.parseInt(line))));
                     
                    String deleteLine = "You saccesfully delete this order line";
                    JOptionPane.showMessageDialog(frame,deleteLine,"DELETE ORDER",JOptionPane.INFORMATION_MESSAGE);
                     
                    B1.getShoppingBasket().getList1().removeAll(B1.getShoppingBasket().getList1()); 
                    intro.setText(" ");
                     
                    B1.getShoppingBasket().showCart(B1);
              
                    if(B1.getShoppingBasket().getOrderList().isEmpty())
                    {
                         intro.setText("The Shopping Basket is Empty");
                    }
                
                    intro.setText( "0." + " " + ((String)B1.getShoppingBasket().getList1().get(0)) + "\n");
              
                    for(int i=1; i < B1.getShoppingBasket().getList1().size() ; i++)
                    {
                       intro.append(i + "." + " " + ((String)B1.getShoppingBasket().getList1().get(i)) + "\n");
                    }    
                  }
              };
              b3.addActionListener(delete);
              
              ActionListener edit = new ActionListener()
              {
                  public void actionPerformed(ActionEvent e)
                  {
                      String line = JOptionPane.showInputDialog(frame,"Enter the number of line of the list you want to edit: ","EDIT ORDER",JOptionPane.INFORMATION_MESSAGE);
                      String quantity = JOptionPane.showInputDialog(frame,"Enter the new Quantity: ","CHANGE QUANTITY",JOptionPane.INFORMATION_MESSAGE);
                      try
                      {
                          B1.getShoppingBasket().changeItemOrderedQuantity(eshop,((ItemOrdered)B1.getShoppingBasket().getOrderList().get(Integer.parseInt(line))),Integer.parseInt(quantity));
                      
                          String change = "You saccesfully change the quantity of this order line";  
                          JOptionPane.showMessageDialog(frame,change,"CHANGE QUANTITY",JOptionPane.INFORMATION_MESSAGE);
                      
                          B1.getShoppingBasket().getList1().removeAll(B1.getShoppingBasket().getList1()); 
                          intro.setText(" ");
                     
                          B1.getShoppingBasket().showCart(B1);
              
                          if(B1.getShoppingBasket().getOrderList().isEmpty())
                          {
                              intro.setText("The Shopping Basket is Empty");
                          }
                
                          intro.setText( "0." + " " + ((String)B1.getShoppingBasket().getList1().get(0)) + "\n");
              
                          for(int i=1; i < B1.getShoppingBasket().getList1().size() ; i++)
                          {
                             intro.append(i + "." + " " + ((String)B1.getShoppingBasket().getList1().get(i)) + "\n");
                          }
                      }
                      catch(NotAQuantityException w)
                      {
                            String notAQuantity = "Sorry but the quantity you want to set for product " + ((ItemOrdered)B1.getShoppingBasket().getOrderList().get(Integer.parseInt(line))).getItem().getID() + " is not realistic!!!";  
      
                           JOptionPane.showMessageDialog(null,notAQuantity,"INPUT QUANTITY",JOptionPane.INFORMATION_MESSAGE);
                      }
                  }
              };
              b4.addActionListener(edit);
              
              ActionListener clearCart = new ActionListener()
              {
                  public void actionPerformed(ActionEvent e)
                  {
                      int answer = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete all the order lines of the list? ","CLEAR CART",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                      
                      if(answer == JOptionPane.YES_OPTION)
                      {
                         B1.getShoppingBasket().clearCart();
                          
                         String clear = "You sacesfully clear your shopping basket";
                         JOptionPane.showMessageDialog(frame,clear,"CLEAR CART",JOptionPane.INFORMATION_MESSAGE);
                          
                          
                          
                         B1.getShoppingBasket().getList1().removeAll(B1.getShoppingBasket().getList1()); 
                         intro.setText(" ");
                     
                         B1.getShoppingBasket().showCart(B1);
              
                         if(B1.getShoppingBasket().getOrderList().isEmpty())
                         {  
                            intro.setText("The Shopping Basket is Empty");
                         }
                
                         intro.setText( "0." + " " + ((String)B1.getShoppingBasket().getList1().get(0)) + "\n");
              
                         for(int i=1; i < B1.getShoppingBasket().getList1().size() ; i++)
                         {
                           intro.append(i + "." + " " + ((String)B1.getShoppingBasket().getList1().get(i)) + "\n");
                         }
                      }
                  }
              };
              b5.addActionListener(clearCart);
            }
         };
         b2.addActionListener(showCart);
        
         ActionListener checkOut = new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               int list =  JOptionPane.showConfirmDialog(frame,"Do you want to see the cost of your order list so far?","CHECK OUT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
               
               b3.setText(null);
               b4.setText(null);
               b5.setText(null);
               
               if(list == JOptionPane.YES_OPTION)
               {

                  if(B1.getShoppingBasket().getOrderList().isEmpty())
                  {
                      intro.setText("The Shopping Basket is Empty");
                  }
                  else
                  {
                      intro.setText(" ");
                      B1.getShoppingBasket().getList2().removeAll(B1.getShoppingBasket().getList2());
                  
                      B1.getShoppingBasket().checkout(B1,list,0);
                   
                     intro.setText(((String)B1.getShoppingBasket().getList2().get(0)) + "\n");
                  
                     if(B1.getShoppingBasket().getList2().size() <= 3)
                     {
                       intro.append(((String)B1.getShoppingBasket().getList2().get(1)) + "\n");
                       intro.append(((String)B1.getShoppingBasket().getList2().get(2)) + "\n");
                     }
                     else
                     {
                      for(int i=1;i < (B1.getShoppingBasket().getList2().size() - 2);i++)
                      {
                          intro.append(((String)B1.getShoppingBasket().getList2().get(i)) + "\n");
                      }
                      
                      intro.append(((String)B1.getShoppingBasket().getList2().get(B1.getShoppingBasket().getList2().size() - 2)) + "\n");
                      intro.append(((String)B1.getShoppingBasket().getList2().get(B1.getShoppingBasket().getList2().size() - 1)) + "\n");
                     }
                  
                     int answer = JOptionPane.showConfirmDialog(frame,"Are you sure you want to buy this Orderlist?","CHECK OUT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                  
                     if(answer == JOptionPane.YES_OPTION)
                     {
                        B1.getShoppingBasket().checkout(B1,0,answer);
                      
                        String congrats ="The bonus points for your buy have been added to your account's points" + "\nHave a good day!!!";
                        JOptionPane.showMessageDialog(frame,congrats,"THANK YOU!!!",JOptionPane.INFORMATION_MESSAGE);
                     }
                  }
               }
               
            }
         };
         b6.addActionListener(checkOut);
        
        
         ActionListener exit = new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
                int answer =  JOptionPane.showConfirmDialog(frame,"Are you sure you want to exit from the shop?","EXIT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                
                if(answer == JOptionPane.YES_OPTION)
                {
                    setVisible(false);
                }
            }
         };
         b9.addActionListener(exit);
         
         ActionListener signOut = new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {
               int answer = JOptionPane.showConfirmDialog(frame,"Do you want to change account with your exit?","SIGN OUT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 

               if(answer == JOptionPane.YES_OPTION)
               {
                    setVisible(false);
                    
                    Menu newMenu = new Menu(eshop); 
                    
                    newMenu.pack();
                    newMenu.resize(5000,5000);
                    newMenu.setVisible(true);
               }
               else if(answer == JOptionPane.NO_OPTION)
               {    
                      setVisible(false);
               }
            }
         };
         b8.addActionListener(signOut);
        
         frame.setLayout(lay);
         frame1.setLayout(lay1);
         frame2.setLayout(lay1);
         frame3.setLayout(lay2);
         frame4.setLayout(lay3);
         frame5.setLayout(lay4);
         frame6.setLayout(lay5);
        
         frame1.add(l1);
         frame1.add(categories);
         frame1.add(l2);
         frame2.add(intro);
         frame2.add(b1);
         frame3.add(b2);
         frame3.add(b6);
         frame4.add(b3);
         frame4.add(b4);
         frame4.add(b5);
         frame5.add(b7);
         frame5.add(b8);
         frame5.add(b9);
         frame6.add(frame3);
         frame6.add(frame5);
         frame.add("West",frame1);
         frame.add("Center",frame2);
         frame.add("South",frame4);
         frame.add("North",frame6);
         setContentPane(frame);
      }
      else if(check == 1)
      { 
        Font f1 = new Font("Helvetica",Font.BOLD,14);
        setFont(f1);
        BorderLayout lay = new BorderLayout(20,0);
        FlowLayout lay1 = new FlowLayout(FlowLayout.LEFT,10,50);
        FlowLayout lay2 = new FlowLayout(FlowLayout.LEFT);
        FlowLayout lay3 = new FlowLayout(FlowLayout.LEFT,10,200);
        FlowLayout lay4 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout lay5 = new FlowLayout(FlowLayout.LEFT,100,0);
        
        
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton("Check Status");
        JButton b4 = new JButton("Back");
        JButton b5 = new JButton("Sign Out");
        JButton b6 = new JButton("Exit");
        JTextArea intro = new JTextArea("Select a Category to see products",20,15);
        JLabel l1 = new JLabel("Choose Category: ");
        JComboBox categories = new JComboBox();
        categories.addItem("1.Notebook");
        categories.addItem("2.Paper");
        categories.addItem("3.Pen");
        categories.addItem("4.Pencil");
        JPanel frame = new JPanel();
        JPanel frame1 = new JPanel();
        JPanel frame2 = new JPanel();
        JPanel frame3 = new JPanel();
        JPanel frame4 = new JPanel();
        JPanel frame5 = new JPanel();
        JPanel frame6 = new JPanel();
        JLabel l2 = new JLabel();
            
        categories.setEditable(false);
        intro.setEditable(false);
            
        ItemListener category = new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
              eshop.getItems().removeAll(eshop.getItems());
              eshop.getPencilList().removeAll(eshop.getPencilList());
              eshop.getPenList().removeAll(eshop.getPenList());
              eshop.getPaperList().removeAll(eshop.getPaperList());
              eshop.getNoteList().removeAll(eshop.getNoteList());
                
              Object epilogh = e.getItem();
              eshop.showProductsInCategory(((String)epilogh));
              
              intro.setText(" ");
              
              if(eshop.getItemsList().isEmpty())
              {
                  intro.setText(((String)eshop.getItems().get(0)));
              }
              
              intro.setText(((String)eshop.getItems().get(0)) + "\n");

              for(int i=1;i < eshop.getItems().size();i++)
              {
                 intro.append(((String)eshop.getItems().get(i)) + "\n");
              }
             
              switch(((String)epilogh))
              {
                case "3.Pen":
                l2.setText("Products:" + "(" + eshop.getPenList().size() + ")");
                break;
                       
                case "2.Paper":
                l2.setText("Products:" + "(" + eshop.getPaperList().size() + ")");
                break;
                       
                case "1.Notebook":
                l2.setText("Products:" + "(" + eshop.getNoteList().size() + ")");
                break;
                       
                case "4.Pencil":
                l2.setText("Products:" + "(" + eshop.getPencilList().size() + ")");
                break;
              }
             
              frame2.add(b1);
              b1.setText("View Details");
             
             
              ActionListener back = new ActionListener()
              {
                 public void actionPerformed(ActionEvent x)
                 {
                     l2.setText(" ");
                     b1.setText(null);
                     
                     categories.setSelectedItem("1.Notebook");
                     
                     intro.setText("Select a Category to see products");
                 }
              };
              b4.addActionListener(back);
             
             
              ActionListener info = new ActionListener()
              {
                 public void actionPerformed(ActionEvent z)
                 {
                     String id = JOptionPane.showInputDialog(frame,"Enter the 4-digit ID Code or Order Number: ","VIEW PRODUCT",JOptionPane.INFORMATION_MESSAGE);
                     frame2.remove(b1);
                     
                     try
                     {
                        intro.setText(" " + eshop.getItemByld(Integer.parseInt(id)));
                         
                        int answer = JOptionPane.showConfirmDialog(frame,"Do you want to change the quantity of this product? ","NEW STOCK!!!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                         
                        if(answer == JOptionPane.YES_OPTION)
                        {
                           String quantity = JOptionPane.showInputDialog(frame,"Set new quantity: ","NEW STOCK",JOptionPane.INFORMATION_MESSAGE);
                           try
                           {
                               eshop.updateItemStock(eshop.getItemByld(Integer.parseInt(id)),Integer.parseInt(quantity)); 
                           
                               String change = "You saccesfully change item's stock";
                               JOptionPane.showMessageDialog(frame,change,"NEW STOCK",JOptionPane.INFORMATION_MESSAGE);
                           
                               intro.setText(" " + eshop.getItemByld(Integer.parseInt(id)));
                           }
                           catch(NotAQuantityException e)
                           {
                              String notAQuantity = "Sorry but the quantity you want to set for product " + eshop.getItemByld(Integer.parseInt(id)).getID() + " is not realistic!!!";
                              JOptionPane.showMessageDialog(frame,notAQuantity,"NEW STOCK",JOptionPane.INFORMATION_MESSAGE);
                           }
                        }
                    }
                    catch(WrongIDException w)
                    {
                        intro.setText("Sorry but the product you demanded does not exist in the shop or maybe you entered a wrong id code!!!");
                    }
                 }
              };
              b1.addActionListener(info); 
            }
        };
        categories.addItemListener(category);
       
        ActionListener checkStatus = new ActionListener()
        {  
             public void actionPerformed(ActionEvent e)
             {  
                b2.setText("View Buyer");
                b1.setText(null);
                
                eshop.getBuyers().removeAll(eshop.getBuyers());


                if(eshop.getBuyersList().isEmpty())
                {
                    intro.setText(((String)eshop.getBuyers().get(0)));
                }
                else
                {
                   eshop.checkStatus() ;
                    
                   intro.setText("0.  " + ((String)eshop.getBuyers().get(0)) + "\n");
                
                   for(int i=1;i < eshop.getBuyers().size();i++)
                   {
                    intro.append(i + ". " + ((String)eshop.getBuyers().get(i)) + "\n");
                   }

                   ActionListener showStatus = new ActionListener()
                   {
                     public void actionPerformed(ActionEvent e)
                     {
                       String line = JOptionPane.showInputDialog(frame,"Enter the number of line of buyer you want to see more details: ","VIEW BUYER ",JOptionPane.INFORMATION_MESSAGE);
                        
                       ((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getList1().removeAll(((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getList1());
              
                       intro.setText(" ");
              
                       
              
                       if(((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getOrderList().isEmpty())
                       {
                          intro.setText("The Shopping Basket is Empty");
                       }
                       else
                       {
                          ((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().showCart(((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))));
                           
                          intro.setText( "0." + " " + ((String)((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getList1().get(0)) + "\n");
              
                          for(int i=1; i < ((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getList1().size() ; i++)
                          {
                            intro.append(i + "." + " " + ((String)((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))).getShoppingBasket().getList1().get(i)) + "\n");
                          }
                       }

                       
                       int delete = JOptionPane.showConfirmDialog(frame,"Do you want to delete this buyer from the shop?","DELETE BUYER",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 
                       
                       
                       if(delete == JOptionPane.YES_OPTION) 
                       {
                          String deleteBuyer = "You saccesfully delete this buyer from the shop";
                          JOptionPane.showMessageDialog(frame,deleteBuyer,"DELETE Buyer",JOptionPane.INFORMATION_MESSAGE);

                          eshop.removeBuyer(((Buyer)eshop.getBuyersList().get(Integer.parseInt(line))));
                          
                          eshop.getBuyers().removeAll(eshop.getBuyers());
                          
                          if(eshop.getBuyersList().isEmpty())
                          {
                             intro.setText("The shop has none registrations yet ");
                          }
                          else
                          {
                             eshop.checkStatus(); 
                              
                             intro.setText("O. " + ((String)eshop.getBuyers().get(0)) + "\n");
                
                             for(int i=1;i < eshop.getBuyers().size();i++)
                             {
                               intro.append(i + ". " + ((String)eshop.getBuyers().get(i)) + "\n");
                             }
                          } 
                       }
                     }
                   };
                   b2.addActionListener(showStatus);
                }
             }
        };
        b3.addActionListener(checkStatus);
        
        
        ActionListener exit = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int answer =  JOptionPane.showConfirmDialog(frame,"Are you sure you want to exit from the shop?","EXIT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                
                if(answer == JOptionPane.YES_OPTION)
                {
                    setVisible(false);
                }
            }
        };
        b6.addActionListener(exit);
         
        ActionListener signOut = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               int answer = JOptionPane.showConfirmDialog(frame,"Do you want to change account with your exit?","SIGN OUT",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 

               if(answer == JOptionPane.YES_OPTION)
               {
                    setVisible(false);
                    
                    Menu newMenu = new Menu(eshop); 
                    
                    newMenu.pack();
                    newMenu.resize(5000,5000);
                    newMenu.setVisible(true);
               }
               else if(answer == JOptionPane.NO_OPTION)
               {    
                      setVisible(false);
               }
            }
        };
        b5.addActionListener(signOut);
        
        frame.setLayout(lay);
        frame1.setLayout(lay1);
        frame2.setLayout(lay1);
        frame3.setLayout(lay2);
        frame4.setLayout(lay3);
        frame5.setLayout(lay4);
        frame6.setLayout(lay5);
        
        frame1.add(l1);
        frame1.add(categories);
        frame1.add(l2);
        frame2.add(intro);
        frame2.add(b1);
        frame3.add(b3);
        frame4.add(b2);
        frame5.add(b4);
        frame5.add(b5);
        frame5.add(b6);
        frame6.add(frame3);
        frame6.add(frame5);
        frame.add("West",frame1);
        frame.add("Center",frame2);
        frame.add("South",frame4);
        frame.add("North",frame6);
        setContentPane(frame);
        
        check = 0;
      }
   }  
}

