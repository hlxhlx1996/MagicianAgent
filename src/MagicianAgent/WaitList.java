package MagicianAgent;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public final class WaitList 
{
    private String name;
    private String holiday;
    
    public WaitList()
    {}

    public WaitList (String holiday, String name)
    {
        setHoliday(holiday);
        setName(name);
    }

    public void setHoliday(String holiday) 
    {
        this.holiday = holiday;
    }
    public String getHoliday() 
    {
        return holiday;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    public String getName() 
    {
        return name;
    }
    
}
