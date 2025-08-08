package day3;

public class Book {
    private int serialNo;
    private String name;
    private String author;
    private int quantity;
    private int availableQuantity;

    public Book() {

    }

    public Book( int serialNo,String name, String author, int quantity) {
        this.serialNo = serialNo;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int quantity) {
        this.availableQuantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "serialNo=" + serialNo +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
