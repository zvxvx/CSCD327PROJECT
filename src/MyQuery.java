/*****************************
 Query the Books Database
 *****************************/
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.*;
import java.lang.String;

@SuppressWarnings("SqlNoDataSourceInspection")
public class MyQuery {
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    public MyQuery(Connection c)throws SQLException
    {
        conn = c;
// Statements allow to issue SQL queries to the database
        statement = conn.createStatement();
    }
    public void findAuthorJuanAdams() throws SQLException
    {
        String query = "select title from BOOKS natural join BOOKAUTHOR natural join AUTHOR"
            + " where fname = 'JUAN' and lname = 'ADAMS';";
        resultSet = statement.executeQuery(query);
    }
    public void printAuthorJuanAdams() throws IOException, SQLException
    {
        System.out.println("******** Query 0 ********");
        System.out.println("Book_Title");
        while (resultSet.next()) {
// It is possible to get the columns via name
// also possible to get the columns via the column number which starts at 1
            String name = resultSet.getString(1);
            System.out.println(name);
        }
        System.out.println();
    }
    public void findCustomerOrder() throws SQLException
    {
        String query = "SELECT CONCAT(FirstName, \" \", LastName) as " +
                "Full_Name, count(distinct Order_num), sum(quantity) " +
                "FROM CUSTOMERS JOIN ORDERS USING (customer_num) JOIN " +
                "ORDERITEMS USING (Order_num) " +
                "group by customer_num " +
                "order by sum(quantity) DESC;";
        resultSet = statement.executeQuery(query);
    }
    public void printCustomerOrder() throws IOException, SQLException
    {
        System.out.println("******** Query 1 ********");
        System.out.println("Num_of_Orders\tNum_of_Books\tFull_Name");
        while (resultSet.next()) {
// It is possible to get the columns via name
// also possible to get the columns via the column number which starts at 1
            String name = resultSet.getString(1);
            String orders = resultSet.getString(2);
            String books = resultSet.getString(3);
            System.out.println(orders +"\t\t\t\t"+ books +"\t\t\t\t"+ name);
        }
        System.out.println();
    }
    public void findBusyAuthor() throws SQLException
    {
        String query = "SELECT CONCAT(Fname,\" \", Lname),count(ISBN) " +
                "FROM BOOKAUTHOR JOIN AUTHOR USING (AuthorID) " +
                "GROUP BY AuthorID " +
                "HAVING count(isbn)>= all ( " +
                "SELECT count(isbn) " +
                "FROM BOOKAUTHOR " +
                "GROUP BY AuthorID);";
        resultSet = statement.executeQuery(query);
    }
    public void printBusyAuthor() throws IOException, SQLException
    {
        System.out.println("******** Query 2 ********");
        System.out.println("Num_of_Books\tAuthor_Name");
        while (resultSet.next()) {
// It is possible to get the columns via name
// also possible to get the columns via the column number which starts at 1
            String author = resultSet.getString(1);
            String books = resultSet.getString(2);
            System.out.println(books +"\t\t\t\t"+ author);
        }
        System.out.println();
    }
    public void findBookProfit() throws SQLException
    {
    }
    public void printBookProfit() throws IOException, SQLException
    {
        System.out.println("******** Query 3 ********");
    }
    public void findHighestProfitPerCategory() throws SQLException
    {
    }
    public void printHighestProfitPerCategory() throws IOException, SQLException
    {
        System.out.println("******** Query 4 ********");
    }
    public void findMinMaxOrderDate() throws SQLException
    {
    }
    public void printMinMaxOrderDate() throws IOException, SQLException
    {
        System.out.println("******** Query 5 ********");
    }
    public void updateDiscount() throws SQLException
    {
    }
    public void printUpdatedDiscount() throws IOException, SQLException
    {
        System.out.println("******** Query 6 ********");
    }
    public void findHighestProfit() throws SQLException
    {
        System.out.println("******** Query 7 ********");
    }
}