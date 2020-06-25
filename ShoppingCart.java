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
