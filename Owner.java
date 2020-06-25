
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
