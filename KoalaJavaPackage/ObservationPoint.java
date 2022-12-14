import java.util.*;
/**
 * An observation point creates and maintains a set of trees and Koalas inside it.
 * The observation point also manages and maintains any changes on the trees and Koalas inside the
 * observation point.
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class ObservationPoint
{
    private ArrayList<Tree> treesAtObservationPoint;
    private ArrayList<Koala> koalasAtObservationPoint;
    private int numberOfPredators;
    
    /**
     * ObservationPoint Constructor
     * The default constructor updates initialises the values of the class.
     */
    public ObservationPoint()
    {
        treesAtObservationPoint = new ArrayList<Tree>();
        koalasAtObservationPoint = new ArrayList<Koala>();
        numberOfPredators = 0;
    }
    
    /**
     * ObservationPoint Constructor
     * The parameterised constructor takes input for the requied fields and calls set functions to set the data of the fields.
     * @param trees A parameter which is an int array which specifies the number of each type of tree in the observation point.
     * @param healthyKoalasValue A parameter that is an int value of how many healthy koalas are in the observation point. Randomly generated.
     * @param injuredKoalasValue A parameter that is an int value of how many injured koalas are in the observation point. Randomly generated.
     * @param numberOfPredators A parameter that is an int value of how many predators are there in the observation point. Randomly generated.
     */
    public ObservationPoint(int[] trees, int healthyKoalasValue, int injuredKoalasValue, int numberOfPredators)
    {
        treesAtObservationPoint = new ArrayList<Tree>();
        koalasAtObservationPoint = new ArrayList<Koala>();
        setTreesAtObservationPoint(trees);
        setHealthyKoalasAtObservationPoint(healthyKoalasValue);
        setInjuredKoalasAtObservationPoint(injuredKoalasValue);
        setNumberOfPredators(numberOfPredators);
    }
    
    /**
     * Method getHealthyKoala
     * The method returns an array list of all the injured koalas in the observation point by looping through all the koalas and checking if they 
     * are injured or not and creating a new list of all the healthy koalas.
     * @return The return value is an array list of all healthy koalas.
     */
    public ArrayList<Koala> getHealthyKoala()
    {
        ArrayList<Koala> healthyKoalas = new ArrayList<Koala>();
        int sizeOfList = koalasAtObservationPoint.size();
        for(int iterator = 0; iterator < sizeOfList; iterator++)
            {
                Koala currentKoala = koalasAtObservationPoint.get(iterator);
                if(!currentKoala.getIsInjured())
                {
                    healthyKoalas.add(currentKoala);
                }
            }
        return healthyKoalas;
    }
    
    /**
     * Method getInjuredKoala
     * The method returns an array list of all the injured koalas in the observation point by looping through all the koalas and checking if they 
     * are injured or not and creating a new list of all the injured koalas.
     * @return The return value is an array list of all injured koalas.
     */
    public ArrayList<Koala> getInjuredKoala()
    {
        ArrayList<Koala> injuredKoalas = new ArrayList<Koala>();
        int sizeOfList = koalasAtObservationPoint.size();
        for(int iterator = 0; iterator < sizeOfList; iterator++)
            {
                Koala currentKoala = koalasAtObservationPoint.get(iterator);
                if(currentKoala.getIsInjured())
                {
                    injuredKoalas.add(currentKoala);
                }
            }
        return injuredKoalas;
    }
    
    /**
     * Method getKoalas
     * The method returns an array list of all the koalas in the observation point which is a field of the class.
     * @return The return value  is an array list of all koalas.
     */
    public ArrayList<Koala> getKoalas()
    {
        return koalasAtObservationPoint;
    }
    
    /**
     * Method getNumberOfPredators
     * The method returns an int value of the number of predators at the observation point which is a field of the class.
     * @return The return value is an int value of the number of predators.
     */
    public int getNumberOfPredators()
    {
        return numberOfPredators;
    }
    
    /**
     * Method getTotalAmountOfFood
     * The method returns a double value of the weight of food generated by the trees everday by looping through every tree and adding the weight produced by it..
     * @return The return value is a double which is a sum of all the food produced by the trees.
     */
    public double getTotalAmountOfFood()
    {
        double totalAmountOfFood = 0.0;
        int listSize = treesAtObservationPoint.size();
        for(int iterator = 0; iterator < listSize; iterator++)
        {
            Tree currentTree = treesAtObservationPoint.get(iterator);
            double currentTreeFood = currentTree.getFoodProduced();
            totalAmountOfFood = totalAmountOfFood + currentTreeFood;
        }
        return totalAmountOfFood;
    }
    
    /**
     * Method getTotalNumberOfShelter
     * Returns the total number of shelter trees at the observation point by looping through every tree and checking if they are shelter.
     * @return The return value is an int of the number of shelter trees at the observation point.
     */
    public int getTotalNumberOfShelter()
    {
        int totalNumberOfShelter = 0;
        int listSize = treesAtObservationPoint.size();
        for(int iterator = 0; iterator < listSize; iterator++)
        {
            Tree currentTree = treesAtObservationPoint.get(iterator);
            boolean currentShelterStatus = currentTree.getIsShelter();
            if(currentShelterStatus)
            {
                totalNumberOfShelter++;
            }
        }
        return totalNumberOfShelter;
    }
    
    /**
     * Method getTreesAtObservationPoint
     * The method returns an ArrayList of all the trees in the observation point, this is also a field in the class.
     * @return The return value is an ArrayList of all the trees.
     */
    public ArrayList<Tree> getTreesAtObservationPoint()
    {
        return treesAtObservationPoint;
    }
    
    /**
     * Method setHealthyKoalasAtObservationPoint
     * Takes an input value and creates healthy koalas at the observation point based on these values.
     * @param healthyKoalasValue A parameter of how many healthy koalas will be in the observation point. Randomly generated.
     */
    public void setHealthyKoalasAtObservationPoint(int healthyKoalasValue)
    {
        if(healthyKoalasValue >= 0 && healthyKoalasValue <= 9)
        {
            for(int iterator = 0; iterator < healthyKoalasValue; iterator++)
            {
                RandomNumber randomGenerator = new RandomNumber();
                int koalaAge = randomGenerator.numberGenerator(1,18);
                Koala healthyKoala = new Koala(0, koalaAge);
                koalasAtObservationPoint.add(healthyKoala);
            }
        }
        else
        {
            System.out.println("Number of Healthy Koalas can't be: " + healthyKoalasValue + ". Please select a value between 0-9");
        }
    }
    
    /**
    Method setInjuredKoalasAtObservationPoint
     * Takes an input value and creates inured koalas at the observation point based on these values.
     * @param injuredKoalasValue A parameter of how many injured koalas will be in the observation point. Randomly generated.
     */
    public void setInjuredKoalasAtObservationPoint(int injuredKoalasValue)
    {
        if(injuredKoalasValue >= 0 && injuredKoalasValue <= 2)
        {
            for(int iterator = 0; iterator < injuredKoalasValue; iterator++)
            {
                RandomNumber randomGenerator = new RandomNumber();
                int koalaAge = randomGenerator.numberGenerator(1,18);
                Koala injuredKoala = new Koala(1, koalaAge);
                koalasAtObservationPoint.add(injuredKoala);
            }
        }
        else
        {
            System.out.println("Number of Injured Koalas can't be: " + injuredKoalasValue + ". Please select a value between 0-2");
        }
    }
    
    /**
     * Method setNumberOfPredators
     * Sets a number of predators at the observation point based on the parameter value.
     * @param predatorsValue A parameter that defines the number of predators in the observation point.
     */
    public void setNumberOfPredators(int predatorsValue)
    {
        if(predatorsValue >= 0 && predatorsValue <= 4)
        {
            numberOfPredators = predatorsValue;
        }
        else
        {
            System.out.println("Number of predators can't be: " + predatorsValue + ". Please select a value between 0-4");
        }
    }
    
    /**
     * Method setTreesAtObservationPoint
     * Created a number of trees at the observation point of all types based on an input parameter.
     * @param trees A parameter which is an int array with 5 numbers, each index being a type of tree and the value being the number
     * of trees.
     */
    public void setTreesAtObservationPoint(int[] trees)
    {
        for (int index = 0; index < trees.length; index++)
            {
                int everyType = trees[index];
                for(int iterator = 1; iterator <= everyType; iterator++)
                {
                    int treeType = index + 1;
                    Tree newTree = new Tree(treeType);
                    treesAtObservationPoint.add(newTree);
                }
            }
    }
    
    /**
     * Method addKoala
     * A method that adds a specific koala to the Observation point, the specific Koala is recieved from the parameter.
     * @param koalaToAdd A parameter that is a specific koala object.
     */
    public void addKoala(Koala koalaToAdd)
    {
        koalasAtObservationPoint.add(koalaToAdd);
    }
    
    /**
     * Method removeInjuredKoala
     * Removes an injured koala from the observation point and returns the Koala object.
     * @return The return value is the Koala that was removed from the observation point.
     */
    public Koala removeInjuredKoala()
    {
        int sizeOfList = koalasAtObservationPoint.size();
        boolean foundInjuredKoala = false;
        Koala injuredKoala = new Koala();
        int iterator = 0;
        while(!foundInjuredKoala && iterator < sizeOfList)
        {       
            Koala currentKoala = koalasAtObservationPoint.get(iterator);
            if(currentKoala.getIsInjured())
            {
                injuredKoala = currentKoala;
                koalasAtObservationPoint.remove(iterator);
                foundInjuredKoala = true;
            }
            iterator++;
        }
        return injuredKoala;
    }
    
    /**
     * Method removeHealthyKoala
     * Removes an healthy koala from the observation point and returns the Koala object.
     * @return The return value is a is the Koala that was removed from the observation point.
     */
    public Koala removeHealthyKoala()
    {
        int sizeOfList = koalasAtObservationPoint.size();
        boolean foundHealthyKoala = false;
        Koala HealthyKoala = new Koala();
        int iterator = 0;
        while(!foundHealthyKoala && iterator < sizeOfList)
        {       
            Koala currentKoala = koalasAtObservationPoint.get(iterator);
            if(!currentKoala.getIsInjured())
            {
                HealthyKoala = currentKoala;
                koalasAtObservationPoint.remove(iterator);
                foundHealthyKoala = true;
            }
            iterator++;
        }
        return HealthyKoala;
    }
    
    /**
     * Method isObservationPointSafe
     * The method checks to see if an observation point is safe or not based on the number of predators, total amount of food 
     * and total number of shelter trees.
     * @return The return value of true or false based on if the observation point is safe or not.
     */
    public boolean isObservationPointSafe()
    {
        double foodAtObservationPoint = getTotalAmountOfFood();
        int numberOfKoalasAtObservationPoint = koalasAtObservationPoint.size();
        int shelterAtObservationPoint = getTotalNumberOfShelter();
        double differenceInFood = foodAtObservationPoint - numberOfKoalasAtObservationPoint;
        int differenceInShelter = shelterAtObservationPoint - numberOfKoalasAtObservationPoint;
        if(differenceInFood >= 1 && differenceInShelter >= 1 && numberOfPredators < 3)
        return true;
        else
        return false;
    }
    
    /**
     * Method killInjuredKoalas
     * The method kills all the injured koalas remaining at the observation point by looping through all the koalas and checking if the are
     * injured or not.
     * @return The return value is an int number of how many koalas were killed.
     */
    public int killInjuredKoalas()
    {
        int sizeOfList = koalasAtObservationPoint.size();
        int killedInjuredKoalas = 0;
        for(int iterator = 0; iterator < sizeOfList; iterator++)
        {       
            Koala currentKoala = koalasAtObservationPoint.get(iterator);
            if(currentKoala.getIsInjured())
            {
                koalasAtObservationPoint.remove(iterator);
                killedInjuredKoalas++;
                sizeOfList--;
            }
        }
        return killedInjuredKoalas;
    }
    
    /**
     * Method killKoala
     * The method kills the first koala in the list.
     */
    public void killKoala()
    {
        if(koalasAtObservationPoint.size() > 0)
        {
            koalasAtObservationPoint.remove(0);
        }
    }
    
    /**
     * Method removeTreeAtObservationPoint
     * The method removes a specific tree from the observation point.
     * @param iteratorOfTree A parameter of the position of the tree in the ArrayList.
     */
    public void removeTreeAtObservationPoint(int iteratorOfTree)
    {
        treesAtObservationPoint.remove(iteratorOfTree);
    }
}
