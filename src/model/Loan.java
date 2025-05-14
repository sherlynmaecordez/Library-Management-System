package model;

import java.time.LocalDate;

public class Loan {
    private Book book;
    private LibraryMember member;
    private LocalDate loanDate;
    private LocalDate dueDate;
    
    public Loan(Book book, LibraryMember member, LocalDate loanDate, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        book.setAvailable(false);
    }
    
    // Getters
    public Book getBook() { return book; }
    public LibraryMember getMember() { return member; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    
    public void returnBook() {
        book.setAvailable(true);
    }
    
    @Override
    public String toString() {
        return book.getTitle() + " loaned to " + member.getName() + 
               " from " + loanDate + " to " + dueDate;
    }
}