import java.util.*;
/**
 * The reserve class creates and manages all the observation points and the safe haven in the
 * Koala rescue mission. The class oversees all the moving of the koala and handling of the data
 * in the observation point class.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class Reserve
{
    private ObservationPoint[] observationPointsInReserve;
    private SafeHaven safeHavenInReserve;
    private int totalNumberOfDeadKoalas;
    private int totalNumberOfTreesLost;
    private int injuredKoalasSaved;
    private int koalasRelocated;
    
    /**
     * Reserve Constructor
     * The default constructor initialises all the values in the class.
     */
    public Reserve()
    {
        observationPointsInReserve = new ObservationPoint[10];
        safeHavenInReserve = new SafeHaven();
        totalNumberOfDeadKoalas = 0;
        totalNumberOfTreesLost = 0;
        injuredKoalasSaved = 0;
        koalasRelocated = 0;
    }
    
    /**
     * Method setObservationPointsInReserve
     * The set observation point function is used to take the return value trees from the ReadAndWrite class and then create trees out of them.
     */
    public void setObservationPointsInReserve()
    {
        ReadAndWrite reader = new ReadAndWrite();
        int arrayOfObservationPoints[][] = reader.readFile();
        int[] observationPointTrees = new int[5];
        RandomNumber randomGenerator = new RandomNumber();
        for (int index = 0; index <= 9; index++)
        {
            int injuredKoalas = randomGenerator.numberGenerator(0,2);
            int predatorNumber = randomGenerator.numberGenerator(0,4);
            int healthyKoalas = randomGenerator.numberGenerator(0,9);
            for (int iterator = 0; iterator <= 4; iterator++)
            {
                 observationPointTrees[iterator] = arrayOfObservationPoints[index][iterator];
            }
            ObservationPoint createObservationPoint = new ObservationPoint(observationPointTrees,healthyKoalas,injuredKoalas,predatorNumber);
            observationPointsInReserve[index] = createObservationPoint;
        }
    }
    
    public double getFoodAtObservationPoint(int observationPointNumber)
    {
        double FoodAtObservationPoint = 0.0;
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        FoodAtObservationPoint = desiredObservationPoint.getTotalAmountOfFood();
        return FoodAtObservationPoint;
    }
    
    public int getShelterAtObservationPoint(int observationPointNumber)
    {
        int shelterAtObservationPoint = 0;
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        shelterAtObservationPoint = desiredObservationPoint.getTotalNumberOfShelter();
        return shelterAtObservationPoint;
    }
    
    public int getPredatorsAtObservationPoint(int observationPointNumber)
    {
        int predatorsAtObservationPoint = 0;
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        predatorsAtObservationPoint = desiredObservationPoint.getNumberOfPredators();
        return predatorsAtObservationPoint;
    }
    
    public int getHealthyKoalasAtObservationPoint(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        return desiredObservationPoint.getHealthyKoala().size();
    }
    
    public int getInjuredKoalasAtObservationPoint(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        return desiredObservationPoint.getInjuredKoala().size();
    }
    
    public void moveHealthyKoalaToSafeHaven(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        safeHavenInReserve.addKoala(desiredObservationPoint.removeHealthyKoala()); 
    }
    
    public void moveInjuredKoalaToSafeHaven(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        safeHavenInReserve.addKoala(desiredObservationPoint.removeInjuredKoala());
        injuredKoalasSaved++;
    }
    
    public boolean moveKoalaToObservationPoint(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        if (safeHavenInReserve.getKoalasInSafeHaven().size() > 0)
        {
            desiredObservationPoint.addKoala(safeHavenInReserve.removeKoala());
            koalasRelocated++;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int[] observationPointFinalizeData(int observationPointNumber)
    {
        ObservationPoint desiredObservationPoint = observationPointsInReserve[observationPointNumber];
        int injuredKoalasKilled = desiredObservationPoint.killInjuredKoalas();
        int foodAtObservationPoint = (int)desiredObservationPoint.getTotalAmountOfFood();
        RandomNumber generateNumber = new RandomNumber();
        int hungryKoalasKilled = 0;
        int excessHungryKoalas = desiredObservationPoint.getKoalas().size() - foodAtObservationPoint;
        for (int iterator = 0; iterator < excessHungryKoalas; iterator++)
        {
            int randomNumber = generateNumber.numberGenerator(1, 5);
            if (randomNumber < 5)
            {
                desiredObservationPoint.killKoala();
                hungryKoalasKilled++;
            }
        }
        int shelterAtObservationPoint = desiredObservationPoint.getTotalNumberOfShelter();
        int homelessKoalasKilled = 0;
        int excessHomelessKoalas = desiredObservationPoint.getKoalas().size() - shelterAtObservationPoint;
        for (int iterator = 0; iterator < excessHomelessKoalas; iterator++)
        {
            int randomNumber = generateNumber.numberGenerator(1, 5);
            if (randomNumber == 5)
            {
                desiredObservationPoint.killKoala();
                homelessKoalasKilled++;
            }
        }
        int randomNumber = generateNumber.numberGenerator(1, 2);
        int koalasEatenByPredator = 0;
        if (desiredObservationPoint.getNumberOfPredators() >= 3 && randomNumber == 2)
        {
            desiredObservationPoint.killKoala();
            koalasEatenByPredator++;
        }
        int totalKoalasKilled = injuredKoalasKilled + hungryKoalasKilled + homelessKoalasKilled + koalasEatenByPredator;
        int[] koalasKilledSplit = {totalKoalasKilled, injuredKoalasKilled, hungryKoalasKilled, homelessKoalasKilled,koalasEatenByPredator};
        totalNumberOfDeadKoalas = totalNumberOfDeadKoalas + totalKoalasKilled;
        return koalasKilledSplit;
    }
    
    public void updateTreesAtObservationPoint()
    {
        RandomNumber generateNumber = new RandomNumber();
        for (int index = 0; index < 10; index++)
        {
            ObservationPoint desiredObservationPoint = observationPointsInReserve[index];
            for (int treeType = 1; treeType <= 5; treeType++)
            {
                Boolean isTreeDeleted = false;
                int randomNumber = generateNumber.numberGenerator(1,20);
                if (randomNumber == 1)
                {
                    for (int iterator = 0; iterator < desiredObservationPoint.getTreesAtObservationPoint().size(); iterator++)
                    {
                        if (desiredObservationPoint.getTreesAtObservationPoint().get(iterator).getTypeOfTree() == treeType && !isTreeDeleted)
                        {
                            desiredObservationPoint.removeTreeAtObservationPoint(iterator);
                            isTreeDeleted = true;
                            totalNumberOfTreesLost++;
                            //System.out.println("One tree lost of type : " + treeType + " at " + index);
                        }
                    }
                }
            }
        }
    }
    
    public void printUpdatedTrees()
    {
        int[][] updatedListOfTrees = new int[10][5];
        for (int index = 0; index < 10; index++)
        {
            ObservationPoint desiredObservationPoint = observationPointsInReserve[index];
            for (int iterator = 0; iterator < 5; iterator++)
            {
                int countOfType = 0;
                int treeType = iterator + 1;
                for (int everyTree = 0; everyTree < desiredObservationPoint.getTreesAtObservationPoint().size(); everyTree++)
                {
                    if (desiredObservationPoint.getTreesAtObservationPoint().get(everyTree).getTypeOfTree() == treeType)
                    {
                        countOfType++;
                    }
                }
                updatedListOfTrees[index][iterator] = countOfType;
            }
        }
        ReadAndWrite writer = new ReadAndWrite();
        writer.writeFile(updatedListOfTrees);
    }
    
    public void printObservationPointDetails(int observationPointNumber)
    {
        int healthyKoalaCount = getHealthyKoalasAtObservationPoint(observationPointNumber);
        int injuredKoalaCount = getInjuredKoalasAtObservationPoint(observationPointNumber);
        int shelterCount = getShelterAtObservationPoint(observationPointNumber);
        double foodCount = getFoodAtObservationPoint(observationPointNumber);
        int predatorCount = getPredatorsAtObservationPoint(observationPointNumber);
        System.out.println("Injured Koalas at Observation Point : " + injuredKoalaCount);        
        System.out.println("Healthy Koalas at Observation Point : " + healthyKoalaCount);
        System.out.println("Food at Observation Point : " + foodCount);
        System.out.println("Shelter at Observation Point : " + shelterCount);
        System.out.println("Predators at Observation Point : " + predatorCount);
    }
    
    public boolean canRelocateKoalaToObservationPoint()
    {
        return safeHavenInReserve.canRelocateKoala();
    }
    
    public int totalHealthyKoalas()
    {
        int totalHealthyKoalas = 0;
        for (int iterator = 0; iterator < 10; iterator++)
        {
            totalHealthyKoalas = totalHealthyKoalas + getHealthyKoalasAtObservationPoint(iterator);
        }
        for (int index = 0; index < safeHavenInReserve.getKoalasInSafeHaven().size(); index++)
        {
            if (!safeHavenInReserve.getKoalasInSafeHaven().get(index).getIsInjured())
            {
                totalHealthyKoalas++;
            }
        }
        return totalHealthyKoalas;
    }
    
    public int[] getFinalOutputData()
    {
        int totalHealthyKoalas = totalHealthyKoalas();
        int[] FinalOutputData = {totalNumberOfTreesLost, totalHealthyKoalas, injuredKoalasSaved, koalasRelocated, totalNumberOfDeadKoalas};
        return FinalOutputData;
    }
}
