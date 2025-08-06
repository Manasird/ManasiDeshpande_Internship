package task2;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class Library {
    CopyOnWriteArrayList<Book> list= new CopyOnWriteArrayList();
    Scanner sc= new Scanner(System.in);
    int count=1;
    public void addBook() throws Exception {
        //private static bookCounter = 1;
//	 System.out.println("Enter serial number");
//	 int serialNo= sc.nextInt();1
        // int count=1;
        int serialNo= count;
        //Book b= new Book();
        //b.setSerialNo(b.getSerialNo()+1);
        if(list.isEmpty()) {
            System.out.println("Enter name of the book");
            String name= sc.next();
            System.out.println("Enter name of the author");
            String author= sc.next();
            System.out.println("Enter quantity");
            int quantity= sc.nextInt();
            //Book b= new Book(int,String,String,int);
            list.add(new Book( serialNo,name,author,quantity));
            System.out.println("Book added Successfully");
            count++;
        }
        else {
            for(Book b:list) {
                System.out.println("Enter name of the book");
                String name= sc.next();
                if(b.getName().equals(name)) {
                    //System.out.println("Book is already available.");
                    throw new Exception("Book already exists");

                }
                else {
                    System.out.println("Enter name of the author");
                    String author= sc.next();
                    System.out.println("Enter quantity");
                    int quantity= sc.nextInt();
                    //Book b= new Book(int,String,String,int);
                    list.add(new Book( serialNo,name,author,quantity));
                    System.out.println("Book added Successfully");
                    count++;
                }
            }
        }
    }
    public void displayBooks() throws Exception {
        if(list.isEmpty()) {
            // System.out.println("Books are not available.");
            throw new Exception("List is empty.");


        }
        else {
            for(Book b:list) {
                System.out.println(b);
            }
        }
    }
    public void remove() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter name of the book");
            String rname= sc.next();
            //System.out.println(rname);
            for(Book b:list) {
                if(b.getName().equals(rname)) {
                    System.out.println(b.getName());
                    list.remove(b);
                    System.out.println("Book removed Successfully");
                }

                else {
                    throw new Exception("Book does not exist..");
                }
            }
        }
    }
    public void searchByName() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter name of the book");
            String rname= sc.next();
            boolean found=false;
            for(Book b:list) {
                if(b.getName().equals(rname)) {
                    found=true;
                    System.out.println(b);

                }

            }
            if(found==false) {
                throw new Exception("Book is not present.");
            }
        }}
    public void issueBook() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter name of the book");
            String iname=sc.next();
            boolean found=false;
            for(Book b:list) {
                if(b.getName().equals(iname)) {
                    found=true;
                    if(b.getBookQuantity()>=b.getAvailableQuantity() && b.getAvailableQuantity()!=0) {
                        int q=b.getAvailableQuantity();
//

                        System.out.println("Book issued Successfully");
                        b.setAvailableQuantity(q-1);
                    }
                    else{

                        throw new Exception("Book is not available");
                    }
                    break;
                }

            }
            if (found==false){

                throw new Exception(" Book does not exist..");
            }
        }
    }
    public void submitBook() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter name of the book");
            String sname=sc.next();
            boolean found=false;
            for(Book b:list) {
                if(b.getName().equals(sname)) {
                    found=true;
                    if(b.getBookQuantity()>b.getAvailableQuantity()) {
                        int q= b.getAvailableQuantity();
                        b.setAvailableQuantity(q+1);
                        System.out.println("Book submitted successfully");
                    }

                    else {
                        throw new Exception("Duplicate book.." +sname);
                    }
                    break;
                }

            }
            if(found==false) {
                throw new Exception("Book does not exist..s");
            }
        }
    }
    public void count() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter name of the book");
            String sname=sc.next();
            for(Book b:list) {
                if(b.getName().equals(sname)) {
                    System.out.println("Quantity of "+sname+" is: "+b.getBookQuantity());
                    System.out.println(" Available quantity of "+sname+" is: "+b.getAvailableQuantity());
                }
            }
        }
    }
    public void updateQuantity() throws Exception {
        if(list.isEmpty()) {
            throw new Exception("List is empty.");
        }
        else {
            System.out.println("Enter the name of book.");
            String ubook= sc.next();
            boolean found=false;
            int a;
            for(Book b: list) {
                if(b.getName().equals(ubook)) {
                    found=true;
                    do {
                        System.out.println("1. Add number of copies.");
                        System.out.println("2. delete no of copies.");
                        System.out.println("3. Exit");


                        a=sc.nextInt();
                        switch(a) {
                            case 1:
                                System.out.println("Enter the Number of copies of book.");
                                int q= sc.nextInt();
                                b.setBookQuantity(q+b.getBookQuantity());
                                b.setAvailableQuantity(q+b.getAvailableQuantity());
                                System.out.println("Quantity updated successfully");
                                break;
                            case 2:
                                System.out.println("Enter the Number of copies of book.");
                                int p= sc.nextInt();
                                b.setBookQuantity(p-b.getBookQuantity());
                                b.setAvailableQuantity(p-b.getAvailableQuantity());
                                System.out.println("Quantity updated successfully");
                                break;
                            case 3:
                                System.out.println("exit!!");
                                break;
                            default :
                                System.out.println("Invalid option");
                        }
                    } while(a!=3);
                }

            }
            if(found==false) {
                throw new Exception(" Book does not exist..");
            }

        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Scanner sc= new Scanner(System.in);

        Library l= new Library();
        int choice ;
        do {
            System.out.println("1. Add Book");
            System.out.println("2. Display list of Book");
            System.out.println("3. Remove the book.");
            System.out.println("4. Issue Book.");
            System.out.println("5. Submit Book.");
            System.out.println("6. Show count of Book.");
            System.out.println("7. Update quantity of book.");
            System.out.println("8. Search book by name.");
            System.out.println("9. Exit.");
            System.out.println("Please enter your choice.");
            choice= sc.nextInt();
            switch(choice){
                case 1:
                    try {
                        l.addBook();
                    }catch (Exception e) {
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        l.displayBooks();
                    } catch (Exception e) {
                        System.out.println("List is empty.");
                    }
                    break;
                case 3:
                    try {
                        l.remove();
                    } catch (Exception e) {
                        System.out.println("Error"+ e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        l.issueBook();
                    }
                    catch (Exception e) {
                        System.out.println("Error"+ e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        l.submitBook();
                    }catch (Exception e) {
                        System.out.println("Error"+ e.getMessage());
                    }


                    break;
                case 6:
                    try {
                        l.count();
                    }
                    catch (Exception e) {
                        System.out.println("Error"+ e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        l.updateQuantity();
                    }catch (Exception e) {
                        System.out.println("Error"+ e.getMessage());
                    }
                    break;
                case 8 :
                    try {
                        l.searchByName();
                    }
                    catch (Exception e) {
                        System.out.println("Error: "+e.getMessage());
                    }
                    break;
                case 9 :
                    System.out.println("Thank you!!.");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }while(choice!=9);


    }
}
