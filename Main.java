
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
            eshop.addBuyer(b2);
            eshop.addBuyer(b1);
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
