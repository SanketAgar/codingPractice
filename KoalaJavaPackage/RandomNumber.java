/**
 * This class is used to generate two random numbers
 * which are used as the correct answers during the game.
 * 
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class RandomNumber
{
    private int randomNumber;

    /**
     * RandomNumber Constructor
     * The RandomNumber constructor initialises the field of the object.
     */
    public RandomNumber()
    {
        randomNumber = 0;
    }

     /**
      * Method numberGenerator
      * The numberGenrator method generates the random numbers when
      * it's called and updates it in the variables.
      * @param maximumNumber A parameter that defines the maximum number that the system will random till.
      * @return The return value will return a random number between 1 and the parameterised maximum value.
      */
    public int numberGenerator(int minimumNumber, int maximumNumber)
    {
        double randomNumberDouble= 0;
        randomNumberDouble = (Math.random()*((maximumNumber+1)-minimumNumber))+minimumNumber;
        randomNumber= (int)(randomNumberDouble);
        //System.out.println("The random number is : " + randomNumber);
        return randomNumber;
    }
}
