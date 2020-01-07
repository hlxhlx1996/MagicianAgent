package MagicianAgent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public class Delete 
{
    private final String URL = "jdbc:derby://localhost:1527/MagicianHoliday";

    private static Connection connection;
    private static PreparedStatement deleteMagician;
    private static PreparedStatement updateWL;
    
    public Delete()
    {
        try
        {
            connection = DriverManager.getConnection(URL, "java","java");
            deleteMagician = connection.prepareStatement("DELETE FROM Magician WHERE magicianID = ?");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int deleteMagician (Magician magician)
    {
        int result = 0;
        try
        {
            deleteMagician.setString(1, magician.getID());
            result = deleteMagician.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            close();
        }
        return result;
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
