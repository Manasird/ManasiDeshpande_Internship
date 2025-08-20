package day3;

import java.util.*;

public class Library {
    private Map<String, Book> bookCatalog = new HashMap<>();
    int count =1;
    Scanner sc = new Scanner(System.in);

    public void addBook() {
        String name = null;
        String author = null;
     //   int count=1;
        int quantity = 0;
        try {
            System.out.println("Enter the name of book.");
            name = sc.next();
            if (name.isEmpty()) {
                throw new CustomException("Name can not be empty.");
            }
            if (bookCatalog.containsKey(name)) {
                throw new CustomException("Book already exist.")
                        ;
            }
            //sc.nextLine();
            System.out.println("Enter the name of author.");
            author = sc.next();
            if (author.isEmpty()) {
                throw new CustomException("author field can not be empty.");
            }
            System.out.println("Enter the number of quantity of book.");
                 String a= sc.next();
                 try {
                     quantity = Integer.parseInt(a);
                     if (quantity <= 0) {
                         System.out.println("Invalid number. Please enter valid number");
                     }
                 } catch (NumberFormatException e) {
                     System.out.println("Invalid number. Please enter valid number");
                     return;
                 }

                int serialNo = count;

                Book book = new Book(serialNo, name, author, quantity);
                bookCatalog.put(name, book);
                System.out.println("Book added successfully.");
             count++;
        } catch (CustomException | NullPointerException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }

    }

