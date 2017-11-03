import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
                  

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(qe.getLocation().distanceTo(from) < distMax){
                answer.add(qe);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String phrase,
    String order) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       
        for(QuakeEntry qe: quakeData){
            if(order.equals("start") && qe.getInfo().indexOf(phrase)==0){
                answer.add(qe);
            }
            if(order.equals("end") && qe.getInfo().endsWith(phrase)){
                answer.add(qe);
            }
            if(order.equals("any") && qe.getInfo().contains(phrase)){
                answer.add(qe);
            }

        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double depthMin,
    double depthMax) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(qe.getDepth() > depthMax && qe.getDepth()< depthMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> listB = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe: listB){
            System.out.println(qe);
        }
        System.out.println("Found "+listB.size()+" quakes that match the criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> listC = filterByDistanceFrom(list, 1000*1000, city);
        
        for(QuakeEntry qe: listC){
            double distance = city.distanceTo(qe.getLocation());
            System.out.println(distance/1000 + " " + qe.getInfo());
        }
        System.out.println("Found " + listC.size() + " quakes that match the criteria");
            
    }
   public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> listD = filterByDepth(list, -5000.0, -10000.0);
        for(QuakeEntry qe: listD){
            System.out.println(qe);
        }
        System.out.println("Found "+listD.size()+" quakes that match the criteria");

    }

   public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> listD = filterByPhrase(list, "Explosion", "start");
        for(QuakeEntry qe: listD){
            System.out.println(qe);
        }
        System.out.println("Found "+listD.size()+" quakes that match the criteria");

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
