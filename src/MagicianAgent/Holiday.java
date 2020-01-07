package MagicianAgent;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public final class Holiday 
{
    private String holidayName;
    
    public Holiday()
    {}
    
    public Holiday(String name)
    {
        setHolidayName(name);
    }
    
    public void setHolidayName(String name)
    {
        this.holidayName = name;
    }
    public String getHolidayName()
    {
        return holidayName;
    }    
}
