import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Transaction {

	
	//private static instance
		private static Transaction instance;

		//private constructor
		private Transaction() {}
		
		//public accessor method
		public static Transaction getInstance() 
		{
			if (instance == null) 
			{
				instance = new Transaction();
			}
			return instance;
		}
		
		
    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            //Call to saveTransaction method
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            //Call to saveTransaction method
            saveTransaction(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    private void saveTransaction(String transactionDetails)
    {
    	//file location
    	File file = new File("transactions.txt");
    	
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)))
    	{
    		//write the transactionDetails on transactions.txt
    		writer.write(transactionDetails);
    		//goes to new line after every transaction detail input
    		writer.newLine();
    	} catch (IOException e) 
    	{
    		System.out.println("Error occurred when going to file: " + e.getMessage());
    	}
    }
    
    public void displayTransactionHistory()
    {
    	//file location
    	File file = new File("transaction.txt");
    	
    	//checks if file exists
    	if (!file.exists())
    	{
    		System.out.println("File does not exist");
    		return;
    	}
    	
    	//try catch used to display transaction history if any error occurs the exception will be caught
    	try (Scanner scanner = new Scanner(file))
    	{
    		System.out.println("Display of Transaction History: ");
    		
    		while (scanner.hasNextLine())
    		{
    			System.out.println(scanner.nextLine());
    		}
    	} catch (IOException e) 
    	{
    		System.out.println("Error with file: " + e.getMessage());
    	}
    }
}
