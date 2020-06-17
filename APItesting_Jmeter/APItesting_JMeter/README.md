# README #

This is example project demonstrating creation of JMeter script via Java API followed by execution. It is a follow up to chapter 4.3 of [5 Ways To Launch a JMeter Test without Using the JMeter GUI](http://blazemeter.com/blog/5-ways-launch-jmeter-test-without-using-jmeter-gui) guide. 

This updated version covers:

* Running more than one sample
* Store generated JMeter test as .jmx file
* Store test execution results as .jtl file

### Configuration instructions ###

* Download repository from [Downloads](https://bitbucket.org/blazemeter/jmeter-from-code/downloads) page. If you prefer using Git you can execute the following command: **git clone https://blazemeter@bitbucket.org/blazemeter/jmeter-from-code.git**
* Unzip downloaded file and go into that folder
* Execute **mvn clean install** command
* Go into *target* folder
* Execute script as **java -Djmeter.home=YOUR_JMETER_LOCATION -jar jmeter-from-code-1.0-SNAPSHOT-jar-with-dependencies.jar**
* Open *example.jmx* file under your "jmeter.home" folder for generated script and *example.jtl* for test results
* Inspect **JMeterFromScratch.java** source file at [Source](https://bitbucket.org/blazemeter/jmeter-from-code/src/) page for details on how it is implemented.