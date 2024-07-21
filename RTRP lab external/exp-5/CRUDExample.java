import java.sql.*;

public class CRUDExample{
	//JDBC URL, username and passowrd of MYSQL server
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookstore";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "$ph00rthy@SPHN";

	public static void main(String[] args){
		try{
			//Step 1: Establishing a connection
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			//Step 2: Creating a statement
			Statement statement = connection.createStatement();
			//Step 3: Perform CRUD operations
			createRecord(statement, "0-676-97376-0", "Novel", "Life of Pi", "Yann Martel", 2001, 295);
			readRecords(statement);
			String isbn =  "0-676-97376-0";
			updateRecord(statement, isbn, 300);
			readRecords(statement);
			deleteRecord(statement, isbn);
			readRecords(statement);
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Create a new record in databases
	private static void createRecord(Statement statement,
					 String isbn,
					 String category,
					 String title,
					 String author,
					 int year,
					 float price)throws SQLException{
		String insertQuery = "INSERT INTO books VALUES ('" + isbn + "','"+ category + "','" + title + "','" + author + "'," + year + "," + price + ")";
		statement.executeUpdate(insertQuery);
		System.out.println("Record created successfully.");
	}
	
	// Read all records from the database
	private static void readRecords(Statement statement) throws SQLException{
		String isbn = null, category = null, title = null, author = null;
		int year = 0;
		float price = 0.0f;
		
		String selectQuery = "SELECT * FROM books";
		ResultSet resultSet = statement.executeQuery(selectQuery);
		//System.out.println("ISBN\t\tCategory Title\t\tAuthor\t\tYear\t\tPrice");
		String titles = String.format("%17s %20s %30s %30s %s %s", "ISBN", "Category", "Title", "Author", "Year", "Price");
		System.out.println(titles);
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		while(resultSet.next()){
			isbn = resultSet.getString("isbn");
			category = resultSet.getString("category");
			title = resultSet.getString("title");
			author = resultSet.getString("author");
			year = resultSet.getInt("year");
			price = resultSet.getFloat("price");
			//System.out.println(isbn + "\t" + category + "\t" + title + "\t" + author + "\t" + year + "\t" + price);	
			String output = String.format("%17s %20s %30s %30s %d %f", isbn, category, title, author, year, price);
			System.out.println(output);
		}

		resultSet.close();
		System.out.println();
	}
	
	// Update a record in the database
	private static void updateRecord(Statement statement,
					 String isbn,
					 float price)throws SQLException{
		String updateQuery = "UPDATE books SET price = "+ price + " WHERE isbn = '" + isbn + "'"; 
		statement.executeUpdate(updateQuery);
		System.out.println("Record updated successfully.");
	}	 

	// Delete a record from the database
	private static void deleteRecord(Statement statement, String isbn) throws SQLException{
		String deleteQuery = "DELETE FROM books WHERE isbn ='" + isbn + "'";
		statement.executeUpdate(deleteQuery);
		System.out.println("Record deleted successfully.");
	} 
}