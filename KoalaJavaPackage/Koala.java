/**
 * The Koala class stores the details and properties of every single Koala.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class Koala
{
    // instance variables - replace the example below with your own
    private boolean isInjured;
    private int age;

    /**
     * Constructor for objects of class Koala
     */
    public Koala()
    {
        isInjured = false;
        age = 1;
    }
    
    public Koala (int injuryStatus, int randomAge)
    {
        setAge(randomAge);
        setInjured(injuryStatus);
    }
    
    public int getAge()
    {
        return age;
    }
    
    public boolean getIsInjured()
    {
        return isInjured;
    }
    
    public void setAge(int randomAge)
    {
        age = randomAge;
    }
    
    public void setInjured(int injuryNumber)
    {
        if(injuryNumber == 1)
        isInjured = true;
        else
        isInjured = false;
    }
}
