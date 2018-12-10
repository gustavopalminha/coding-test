package com.connectgroup;

/**
 * Represents a line inside the Log file
 * @author Gustavo Palminha
 * @version 1.0 
 * */
public class DataLog {
	private long requestTime;
	private String countryCode;
	private long responseTime;
	
	public DataLog() {};
		
	public DataLog (long request, String countryCode, long timeSpan) {
		this.requestTime = request;
		this.countryCode = countryCode;
		this.responseTime = timeSpan;		
	}

	
	public DataLog (String body) {
		String[] parts = body.split(",");
		
		this.requestTime = Long.parseLong(parts[0]);
		this.countryCode = parts[1];
		this.responseTime = Long.parseLong(parts[2]);		
	}
	
	/**
	 * Get Property requestTime
	 * @return property
	 * */
	public long getRequestTime() {
		return requestTime;
	}

	/**
	 * Set Property requestTime
	 * @return property
	 * */	
	public void setRequestTime(long request) {
		this.requestTime = request;
	}

	/**
	 * Get Property countryCode
	 * @return property
	 * */	
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Set Property countryCode
	 * @return property
	 * */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Get Property responseTime
	 * @return property
	 * */	
	public long getResponseTime() {
		return responseTime;
	}

	/**
	 * Set Property responseTime
	 * */	
	public void setResponseTime(long timeSpan) {
		this.responseTime = timeSpan;
	};
	
	/** Inquire if the DataLog is from that country code.
	 * @param countryCode A country code to check if equals to that country.
	 * @return A boolean with the result of the question. 
	 * */
	public boolean isCountryCode(String countryCode) {
		return this.countryCode.equals(countryCode);
	};
	
	/** Inquire if the DataLog has a from that country code.
	 * @param limit Is the long value to to check if the Item is above it.
	 * @return A boolean with the result of the question.  
	 * */
	public boolean isResponseTimeAboveLimit(long limit) {
		return (this.responseTime > limit);
	};	

	/**
	 * Convert this object to String
	 * @return String representation
	 * */
	public String toString() {
		return String.format(
			"[DataLog [request=t%d], [countryCode=%s], [timeSpan=%d]]",
			this.requestTime, this.countryCode, this.responseTime
		);
	};

}
