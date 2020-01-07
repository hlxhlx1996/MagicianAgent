package MagicianAgent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public class Book 
{
    private final String URL = "jdbc:derby://localhost:1527/MagicianHoliday";

    private static Connection connection;
    private static PreparedStatement insertNewCustomer;
    private static PreparedStatement findMagician;
    private static PreparedStatement getWaitList;
    private static PreparedStatement deleteBooking;
    private static PreparedStatement deleteWL;
    private static Timestamp currentTimeStamp;
    
    public Book()
    {
        try
        {
            connection = DriverManager.getConnection(URL, "java","java");
            insertNewCustomer = connection.prepareStatement("INSERT INTO bookingTable (holidayName, customerName, magicianID) VALUES ( ?, ?,?)");
            findMagician = connection.prepareStatement("SELECT MagicianID FROM Magician WHERE MagicianID NOT IN ( SELECT MagicianID FROM bookingTable where bookingTable.holidayName = ?)");
            getWaitList = connection.prepareStatement("INSERT INTO waitList (holidayName, customerName, timeStamp) values (?, ?,?)"); 
            deleteBooking = connection.prepareStatement("DELETE FROM bookingTable WHERE customerName = ? AND holidayName = ? AND magicianID = ?");
            deleteWL = connection.prepareStatement("DELETE FROM waitList WHERE holidayName = ? AND customerName = ?");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int addCustomer(Holiday holiday, Customer customer, Magician magician)
    {
        int result = 0;
        try
        {
            insertNewCustomer.setString(1, holiday.getHolidayName());
            insertNewCustomer.setString(2, customer.getName());
            insertNewCustomer.setString(3, magician.getID());
            
            result = insertNewCustomer.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    public static int addWaitList(Customer customer, Holiday holiday)
    {
        int result = 0;
        currentTimeStamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
            getWaitList.setString(1, holiday.getHolidayName());
            getWaitList.setString(2, customer.getName());
            getWaitList.setTimestamp(3, currentTimeStamp);
            result = getWaitList.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    public static int insertWaitList(Customer customer, Holiday holiday)
    {
        int result = 0;
        currentTimeStamp = Timestamp.valueOf("2000-01-01 00:00:01.0");
        try
        {
            getWaitList.setString(1, holiday.getHolidayName());
            getWaitList.setString(2, customer.getName());
            getWaitList.setTimestamp(3, currentTimeStamp);
            result = getWaitList.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    public String getFreeMagician(String holiday)
    {
        String results = null;
        ResultSet resultSet = null;
        try
        {
            findMagician.setString(1, holiday);
            resultSet = findMagician.executeQuery();
            results = new String();
            if (resultSet.next() == false)
            {
                results = null;
            }
            else
            {
                results = resultSet.getString("magicianID");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return results;
    }
    
    public static int deleteBooking (bookingTable entry)
    {
        int results = 0;
        try
        {
            deleteBooking.setString(1, entry.getName());
            deleteBooking.setString(2, entry.getHoliday());
            deleteBooking.setString(3, entry.getMagician());

            results = deleteBooking.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            close();
        }
        return results;
    }
    
    public static int deleteWaitList (WaitList wl)
    {
        int results = 0;
        
        try
        {
            deleteWL.setString(1, wl.getHoliday());
            deleteWL.setString(2, wl.getName());
            
            results = deleteWL.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            close();
        }
        return results;
    }
    
    public static void close()
    {
        try
        {
            connection.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
