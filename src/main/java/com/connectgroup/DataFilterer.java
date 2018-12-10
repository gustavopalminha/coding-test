package com.connectgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 
 * @author Gustavo Palminha
 *
 */
public class DataFilterer {
	
	/**
	 * Read all DataLog items from a source.
	 * @param source File to be loaded
	 * @return An ArrayList<DataLog> of items found
	 */
	public static Collection<DataLog> readAllItems(Reader source){
	
    	Collection<DataLog> allItems = new ArrayList<DataLog>();
    	
    	BufferedReader inBufferReader = new BufferedReader(source);
        String line;       
        try {
        	inBufferReader.readLine();
			while ((line = inBufferReader.readLine()) != null) {
				DataLog item = new DataLog(line);
				allItems.add(item);
			}
		} catch (IOException e) {
			// Print stacktrace
			e.printStackTrace();
		}
    	    	
    	return allItems;
    	
	}
	
	/**
	 * Filter ArrayList<DataLog> of DataLogs to apply the filter indicated by it's name and parameters
	 * @param source File to be loaded
	 * @param country String with 2 chars
	 * @return Collection of filtered items depending the parameters
	 */
    public static Collection<?> filterByCountry(Reader source, String country) {
    	
    	Collection<DataLog> itemsList = readAllItems(source);
    	return itemsList
    			.stream()
    			.filter(i -> i.isCountryCode(country))
    			.collect(
    					Collectors.toCollection(ArrayList::new)
    	);  	
    	
    }

    /**
     * Filter ArrayList<DataLog> of DataLogs to apply the filter indicated by it's name and parameters
     * @param source File to be loaded
     * @param country String with 2 chars
     * @param limit Long representing the ResponseTime
     * @return Collection of filtered items depending the parameters
     */
    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        
    	Collection<DataLog> itemsList = readAllItems(source);
    	
    	return itemsList
    			.stream()
    			.filter(i -> i.isCountryCode(country) && i.isResponseTimeAboveLimit(limit))
    			.collect(
    					Collectors.toCollection(ArrayList::new)
    	);
    	
    }

    /**
     * Filter ArrayList<DataLog> of DataLogs to apply the filter indicated by it's name and parameters
     * @param source File to be loaded
     * @return Collection of filtered items depending the parameters
     */
    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
    	Collection<DataLog> itemsList = readAllItems(source);
 	    	
    	double average = itemsList
    			.stream()
    			.mapToDouble(DataLog::getResponseTime)
    			.average().orElse(0);
    	
    	return itemsList.stream()
    			.filter(i -> i.getResponseTime() > average)
    			.collect(
    					Collectors.toCollection(ArrayList::new)
    			); 	
    	
    }
    
}