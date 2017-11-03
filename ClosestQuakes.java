
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        double dis=copy.get(0).getLocation().distanceTo(current);
        int ind=0;
          
        for(int i=0; i<howMany; i++){
            
        for(int k=1; k < copy.size(); k++){
            QuakeEntry entry = copy.get(k);
            
           if(entry.getLocation().distanceTo(current)<dis){
               dis=entry.getLocation().distanceTo(current);
               ind=k;
            }
               
        }
        ret.add(copy.get(ind));
        copy.remove(ind);
    }

        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
