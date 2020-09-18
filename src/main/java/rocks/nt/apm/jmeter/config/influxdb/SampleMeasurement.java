package rocks.nt.apm.jmeter.config.influxdb;

/**
 * Constants (Tag, Field, Measurement) names for the requests measurement.
 * 
 * @author Edoardo Rizzardi
 *
 */
public interface SampleMeasurement {

	/**
	 * Measurement name.
	 */
	String MEASUREMENT_NAME = "samples";
	
	// String HISTORY_MEASUREMENT_NAME = "samplesHistory";

	/**
	 * Tags.
	 * 
	 * @author Edoardo Rizzardi
	 *
	 */
	public interface Tags {
		/**
		 * Request name tag.
		 */
		String REQUEST_NAME = "requestName";

                /** 
                 * Influx DB tag for a unique identifier for each execution(aka 'run') of a load test.
                 */  
                String RUN_ID = "runId";

                /** 
                 * Test name field
                 */  
                String TEST_NAME = "testName";
                
                String RESPONSE_CODE = "responseCode";
                
                
	}

	/**
	 * Fields.
	 * 
	 * @author Edoardo Rizzardi
	 *
	 */
	public interface Fields {

		String THREAD_NAME = "testname";

        String NODE_NAME = "nodename";

        String LABEL = "label"
        
        String SERVICE = "service";

        String TEST_NAME = "testname";
		
		String RESPONSE_SIZE = "responsebytes";
		
        String RESPONSE_LATENCY = "latency";

        String RESPONSE_CODE = "responsecode";

        String RESPONSE_MESSAGE = "responseMessage";


        // influx structure

        // label           string 
        // latency         float 
        // nodename        string
        // responsebytes   float 
        // responsecode    float 
        // responsemessage string
        // service         string
        // testname        string
		
		
		
		
		
	}
}
