import java.util.*;
import edu.duke.*;

/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry>  la = getTheLargest(list,5);
     for(QuakeEntry qe: la){
            System.out.println(qe);
        }
            
        System.out.println("Found "+list.size()+" quakes that match the criteria");

    }
    public int indexOfLargestQuake(ArrayList<QuakeEntry> list){
        int ind=0; 
        double largeQ=0.0;
        for(int k=0; k<list.size(); k++){
            
            if(list.get(k).getMagnitude()>largeQ) {
                ind=k;
                largeQ=list.get(k).getMagnitude();
            }
        }
        return ind;
        
    }
    public ArrayList<QuakeEntry> getTheLargest(ArrayList<QuakeEntry> quakeData,int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int j=0; j<howMany; j++){
            int bigI= indexOfLargestQuake(copy);
            answer.add(copy.get(bigI));
            copy.remove(bigI);
        
        }

        return answer;
    }

}
