package MagicianAgent;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public final class Magician 
{
    private String magicianID;
    
    public Magician()
    {}
    
    public Magician(String magicianID)
    {
        setID(magicianID);
    }
    
    public void setID(String magicianID)
    {
        this.magicianID = magicianID;
    }
    public String getID()
    {
        return magicianID;
    }
}