    public void display() {
        try {
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            for (Map.Entry<String, Book> entry : bookCatalog.entrySet()) {
                System.out.println(entry.getKey()+"  "+entry.getValue());
                //System.out.println(bookCatalog);
            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove() {
        try {
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String removeName = sc.next();
            if (removeName.isEmpty()) {
                throw new CustomException("Name can not be empty.");
            }
            if (bookCatalog.containsKey(removeName)) {
                bookCatalog.remove(removeName);
                System.out.println(removeName + "book is deleted successfully.");
            } else {
                System.out.println(removeName + "book does not exist.");
            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());

        }
    }
    public void searchByName(){
        try{
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter name of the book.");
            String searchName=sc.next();
            if(searchName.isEmpty()){
                throw new CustomException("Name can not be empty.");
            }
            if(bookCatalog.containsKey(searchName)){
                System.out.println(bookCatalog.get(searchName));
            }
            else{
                throw new CustomException("Book does not exist..");
            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void issubook() {
        try {
//            Book b = new Book();
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String issubook = sc.next();
            if (issubook.isEmpty()) {
                throw new CustomException("Name can not be empty.");
            }
            if(bookCatalog.containsKey(issubook)) {
                Book b=bookCatalog.get(issubook);
                System.out.println("Enter number of copies.");
                String d= sc.next();
                int c;
                try {
                    c = Integer.parseInt(d);
                    if (c <= 0) {
                        System.out.println("Invalid number. Please enter valid number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter valid number");
                    return;
                }

                    if (b.getAvailableQuantity() >= c && b.getAvailableQuantity()!=0) {
                        int a=b.getAvailableQuantity();
                        b.setAvailableQuantity(a-c);
                        System.out.println("Book issued successfully..");
                       // bookCatalog.put(issubook,b);
                    }else {
                        throw new CustomException("Not enough books vailable.");
                    }

            }
            else {
                throw new CustomException("Book does not exist..");
            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        }}
    public void submitBook(){
        try{
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String subBook=sc.next();
            if (subBook.isEmpty()) {
                throw new CustomException("Name can not be empty.");
            }
            if(bookCatalog.containsKey(subBook)) {
                Book b= bookCatalog.get(subBook);
                System.out.println("Enter the number of copies.");
                String h= sc.next();
                int s = 0;
                try {
                    s = Integer.parseInt(h);
                    if (s <= 0) {
                        System.out.println("Invalid number. Please enter valid number");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter valid number");

                }

                int total= b.getQuantity();
                int available = b.getAvailableQuantity();
                int issued = total-available;
                if(issued==0){
                    throw new CustomException("No book is issued");
                }
                if(s>issued){
                    throw new CustomException("You are trying to submit more number of copies than issued.");
                }
                b.setAvailableQuantity(available + s);
                        System.out.println("Books submitted successfully..");



                }

            else {
                throw new CustomException("Book does not exist..");
            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
           System.out.println(e.getMessage());
           sc.nextLine();
        }
    }
    public void update(){
        int choice = 0;
       // Book b =new Book();
        try{

            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String updatebook= sc.next();
            if (updatebook.isEmpty()) {
                throw new CustomException("Name can not be empty.");
            }
            if(bookCatalog.containsKey(updatebook)){
                Book b= bookCatalog.get(updatebook);
                System.out.println("Enter the name of author.");
                String updateauthor= sc.next();
                if (updateauthor.isEmpty()) {
                    throw new CustomException("Author field can not be empty.");
                }
                else {
                    b.setAuthor(updateauthor);
                }
                do{
                    System.out.println("1. Add number of copies.");
                    System.out.println("2. delete no of copies.");
                    System.out.println("3. Other");
                    String l=sc.next();
                      try {
                          choice = Integer.parseInt(l);
                           if(choice<=0){
                               System.out.println("Invalid number.Please Enter valid number.");
                              // return;
                           }
                      }catch (NumberFormatException e){
                          System.out.println("Invalid number.Please enter valid number.");
                          continue;
                      }
                          switch (choice) {
                              case 1:
                                  System.out.println("Enter the number of copies.");
                                  String j = sc.next();
                                  int add;
                                  try {
                                      add = Integer.parseInt(j);
                                      if (add <= 0) {
                                          System.out.println("Invalid number. Please enter valid number");
                                      }
                                  } catch (NumberFormatException e) {
                                      System.out.println("Invalid number. Please enter valid number");
                                      return;
                                  }
                                  if (add > 0) {
                                      b.setQuantity(b.getQuantity() + add);
                                      b.setAvailableQuantity(b.getAvailableQuantity() + add);
                                      System.out.println("Number of copies added Successfully.");
                                  } else {
                                      throw new CustomException("Please enter valid number.");

                                  }
                                  break;
                              case 2:
                                  System.out.println("Enter the number of copies.");
                                  String k = sc.next();
                                  int sub;
                                  try {
                                      sub = Integer.parseInt(k);
                                      if (sub <= 0) {
                                          System.out.println("Invalid number. Please enter valid number");
                                      }
                                  } catch (NumberFormatException e) {
                                      System.out.println("Invalid number. Please enter valid number");
                                      return;
                                  }
                                  if (sub > 0) {
                                      if ((b.getQuantity() - sub) > 0) {
                                          b.setQuantity(b.getQuantity() - sub);
                                          b.setAvailableQuantity(b.getAvailableQuantity() - sub);
                                          System.out.println("Number of copies deleted Successfully.");
                                      } else {
                                          System.out.println("You are trying to delete copies more than available");
                                      }
                                  } else {
                                      throw new CustomException("Please enter valid number.");

                                  }
                                  break;
                              default: {
                                  System.out.println("Invalid input.please enter valid input.");
                              }
                          }

                }while(choice!=3);

            }
        } catch (CustomException| NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        }
    }
    public void inventory(){

        try{
            if (bookCatalog.isEmpty()) {
                throw new CustomException("Sorry books are not present in the catalog.");
            }
            System.out.println("Enter the name of book.");
            String bookName=sc.next();
            if(bookName.isEmpty()){
                throw new CustomException("Name can not be empty.");
            }
            if(bookCatalog.containsKey(bookName)){
                Book b= bookCatalog.get(bookName);
                System.out.println("Quantity of book"+bookName+" : "+b.getQuantity());
                System.out.println("Available Quantity of book"+bookName+" : "+b.getAvailableQuantity());
                System.out.println("Issued Quantity of book"+bookName+" : "+(b.getQuantity()-b.getAvailableQuantity()));
            }
            else {
                throw new CustomException("Book does not exist..");
            }
        } catch (CustomException | NullPointerException | InputMismatchException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
        public static void main(String[] args) throws Exception {
            Library l= new Library();
            Scanner sc= new Scanner(System.in);
            int choice=0;

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

                String m= sc.next();
                try {
                     choice=Integer.parseInt(m) ;
                    if(choice<=0){
                        System.out.println("Invalid number.Please Enter valid number.");
                        // return;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid number.Please enter valid number.");
                    continue;
                }

                switch(choice){
                    case 1:
                        l.addBook();
                        break;
                    case 2:
                        l.display();
                        break;
                    case 3:
                        l.remove();
                        break;
                    case 4:
                        l.issubook();
                        break;
                    case 5:
                        l.submitBook();


                        break;
                    case 6:
                        l.inventory();
                        break;
                    case 7:
                        l.update();
                        break;
                    case 8 :
                        l.searchByName();
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
