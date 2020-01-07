package MagicianAgent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public class Add 
{
    private final String URL = "jdbc:derby://localhost:1527/MagicianHoliday";

    private static Connection connection;
    private static PreparedStatement addNewHoliday;
    private static PreparedStatement addNewMagician;
    
    public Add()
    {
        try
        {
            connection = DriverManager.getConnection(URL, "java","java");
            addNewHoliday = connection.prepareStatement("INSERT INTO Holiday (holidayName) VALUES (?)");
            addNewMagician = connection.prepareStatement("INSERT INTO Magician (magicianID) VALUES (?)");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int addHoliday(Holiday newHoliday) 
    {
        int result = 0;
        try
        {
            addNewHoliday.setString(1, newHoliday.getHolidayName());
            result = addNewHoliday.executeUpdate();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            close();
        }
        return result;
    }
    
    public static int addMagician(Magician newMagician) 
    {
        int result = 0;
        try
        {
            addNewMagician.setString(1, newMagician.getID());
            result = addNewMagician.executeUpdate();
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
