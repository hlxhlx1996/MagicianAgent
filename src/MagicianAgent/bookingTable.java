package MagicianAgent;
/**
 *
 * @author Liuxuan Huang lkh5155
 */
public final class bookingTable 
{
    private String name;
    private String holiday;
    private String magician;

    public bookingTable()
    {}

    public bookingTable(String holiday, String magician, String name)
    {
        setName(name);
        setHoliday(holiday);
        setMagician(magician);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setHoliday(String holiday)
    {
        this.holiday = holiday;
    }

    public String getHoliday()
    {
        return holiday;
    }

    public void setMagician(String magician)
    {
        this.magician = magician;
    }

    public String getMagician()
    {
        return magician;
    }
}