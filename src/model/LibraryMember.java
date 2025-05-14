package model;

public class LibraryMember {
    private String memberId;
    private String name;
    private String email;
    
    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }
    
    // Getters and setters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return name + " (ID: " + memberId + ", Email: " + email + ")";
    }
}