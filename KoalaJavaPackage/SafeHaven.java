import java.util.*;
/**
 * A safe haven is a safe zone for the Koalas, the user can choose to move Koalas into the observation point
 * but they can only move healthy koalas out of the safe haven.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class SafeHaven
{
    private ArrayList<Koala> KoalasInSafeHaven;

    /**
     * Constructor for objects of class Safehaven
     */
    public SafeHaven()
    {
        KoalasInSafeHaven = new ArrayList<Koala>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addKoala(Koala koalaToBeAdded)
    {
        KoalasInSafeHaven.add(koalaToBeAdded);
    }
    
    public ArrayList<Koala> getKoalasInSafeHaven()
    {
        return KoalasInSafeHaven;
    }
    
    public boolean canRelocateKoala()
    {
        int listSize = KoalasInSafeHaven.size();
        for (int iterator = 0; iterator < listSize; iterator++)
        {
            Koala currentKoala = KoalasInSafeHaven.get(iterator);
            if (!currentKoala.getIsInjured())
            {
                return true;
            }
        }
        return false;
    }
    
    public Koala removeKoala()
    {
        int listSize = KoalasInSafeHaven.size();
        int highestAge = 0;
        int oldestKoalaPosition = 0;
        Koala oldestKoala = new Koala();
        for (int iterator = 0; iterator < listSize; iterator++)
        {
            Koala currentKoala = KoalasInSafeHaven.get(iterator);
            if (currentKoala.getAge() > highestAge && !currentKoala.getIsInjured())
            {
                oldestKoala = currentKoala;
                highestAge = currentKoala.getAge();
                oldestKoalaPosition = iterator;
            }
        }
        KoalasInSafeHaven.remove(oldestKoalaPosition);
        return oldestKoala;
    }
}
