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
 * @version February 16, 2021
 */
public class HurricaneOrganizerArrayList
{
    private ArrayList<Hurricane> hurricanes;

    /**
     * Comment this constructor even though you did not write it.
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     * @param filename  hurricane information
     */
    public HurricaneOrganizerArrayList(String filename)throws IOException
    {
        readFile(filename);   
    }

    /**
     * Reads a file for details of all the hurricanes listed in it.
     * @param filename  hurricane information
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new ArrayList<Hurricane>();
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        while(inFile.hasNextLine())
        {
            hurYear=inFile.nextInt();
            hurMonth=inFile.next();
            hurPressure=inFile.nextInt();
            hurSpeed=inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if (('a' <= c && c <= 'z') || ('A'<=c && c <= 'Z'))
                {
                    hurName += c;
                }
            }
            Hurricane hurricane = new Hurricane(hurYear,hurMonth,hurPressure,hurSpeed,hurName);
            hurricanes.add(hurricane);
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
        Hurricane currentSpeed;
        for (int i = 0; i < hurricanes.size(); i++)
        {
            currentSpeed = hurricanes.get(i);
            maxSpeed = Math.max(maxSpeed, (currentSpeed.getSpeed()));
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
        Hurricane currentPressure;
        for (int i = 0; i < hurricanes.size(); i++)
        {
            currentPressure = hurricanes.get(i);
            maxPressure = Math.max(maxPressure, (currentPressure.getPressure()));
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
        int minSpeed = (hurricanes.get(0)).getSpeed();
        Hurricane currentSpeed;
        for (int i = 1; i < hurricanes.size(); i++)
        {
            currentSpeed = hurricanes.get(i);
            minSpeed = Math.min(minSpeed, (currentSpeed.getSpeed()));
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
        int minPressure = (hurricanes.get(0)).getPressure();
        Hurricane currentPressure;
        for (int i = 1; i < hurricanes.size(); i++)
        {
            currentPressure = hurricanes.get(i);
            minPressure = Math.min(minPressure, (currentPressure.getPressure()));
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
        Hurricane x = hurricanes.get(0);
        double sum = x.getSpeed();
        for (int i = 1; i < hurricanes.size(); i++)
        {
            Hurricane current = hurricanes.get(i);
            sum += (current).getSpeed();
        }
        return sum/(double)hurricanes.size();
    }

    /**
     * Finds the average pressure.
     * 
     * @return averagePressure the average pressure
     */
    public double calculateAveragePressure( )
    {
        Hurricane x = hurricanes.get(0);
        double sum = x.getPressure();
        for (int i = 1; i < hurricanes.size(); i++)
        {
            Hurricane current = hurricanes.get(i);
            sum += (current).getPressure();
        }
        return sum/(double)hurricanes.size();
    }

