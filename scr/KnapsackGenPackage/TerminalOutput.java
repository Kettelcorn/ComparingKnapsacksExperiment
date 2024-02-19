package KnapsackGenPackage;

import SelectingAlgoPackage.TestResult;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public class TerminalOutput {


    /**
     * The blank constructor for the TerminalOutput class that will construct
     * an object with the given inputs that then can be called to be displayed
     */
    public TerminalOutput(){

    }//

    /**
     * This class will create the welcome message for the test.
     */
    public void printIntro(){
        System.out.println("\n\n||||||||||WELCOME TO THE KNAPSACK COMPUTATION TESTER||||||||||");
        System.out.println("|This program will test the run times for how fast a ");
        System.out.println("|algorithm will find the optimal solution");
        System.out.println("The following algorithms will be tested for the 01 Knapsack: ");
        System.out.println("1. Brute Force\n2. Greedy Algorithm (even thought this could be wrong)\n3. Dynamic program\n");
        System.out.println("The following algorithms will be tested for the fractional Knapsack: ");
        System.out.println("1. Brute force\n2. Greedy Algorithm\n");
        System.out.println("Starting Test");
    }//end intro

    /**
     * O(n) time to print the items to the screen.
     * Even though we are using a nested loop, we are iterating by three
     * and only going through each tuple set once.
     * @param k is the knapsack used for testing.
     * @param perLine is how many tuple per line would you like?
     */
    public void printProblemSet(Knapsack k, int testNum, int perLine){
        int length = k.getItems().size();
        System.out.println("\n**** Problem Set #" + testNum + "  ****");
        printLine("=", 40);
        System.out.println("Maximum Capacity: " + k.getMaximumCapacity());
        System.out.println("number of items: " + length);
        System.out.println("Items tuples (v, w)");
        printLine("-", 40);
        ArrayList itemList = k.getItems();
        for(int i = 0; i < length; i += perLine){
            int pline = perLine;
            if (length - i < perLine){
                pline = length % perLine;
            }
            for(int j = i; j < i + pline; j++){
                Item item = k.getItems().get(j);
                System.out.print("(" + item.getVal() + ",");
                if(length - j == 1){
                    System.out.print(" " + item.getWt() + ") ");
                }else {
                    System.out.print(" " + item.getWt() + "), ");
                }
            }//end for
            System.out.println("");
        }//end for
        printLine("*", 40);
    }//end Problem Se1

    /**
     * O(n) for the number of items in test,
     * This will print the unique test results that will show how the
     * algorithm time and accuracy was.
     * @param t is the test result object
     */
    public void printTestResults(TestResult t) {

        //find the Capacity
        int capacityUsed = 0;
        int totalProfit = 0;
        Knapsack k = t.getKnapsack();
        ArrayList<Item> itemList = k.getItems();
        StringBuilder resultTuple = new StringBuilder();
        Iterator<Item> keyIter = t.getItemsUsed().keySet().iterator();
        while(keyIter.hasNext()){
            Item item = keyIter.next();
            int value = item.getVal();
            int weight = item.getWt();
            int numberItemsUsed = t.getItemsUsed().get(item);
            double benifit = (double) value/weight;
            double usedCapacity = benifit * numberItemsUsed;
            capacityUsed += numberItemsUsed;
            int itemNumber = 0;
            //get Item number
            int itemNumberIndex = 0;
            boolean done = false;
            while(!done){
                if(value == k.getItems().get(itemNumberIndex).getVal() && weight == k.getItems().get(itemNumberIndex).getWt()){
                    itemNumber = itemNumberIndex + 1;
                    done = true;
                }//
                itemNumberIndex++;
            }//end while
                    resultTuple.append("Item # " + itemNumber + " : (" + value + ", " + weight + ")" +
                            " \t | Used " + numberItemsUsed  + " items for " + Math.round(usedCapacity * 100.0)/100.0 + "\n");
        }//end while loop



        System.out.println("Results of " + t.getAlgorithmName());
        printLine("_", 40);
        System.out.println("Run Time: " + t.getTime());
        System.out.println("Capacity Used: " + capacityUsed + " (" + k.getMaximumCapacity() + " max Capacity)");
        System.out.println("Items used for Project (v, w)");
        System.out.println(resultTuple.toString());
        System.out.println("Total Profit: " + t.getTotalValue());
        printLine("=", 40);
    }//end printTestResults

    /**
     * O(n) for line size
     * method to print output lines
     * @param lineType the string type of line you would like
     * @param length how many spaces you want to print
     */
    public void printLine(String lineType, int length){
        for(int i = 0; i < length; i++){
            System.out.print(lineType);
        }
        System.out.print("\n");
    }

}
