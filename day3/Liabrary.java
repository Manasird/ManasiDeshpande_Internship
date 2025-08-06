package day3;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Liabrary {
    private Map<String , Integer> bookCatalog= new HashMap<>();
    Scanner sc= new Scanner(System.in);
    public void addBook(String name,String author,int quantity){
        try {
            System.out.println("Enter the name of book.");
            name = sc.next();
            if(bookCatalog.containsKey(name)){
                throw new CustomException("Book already exist.")
;            }
            System.out.println("Enter the name of author.");
            author = sc.next();
            System.out.println("Enter the number of quantity of book.");
            quantity = sc.nextInt();
            if(quantity<=0){
                throw new NumberFormatException("Invalid number. Please enter valid number");
            }
            if(name == null&& author==null && quantity==0){
                throw new NullPointerException("Fields cannot be empty.please fill all the fields ");
            }
        } catch (CustomException |NullPointerException|InputMismatchException|NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        Book book =new Book( name,author,quantity);
        bookCatalog.put(name,quantity);
        System.out.println("Book added successfully.");
    }
    public static void main(String[] args){
        Liabrary l= new Liabrary();

    }

}
