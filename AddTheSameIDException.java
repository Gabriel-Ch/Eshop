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
