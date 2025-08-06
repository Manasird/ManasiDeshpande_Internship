package task2;

public class Book {
    private int serialNo;
    private String name;
    private String authorName;
    private int bookQuantity;
    private boolean isIssued;
    private int availableQuantity;

    public Book() {
        super();
    }
    public Book( int serialNo,String name, String authorName, int bookQuantity) {
        super();
        this.serialNo = serialNo;
        this.name = name;
        this.authorName = authorName;
        this.bookQuantity = bookQuantity;
        this.isIssued = isIssued;
        this.availableQuantity=bookQuantity;
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
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public int getBookQuantity() {
        return bookQuantity;
    }
    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
    public boolean isIssued() {
        return isIssued;
    }
    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int bookQuantity) {
        this.availableQuantity = bookQuantity;
    }
    @Override
    public String toString() {
        return "Book [serialNo=" + serialNo + ", name=" + name + ", authorName=" + authorName + ", bookQuantity="
                + bookQuantity + ", isIssued=" + isIssued + ", availableQuantity=" + availableQuantity + "]";
    }
}
