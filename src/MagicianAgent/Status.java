package MagicianAgent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Liuxuan Huang lkh5155
 */
public class Status 
{
    private static final String URL = "jdbc:derby://localhost:1527/MagicianHoliday";
    private static Connection connection;
    private static PreparedStatement selectAllOrders;
    private static PreparedStatement selectAllHolidays;
    private static PreparedStatement selectAllMagicians;
    private static PreparedStatement selectAllCustomers;
    private static PreparedStatement selectMagicianStatus;
    private static PreparedStatement selectHolidayStatus;
    private static PreparedStatement selectRescheduledMagician;
    private static PreparedStatement selectWLCustomer;
    
    public Status()
    {
        try
        {
            connection = DriverManager.getConnection(URL, "java","java");
            selectAllOrders = connection.prepareStatement("SELECT * FROM bookingTable");
            selectAllHolidays = connection.prepareStatement("SELECT * FROM Holiday");
            selectAllMagicians = connection.prepareStatement("SELECT * FROM Magician");
            selectAllCustomers = connection.prepareStatement("SELECT customerName FROM bookingTable");
            selectHolidayStatus = connection.prepareStatement("SELECT * FROM bookingTable WHERE holidayName = ?");
            selectMagicianStatus = connection.prepareStatement("SELECT * FROM bookingTable WHERE magicianID = ?");
            selectRescheduledMagician = connection.prepareStatement("SELECT magicianID FROM bookingTable WHERE customerName = ? AND holidayName = ?");
            selectWLCustomer = connection.prepareStatement("SELECT customerName FROM WaitList WHERE holidayName = ? ORDER BY timeStamp ASC");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public List< bookingTable > getAllOrders()
    {
        List< bookingTable > results = null;
        ResultSet resultSet = null;
        try
        {
            resultSet = selectAllOrders.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next())
            {
                results.add(new bookingTable(
                    resultSet.getString("holidayName"),             
                    resultSet.getString("magicianID"),
                    resultSet.getString("customerName")));
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return results;
    }
    public List<Holiday> getAllHolidays()
    {
        List<Holiday> results = null;
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllHolidays.executeQuery();
            results = new ArrayList<>();
            
            while(resultSet.next())
            {
                results.add(new Holiday(resultSet.getString("holidayName")));
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return results;
    }
    
    public List<Magician> getAllMagicians()
    {
        List<Magician> results = null;
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllMagicians.executeQuery();
            results = new ArrayList<>();
            
            while(resultSet.next())
            {
                results.add(new Magician(resultSet.getString("magicianID")));
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return results;
    }
        public List<Customer> getAllCustomers()
    {
        List<Customer> results = null;
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllCustomers.executeQuery();
            results = new ArrayList<>();
            
            while(resultSet.next())
            {
                results.add(new Customer(resultSet.getString("customerName")));
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return results;
    }
    public static List< bookingTable > getStatusH(String holidayName)
    {
        List< bookingTable > results = null;
        ResultSet resultSet = null;

        try
        {
            selectHolidayStatus.setString(1, holidayName);
            resultSet = selectHolidayStatus.executeQuery();
            results = new ArrayList<>();

            while (resultSet.next())
            {
                results.add(new bookingTable(
                    resultSet.getString("holidayName"),             
                    resultSet.getString("magicianID"),
                    resultSet.getString("customerName")));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }
    public static List< bookingTable > getStatusM(String magicianID)
    {
        List< bookingTable > results = null;
        ResultSet resultSet = null;
        try
        {
            selectMagicianStatus.setString(1, magicianID);
            resultSet = selectMagicianStatus.executeQuery();
            results = new ArrayList< bookingTable >();

            while (resultSet.next())
            {
                results.add(new bookingTable(
                    resultSet.getString("holidayName"),
                    resultSet.getString("magicianID"),
                    resultSet.getString("customerName")));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
                close();
            }
        }
        return results;
    }
    public String getRescheduledMagician(String name, String holiday)
    {
        String results = null;
        ResultSet resultSet = null;
        try
        {
            selectRescheduledMagician.setString(1, name);
            selectRescheduledMagician.setString(2, holiday);
            resultSet = selectRescheduledMagician.executeQuery();
            
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
    
    public String checkWLCustomer(String holiday)
    {
        String results = null;
        ResultSet resultSet = null;
        try
        {
            selectWLCustomer.setString(1, holiday);
            resultSet = selectWLCustomer.executeQuery();
            
            results = new String();
            if (resultSet.next() == false)
            {
                results = null;
            }
            else
            {
                results = resultSet.getString("customerName");
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
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