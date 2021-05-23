import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King
 * @author Rohit Tallapragada
 *
 * @version January 17, 2019
 * @version January 5, 2020
 */
public class Hurricane
{
    //Instance variables
    private int year, pressure, speed, category;
    private String month, name;

    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane( )
    {

    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, 
    int pressure, int speed, String name)
    {
        this.year = year;
        this.pressure = pressure;
        this.speed = speed;
        this.month = month;
        this.name = name;
        this.category = determineCategory(speed);
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * Use https://en.wikipedia.org/wiki/Saffir%E2%80%93Simpson_scale.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if(knots < 64)
        {
            category = 0;
        }
        else if (knots < 83)
        {
            category = 1;
        }
        else if (knots < 96)
        {
            category = 2;
        }
        else if(knots < 113)
        {
            category = 3;
        }
        else if (knots < 137)
        {
            category = 4;
        }
        else
        {
            category = 5;
        }
        return category;
    }

    //Getters

    /**
     * Finds the name of the hurricane.
     * 
     * @return name the hurricane's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Finds the month the hurricane hits.
     * 
     * @return month the hurricane's month
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Finds the air pressure of the hurricane.
     * 
     * @return pressure the hurricane's pressure
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Finds the speed of the hurricane.
     * 
     * @return speed the hurricane's speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Finds the year the hurricane hits.
     * 
     * @return year the hurricane's year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Finds what category (1-5) the hurricane is.
     * 
     * @return category the hurricane's category
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Prints the hurricane information using the toString() method to format it properly.
     */
    public void print()
    {
        System.out.println(toString( ));
    }

    /**
     * Formats hurricane information to be on a single line.
     * 
     * @return formatted information
     */
    @Override
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
            year, month, name, category, 
            speed, pressure);
    }

    /**
     * Compares two Hurricane's years.
     * 
     * @param h the hurricane being compared
     * 
     * @return   the value 0 if the parameter hurricane's year is
     *           the same as this hurricane's year;
     *      
     *           a value less than 0 if this hurricane's year before
     *           the parameter hurricane's year;
     *      
     *           and a value greater than 0 if this hurricane's year
     *           is greater.

     */
    public int compareYearTo(Hurricane h)
    {
        return this.getYear() - h.getYear();
    }

    /**
     * Compares two Hurricane's names.
     * 
     * @param h the hurricane being compared
     * 
     * @return   the value 0 if the parameter hurricane's name is
     *           the same as this hurricane's name;
     *      
     *           a value less than 0 if this hurricane's name is
     *           lexiographically less than the hurricane parameter's name;
     *      
     *           and a value greater than 0 if this hurricane's name
     *           is greater.
     */
    public int compareNameTo(Hurricane h)
    {
        String thisNameLC = this.getName().toLowerCase();
        String hNameLC = h.getName().toLowerCase();
        return thisNameLC.compareTo(hNameLC);
    }

    /**
     * Compares two Hurricane's air pressures.
     * 
     * @param h the hurricane being compared
     * 
     * @return   the value 0 if the parameter hurricane's air pressure is
     *           the same as this hurricane's air pressure;
     *      
     *           a value less than 0 if this hurricane's air pressure
     *           is less than the hurricane parameter's air pressure;
     *      
     *           and a value greater than 0 if this hurricane's air pressure
     *           is greater.
     */
    public int comparePressureTo(Hurricane h)
    {
        return this.getPressure() - h.getPressure();
    }

    /**
     * Compares two Hurricane's speeds.
     * 
     * @param h the hurricane being compared
     * 
     * @return   the value 0 if the parameter hurricane's speed is
     *           the same as this hurricane's air speed;
     *      
     *           a value less than 0 if this hurricane's speed
     *           is less than the hurricane parameter's speed;
     *      
     *           and a value greater than 0 if this hurricane's speed
     *           is greater.
     */
    public int compareSpeedTo(Hurricane h)
    {
        return this.getSpeed() - h.getSpeed();
    }

    /**
     * Compares two Hurricane's categories.
     * 
     * @param h the hurricane being compared
     * 
     * @return   the value 0 if the parameter hurricane's category is
     *           the same as this hurricane's category;
     *      
     *           a value less than 0 if this hurricane's category
     *           is less than the hurricane parameter's category;
     *      
     *           and a value greater than 0 if this hurricane's category
     *           is greater.
     */
    public int compareCategoryTo(Hurricane h)
    {
        return this.getCategory() - h.getCategory();
    }
}