    /**
     * Finds the average category.
     * 
     * @return averageCategory the average category
     */
    public double calculateAverageCategory( )
    {
        Hurricane x = hurricanes.get(0);
        double sum = x.getCategory();
        for (int i = 1; i < hurricanes.size(); i++)
        {
            Hurricane hurrTemp = hurricanes.get(i);
            sum += (hurrTemp).getCategory();
        }
        return sum/(double)hurricanes.size();
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        int k = 0;
        int posMin = 0; //position of minimum element in array

        Hurricane temp;
        for (int i = 0; i < hurricanes.size(); i++)
        {
            posMin = i;
            for (k = i + 1; k < hurricanes.size(); k++)
            {
                if(hurricanes.get(k).compareYearTo(hurricanes.get(posMin)) < 0)
                {
                    posMin = k;  //find the position of the minimum value

                }
            }

            //swap
            temp = hurricanes.get(posMin);
            hurricanes.set(posMin, hurricanes.get(i));
            hurricanes.set(i, temp);

        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        int n = hurricanes.size();  

        for (int i = 1; i < n; i++)
        {   
            Hurricane key = hurricanes.get(i);  
            int j = i - 1;  

            while ((j > -1) && ( hurricanes.get(j).compareNameTo(key) > 0) )
            {  
                hurricanes.set(j+1, hurricanes.get(j));
                j--;  
            }  
            hurricanes.set(j+1, key);
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
        for (int i = 0; i < hurricanes.size(); i++)
        {
            posMax = i;
            for (k = i+1; k < hurricanes.size(); k++)
            {
                if(hurricanes.get(k).compareCategoryTo(hurricanes.get(posMax)) > 0)
                {
                    posMax = k;  //find the position of the maximum value

                }
            }

            //swap
            temp = hurricanes.get(posMax);
            hurricanes.set(posMax, hurricanes.get(i));
            hurricanes.set(i, temp); 

        }
        return;
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        ArrayList<Hurricane> merged = new ArrayList<Hurricane>();
        int findex = 0;
        int index2 = 0; 

        sortPressuresHelper(0, hurricanes.size()/2);
        sortPressuresHelper(hurricanes.size()/2, hurricanes.size());

        for(int i = 0; i < hurricanes.size(); i++)
        {
            merged.add(hurricanes.get(i));
        }

        index2 = merged.size()/2; 
        for (int index = 0; index < hurricanes.size(); index++)
        {
            Hurricane firstTemp = hurricanes.get(findex);
            if (findex >= hurricanes.size()/2)
            {
                Hurricane overTemp = hurricanes.get(index2);
                merged.set(index,overTemp);
                index2++;
            }
            else if (index2 >= hurricanes.size())
            {
                Hurricane secondOverTemp = hurricanes.get(findex);
                findex++;
            }
            else if (firstTemp.comparePressureTo(hurricanes.get(index2)) > 0)
            {
                merged.set(index,firstTemp);
                findex++;
            }
            else if (hurricanes.get(index2) != null)
            {
                merged.set(index,hurricanes.get(index2));
                index2++;
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
        for (int i = start; i < end; i++)
        {
            int index = i-1;
            Hurricane temp = hurricanes.get(i);
            while (index >= start && temp.comparePressureTo(hurricanes.get(index)) > 0)
            {
                Hurricane indexTemp = hurricanes.get(index);
                hurricanes.set(index+1,indexTemp);
                index--;
            }
            hurricanes.set(index+1,temp);
        }
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
            return; //base case
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
        ArrayList<Hurricane> merged = new ArrayList<Hurricane>();
        int findex = low;
        int sindex = mid;
        for(int index = low; index < high+1; index++)
        {
            merged.add(hurricanes.get(index));
        }
        for(int index = 0; index < merged.size(); index++)
        {
            if(findex >= mid)
            {
                merged.set(index,hurricanes.get(sindex));
                sindex++;
            }
            else if (sindex > high)
            {
                merged.set(index,hurricanes.get(findex));
                findex++;
            }
            else if ((hurricanes.get(findex)).compareSpeedTo(hurricanes.get(sindex)) < 0)
            {
                merged.set(index,hurricanes.get(findex));
                findex++;
            }
            else
            {
                merged.set(index,hurricanes.get(sindex));
                sindex++;
            }
        }
        for(int index = 0; index < merged.size(); index++)
        {
            hurricanes.set(low+index,merged.get(index));
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year the year of the hurricane
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public ArrayList<Hurricane> searchYear(int year)
    {
        int counter = 0;
        ArrayList<Hurricane> matches = new ArrayList<Hurricane>();

        for(int i = 0; i < hurricanes.size(); i++)
        {
            if ((hurricanes.get(i)).getYear() == year)
            {
                matches.add(hurricanes.get(i));
            }
        }

        return matches;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being search
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public ArrayList<Hurricane> searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.size() - 1);
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
    private ArrayList<Hurricane> searchHurricaneNameHelper(String name, int low , int high)
    {
        // Test for the base case when a match is not found
        if (low > high)
        {
            return null;
        }
        int mid = (low + high)/2;
        int compare = ((hurricanes.get(mid).getName()).compareTo(name));

        if (compare == 0)
        {
            return retrieveMatchedNames(name, mid);
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
    private ArrayList<Hurricane> retrieveMatchedNames (String name, int index)
    {
        int findex = index-1;
        while(findex >= 0 && ((hurricanes.get(findex)).getName()).compareTo(name) == 0)
        {   
            findex--;
        }

        findex++;
        int index2 = index+1;
        while(index2 < hurricanes.size() && 
        ((hurricanes.get(index2)).getName()).compareTo(name) == 0)
        {
            index2++;
        }

        index2--;
        ArrayList<Hurricane> matched = new ArrayList<Hurricane>();
        for(int i = findex; i <= index2; i++)
        {
            matched.add(hurricanes.get(i));
        }

        return matched;
    }

    /**
     * Prints the header in a specific format.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Prints the Hurricane objects.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints the hurricane array.
     * 
     * @param hurs the hurricane array
     */
    public void printHurricanes(ArrayList<Hurricane> hurs)
    {
        if(hurs == null || hurs.size() == 0)
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
     * Displays the menu.
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
     * Prints out the maximum and minimum wind speeds and pressures.
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
     * Prints the average wind speed, pressure, and category.
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
     * A
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
            sortWindSpeeds(0, hurricanes.size() - 1);
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
        HurricaneOrganizerArrayList cane = new HurricaneOrganizerArrayList("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
