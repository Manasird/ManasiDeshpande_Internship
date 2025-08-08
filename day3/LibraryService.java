package day3;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class LibraryService {
    private Map<String, Book> bookCatalog = new HashMap<>();
    int count =1;
    Scanner sc = new Scanner(System.in);

    //    public void addBook() {
//        String name = null;
//        String author = null;
//     //   int count=1;
//        int quantity = 0;
//        try {
//            System.out.println("Enter the name of book.");
//            name = sc.next();
//            if (name.isEmpty()) {
//                throw new CustomException("Name can not be empty.");
//            }
//            if (bookCatalog.containsKey(name)) {
//                throw new CustomException("Book already exist.")
//                        ;
//            }
//            //sc.nextLine();
//            System.out.println("Enter the name of author.");
//            author = sc.next();
//            if (author.isEmpty()) {
//                throw new CustomException("author field can not be empty.");
//            }
//            System.out.println("Enter the number of quantity of book.");
//            quantity = sc.nextInt();
//
//            if (quantity <= 0) {
//                throw new NumberFormatException("Invalid number. Please enter valid number");
//            }
//            int serialNo = count;
//
//            Book book = new Book( serialNo,name, author, quantity);
//            bookCatalog.put(name, book);
//            System.out.println("Book added successfully.");
//            count++;
//        } catch (CustomException | NullPointerException | InputMismatchException | NumberFormatException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//    }
    public Map<String,Book> getBookCatalog(){
        return bookCatalog;
    }

    public String addBook(String name,String author,int quantity){
        try {
//            System.out.println("Enter the name of book.");
//            name = sc.next();
            if (name.isEmpty()) {
               return ("Name can not be empty.");
            }
            if (bookCatalog.containsKey(name)) {
               return ("Book already exist.")
                        ;
            }
            //sc.nextLine();
//            System.out.println("Enter the name of author.");
//            author = sc.next();
            if (author.isEmpty()) {
                return ("author field can not be empty.");
            }
//            System.out.println("Enter the number of quantity of book.");
//            quantity = sc.nextInt();

            if (quantity <= 0) {
                return ("Invalid number. Please enter valid number");
            }
            int serialNo = count;

            Book book = new Book( serialNo,name, author, quantity);
            bookCatalog.put(name, book);
            count++;
            return "Book added successfully.";
        } catch ( Exception e) {
            return "Error: " + e.getMessage();
        }

    }

    public String display() {
        try {
            if (bookCatalog.isEmpty()) {
                return ("Sorry books are not present in the catalog.");
            }
            String result = "Book Catalog:";
            for (Map.Entry<String, Book> entry : bookCatalog.entrySet()) {

                result=(entry.getKey()+"  "+entry.getValue());

                //System.out.println(bookCatalog);
            }
            return result;

        } catch(Exception e)  {
           return (e.getMessage());
        }


    }

    public String remove(String removeName) {
        try {
            if (bookCatalog.isEmpty()) {
                return ("Sorry books are not present in the catalog.");
            }
//            System.out.println("Enter the name of book.");
//            String removeName = sc.next();
            if (removeName.isEmpty()) {
                return ("Name can not be empty.");
            }
            if (bookCatalog.containsKey(removeName)) {
                bookCatalog.remove(removeName);
               return ("book is deleted successfully.");
            } else {
                return ("book does not exist.");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
        //return ("book is deleted successfully.");
    }
    public String searchByName(String searchName){
        try{
            if (bookCatalog.isEmpty()) {
               return ("Sorry books are not present in the catalog.");
            }
//            System.out.println("Enter name of the book.");
//            String searchName=sc.next();
            if(searchName.isEmpty()){
                return ("Name can not be empty.");
            }
            if(bookCatalog.containsKey(searchName)){
             Book result=bookCatalog.get(searchName);
              return ("Book Details :"+result);
            }
            else{
                return ("Book does not exist..");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    public String issubook(String issubook,int c) {
        try {
//            Book b = new Book();
            if (bookCatalog.isEmpty()) {
                return("Sorry books are not present in the catalog.");
            }
//            System.out.println("Enter the name of book.");
//            String issubook = sc.next();
            if (issubook.isEmpty()) {
                return ("Name can not be empty.");
            }
            if(bookCatalog.containsKey(issubook)) {
                Book b=bookCatalog.get(issubook);
//                System.out.println("Enter number of copies.");
//                int c = sc.nextInt();
                if (c <= 0) {
                    return ("Please enter valid number.");
                }

                if (b.getAvailableQuantity() >= c && b.getAvailableQuantity()!=0) {
                    int a=b.getAvailableQuantity();
                    b.setAvailableQuantity(a-c);
                    return ("Book issued successfully..");
                    // bookCatalog.put(issubook,b);
                }else {
                    return ("Not enough books available.");
                }

            }
            else {
                return ("Book does not exist..");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }}
    public String submitBook(String subBook,int s){
        try{
            if (bookCatalog.isEmpty()) {
                return ("Sorry books are not present in the catalog.");
            }
//            System.out.println("Enter the name of book.");
//            String subBook=sc.next();
            if (subBook.isEmpty()) {
               return ("Name can not be empty.");
            }
            if(bookCatalog.containsKey(subBook)) {
                Book b= bookCatalog.get(subBook);
//                System.out.println("Enter the number of copies.");
//                int s = sc.nextInt();
                if (s <= 0) {
                    return ("Please enter valid number.");
                }

                int total= b.getQuantity();
                int available = b.getAvailableQuantity();
                int issued = total-available;
                if(issued==0){
                   return ("No book is issued");
                }
                if(s>issued){
                    return ("You are trying to submit more number of copies than issued.");
                }
                b.setAvailableQuantity(available + s);
              return ("Books submitted successfully..");



            }

            else {
                return ("Book does not exist..");
            }
        } catch (Exception e) {
          return (e.getMessage());
        }
    }
    public String update(){
       int choice;
        // Book b =new Book();
        try{

            if (bookCatalog.isEmpty()) {
                return ("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String updatebook= sc.next();
            if (updatebook.isEmpty()) {
               return ("Name can not be empty.");
            }
            if(bookCatalog.containsKey(updatebook)){
                Book b= bookCatalog.get(updatebook);
                System.out.println("Enter the name of author.");
                String updateauthor= sc.next();
                if (updateauthor.isEmpty()) {
                   return ("Author field can not be empty.");
                }
                do{
                    System.out.println("1. Add number of copies.");
                    System.out.println("2. delete no of copies.");
                    System.out.println("3. Other");
                    choice= sc.nextInt();
                    switch (choice){
                        case 1:
                            System.out.println("Enter the number of copies.");
                            int add= sc.nextInt();
                            if(add>0){
                                b.setQuantity(b.getQuantity()+add);
                                return ("Number of copies added Successfully.");
                            }
                            else {
                                return ("Please enter valid number.");

                            }
                            //break;
                        case 2:
                            System.out.println("Enter the number of copies.");
                            int sub= sc.nextInt();
                            if(sub>0){
                                if((b.getQuantity()-sub)>0) {
                                    b.setQuantity(b.getQuantity() - sub);
                                   return ("Number of copies deleted Successfully.");
                                }
                            }
                            else {
                                return ("Please enter valid number.");

                            }
                           // break;
                        default:{
                            return ("Invalid input.please enter valid input.");
                        }
                    }
                }while(choice!=3);

            }
        } catch (Exception e) {
            return (e.getMessage());
        }
        return "Unable to update";
    }
    public String inventory(String bookName){

        try{
            if (bookCatalog.isEmpty()) {
                return ("Sorry books are not present in the catalog.");
            }
//            System.out.println("Enter the name of book.");
//            String bookName=sc.next();
            if(bookName.isEmpty()){
                return ("Name can not be empty.");
            }
            if(bookCatalog.containsKey(bookName)){
                Book b= bookCatalog.get(bookName);
                System.out.println("Quantity of book"+bookName+" : "+b.getQuantity());
                System.out.println("Available Quantity of book"+bookName+" : "+b.getAvailableQuantity());
                System.out.println("Issued Quantity of book"+bookName+" : "+(b.getQuantity()-b.getAvailableQuantity()));
                return "Invetory";
            }
            else {
               return ("Book does not exist..");
            }
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        LibraryService l= new LibraryService();
        Scanner sc= new Scanner(System.in);
        int choice ;
        do {
            System.out.println("1. Add Book");
            System.out.println("2. Display catalog.");
            System.out.println("3. Remove the book.");
            System.out.println("4. Issue Book.");
            System.out.println("5. Submit Book.");
            System.out.println("6. Show inventory of Book.");
            System.out.println("7. Update the book.");
            System.out.println("8. Search book by name.");
            System.out.println("9. Exit.");
            System.out.println("Please enter your choice.");
            choice= sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the name of book.");
                    String name = sc.next();
                    System.out.println("Enter the name of author.");
                    String author = sc.next();
                    System.out.println("Enter the number of quantity of book.");
                    int quantity = sc.nextInt();

                    l.addBook(name,author,quantity);
                    break;
                case 2:
                    l.display();
                    break;
                case 3:
                    System.out.println("Enter the name of book.");
                    String removeName = sc.next();
                    l.remove(removeName);
                    break;
                case 4:
                    System.out.println("Enter the name of book.");
                    String issubook = sc.next();
                    System.out.println("Enter number of copies.");
                    int c = sc.nextInt();
                    l.issubook(issubook,c);
                    break;
                case 5:
                    System.out.println("Enter the name of book.");
                    String subBook=sc.next();
                    System.out.println("Enter the number of copies.");
                    int s = sc.nextInt();
                    l.submitBook(subBook,s);


                    break;
                case 6:
                    System.out.println("Enter the name of book.");
                    String bookName=sc.next();
                    l.inventory(bookName);
                    break;
                case 7:
                    l.update();
                    break;
                case 8 :
                    System.out.println("Enter name of the book.");
                    String searchName=sc.next();
                    l.searchByName(searchName);
                case 9 :
                    System.out.println("Thank you!!.");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }while(choice!=9);


    }
}
