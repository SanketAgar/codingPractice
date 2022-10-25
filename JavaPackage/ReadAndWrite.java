import java.util.*;
import java.io.*;
/**
 * The read and write class reads the data in the trees.txt file, stores it as a 2D int
 * array and returns it. The class also writes the data to the updatedtrees.txt file after
 * the trees have been destroyed by the simulation.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class ReadAndWrite
{    
    public ReadAndWrite()
    {
    }
    
    /**
     * Method readFile
     * The method loops through all the lines in the txt file, and reads them, it them splits the lines  into a set of 5 integers and stores
     * them in an array. It does this 10 times, once for each line in the text. It saves all the arrays in a 2D int array.
     * @return The return value is the 2D int array which has all the numbers from the txt file saved as different elements.
     */
    public int[][] readFile()
    {
        String file = "trees.txt";
        int[] everyLine = new int[5];
        int[][] allLines = new int[10][5];
        try
        {
            FileReader openFile = new FileReader(file);
            Scanner reader = new Scanner(openFile);
            int iterator = 0; 
            try
            {
                {
                    while (reader.hasNextLine())
                    {
                        String eachLine = reader.nextLine();
                        String[] inputs = eachLine.split(",");
                        for (int i=0; i<inputs.length; i++)
                        {
                            String eachValue = inputs[i];
                            int eachInt = Integer.parseInt(eachValue);
                            allLines[iterator][i]= eachInt;
                        }
                        iterator++;
                    }
                }
            }
            catch(Exception exception)
            {
                System.out.println("UNKNOWN ERROR : " + exception);
            }
            finally
            {
                openFile.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("ERROR 1 : The file you are trying to access(" + file + ") can't be found");
        }
        catch (IOException exception)
        {
            System.out.println("ERROR 2 : The file you are trying to access(" + file + ") can't be closed");
        }
        return allLines;
    }
    
    /**
     * Method writeFile
     * The write files takes an input for a 2D array of the updated trees in the array and writes it in a txt file.
     * @param ObservationPointTrees A parameter of the trees in the observation points.
     */
    public void writeFile(int[][] ObservationPointTrees)
    {
        String file = "updatedTrees.txt";
        try
        {
            PrintWriter openFile = new PrintWriter(file);
            try
            {
                {
                    int pointCount = 0;
                    for (int[]eachPoint : ObservationPointTrees)
                    {
                        int[] currentPointTrees = new int[5];
                        for(int iterator = 0; iterator < currentPointTrees.length; iterator++)
                        {
                            currentPointTrees[iterator] = ObservationPointTrees[pointCount][iterator];                            
                        }
                        openFile.println(currentPointTrees[0] + ", " + currentPointTrees[1] + ", " + currentPointTrees[2] + ", " + currentPointTrees[3] + ", " + currentPointTrees[4]);
                        pointCount++;
                    }
                }
            }
            catch(Exception exception)
            {
                System.out.println("UNKNOWN ERROR : " + exception);
            }
            finally
            {
                openFile.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("ERROR 1 : The file you are trying to access(" + file + ") can't be found");
        }
        catch (IOException exception)
        {
            System.out.println("ERROR 2 : The file you are trying to access(" + file + ") can't be closed");
        }
    }
}