package MagicianAgent;

/**
 *
 * @author Liuxuan Huang lkh5155
 */
public final class Customer 
{
    private String name;
    
    public Customer()
    {}
    
    public Customer (String name)
    {
        setName(name);
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
