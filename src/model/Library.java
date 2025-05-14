package model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<LibraryMember> members;
    private List<Loan> loans;
    
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();
    }
    
    // Book operations
    public void addBook(Book book) {
        books.add(book);
    }
    
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
    
    // Member operations
    public void addMember(LibraryMember member) {
        members.add(member);
    }
    
    public List<LibraryMember> getMembers() {
        return new ArrayList<>(members);
    }
    
    // Loan operations
    public void addLoan(Loan loan) {
        loans.add(loan);
    }
    
    public List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
    
    public void returnBook(Book book) {
        for (Loan loan : loans) {
            if (loan.getBook().equals(book) && !book.isAvailable()) {
                loan.returnBook();
                return;
            }
        }
    }
}