package com.apitesting.demo;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.RandomVariableConfig;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.CookieManager;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.gui.CookiePanel;
import org.apache.jmeter.protocol.http.gui.HeaderPanel;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.sampler.TestAction;
import org.apache.jmeter.sampler.gui.TestActionGui;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testbeans.gui.TestBeanGUI;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class JMeterFromScratch {

    public static void main(String[] argv) throws Exception {

        File jmeterHome = new File(System.getProperty("jmeter.home", "../jmeter"));
        System.out.println(jmeterHome.getPath() + " this is the path");
        String slash = System.getProperty("file.separator");

        if (jmeterHome.exists()) {
            File jmeterProperties = new File(jmeterHome.getPath() + slash + "bin" + slash + "jmeter.properties");
            if (jmeterProperties.exists()) {
                //JMeter Engine
                StandardJMeterEngine jmeter = new StandardJMeterEngine();

                //JMeter initialization (properties, log levels, locale, etc)
                JMeterUtils.setJMeterHome(jmeterHome.getPath());
                JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
                JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
                JMeterUtils.initLocale();

                // Set directory for HTML report
                String repDir =  "HTMLReport";
                JMeterUtils.setProperty("jmeter.reportgenerator.exporter.html.property.output_dir",repDir);


                // JMeter Test Plan, basically JOrphan HashTree
                ListedHashTree testPlanTree = new ListedHashTree();


                // Test Plan
                TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");
                testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
                testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
                testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

                // Cookie Manager
                CookieManager cookieManager = new CookieManager();
                cookieManager.setName("Cookie Manager");
                cookieManager.setProperty(TestElement.TEST_CLASS, CookieManager.class.getName());
                cookieManager.setProperty(TestElement.GUI_CLASS, CookiePanel.class.getName());

                // Random Variable config element
                RandomVariableConfig randomVariableConfig = new RandomVariableConfig();
                randomVariableConfig.setProperty("variableName","PauseTime");
                randomVariableConfig.setProperty("minimumValue","2000");
                randomVariableConfig.setProperty("maximumValue","5000");
                randomVariableConfig.setProperty("perThread",true);
                randomVariableConfig.setName("Random Time");
                randomVariableConfig.setProperty(TestElement.TEST_CLASS, RandomVariableConfig.class.getName());
                randomVariableConfig.setProperty(TestElement.GUI_CLASS, TestBeanGUI.class.getName());


                // Test Action - Pause
                TestAction pauseAction = new TestAction();
                pauseAction.setDuration("${PauseTime}");
                pauseAction.setAction(1);
                pauseAction.setTarget(0);
                pauseAction.setName("Pause");
                pauseAction.setProperty(TestElement.TEST_CLASS, TestAction.class.getName());
                pauseAction.setProperty(TestElement.GUI_CLASS, TestActionGui.class.getName());


                //Header Manager

                HeaderManager mngr1= new HeaderManager();
                org.apache.jmeter.protocol.http.control.Header h2= new org.apache.jmeter.protocol.http.control.Header("Accept", "application/json");
                org.apache.jmeter.protocol.http.control.Header h1 = new org.apache.jmeter.protocol.http.control.Header("Content-Type", "application/json");
                mngr1.add(h1);
                mngr1.add(h2);
                mngr1.setName("Header manager 1");
                mngr1.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
                mngr1.setProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName());


                HeaderManager mngr2= new HeaderManager();
                org.apache.jmeter.protocol.http.control.Header h3 = new org.apache.jmeter.protocol.http.control.Header("Accept", "text/plain");
                mngr2.add(h3);
                mngr2.add(h1);
                mngr2.setName("Header Manager 2");
                mngr2.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
                mngr2.setProperty(TestElement.GUI_CLASS, HeaderPanel.class.getName());

                Inputs base= new Inputs();
                GetInput allget = new GetInput();
                PostInput allpost = new PostInput();
                PutInput allput =new PutInput();
                DelInput alldel = new DelInput();

                //Welcome Message
                System.out.println("\nWelcome to API Performance Testing Tool  :)) ");

                //Taking User Input
                System.out.println("\nChoose one Input Method :");
                System.out.println("Press 1 for Input through File");
                System.out.println("Press 2 for Input through Console");
                Scanner sc= new Scanner(System.in);
                int n=sc.nextInt();


                int total;
                switch (n)
                {
                case 1:
                	FileIn fl= new FileIn();
                    Allsource req=new Allsource();
                    req = fl.takein();

                    total = req.getGetcount() + req.getPostcount() + req.getPutcount() + req.getDelcount() ;
                    if(total ==0)
                    {
                    	System.out.println("\nNo HTTP Requests.");
                    	System.out.println("Exiting Application.....\n");
                    	System.exit(0);
                    }

                 // Loop Controller
                    LoopController loopController = new LoopController();
                    loopController.setLoops(req.getLoop());
                    loopController.setFirst(true);
                    loopController.setName("Loop controller");
                    loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
                    loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
                    loopController.initialize();


                    // Thread Group
                    ThreadGroup threadGroup = new ThreadGroup();
                    threadGroup.setName("Example Thread Group");
                    threadGroup.setNumThreads(req.getThread());
                    threadGroup.setRampUp(req.getRamp());
                    threadGroup.setSamplerController(loopController);
                    threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
                    threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());


                 // Construct Test Plan from previously initialized elements

                    // 1st method to initialize subtrees
                    testPlanTree.add(testPlan).add(cookieManager);

                    // 2nd method to initialize subtrees
                    testPlanTree.add(testPlan);
                    testPlanTree.add(testPlan, randomVariableConfig);
                    //HashTree del= testPlanTree.add(Sampler7,mngr1);

                    HashTree threadgrp =  testPlanTree.add(testPlan,threadGroup);


                    //Creating All GET HTTP Requests
                    ArrayList<HTTPSamplerProxy> getreq = new ArrayList<HTTPSamplerProxy>();

                    ArrayList<String> getpath = req.getGetpaths();
                    for(int i=0;i<req.getGetcount();i++)
                    {
                    	HTTPSamplerProxy getsampler = new HTTPSamplerProxy();
                        getsampler.setMethod("GET");
                        getsampler.setDomain(req.getDomain());
                        getsampler.setPort(req.getPort());
                        getsampler.setImplementation("Java");
                        getsampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        getsampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	getsampler.setName("Get Request "+ (i+1));
                    	getsampler.setPath(getpath.get(i));
                    	getreq.add(getsampler);
                    }
                    threadgrp.add(getreq);



                    //Creating All Put HTTP requests
                    ArrayList<HTTPSamplerProxy> putreq = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> putpath =req.getPutpaths();
                  //  ArrayList<String> putbody =allput.getPutbody();
                    for(int i=0;i<req.getPutcount();i++)
                    {
                    	HTTPSamplerProxy putsampler =new HTTPSamplerProxy();
                        putsampler.setMethod("PUT");
                        putsampler.setDomain(req.getDomain());
                        putsampler.setPort(req.getPort());
                        putsampler.setImplementation("Java");
                        putsampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        putsampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	putsampler.setName("Put Request " + (i+1));
                    	putsampler.setPath(putpath.get(i));
                    	putreq.add(putsampler);
                    }
                    for(int i=0;i<req.getPutcount();i++)
                    {
                    	HashTree putsamp =threadgrp.add(putreq.get(i),mngr2);
                    }

                    //Creating all Post Requests
                    ArrayList<HTTPSamplerProxy> postreq = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> postpath =req.getPostpaths();
                    ArrayList<String> postbody =req.getPostbody();
                    for(int i=0;i<req.getPostcount();i++)
                    {
                    	HTTPSamplerProxy postsampler = new HTTPSamplerProxy();
                        postsampler.setMethod("POST");
                        postsampler.setDomain(req.getDomain());
                        postsampler.setPort(req.getPort());
                        postsampler.setImplementation("Java");
                        postsampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        postsampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	postsampler.setName("Post Request "+(i+1));
                    	postsampler.setPath(postpath.get(i));
                    	postsampler.setPostBodyRaw(true);
                        postsampler.addNonEncodedArgument("", postbody.get(i),"=");
                        postreq.add(postsampler);
                    }
                    for(int i=0;i<req.getPostcount();i++)
                    {
                    	HashTree postsamp =threadgrp.add(postreq.get(i),mngr1);
                    }


                  //Creating All DELETE HTTP requests
                    ArrayList<HTTPSamplerProxy> delreq = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> delpath =req.getDelpaths();
                    //ArrayList<String> delbody =req.getDelbody();
                    for(int i=0;i<req.getDelcount();i++)
                    {
                    	HTTPSamplerProxy delsampler =new HTTPSamplerProxy();
                        delsampler.setMethod("DELETE");
                        delsampler.setDomain(req.getDomain());
                        delsampler.setPort(req.getPort());
                        delsampler.setImplementation("Java");
                        delsampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        delsampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	delsampler.setName("DeLETE Request " + (i+1));
                    	delsampler.setPath(delpath.get(i));
                    	delreq.add(delsampler);
                    }
                    for(int i=0;i<req.getDelcount();i++)
                    {
                    	HashTree delsamp =threadgrp.add(delreq.get(i),mngr1);
                    }

                    break;


                case 2:
                	Read scan = new Read();
                    base = scan.genread();
                    allget = scan.getread();
                    allpost = scan.postread();
                    allput = scan.putread();
                    alldel = scan.delread();

                    total =allget.getGetcount() + allpost.getPostcount() +allput.getPutcount() +alldel.getDelcount() ;

                    if(total ==0)
                    {
                    	System.out.println("\nNo HTTP Requests.");
                    	System.out.println("Exiting Application.....\n");
                    	System.exit(0);
                    }

                 // Loop Controller
                    LoopController loopController1 = new LoopController();
                    loopController1.setLoops(base.getLoop());
                    loopController1.setFirst(true);
                    loopController1.setName("Loop controller");
                    loopController1.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
                    loopController1.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
                    loopController1.initialize();


                    // Thread Group
                    ThreadGroup threadGroup1 = new ThreadGroup();
                    threadGroup1.setName("Example Thread Group");
                    threadGroup1.setNumThreads(base.getThread());
                    threadGroup1.setRampUp(base.getRamp());
                    threadGroup1.setSamplerController(loopController1);
                    threadGroup1.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
                    threadGroup1.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());


                 // Construct Test Plan from previously initialized elements

                    // 1st method to initialize subtrees
                    testPlanTree.add(testPlan).add(cookieManager);

                    // 2nd method to initialize subtrees
                    testPlanTree.add(testPlan);
                    testPlanTree.add(testPlan, randomVariableConfig);
                    //HashTree del= testPlanTree.add(Sampler7,mngr1);

                    HashTree threadgrp1 =  testPlanTree.add(testPlan,threadGroup1);


                    //Creating All GET HTTP Requests
                    ArrayList<HTTPSamplerProxy> getreq1 = new ArrayList<HTTPSamplerProxy>();

                    ArrayList<String> getpath1 = allget.getGetpaths();
                    for(int i=0;i<allget.getGetcount();i++)
                    {
                    	HTTPSamplerProxy getsampler1 = new HTTPSamplerProxy();
                        getsampler1.setMethod("GET");
                        getsampler1.setDomain(base.getDomain());
                        getsampler1.setPort(base.getPort());
                        getsampler1.setImplementation("Java");
                        getsampler1.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        getsampler1.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	getsampler1.setName("Get Request "+ (i+1));
                    	getsampler1.setPath(getpath1.get(i));
                    	getreq1.add(getsampler1);
                    }
                    threadgrp1.add(getreq1);



                    //Creating All Put HTTP requests
                    ArrayList<HTTPSamplerProxy> putreq1 = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> putpath1 =allput.getPutpaths();
                  //  ArrayList<String> putbody1 =allput.getPutbody();
                    for(int i=0;i<allput.getPutcount();i++)
                    {
                    	HTTPSamplerProxy putsampler1 =new HTTPSamplerProxy();
                        putsampler1.setMethod("PUT");
                        putsampler1.setDomain(base.getDomain());
                        putsampler1.setPort(base.getPort());
                        putsampler1.setImplementation("Java");
                        putsampler1.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        putsampler1.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	putsampler1.setName("Put Request " + (i+1));
                    	putsampler1.setPath(putpath1.get(i));
                    	putreq1.add(putsampler1);
                    }
                    for(int i=0;i<allput.getPutcount();i++)
                    {
                    	HashTree putsamp =threadgrp1.add(putreq1.get(i),mngr2);
                    }



                    //Creating all Post Requests
                    ArrayList<HTTPSamplerProxy> postreq1 = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> postpath1 =allpost.getPostpaths();
                    ArrayList<String> postbody1 =allpost.getPostbody();
                    for(int i=0;i<allpost.getPostcount();i++)
                    {
                    	HTTPSamplerProxy postsampler1 = new HTTPSamplerProxy();
                        postsampler1.setMethod("POST");
                        postsampler1.setDomain(base.getDomain());
                        postsampler1.setPort(base.getPort());
                        postsampler1.setImplementation("Java");
                        postsampler1.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        postsampler1.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	postsampler1.setName("Post Request "+(i+1));
                    	postsampler1.setPath(postpath1.get(i));
                    	postsampler1.setPostBodyRaw(true);
                        postsampler1.addNonEncodedArgument("", postbody1.get(i),"=");
                        postreq1.add(postsampler1);
                    }
                    for(int i=0;i<allpost.getPostcount();i++)
                    {
                    	HashTree postsamp =threadgrp1.add(postreq1.get(i),mngr1);
                    }


                  //Creating All DELETE HTTP requests
                    ArrayList<HTTPSamplerProxy> delreq1 = new ArrayList<HTTPSamplerProxy>();
                    ArrayList<String> delpath1 =alldel.getDelpaths();
                    //ArrayList<String> delbody1 =alldel.getDelbody();
                    for(int i=0;i<alldel.getDelcount();i++)
                    {
                    	HTTPSamplerProxy delsampler1 =new HTTPSamplerProxy();
                        delsampler1.setMethod("DELETE");
                        delsampler1.setDomain(base.getDomain());
                        delsampler1.setPort(base.getPort());
                        delsampler1.setImplementation("Java");
                        delsampler1.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                        delsampler1.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
                    	delsampler1.setName("DeLETE Request " + (i+1));
                    	delsampler1.setPath(delpath1.get(i));
                    	delreq1.add(delsampler1);
                    }
                    for(int i=0;i<alldel.getDelcount();i++)
                    {
                    	HashTree delsamp =threadgrp1.add(delreq1.get(i),mngr1);
                    }
                    break;
                }
                // save generated test plan to JMeter's .jmx file format
                SaveService.saveTree(testPlanTree, new FileOutputStream("example.jmx"));

                //add Summarizer output to get test progress in stdout like:
                // summary =      2 in   1.3s =    1.5/s Avg:   631 Min:   290 Max:   973 Err:     0 (0.00%)
                Summariser summer = null;
                String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
                if (summariserName.length() > 0) {
                    summer = new Summariser(summariserName);
                }


                String logFile = "example.jtl";
                // Store execution results into a .jtl file
                File log_File = new File("example.jtl");
                //delete log file if exists
                if (log_File.exists()){
                    boolean delete = log_File.delete();
                    System.out.println("Jtl deleted: "+delete);
                }
                ResultCollector logger = new ResultCollector(summer);
                ReportGenerator reportGenerator = new ReportGenerator( "example.jtl", logger); //creating ReportGenerator for creating HTML report
                logger.setFilename(logFile);
                testPlanTree.add(testPlanTree.getArray()[0], logger);

                // Run Test Plan
                jmeter.configure(testPlanTree);
                jmeter.run();

                // Report Generator
	                FileUtils.deleteDirectory(new File(repDir));//delete old report
	                reportGenerator.generate();

                System.out.println("Test completed. See target/example.jtl file for results");
                System.out.println("JMeter .jmx script is available at target/example.jmx");

               // String repDir =  "target\\HTMLReport";
                File htmlFile = new File(repDir + "/index.html");
                Desktop.getDesktop().browse(htmlFile.toURI());

             // moving result obtained in the logs directory
                Timestamp stamp = new Timestamp(System.currentTimeMillis());
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                 String recent  = sdf.format(stamp);
                File report = new File("report-output/statistics.json");
                if(!report.exists())
                	{
                	System.out.println("json result file not created");
                	}
                else {
                	System.out.println("json result file created");
                }

                File file = new File("logs");
                if (!file.exists()) {
                    	file.mkdir();
                    }
                File newFile = new File( file.getPath()+"/"+recent+".json");

                Path result = null;
                try {
                   result =  Files.move(Paths.get(report.getPath()), Paths.get(newFile.getPath()));
                } catch (IOException e) {
                   System.out.println("Exception while moving file: " + e.getMessage());
                }
                if(result != null) {
                   System.out.println("File moved successfully to logs.");
                }else{
                   System.out.println("File movement to logs failed .");
                }

                System.exit(0);

            }
        }

        System.err.println("jmeter.home property is not set or pointing to incorrect location");
        System.exit(1);


    }
}
