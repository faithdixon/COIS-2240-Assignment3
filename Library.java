import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

    // Add a new member to the library
    public boolean addMember(Member member) 
    {
    	if (findMemberById(member.getId()) != null) 
    	{
            System.out.println("A Member with the ID " + member.getId() + "exists already.");
            //member with same id exists already
            return false; 
        }
        members.add(member);
      //successful addition
        return true;
    }
    
    // Add a new book to the library
    public boolean addBook(Book book) 
    {
    	
    	if (findBookById(book.getId()) != null)
    	{
    		System.out.println("A Book with the ID " + book.getId() + "exists already.");
    		//book with same id exists already
    		return false;
    	}
        books.add(book);
        //successful addition
        return true;
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
