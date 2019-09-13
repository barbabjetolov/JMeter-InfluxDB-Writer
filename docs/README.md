# JMeter-InfluxDB-Writer
Plugin for JMeter that allows to write load test data on-the-fly to influxDB.


# How to use this plugin

 - For using this plugin we need to have InfluxDB and Grafana up and running. The easiest way to spin up is by using Docker.
 Refer [here]( https://www.blazemeter.com/blog/how-to-create-a-lightweight-performance-monitoring-solution-with-docker-grafana-and-influxdb/) which gives detailed description of how to spawn Grafana and InfluxDB using docker. 

    - Influx db is spawned 
    - Database with name "influxdb" is created with 
    - Grafana is spawned
 
 
 - Download the [JMeter-InfluxDB-Writer](https://github.com/sfakrudeen78/JMeter-InfluxDB-Writer/releases/download/v-1.2.2/JMeter-InfluxDB-Writer-plugin-1.2.2.jar) plugin.
 
 - Place the jar in JMETER_HOME/lib/ext directory and start the Jmeter.
 
 - Once the JMeter is restarted, Go to Test Plan > Add > Listener > Backend Listener
 ![Sample](Screenshot1.png)
 
 - In the backend listener select rocks.nt.apm.jmeter.JmeterInfluxDBBackendListenerClient
 ![Sample](Screenshot2.png)
