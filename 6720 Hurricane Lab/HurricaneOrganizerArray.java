import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data.
 * 
 * Data came from http://www.aoml.noaa.gov/hrd/tcfaq/E23.html except for 2018.
 * 2018 data came from https://en.wikipedia.org/wiki/2018_Atlantic_hurricane_season.
 *
 * @author Susan King
 * @author Rohit Tallapragada
 *
 * @version January 17, 2019
 * @version January 5, 2020
 * @version February 10, 2020 Polished code via variable names
 */
public class HurricaneOrganizerArray
{
    private Hurricane [] hurricanes;

    /**
     * Comment this constructor even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     * @param filename  hurricane information
     */
    public HurricaneOrganizerArray(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Determines the length of a file by counting the number of lines.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     * @param filename  hurricane information
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Reads a file for details of all the hurricanes listed in it.
     * @param filename  hurricane information
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Finds the maximum windspeed.
     * 
     * @return maxSpeed the maximum windspeed
     */

    public int findMaxWindSpeed( )
    {
        int maxSpeed = 0;
        int currentSpeed = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            currentSpeed = hurricanes[i].getSpeed();
            if (currentSpeed > maxSpeed) 
            {
                maxSpeed = currentSpeed;
            }
        }
        return maxSpeed;
    }

    /**
     * Finds the maximum pressure.
     * 
     * @return maxPressure the maximum pressure
     */
    public int findMaxPressure( )
    {
        int maxPressure = 0;
        int currentPressure = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            currentPressure = hurricanes[i].getPressure();
            if (currentPressure > maxPressure) 
            {
                maxPressure = currentPressure;
            }
        }
        return maxPressure;
    }

    /**
     * Finds the minimum windspeed.
     * 
     * @return minSpeed the minimum windspeed
     */
    public int findMinWindSpeed( )
    {
        int minSpeed = Integer.MAX_VALUE;
        for(int i = 0; i < hurricanes.length; i++)
        {
            if(minSpeed > hurricanes[i].getSpeed())
            {
                minSpeed = hurricanes[i].getSpeed();
            }
        }
        return minSpeed;
    }

    /**
     * Finds the minimum pressure.
     * 
     * @return minPressure the minimum pressure
     */
    public int findMinPressure( )
    {
        int minPressure = Integer.MAX_VALUE;
        for(int i = 0; i < hurricanes.length; i++)
        {
            if(minPressure > hurricanes[i].getPressure())
            {
                minPressure = hurricanes[i].getPressure();
            }
        }
        return minPressure;
    }

    /**
     * Finds the average windspeed.
     * 
     * @return averageSpeed the average speed
     */
    public double calculateAverageWindSpeed( )
    {
        double addedSpeeds = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            addedSpeeds += (double)(hurricanes[i].getSpeed());
        }
        return addedSpeeds/hurricanes.length;
    }

    /**
     * Finds the average pressure.
     * 
     * @return averagePressure the average pressure
     */
    public double calculateAveragePressure( )
    {
        double addedPressure = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            addedPressure += (double)(hurricanes[i].getPressure());
        }
        return addedPressure/hurricanes.length;
    }

    /**
     * Finds the average category.
     * 
     * @return averageCategory the average category
     */
    public double calculateAverageCategory( )
    {
        double addedCategory = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            addedCategory += (double)(hurricanes[i].getCategory());
        }
        return addedCategory/hurricanes.length;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        int k;
        int posMin; //position of minimum element in array

        Hurricane temp;
        for (int i = 0; i < hurricanes.length; i++)
        {
            posMin = i;
            for (k = i+1; k < hurricanes.length; k++)
            {
                if(hurricanes[k].compareYearTo(hurricanes[posMin]) < 0)
                {
                    posMin = k;  //find the position of the minimum value

                }
            }

            //swap
            temp = hurricanes[posMin];
            hurricanes[posMin] = hurricanes[i]; //swap
            hurricanes[i] = temp;

        }
        return;
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        int n = hurricanes.length;  

        for (int i = 1; i < n; i++)
        {   
            Hurricane key = hurricanes[i];  
            int j = i-1;  
            while ((j > -1) && (hurricanes[j].compareNameTo(key) > 0))
            {  
                hurricanes [j+1] = hurricanes [j];  
                j--;  
            }  
            hurricanes[j+1] = key; 
        }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        int k;
        int posMax; //position of maximum element in array

        Hurricane temp;
        for (int i = 0; i < hurricanes.length; i++)
        {
            posMax = i;
            for (k = i+1; k < hurricanes.length; k++)
            {
                if(hurricanes[k].compareCategoryTo(hurricanes[posMax]) > 0)
                {
                    posMax = k;  //find the position of the maximum value

                }
            }

            //swap
            temp = hurricanes[posMax];
            hurricanes[posMax] = hurricanes[i]; //swap
            hurricanes[i] = temp;

        }
        return;
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        Hurricane[] merged = new Hurricane[hurricanes.length];
        int len = hurricanes.length;
        int mid = len/2;

        sortPressuresHelper(0, hurricanes.length/2);
        sortPressuresHelper(hurricanes.length/2, hurricanes.length);

        int findex = 0;
        int sindex = mid; 

        for (int index = 0; index < hurricanes.length; index++)
        {
            if (findex >= mid)
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
            else if (sindex >= len)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else if (hurricanes[findex].comparePressureTo(hurricanes[sindex]) > 0)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
        }
        hurricanes = merged;
    }

    /**
     * Sorts descending a portion of array based upon pressure, 
     * using selection sort.
     * 
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
        int k;
        int posMax; //position of maximum element in array

        Hurricane temp;
        for (int i = start; i < end; i++)
        {
            posMax = i;
            for (k = i+1; k < end; k++)
            {
                if(hurricanes[k].comparePressureTo(hurricanes[posMax]) > 0)
                {
                    posMax = k;  //find the position of the maximum value

                }
            }

            //swap
            temp = hurricanes[posMax];
            hurricanes[posMax] = hurricanes[i]; //swap
            hurricanes[i] = temp;

        }
        return;
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort.
     * 
     * @param   low the lowest wind speed
     * @param   high the highest windspeed
     */
    public void sortWindSpeeds(int low, int high)
    {
        if (low == high)
        {
            //base case
            return;
        }
        int mid = (low + high) / 2;
        sortWindSpeeds(low, mid);
        sortWindSpeeds(mid+1, high);
        mergeWindSpeedsSortHelper(low, mid+1, high);   
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        Hurricane [] merged = new Hurricane[high-low+1];
        int findex = low;
        int sindex = mid;
        for (int index = 0; index < merged.length; index++) 
        {
            if (findex >= mid) 
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
            else if (sindex > high) 
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else if (hurricanes[findex].compareSpeedTo(hurricanes[sindex]) < 0) 
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            else 
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
        }   
        for (int index = 0; index < merged.length; index++)
        {
            hurricanes[low+index] = merged[index];
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year the year of the hurricane
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane [] searchYear(int year)
    {
        int count = 0;

        for(int i = 0; i < hurricanes.length; i++)
        {
            if (hurricanes[i].getYear() == year)
            {
                count++;
            }
        }

        Hurricane[] match = new Hurricane[count];
        int index = 0;
        for(int i = 0; i < hurricanes.length; i++)
        {
            if (hurricanes[i].getYear() == year)
            {
                match[index] = hurricanes[i];
                index++;
            }
        }

        return match;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        if (low > high)
        {
            return null;
        }
        int mid = (low + high)/2;
        int compare = (hurricanes[mid].getName()).compareTo(name);

        if (compare == 0)
        {
            return retrieveMatchedNames(name,mid);
        }

        if (compare > 0)
        {
            return searchHurricaneNameHelper(name, low, mid - 1);
        }

        return searchHurricaneNameHelper(name, mid + 1, high);

    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        int findex = index-1;
        while(findex >= 0 && ((hurricanes[findex]).getName()).compareTo(name) == 0)
        {   
            findex--;
        }
        findex++;

        int lindex = index + 1;
        while(lindex < hurricanes.length && ((hurricanes[lindex]).getName()).compareTo(name) == 0)
        {
            lindex++;
        }

        lindex--;
        Hurricane[] matched = new Hurricane[lindex-findex+1];
        for(int i = 0; i < matched.length; i++)
        {
            matched[i] = hurricanes[i + findex];
        }

        return matched;

    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Comment this method even though you did not write it.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints the hurricanes.
     * 
     * @param hurs the hurricane array
     */
    public void printHurricanes(Hurricane [] hurs)
    {
        if(hurs == null || hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending with range \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Add comments here even though you did not write the method.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Add comments here even though you did not write the method.
     * 
     * @return done an indicator for if the process is done or not
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = in.nextInt();
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Comment the method even though you did not write it.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArray cane = new HurricaneOrganizerArray("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
