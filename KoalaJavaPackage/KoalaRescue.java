import java.util.*;
/**
 * The Koala rescue class simulates a rescue mission to save koalas.
 * The user is given free reign of 10 randomly generated observation points and is assigned a budget.
 * The user then visits each observation point and is allowed to perform a variety of actions.
 * Once the player is done visiting all the points, the program prints a result of the players choices.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class KoalaRescue
{
    private String name;
    private int amountSpent;
    private int budget;
    private Reserve reserveInRescue;
    private int observationPointindex;

    /**
     * KoalaRescue Constructor
     * The default constructor initialises a reserve class object and all other fields for the Koala Rescue Class.
     */
    public KoalaRescue()
    {
        reserveInRescue = new Reserve();
        budget = 0;
        amountSpent  = 0;
        name = " ";
        observationPointindex = 0;
    }
    
    /**
     * Method startMission
     * The Start mission method prints the introduction statements.
     */
    public void startMission()
    {
        System.out.println("Hi, welcome to the team, you have been tasked with the responsibility");
        System.out.println("to oversee all Koala Rescue operations in the area. You must decide");
        System.out.println("a fair budget for yourself and try to save as many Koalas as you can");
        System.out.println("in the given budget. Be sure to plan ahead, it ain't an easy task.");
        enterName();
    }
    
    /**
     * Method enterName
     * The enter name method accepts a name input from the user and calls data validation for it.
     */
    public void enterName()
    {
        System.out.println("\nTo begin, let's start by typing in your name!");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        if (name.length() < 16 && name.length() > 0)
        while (!isAlphabets(name))
        {
            System.out.println("Please use only alphabets in the name field.");
            System.out.println("Please enter your name again");
            name = in.nextLine();
        }
        else if (name.length() == 0)
        {
            System.out.println("Your name cannot be empty!");
        }
        else
        {
            System.out.println("Your name cannot have more than 16 characters.");
        }
        System.out.println("Your name has been saved as : " + name);
        enterBudget();
    }
    
     /**
      * Method enterBudget
      * The enterBudget method accepts a value for the budget and calls data validation on it.
      */
     public void enterBudget()
    {
        System.out.println("Enter a budget between 100 to 200!");
        Scanner in = new Scanner(System.in);
        String budget = in.nextLine();
        while (!isNumeric(budget) || !budgetCheck(budget))
        {
            if (!isNumeric(budget))
            {
                System.out.println("Please use only numbers in the budget field.");
                System.out.println("Please enter the budget again");
                budget = in.nextLine();
            }
            else if (!budgetCheck(budget))
            {
                System.out.println("Please use a value between 100 and 200");
                System.out.println("Please enter the budget again");
                budget = in.nextLine();
            }
        }
        this.budget = Integer.parseInt(budget);
        System.out.println("Your budget has been saved as : " + this.budget);
        reserveInRescue.setObservationPointsInReserve();
        reserveInRescue.updateTreesAtObservationPoint();
        observationPointDetails(observationPointindex);
    }
        
    /**
     * Method observationPointDetails
     * The method checks what observation point the player is at and calls the final functions if all observation points are over.
     * 
     * @param observationPointindex A parameter that is the index of what observation point the player is at.
     */
    public void observationPointDetails(int observationPointindex)
    {
        if (observationPointindex < 10)
        {
            int playerShow = observationPointindex + 1; 
            System.out.println("\nYou are at Observation Point " + playerShow);
            playerChoice(observationPointindex);
        }
        else
        {
            finalOutput();
        }
    }
    
    /**
     * Method playerChoice
     * The method prints all the options for the player, the outcomes of the action and accepts an input for the option,
     *
     * @param observationPointNumber A parameter that is the index of what observation point the player is at.
     */
    public void playerChoice(int observationPointNumber)
    {
        System.out.println("Your remaining Budget is $" + budget);
        reserveInRescue.printObservationPointDetails(observationPointNumber);
        System.out.println("\nWhat would you like to do?");
        System.out.println("A. Move an injured koala to the safe haven – an injured koala can be sent to the safe haven\nwhere it can be treated. If an injured koala is not taken to the safe haven\nthen it does not survive.\nThe cost of moving each injured koala is $20.");
        System.out.println("\nB. Move a healthy koala to the safe haven – if there is a shortage of food or shelter then\na koala is sent to the safe haven to await possible relocation.\nThe cost of moving each healthy koala is $10.");
        System.out.println("\nC. Relocate a koala to this location – a koala is relocated to this location from the safe haven.\nThe oldest healthy koala in the safe haven is chosen each time.\nNote that koalas can only be relocated to a location where there is enough food,\nenough shelter, and fewer than three predators.\nFor each koala relocated, $5 is added to the rescue budget");
        System.out.println("\nD. Take no further action");
        System.out.println("\nWhat would you like to do? ");
        Scanner in = new Scanner(System.in);
        String playerChoice = in.nextLine();
        while (playerChoice.length() != 1 || !isAlphabets(playerChoice))
        {
            System.out.println("\nPlease select an option from 'A', 'B', 'C' or 'D'");
            playerChoice = in.nextLine();
        }
        playerChoice = playerChoice.toUpperCase();
        while (!playerChoice.equals("A") && !playerChoice.equals("B") && !playerChoice.equals("C") && !playerChoice.equals("D"))
        {
            System.out.println("\nPlease select an option from 'A', 'B', 'C' or 'D'");
            playerChoice = in.nextLine();
        }
        
        switch(playerChoice)
        {
            case "A":
            if (reserveInRescue.getInjuredKoalasAtObservationPoint(observationPointNumber) > 0)
            {
                if (budget >= 20)
                {
                    reserveInRescue.moveInjuredKoalaToSafeHaven(observationPointNumber);
                    budget = budget - 20;
                    amountSpent = amountSpent + 20;
                    System.out.println("You have saved an injured Koala, you used $20 from your budget");
                    playerChoice(observationPointNumber);
                }
                else
                {
                    System.out.println("You don't have enough budget to move an injured Koala");
                    playerChoice(observationPointNumber);
                }
            }
            else
            {
                System.out.println("There are no injured Koalas at this Observation Point.");
                playerChoice(observationPointNumber);
            }
            break;
            case "B":
            if (reserveInRescue.getHealthyKoalasAtObservationPoint(observationPointNumber) > 0)
            {
                if (budget >= 10)
                {
                    reserveInRescue.moveHealthyKoalaToSafeHaven(observationPointNumber);
                    budget = budget - 10;
                    amountSpent = amountSpent + 10;
                    System.out.println("You have saved a healthy Koala, you used $10 from your budget");
                    playerChoice(observationPointNumber);
                }
                else
                {
                    System.out.println("You don't have enough budget to move a healthy Koala");
                    playerChoice(observationPointNumber);
                }
            }
            else
            {
                System.out.println("There are no healthy Koalas at this Observation Point.");
            }
            break;
            case "C":
            if(reserveInRescue.canRelocateKoalaToObservationPoint())
            {
                if(reserveInRescue.moveKoalaToObservationPoint(observationPointNumber))
                {
                    budget = budget + 5;
                    System.out.println("You relocated a healthy Koala to the Observation Point, budget increased by 5");
                    playerChoice(observationPointNumber);
                }
                else
                {
                    System.out.println("The Observation Point isn't safe for Koalas");
                    playerChoice(observationPointNumber);
                }
            }
            else
            {
                System.out.println("There are no healthy Koalas in the safe haven");
                playerChoice(observationPointNumber);
            }
            break;
            case "D":
            int[] observationPointFinalData = reserveInRescue.observationPointFinalizeData(observationPointNumber);
            printObservationPointFinalData(observationPointFinalData);
            observationPointindex++;
            observationPointDetails(observationPointindex);
            break;
        }
    }
    
    /**
     * Method printObservationPointFinalData
     * The method prints the final data of the observation point once the player chooses to not
     * take any more actions at the observation point
     * @param observationPointFinalData a parameter that is an array of all the details of the observation point the player is at.
     */
    public void printObservationPointFinalData(int[] observationPointFinalData)
    {
        System.out.println("You finished the rescue operation at the current observation point");
        System.out.println("The number of Koalas lost to injuries : " + observationPointFinalData[1]);
        System.out.println("The number of Koalas lost to hunger : " + observationPointFinalData[2]);
        System.out.println("The number of Koalas lost to lack of shelter : " + observationPointFinalData[3]);
        System.out.println("The number of Koalas eaten by predators : " + observationPointFinalData[4]);
        System.out.println("Total number of Koalas lost : " + observationPointFinalData[0]);
    }
   
    /**
     * Method isAlphabets
     * A data validation method that checks all the chars in a string to see if they are letters or not.
     * @param stringToCheck A parameter that is the string that needs to be validated
     * @return The return value returns a true or false value of whether the string pass the validation or not.
     */
    public boolean isAlphabets(String stringToCheck)
    {
      if (stringToCheck == null || stringToCheck.equals("") || stringToCheck.equals("\n") || stringToCheck.equals(" "))
      {
         return false;
      }
      char[] allCharacters = stringToCheck.toCharArray();
      for (char alphabet : allCharacters)
      {
         if (!Character.isLetter(alphabet))
         {
            return false;
         }
      }
      return true;
    }
    
    /**
     * Method isNumeric
     * A data validation method that checks all the chars in a string to see if they are digits or not.
     * @param stringToCheck A parameter that is the string that needs to be validated
     * @return The return value returns a true or false value of whether the string pass the validation or not.
     */
    public boolean isNumeric(String stringToCheck)
    {
        if (stringToCheck == null || stringToCheck.equals("") || stringToCheck.equals("\n") || stringToCheck.equals(" "))
        {
            return false;
        }
        char[] allCharacters = stringToCheck.toCharArray();
        for (char number : allCharacters)
        {
             if (!Character.isDigit(number))
             {
                return false;
             }
        }
        return true;
    }
    
    /**
     * Method budgetCheck
     * The method checks if the entered value is between 100 and 200
     * @param budgetToCheck A parameter that is the string of the number to be input
     * @return The return value returns a true or false value of whether the string pass the validation or not.
     */
    public boolean budgetCheck(String budgetToCheck)
    {
        int budget = Integer.parseInt(budgetToCheck);
        if (budget < 100 || budget > 200)
            return false;
        else
            return true;
    }
    
    /**
     * Method finalOutput
     * The method prints the final output of the project once the player has finished visiting all the observation points.
     */
    public void finalOutput()
    {
        int[] finalOutputData = reserveInRescue.getFinalOutputData();
        System.out.println("You have visited all the Observation Points.");
        System.out.println("The total number of trees lost was : " + finalOutputData[0]);
        System.out.println("The total number of healthy Koalas remaining was  : " + finalOutputData[1]);
        System.out.println("The total number of injured Koalas taken to safe haven was  : " + finalOutputData[2]);
        System.out.println("The total number of Koalas relocated to a safe Observation Point  : " + finalOutputData[3]);
        System.out.println("The total amount of money spent during the rescue  : " + amountSpent);
        if (finalOutputData[4] == 0)
        {
            System.out.println("Rescue was successful, with no koala deaths");
        }
        else
        {
            System.out.println("Rescue completed with " + finalOutputData[4] + " koalas deaths");
        }
        reserveInRescue.printUpdatedTrees();
    }
}
