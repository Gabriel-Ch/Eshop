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

