//change response message if assertion failure
import org.apache.jmeter.assertions.AssertionResult;


String errorMessages="ERRORS:\n"

for (AssertionResult assertionResult : sampleResult.getAssertionResults()) {
    if (assertionResult.isFailure()) {
    	   errorMessages=errorMessages + assertionResult.getFailureMessage()+"\n";
        log.error("ERROR "+assertionResult.getFailureMessage())     
    }
}

if (!errorMessages.equals("ERRORS:\n")){
sampleResult.setResponseMessage(errorMessages);	
}



//qui inzia il listner per influxdb
// Post the result to influxDB
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;



void PostMeasurement(String metric) {

   String DEFAULT_USER="jmeter"
   String DEFAULT_PASS="jmeter"
   String DEFAULT_DB="jmeter"
   String DEFAULT_INFLUXDB=vars.get("INFLUXDB")
   String URL_SECURED_BY_BASIC_AUTHENTICATION = "http://"+DEFAULT_INFLUXDB+":8086/write";


    def httpclient = new DefaultHttpClient(new BasicHttpParams());
    //def httpPost = new HttpPost();
    def httpPost = new HttpPost(URL_SECURED_BY_BASIC_AUTHENTICATION);
    String auth = DEFAULT_USER + ":" + DEFAULT_PASS;
    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
    String authHeader = "Basic " + new String(encodedAuth);
    httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

    //URL format : http://[ipaddress]:[port]/write?db=[database]

    httpPost.setURI(new URI("http://"+DEFAULT_INFLUXDB+":8086/write?db="+DEFAULT_DB+"&rp=one_month"));
    //log.info("Result : " + metric);
    httpPost.setEntity(new StringEntity(metric));
    HttpResponse response = httpclient.execute(httpPost);
    EntityUtils.consumeQuietly(response.getEntity());

}

String escapeValue(String val) {

    val = val.replaceAll(",", "\\\\,")
        .replaceAll("=", "\\\\=")
        .replaceAll("\"", "\\\\\"")        
        .trim();

        // .replaceAll(" ", "\\\\ ")

    return val;

}


String Label = escapeValue(sampleResult.getSampleLabel())

if (Label.matches("T[0-9]+_(.*)")){

String ResponseCode = escapeValue(sampleResult.getResponseCode())
String ResponseMessage = escapeValue(sampleResult.getResponseMessage())
String Latency = Long.toString(sampleResult.getLatency())
String ResponseBytes = Long.toString(sampleResult.getBytesAsLong())
String TimeStampNano = Long.toString(sampleResult.currentTimeInMillis()) + "000000"
//String ResponseBytes = Long.toString(1000)                                   
String Service=escapeValue(vars.get("SERVICE_NAME"))

//log.error("ResponseBytes "+ResponseBytes);

String Status = "ko";
if (sampleResult.isSuccessful())
    Status = "ok";


//samples,label=Label,status=Status service=Service,testname=Modulo,responsecode=ResponseCode,responsemessage=ResponseMessage
result = new StringBuilder();

result.append("samples")
      .append(",status=")
      .append(Status)
      .append(" ")
      .append("service=\"")
      .append(Service)
      .append("\"")
      .append(",testname=\"")
      .append("${__threadGroupName}")
      .append("\"")
      .append(",nodename=\"")
      .append("${__machineName()}")
      .append("\"")
      .append(",label=\"")
      .append(Label)
      .append("\"")
      .append(",responsecode=")
      .append(ResponseCode)
      .append(",responsemessage=\"")
      .append(ResponseMessage)
      .append("\"")
      .append(",latency=")
      .append(Latency)
      .append(",responsebytes=")
      .append(ResponseBytes)
      .append(" ")
      .append(TimeStampNano)
    
PostMeasurement(result.toString())  

//vars.put("TTT",result.toString())
}




