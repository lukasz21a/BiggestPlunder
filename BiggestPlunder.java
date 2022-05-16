import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;


public class BiggestPlunder {
    private static double getOptimalPlunder(int capacity, int[] values, int[] weights) {
        double value = 0;
        double weight = 0;
        
        //create a treeMap to sort the values in descending order (from the highest).
        TreeMap<Double, Integer> treeMap = 
        		new TreeMap<Double, Integer>(Collections.reverseOrder());
        
        //calculate "val" as value per kilogram, to define which item is the 
        //most profitable to steal, as we are limited to the weight of the loot.
        for(int i = 0; i < values.length; i++) {
        	double val = (double) values[i] / weights[i];
        	treeMap.put(Double.valueOf(val), Integer.valueOf(weights[i]));
        }
        
        Iterator tmIterator = treeMap.entrySet().iterator();
        
        
        while (tmIterator.hasNext() && weight <= capacity) {
	        Map.Entry mapElement = (Map.Entry)tmIterator.next();
	        double val = ((double)mapElement.getKey());
	        int wei = ((int)mapElement.getValue());
	        
	        if(wei <= capacity - weight) {
	        	value = value + val * wei;
	        	weight = weight + wei;
	        }
	        else {
	        	value = value + val * (capacity - weight);
	        	weight = weight + wei;
	        }
        }

        return value;
    }

    public static void main(String args[]) {

    	int weightCapacity = 20;
    	int[] lootValues = {4, 8, 17, 6};
    	int[] lootWeights = {5, 11, 25, 9};
    	
        System.out.println(getOptimalPlunder(weightCapacity, lootValues, lootWeights));
    }
} 
