/**
 * The tree class stores the property of every single tree in the Koala Rescue.
 *
 * @author (Sanket Agarwal)
 * @studentID (31224482)
 * @version (12/06/2020)
 */
public class Tree
{
    private boolean isShelter;
    private double foodProduced;
    private int typeOfTree;
    private String treeName;
    
    public Tree()
    {
        foodProduced = 0.00;
        isShelter = false;
        typeOfTree = 0;
        treeName = " ";
    }
    
    public Tree (int treeType)
    {
        setFoodProduced(treeType);
        setIsShelter(treeType);
        setNameOfTree(treeType);
        typeOfTree = treeType;
    }
    
    public double getFoodProduced()
    {
        return foodProduced;
    }
    
    public boolean getIsShelter()
    {
        return isShelter;
    }
    
    public String getNameOfTree()
    {
       return treeName;
    }
    
    public int getTypeOfTree()
    {
        return typeOfTree;
    }
    
    public void setFoodProduced (int treeType)
    {
        try
        {
            double[] treeFoodList = {1.00, 0.34, 0.90, 0.40, 0};
            foodProduced = treeFoodList[treeType-1];
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            System.out.println("That's not a valid tree type entry, please enter a value between 1-5");
        }
    }
    
    public void setIsShelter (int treeType)
    {
        try
        {
            boolean[] treeTypeList = {false, false, false, false, true};
            isShelter = treeTypeList[treeType-1];
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            System.out.println("That's not a valid tree type entry, please enter a value between 1-5");
        }
    }
    
    public void setNameOfTree (int treeType)
    {
        try
        {
            String[] namesOfTrees = {"Manna Gum", "Swamp Gum", "Blue Gum", "River Red Gum", "Wattle"};
            int index = treeType - 1;
            this.treeName = namesOfTrees[index];
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            System.out.println("That's not a valid tree type entry, please enter a value between 1-5");
        }
    }
}
