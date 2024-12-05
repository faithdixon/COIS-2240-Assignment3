import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest 
{
	private Library library;
	
	@Before
	public void setUp() 
	{
		//before every test library is instantiated 
		library = new Library();
	}
	
	@Test
	public void testBookId()
	{
		//book with id which is in the allowed range is tested, exception should not be thrown
		try {
			Book validBook1 = new Book(100, "100 is a valid book");
			assertEquals("Id should be 100", 100, validBook1.getId());
			
			Book validBook2 = new Book(999, "999 is a valid book");
			assertEquals("Id should be 999", 999, validBook2.getId());
		} catch (Exception e)
		{
			fail("error shouldn't be thrown if id is valid: " + e.getMessage());
		}
		
		//book with id which is not in the allowed range is tested, exception should be thrown
		try {
			Book invalidBook1 = new Book(1000, "1000 is an invalid book");
			fail("exception should be thrown");
		} catch (Exception e) 
		{
			assertEquals("id should be between 100 and 999.", e.getMessage());
		}
		//book with id which is not in the allowed range is tested, exception should be thrown
		try {
			Book invalidBook2 = new Book(1012, "1012 is an invalid book");
			fail("exception should be thrown");
		} catch (Exception e) 
		{
			assertEquals("id should be between 100 and 999.", e.getMessage());
		}
		
		//book with id which is not in the allowed range is tested, exception should be thrown
		try {
			Book invalidBook3 = new Book(20, "20 is an invalid book");
			fail("exception should be thrown");
		} catch (Exception e) 
		{
			assertEquals("id should be between 100 and 999.", e.getMessage());
		}
	}
	
	@Test
	public void testBorrowReturn() 
	{
		Book book = new Book(200, "Test Book");
		Member member = new Member(1, "Test Member");
		
		//adds the book and member from above to library
		library.addBook(book);
		library.addMember(member);
		
		//asserts that book is available
		assertTrue("Book is initially available", book.isAvailable());
		
		boolean borrowSuccess = transaction.borrowBook(book, member);
		
		//asserts that the book is borrowed 
		assertTrue("The book has been successfully borrowed", borrowSuccess);
		
		//asserts that the book is unavailable to be borrowed
		assertFalse("The Book is unavailable", book.isAvailable());
		
		//makes sure borrowing still fails
		borrowSuccess = transaction.borrowBook(book, member);
		
		//book is returned 
		boolean returnSuccess = transaction.returnBook(book, member);
		
		assertTrue("Book is available again", returnSuccess);
		
		//should return false as the book is not borrowed 
		returnSuccess = transaction.returnBook(book, member);
		
		assertFalse("Book cannot be returned if not borrowed", returnSuccess);
	}
	
	public void  testSingletonTransaction() throws Exception
	{
		Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
		
		int modifiers = constructor.getModifiers();
		assertEquals("Constructor is private", Modifier.isPrivate(modifiers));
		
		//give access to private constructor
		constructor.setAccessible(true);
		Transaction instance1 = constructor.newInstance();
		Transaction instance2 = constructor.newInstance();
		assertTrue("Transaction instances shouldn't be the same", instance1!=instance2);
		
	}
}