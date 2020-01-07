package MagicianAgent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public class WaitListManage 
{
    private static final String URL = "jdbc:derby://localhost:1527/MagicianHoliday";
    private static Connection connection;
    private static PreparedStatement selectAllWaitList;
    
    public WaitListManage()
    {
        try
        {
            connection = DriverManager.getConnection(URL, "java","java");
            selectAllWaitList = connection.prepareStatement("SELECT * FROM waitList ORDER BY timeStamp ASC");
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            System.exit(1);
        }
    }
    
    public List<WaitList> getAllWaitList ()
    {
        List<WaitList> results = null;
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllWaitList.executeQuery();
            results = new ArrayList<>();

            while(resultSet.next())
            {
                results.add(new WaitList (                        
                    resultSet.getString("holidayName"),
                    resultSet.getString("customerName")));
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            close();
        }
        return results;
    }
    public List<Customer> getAllWLCustomers ()
    {
        List<Customer> results = null;
        ResultSet resultSet = null;
        
        try
        {
            resultSet = selectAllWaitList.executeQuery();
            results = new ArrayList<>();

            while(resultSet.next())
            {
                results.add(new Customer (resultSet.getString("customerName")));
            }
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
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
    }
}
