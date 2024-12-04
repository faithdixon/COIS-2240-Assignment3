import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

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
}