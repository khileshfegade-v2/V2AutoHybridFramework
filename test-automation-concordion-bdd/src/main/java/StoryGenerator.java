
import java.io.File;
import java.io.Reader;
import java.util.Properties;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.RandomAccessFile;

/**
	Please refer to "Story Generator Concordion to TestNG.doc" document located in Concordion docs folder<p>
	on how to use this utility.<p>
	
	This class is a code generator utility for easing developers repetitive tasks.<p>
	This utility generates code for Concordion and Script project test cases.<p>
	<p>
	This utility performs following tasks <p>
	<p>
	a). Take user story written in plain English and in plain text format as input and generate following code <p>
	b). Generate Concordion HTML Spec file in proper folder which can be viewed in any browser <p>
	c). Make entry of newly generated Concordion HTML Spec in Concordion test suite html called Overview.html<p>
	d). Generate Concordion java factory class code which is used by the Concordion test class<p>
	e). Generate Concordion test class which act as bridge between Concordion and TestNG Script project test class<p>
	f). Generate v2solutions Framework Script TestNG test class which uses TestNG features of data provider, test annotation , assert statements <p>
	g). Generate v2solutions Framework Script TestNG core logic class which contains code related to Selenium for performing operation on web page and return result of it to calling TestNG test class for testing with assert. This class is independent of TestNG feature<p>
	h). Make entry of test URL used for selenium web page object in frameworks input config file<p>
	i). Make entry of TestNG Selenium test class in TestNG suite file called sb-testng.xml		<p>
	
	<p>This utility is always executed through Concordion project and so its know location. Location of TestNG Script<p>
	project is provided through config file called StoryGenerator.properties. As location of both projects can be different<p>
	on each developers machine.<p>
*/
public class StoryGenerator
{
	org.apache.log4j.Logger log =  org.apache.log4j.Logger.getLogger(this.getClass());
	
	String packageName = null;
	String storyClassName = null;
	String locationOfTestNGProject = null;
	String locationOfTextStory = null;
	String pageURL = null;
	
	Properties configProperties = new Properties();
	Object storyLines[] = null;
	Object storyCommentLines[] = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Date todaysDate = null;
	Calendar cal = Calendar.getInstance();
	
	/**
	* Main entry point in utility from where execution of this program starts.
	* JVM invokes this method.
	*/
	public static void main(String commandLineArguments[])
	{		
		StoryGenerator storyGenerator = new StoryGenerator();
		
		storyGenerator.readPropertiesFile();
		
		if(commandLineArguments != null )
			storyGenerator.parseCommandLineArguments(commandLineArguments);
		
		storyGenerator.checkInputs();

		/*
		Read plain text story file with comments
		Write Concordion BDD HTML Spec file
		Write Concordion Test factory class
		Write Concordion Test class
		Make entry of spec in main Concordion suite html Overview.html
		Write Framework Script TestNG skeleton base logic class
		Write Framework Script TestNG skeleton test  class
		Make entry in page urls file for test URL
		Make entry of test class in sb-testng.xml file 
		*/
		storyGenerator.readTextStory();
		storyGenerator.writeSpecFile();
		storyGenerator.writeBDDFactoryClassFile();
		storyGenerator.writeBDDTestClassFile();
		storyGenerator.makeEntryInOverviewHTML();		
		storyGenerator.makePageURLSEntry(false);
		
		storyGenerator.writeTestNGLogicClass();
		storyGenerator.writeTestNGTestClass();
		storyGenerator.makePageURLSEntry(true);
		storyGenerator.makeSBTestNGXMLFileEntry();
	}

	/**
	*	Method to validate all user input from command line and from config file before code generation
	*	starts to make sure that code is generated in correct folders/location. Also displays execution syntax
	*	of this utility if not given any required arguments. 
	*/
	public void checkInputs()
	{
		if(packageName == null || storyClassName == null )
		{
			log.fatal(" ");
			log.fatal("This program expects minimum two input, a story file package name and a story file name.");
			log.fatal("Example: runstorygenerator.cmd --packageName com.v2solutions.homepage.funatv2 --storyName Initializingmultipleimages.txt");
			log.fatal("or");
			log.fatal("Example: runstorygenerator.cmd --packageName com.v2solutions.homepage.funatv2 --storyName Initializingmultipleimages");
			log.fatal("or");
			log.fatal("Example: runstorygenerator.cmd --packageName com.v2solutions.homepage.funatv2 --storyName Initializingmultipleimages --pageURL /company/fun-at-v2");
			log.fatal("\n");
			System.exit(0);
		}

		if(pageURL != null && pageURL.startsWith("http://") == true )
		{
			log.fatal(" ");
			log.fatal("Only partial URLS is required.So instead of http://www.v2solutions.com/company/fun-at-v2");
			log.fatal("Only required is /company/fun-at-v2");
			log.fatal(" \n");
			System.exit(0);
		}
		if(locationOfTestNGProject == null || locationOfTestNGProject.trim().length() == 0 )
		{
			log.fatal(" ");
			log.fatal("Please make entry in StoryGenerator.properties file with location of Frame work TestNG Script project.");
			log.fatal("Example:");
			log.fatal("locationOfTestNGProject=D:\\\\v2solutions\\\\code\\\\test-automation-scripts\\\\");
			log.fatal(" \n");
			System.exit(0);
		}
		if(locationOfTestNGProject.endsWith(java.io.File.separator) == false)
			locationOfTestNGProject = locationOfTestNGProject+java.io.File.separator;
		
		if(storyClassName.indexOf(".txt") > -1 )
			storyClassName = storyClassName.replace(".txt","");
	}
	
	/**
	* This method reads the input user story, input user story is written in plain text format in plain english.
	* Location of user story and name of user story file are given as two distinct parameters as input.
	* Location of user story is give in java package naming convention where as user story file name is give as plain file name.
	* Both location and file name are used in case sensitive manner as both are used in class name generation.
	* Method stores user story lines and comment in two different array. Comments are put in header section of 
	* generated class comments.
	*/
	public boolean readTextStory()
	{
		String completeTextStoryName = null;
		String line = null;
		int lineCount = 0;
		String tempFileName = null;
		
		log.info("...Reading story file started");
		if(locationOfTextStory == null )
			locationOfTextStory = "src"+java.io.File.separator+"test"+java.io.File.separator+"stories"+java.io.File.separator;
		
		locationOfTextStory = locationOfTextStory + packageName;
		
		locationOfTextStory = locationOfTextStory.replace(".",java.io.File.separator);
		locationOfTextStory = locationOfTextStory + java.io.File.separator;
		
		tempFileName = locationOfTextStory+storyClassName;
		if(tempFileName.endsWith(".txt") == false )
			tempFileName = tempFileName + ".txt";
		
		log.info("Complete story path: "+tempFileName);
		
		try
		{
			File file = new File(tempFileName);
			
			if(file.exists() == false )
			{
				log.fatal("Story file not found "+file.getAbsolutePath() +"\n\n");
				System.exit(0);
			}
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			log.info("Story Lines: ");
			ArrayList tempStoryLines = new ArrayList();
			ArrayList tempstoryCommentLines = new ArrayList();
			
			while ((line = bufferedReader.readLine()) != null) 
			{
				lineCount++;
				log.info("Story Line: "+line);
				
				if(line != null) line = line.trim();
				
				if( line != null && line.length() > 0 && line.startsWith("#") == true)
				{
					tempstoryCommentLines.add(line);
					continue;
				}
				
				if( line != null && line.length() > 0 )
					tempStoryLines.add(line);
			}
			fileReader.close();
			
			if(lineCount > 0 )
			{
				log.info("...Reading story file end");
				storyLines = tempStoryLines.toArray();
				storyCommentLines = tempstoryCommentLines.toArray();
				return true;
			}
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred : "+exp.getMessage(),exp);
		}
		
		log.info("...Reading story file end");
		return false;
	}
	
	/**
	* This method generates a HTML file with Concordion syntax. Generated HTML file acts as
	* Concordion spec file. Method use supplied package name and user story file name for saving
	* generated html file in that particular location/folder and with given name.
	*/
	public void writeSpecFile()
	{
		try
		{
			log.info("...Writing spec/html file started");
			
			String specFileDirectoryName = "src"+java.io.File.separator+"test"+java.io.File.separator+"specs"+java.io.File.separator+packageName;
			specFileDirectoryName = specFileDirectoryName.replace(".",java.io.File.separator);
			specFileDirectoryName = specFileDirectoryName + java.io.File.separator;
			
			log.info("Spec file location and name: "+specFileDirectoryName+"BDD"+storyClassName+".html");
			File file = new File(specFileDirectoryName+java.io.File.separator+"BDD"+storyClassName+".html");
			
			//file.deleteOnExit();
			
			if (file.exists()) 
			{
				log.fatal("Spec/Concordion specification file "+specFileDirectoryName+"BDD"+storyClassName+".html already exist. Not overwriting. Quiting.");
				System.exit(0);
			}
			else
			{
				log.info("Creating file:  "+file.getAbsoluteFile());
				File tempFile = new File(specFileDirectoryName+java.io.File.separator);
				tempFile.mkdirs();
				tempFile = new File(file.getAbsoluteFile().toString());
				tempFile.createNewFile();				
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("<html xmlns:concordion=\"http://www.concordion.org/2007/concordion\" xmlns:ext=\"urn:concordion-extensions:2010\">\n");
			bw.write("\t<head lang=\"en\">\n");
			bw.write("\t\t<meta charset=\"UTF-8\"/>\n\n");

			bw.write("\t\t<script type=\"text/javascript\">\n");
			bw.write("\t\tfunction goBack() {\n");
			bw.write("\t\t\twindow.history.back();\n");
			bw.write("\t\t}\n");
			bw.write("\t\t</script>\n");
			bw.write("\t\t<style  type=\"text/css\">\n");
			bw.write("\t\th1,h2\n");
			bw.write("\t\t{\n");
			bw.write("\t\t\tbackground: linear-gradient(to right, #005CE6, #E6F0FF);\n");
			bw.write("\t\t}\n");
			bw.write("\t\th2\n");
			bw.write("\t\t{\n");
			bw.write("\t\t\tbackground: linear-gradient(to right, #E6F0FF,#005CE6);\n");
			bw.write("\t\t}\n");
			bw.write("\t\tbody\n"); 
			bw.write("\t\t{\n");
			bw.write("\t\t\tpadding: 0px;\n");
			bw.write("\t\t\tbackground:#C0C0C0;\n");
			bw.write("\t\t}\n");
			bw.write("\t\tbutton");
			bw.write("\t\t{\n");
			bw.write("\t\t\tcolor:blue;\n");
			bw.write("\t\t\tbackground:#E6F0FF;\n");
			bw.write("\t\t\tborder:none;\n");
			bw.write("\t\t}\n");
			
			bw.write("\t\tdiv\n"); 
			bw.write("\t\t{\n");
			bw.write("\t\t\tmin-height:22px;\n");
			bw.write("\t\t}\n");
					
			bw.write("\t\t</style>\n");
			bw.write("\t\t<title>BDD"+storyClassName+"</title>\n");
			
			bw.write("\t</head>\n");
			bw.write("\t<body style='margin-top:0;margin-left:15;margin-right:0;'>\n");
			bw.write("\t\t\t<center><table width=\"100%\" border=\"0\"><tr align=\"center\" valign=\"center\" style=\"background: linear-gradient(to right, #005CE6, #E6F0FF);\"><td><h1><font color=\"white\">Fox Sports Acceptance Tests</font></h1></td></tr></table></center>\n");
			bw.write("\t\t\t<br/><button onclick=\"goBack()\">Go Back</button><br/>\n");
			bw.write("\t\t\t<h2><u>    BDD "+breakOnCapLetter(storyClassName)+"</u></h2>\n");
			
			String methodName = null;
			for(int i=0;i < storyLines.length;i++)
			{
				methodName = constructMethodName((String)storyLines[i]);
				methodName = methodName.trim();
				methodName = lowerFirstLetter(methodName);
				if(methodName.length() > 255)
					methodName = methodName.substring(254);
				
				bw.write("\t\t\t<div concordion:assertTrue=\""+methodName+"()\" style=\"font-family: Calibri,Verdana,Geneva,sans-serif;font-size: 18px;font-style: normal;font-variant: normal;font-weight: 400;line-height: 20px;\">"+(String)storyLines[i]+"</div>\n");
			}
			bw.write("\t\t\t<h2><u>End</u></h2>\n");
			bw.write("\t\t\t<div><button onclick=\"goBack()\">Go Back</button></div><br />\n");
			bw.write("\t</body>\n");
			bw.write("</html>\n");
				
			bw.close();		
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred: "+exp.getMessage(),exp);
		}
		log.info("...Writing spec/html file end");
	}

	/**
	* This method generates factory class to TestNG API, its TestNG API feature where name of test class is 
	* provided by factory class. Reason of using this particular TestNG feature is to make it possible to return same
	* class instance for running all test methods belong to that class. If this is not done then for each method
	* execution a new instance of class is generated which results in opening one browser per method call even
	* if all test method belong to same class.
	*/
	public void writeBDDFactoryClassFile()
	{
		try
		{
			log.info("...Writing test class factory class started");
			
			String testNGScriptClassName = null;
			String directoryStructure = null;
			
			todaysDate = cal.getTime();       
			String testNGClassPath = "src"+ java.io.File.separator+"test"+java.io.File.separator+"java"+java.io.File.separator+packageName;
			testNGClassPath = testNGClassPath.replace(".",java.io.File.separator);
			
			if(testNGClassPath.endsWith(java.io.File.separator) == false) testNGClassPath = testNGClassPath + java.io.File.separator;
			
			directoryStructure = testNGClassPath;
			
			testNGClassPath = testNGClassPath + "BDD" + storyClassName;
			
			log.info("Location of class files: "+testNGClassPath);
			log.info("Location of factory class: "+testNGClassPath+"ClassFactory.java");
			
			File file = new File(testNGClassPath+"ClassFactory.java");
			
			String testNGClassName = packageName;
			
			if(testNGClassName.endsWith(".") == false) testNGClassName = testNGClassName+".";
			
			testNGScriptClassName = testNGClassName + "Test"+storyClassName;
			
			testNGClassName = testNGClassName + storyClassName;
			
			//file.deleteOnExit();
			
			if (file.exists())
			{
				log.fatal("Factory class at location "+testNGClassPath+" , "+testNGClassPath+"ClassFactory.java already exists. Not overwriting. Quiting.");
				System.exit(0);
			}
			else
			{
				log.info("Creating file:  "+file.getAbsoluteFile());
				File tempFile = new File(directoryStructure);
				tempFile.mkdirs();
				tempFile = new File(file.getAbsoluteFile().toString());
				tempFile.createNewFile();
			}		
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("package "+packageName+";\n\n");
			
			bw.write("/**\n");
			bw.write("*\tThis class is generated with code generating utility. \n");
			bw.write("*\tPurpose of this class when using TestNG API through Concordion is to provide single instance of test class\n");
			bw.write("*\tinstead of multiple (one instance per method test). This class is generated per TestNG class and kept\n");
			bw.write("*\tin respective folder. TestNG API's provide @Factory annotation which provides means for feeding test classes\n");
			bw.write("*\tto TestNG runner for test execution.\n");
			bw.write("*\t\n");
			bw.write("*\tCompany: V2 Solutions Pvt Ltd\n");
			bw.write("*\tAuthor: V2 Solution Automation Team\n");			
			bw.write("*\tGeneration Date: "+sdf.format(todaysDate)+"\n");			
			bw.write("*/\n\n");

			bw.write("import org.testng.annotations.*;\n\n");

			bw.write("public class BDD"+storyClassName+"ClassFactory \n");
			bw.write("{\n");
			
			//testNGClassName = testNGClassName.replace("BDD","Test");
			testNGScriptClassName = testNGScriptClassName.replace("com.","test.");
			
			bw.write("\tstatic "+testNGScriptClassName+" temp = new "+testNGScriptClassName+"();\n\n");

			bw.write("\t@Factory\n");
			bw.write("\tpublic java.lang.Object[] getObjects()\n");
			bw.write("\t{\n");		
			bw.write("\t\treturn new Object[]{temp};\n");
			bw.write("\t}\n");
			bw.write("}\n");
						
			bw.close();		
		}
		catch(Exception exp)
		{
			log.fatal("Error occurred  : "+exp.getMessage(),exp);
		}
		
		log.info("...Writing test class factory class end");
	}
	
	/**
	* This method generates Concordion test class. When Concordion start executing test spec in html format
	* (method call is embedded in html) it instantiates corresponding test class. This method generates that class.
	* Generated class is bridge code from Concordion to Script project for reusing Script TestNG test class implementation
	* and avoid duplicate or multiple implementation in different project. Generated test class calls the TestNG Script test class
	* methods using TestNG API.
	*/
	public void writeBDDTestClassFile()
	{
		try
		{
			log.info("...Writing BDD test class started");
			String directoryStructure = null;
			
			String testNGClassPath = "src"+ java.io.File.separator+"test"+java.io.File.separator+"java"+java.io.File.separator+packageName;
			testNGClassPath = testNGClassPath.replace(".",java.io.File.separator);
			
			if(testNGClassPath.endsWith(java.io.File.separator) == false) testNGClassPath = testNGClassPath + java.io.File.separator;
			
			directoryStructure = testNGClassPath;
			
			testNGClassPath = testNGClassPath + "BDD" + storyClassName;
			
			log.info("Location of class files: "+testNGClassPath);
			log.info("Location of test class: "+"BDD"+testNGClassPath+".java");
			
			File file = new File(testNGClassPath+".java");
			
			String testNGClassName = packageName;
			
			if(testNGClassName.endsWith(".") == false) testNGClassName = testNGClassName+".";
			
			testNGClassName = testNGClassName + "BDD" + storyClassName;
			
			
			//file.deleteOnExit();
			
			if (file.exists())
			{
				log.fatal("Concordion test class "+"BDD"+testNGClassPath+".java already exists. Not overwriting. Quiting");
				System.exit(0);
			}
			else
			{
				log.info("Creating file:  "+file.getAbsoluteFile());
				File tempFile = new File(directoryStructure);
				tempFile.mkdirs();
				tempFile = new File(file.getAbsoluteFile().toString());
				tempFile.createNewFile();
			}
			
				
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+packageName+";\n\n");
				
			bw.write("/**\n");
			bw.write("*\tThis class is generated with code generating utility.\n"); 
			bw.write("*\tThis class act as Concordion proxy glue code. What that means is, TestNG test methods \n");
			bw.write("*\tare called from Concordion runner class directly. In other word, Concordion implementation\n");
			bw.write("*\thas no code of itself and it calls already implemented code in TestNG class.\n");
			bw.write("*\tEach of method in following class is embedded in html file which is called by Concordion.\n");
			bw.write("*\tInternally each method executed TestNG method by calling 'callMethod ' function (from Base class).\n");
			bw.write("*\tCode of initializing TestNG and calling its test methods/function (API's) is common to\n");
			bw.write("*\tall proxy glue code and hence kept in base class called BDDTestNGBaseClass. That's why\n");
			bw.write("*\tfollowing class is extended from BDDTestNGBaseClass java class\n");
			bw.write("*\n");
			bw.write("*\tCompany: V2 Solutions Pvt Ltd\n");
			bw.write("*\tAuthor: V2 Solutions Automation Team\n");
			bw.write("*\tGeneration Date: "+sdf.format(todaysDate)+"\n");			
			bw.write("*/\n\n");

			bw.write("import com.v2solutions.taf.common.*;\n");
			bw.write("import org.concordion.integration.junit4.ConcordionRunner;\n");
			bw.write("import org.junit.runner.RunWith;\n");
			bw.write("import org.concordion.ext.runtotals.RunTotalsExtension;\n");
			bw.write("import org.concordion.api.extension.Extensions;\n");
			bw.write("import org.concordion.ext.LoggingTooltipExtension;\n");
			bw.write("import org.concordion.ext.inputstyle.InputStyleExtension;\n");
			//bw.write("import org.concordion.ext.ScreenshotExtension;\n");
			//bw.write("import org.junit.BeforeClass;\n");
			//bw.write("import org.junit.AfterClass;\n");
			bw.write("import org.concordion.ext.TimestampFormatterExtension;\n");

			bw.write("\n@RunWith(ConcordionRunner.class)\n");
			bw.write("@Extensions({RunTotalsExtension.class,LoggingTooltipExtension.class,InputStyleExtension.class,TimestampFormatterExtension.class})\n");
			bw.write("public class BDD"+storyClassName+" extends BDDTestNGBaseClass\n{\n");
			
			bw.write("\tpublic BDD"+storyClassName+"()\n");
			bw.write("\t{\n");
			bw.write("\t\tsuper(\""+testNGClassName+"ClassFactory\");\n");
			bw.write("\t}\n");
			
			String methodName = null;
			for(int i=0;i < storyLines.length;i++)
			{
				
				methodName = constructMethodName((String)storyLines[i]);
				methodName = methodName.trim();
				methodName = lowerFirstLetter(methodName);
				
				if(methodName.length() > 255)
					methodName = methodName.substring(254);
				
				bw.write("\tpublic boolean "+lowerFirstLetter(methodName)+"() throws Throwable\n");
				bw.write("\t{\n");
				bw.write("\t\treturn callMethod(\""+methodName+"\");\n");
				bw.write("\t}\n\n");
			}
			
			bw.write("}\n");
						
			bw.close();		
		}
		catch(Exception exp)
		{
			log.fatal("Error occurred  : "+exp.getMessage(),exp);
		}
		log.info("...Writing BDD test class end");
	}
	
	/**
	* This methods make entry of generated Concordion test specs HTML file in main execution suite HTML file called Overview.html
	* Suite file contains multiple specs each corresponding to one test class. 
	* Concordion executes each spec and hence test class in sequence one after another.
	* And end Concordion execution report is generated using same suite HTML and specs HTML file
	* indicating which test cases passed and which test cases failed. Concordion stores execution report in
	* target/concordion folder. This location is configured in Concordion maven pom.xml file.
	*/
	public void makeEntryInOverviewHTML()
	{
		try
		{
			log.info("...Adding BDD story to test suite Overview.html started");
			
			String htmlPage = packageName+"."+"BDD"+storyClassName;
			htmlPage = htmlPage.replace('.','/') + ".html";

			//following code add story link to main html Overview.html
			String stringToAdd = "\n\t\t\t\t<tr style=\"background: linear-gradient(to right, #EBEBEB,#494A4D);\"><td><li><a concordion:run=\"concordion\" href=\""+htmlPage+"\"><Font face=\"System\" size=\"3\" color=\"black\">BDD"+storyClassName+"</Font></a></li></td></tr>\n";
			
			//open html file in read mode
			String locationOfOverviewHTML = "src"+java.io.File.separator+"test"+java.io.File.separator+"specs"+java.io.File.separator+"Overview.html";
			
			java.io.RandomAccessFile rd = new java.io.RandomAccessFile(new File(locationOfOverviewHTML),"r");
			String fileInputLine = null;
			String wholeHTMLAsString = "";
			
			//read all line in one big string
			while ((fileInputLine = rd.readLine()) != null) 
			{				
				//concat each line to big string
				if(wholeHTMLAsString.length() == 0)
					wholeHTMLAsString =  fileInputLine;
				else
					wholeHTMLAsString = wholeHTMLAsString + "\n"+ fileInputLine;
				fileInputLine = null;
			}
			
			rd.close();
			
			//insert new string with replace
			if(wholeHTMLAsString.indexOf(stringToAdd) > 0 || wholeHTMLAsString.indexOf(storyClassName) > 0 )
				log.error("Story already added to main html page Overview.html");
			else
			{
				wholeHTMLAsString = wholeHTMLAsString.replace("<tr><td><hr></hr></td></tr>", stringToAdd+"\t\t\t\t<tr><td><hr></hr></td></tr>");
				
				File file = new File(locationOfOverviewHTML);
			 
				// if file doesnt exists, then create it
				if (!file.exists()) 
				{
					file.createNewFile();
				}
				System.out.println("................."+wholeHTMLAsString);
				//open html file again with write
				/*rd = new java.io.RandomAccessFile(new File(locationOfOverviewHTML),"rwd");
				
				//write inserted/modified html
				rd.writeUTF(wholeHTMLAsString);
				
				//close the html file
				rd.close();*/
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(wholeHTMLAsString);
				bw.close();
			}
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred : "+exp.getMessage(),exp);
		}
		
		log.info("...Adding BDD story to test suite Overview.html end");
	}

	/**
	* This method make entry of provided test URL which is in partial form in qa.pageurls.properties file.
	* qa.pageurls.properties file is present in both Concordion and TestNG Script project. 
	* Each test class corresponding or represents one web page on which test cases are executed.
	* When execution of test class starts it appends environment related URL to provided partial URL
	* and uses completed URL for opening web page in browser. Environment related URL is like
	* http://qa.v2solutions.com or http://staging.v2solutions.com or http://www.v2solutions.com
	* This give facility of executing same test cases in different environment as page release propogate
	* from one environment to other
	*/
	public void makePageURLSEntry(boolean pScriptProject)
	{
		log.info("...Making url entry in qa.pageurls.properties started");
		
		try
		{
			if(pageURL == null )
			{
				log.fatal("Page URLS entry not done as its not provided on command line.");
				return;
			}
			
			String pageURLSFileNameAndPath = null;
			
			if(pScriptProject == true)
				pageURLSFileNameAndPath = locationOfTestNGProject+"src"+ java.io.File.separator+"test"+java.io.File.separator+"resources"+java.io.File.separator+"pageurls"+java.io.File.separator+"qa.pageurls.properties";
			else
				pageURLSFileNameAndPath = "src"+ java.io.File.separator+"test"+java.io.File.separator+"resources"+java.io.File.separator+"pageurls"+java.io.File.separator+"qa.pageurls.properties";
			
			java.io.RandomAccessFile rd = new java.io.RandomAccessFile(new File(pageURLSFileNameAndPath),"r");
			String fileInputLine = null;
			boolean entryAlreadyDone = false;
			
			//read all line in one big string
			while ((fileInputLine = rd.readLine()) != null) 
			{
				fileInputLine = fileInputLine.trim();
				
				if(fileInputLine.startsWith(storyClassName+"Page=") == true )
				{
					entryAlreadyDone = true;
					log.fatal("Entry in qa.pageurls.properties already exist.It will not be modified or replaced. Please check it for correctness");
					break;
				}
			}		
			rd.close();
				
			if(entryAlreadyDone == false)
			{
				File file = new File(pageURLSFileNameAndPath);
				FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				if(pageURL != null && pageURL.length() > 0 && pageURL.startsWith("/") == true )
					bw.write("\n"+storyClassName+"Page=<baseurl>"+pageURL+"\n");
				else
					bw.write("\n"+storyClassName+"Page=<baseurl>/"+pageURL+"\n");
				
				bw.close();
			}
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred : "+exp.getMessage(),exp);
		}
		finally
		{
				log.info("...Making url entry in qa.pageurls.properties end");
		}
	}

	/**
	* This method generates the TestNG Script project related logic class skeleton.
	* TestNG Script test class is divided in two part one related to TestNG and one related to selenium.
	* TestNG related class has only assert statments in it while class related to selenium contains all 
	* selenium web API related code. TestNG related class is called test class here and selenium related
	* class is called logic class. Following method generates code skeleton of logic class.
	*/
	public void writeTestNGLogicClass()
	{
		log.info("...writing TestNG Script Logic Class started");
		
		try
		{
			String testNGScriptClassName = null;
			String directoryStructure = null;
			
			todaysDate = cal.getTime();       
			
			String testNGClassPath = locationOfTestNGProject+"src"+ java.io.File.separator+"main"+java.io.File.separator+"java"+java.io.File.separator+packageName;
			testNGClassPath = testNGClassPath.replace(".",java.io.File.separator);
			
			if(testNGClassPath.endsWith(java.io.File.separator) == false) testNGClassPath = testNGClassPath + java.io.File.separator;
			
			directoryStructure = testNGClassPath;
			
			testNGClassPath = testNGClassPath + replaceColRefrence(storyClassName);
			
			
			log.info("Location of factory class: "+testNGClassPath+".java");
			
		
			String testNGClassName = packageName;
			
			if(testNGClassName.endsWith(".") == false) testNGClassName = testNGClassName+".";
			
			testNGScriptClassName = testNGClassName + "Test"+storyClassName;
			
			log.info("TestNG script logic class name: "+testNGScriptClassName);
			
			File file = new File(testNGClassPath+".java");
			
			//file.deleteOnExit();
			
			if (!file.exists()) 
			{
				log.info("Creating file:  "+directoryStructure);
				File tempFile = new File(directoryStructure);
				tempFile.mkdirs();
				tempFile = new File(file.getAbsoluteFile().toString());
				tempFile.createNewFile();
			}
			else
			{
				log.fatal("Logic class already present not over writing it. For Col1, Col2 and Col3 layout same logic class is used.");
				return;
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("package "+packageName+";\n\n");
			bw.write("import org.apache.commons.logging.Log;\n");
			bw.write("import org.openqa.selenium.By;\n");
			bw.write("import org.openqa.selenium.Dimension;\n");
			bw.write("import org.openqa.selenium.JavascriptExecutor;");
			bw.write("import org.openqa.selenium.Point;\n");
			bw.write("import org.openqa.selenium.WebElement;\n");
			bw.write("import org.openqa.selenium.remote.server.rest.ResultType;\n");
			bw.write("import com.v2solutions.taf.common.pages.CommonPage;\n");
			bw.write("import com.v2solutions.taf.core.WebPage;\n");
			bw.write("import com.v2solutions.taf.util.BrowserInfoUtil;\n");
			bw.write("import com.v2solutions.taf.util.JSUtil;\n");
			bw.write("import com.v2solutions.taf.util.LogUtil;\n");
			bw.write("\n\n/**\n");
			bw.write("* Page Class for ?? page. It calls the Framework APIs.\n");
			bw.write("* \n");
			bw.write("* Test Class resembles the test specification. Page Class resembles the test\n");
			bw.write("* implementation\n");
			
			bw.write("* \n");
			bw.write("* @author v2solutions pvt ltd\n");
			bw.write("* Generation Date: "+sdf.format(todaysDate)+"\n");			
			bw.write("* \n");
			bw.write("*/\n\n");
			
			bw.write("public class "+replaceColRefrence(storyClassName)+" extends CommonPage \n");
			bw.write("{\n");

			bw.write("\tprivate static Log log = LogUtil.getLog("+storyClassName+".class);\n\n");

			bw.write("\tpublic "+replaceColRefrence(storyClassName)+"(String sbPageUrl, WebPage webPage) \n");
			bw.write("\t{\n");
			bw.write("\t\tsuper(sbPageUrl, webPage);\n");
			bw.write("\t\tloadPage();\n");
			bw.write("\t\twebPage.waitForLoad(webPage.getDriver());\n");
			bw.write("\t}\n");
	
			bw.write("\n\t/*\n\t* Following method(s) code is generator by code generator for helping purpose to avoid duplication of works and speed up some of repeated task.\n");
			bw.write("\t* Developer is free to modify anything related to method including return type, method parameters, number of parameters, type of parameters and so on.\n");
			bw.write("\t*/\n\n");
			
			String methodName = null;
			for(int i=0;i < storyLines.length;i++)
			{
				
				methodName = constructMethodName((String)storyLines[i]);
				methodName = methodName.trim();
				methodName = lowerFirstLetter(methodName);
				
				if(methodName.length() > 255)
					methodName = methodName.substring(254);
				
				bw.write("\n\t/*\n");
				bw.write("\t* Please write method description here.\n");
				bw.write("\t* "+storyLines[i]+".\n");
				bw.write("\t*/");
				bw.write("\n\tpublic boolean "+methodName+"()\n");
				bw.write("\t{\n");
				bw.write("\t\t/* Actual logic of method and return true or false or any other data type depending on method logic.*/\n");
				bw.write("\t\t/* Method is returning true for completion purpose only.*/\n");
				bw.write("\t\treturn true;\n");
				bw.write("\t}\n\n");
			}
			//class end
			bw.write("}\n");
			bw.close();		
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred: " +exp.getMessage(),exp);
		}
		finally
		{
			
		}
		log.info("...writing TestNG Script Logic Class end");
	}
	
	/**
	* This method generates the TestNG Script project related test class skeleton.
	* TestNG Script test class is devided in two part one related to TestNG and one related to selenium.
	* TestNG related class has only assert statments in it while class related to selenium contains all 
	* selenium web API related code. TestNG related class is called test class here and selenium related
	* class is called logic class. Following method generates code skeleton of Test class.
	*/
	public void writeTestNGTestClass()
	{
		log.info("...writing TestNG Script Logic Class started");
		
		try
		{
			String testNGScriptClassName = null;
			String directoryStructure = null;
			String tempPackageName = null;
			
			todaysDate = cal.getTime();       
			
			tempPackageName = packageName;
			tempPackageName = tempPackageName.replace("com.","test.");
			
			String testNGClassPath = locationOfTestNGProject+"src"+ java.io.File.separator+"test"+java.io.File.separator+"java"+java.io.File.separator+tempPackageName;
			testNGClassPath = testNGClassPath.replace(".",java.io.File.separator);
			
			if(testNGClassPath.endsWith(java.io.File.separator) == false) testNGClassPath = testNGClassPath + java.io.File.separator;
			
			directoryStructure = testNGClassPath;
			
			testNGClassPath = testNGClassPath + "Test" + storyClassName;
			
			
			log.info("Location of factory class: "+testNGClassPath+".java");
			
		
			String testNGClassName = packageName;
			
			if(testNGClassName.endsWith(".") == false) testNGClassName = testNGClassName+".";
			
			testNGScriptClassName = testNGClassName + "Test" + storyClassName;
			
			log.info("TestNG script test class name: "+testNGScriptClassName);
			
			File file = new File(testNGClassPath+".java");
			
			//file.deleteOnExit();
			
			if (file.exists())
			{
				log.fatal("TestNG Script test class "+testNGScriptClassName+" already present. Not overwriting it. Quiting.");
				System.exit(0);
			}
			else
			{
				log.info("Creating file:  "+directoryStructure);
				File tempFile = new File(directoryStructure);
				tempFile.mkdirs();
				tempFile = new File(file.getAbsoluteFile().toString());
				tempFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			//packageName = packageName.replace("com.","test.");
			
			bw.write("package "+tempPackageName+";\n\n");
			bw.write("import java.awt.AWTException;\n");
			bw.write("import org.apache.commons.logging.Log;\n");
			bw.write("import org.testng.Assert;\n");
			bw.write("import org.testng.SkipException;\n");
			bw.write("import org.testng.annotations.BeforeClass;\n");
			bw.write("import org.testng.annotations.Test;\n");
			bw.write("import com.v2solutions.common.BaseTest;\n");
			bw.write("import com.v2solutions.taf.listener.SuiteListener;\n");
			bw.write("import com.v2solutions.taf.core.WebPage;\n");
			bw.write("import com.v2solutions.taf.dataprovider.TafExcelDataProvider;\n");
			bw.write("import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;\n");
			bw.write("import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;\n");
			bw.write("import com.v2solutions.taf.exception.PageException;\n");
			bw.write("import com.v2solutions.taf.util.BrowserInfoUtil;\n");
			bw.write("import com.v2solutions.taf.util.LogUtil;\n");
			packageName = packageName.replace("test.","com.");
			bw.write("import "+packageName+"."+replaceColRefrence(storyClassName)+";\n\n");

			bw.write("/**\n");
			for(int i = 0; i < storyCommentLines.length;i++)
			{
				bw.write("* "+storyCommentLines[i]+"\n");
			}
			bw.write("* Please write user story name FSCOM-???? Description here.\n");
			bw.write("* \n");
			bw.write("* @author v2solutions pvt ltd\n");
			bw.write("* Generation Date: "+sdf.format(todaysDate)+"\n");			
			bw.write("* \n");			
			bw.write("*/\n\n");
			bw.write("@Test(groups= \""+storyClassName+"\")\n");
			bw.write("@IExcelDataFiles(excelDataFiles = { \"BaseLayoutDataFile=src/test/resources/testdata/watch/automation_testdata.xls\"})\n");
			
			bw.write("public class Test"+storyClassName+" extends BaseTest \n");
			bw.write("{\n");

			
			bw.write("\tprivate static Log log = LogUtil.getLog("+storyClassName+".class);\n");
			bw.write("\tprivate String	          homePageUrl = null;\n");
			bw.write("\tprivate "+replaceColRefrence(storyClassName)+" "+replaceColRefrence(lowerFirstLetter(storyClassName))+" = null;\n\n");
			
			bw.write("\t@BeforeClass(alwaysRun = true)\n");
			bw.write("\tpublic void prepareBeforeClass() throws Exception\n");
			bw.write("\t{\n");
			bw.write("\t\tif(classInitDone == false )\n");
			bw.write("\t\t{\n");
			bw.write("\t\t\tsuper.loadDriver(); \n");
			bw.write("\t\t\tloadUrl();\n");
			bw.write("\t\t\tWebPage webPage = new WebPage(testBedManager.getDriver(), testBedManager);\n");
			bw.write("\t\t\t"+replaceColRefrence(lowerFirstLetter(storyClassName))+" = new "+replaceColRefrence(storyClassName)+"(homePageUrl, webPage);\n");
			bw.write("\t\t\twebPage.waitForLoad(webPage.getDriver());\n");
			bw.write("\t\t\tclassInitDone = true;\n");
			bw.write("\t\t}\n");		
			bw.write("\t}\n");
	
			bw.write("\n\tprivate void loadUrl() \n");
			bw.write("\t{\n");
			bw.write("\t\thomePageUrl = constructURL(SuiteListener.pageURLs.getProperty(\""+storyClassName+"Page\"));\n");
			bw.write("\t}\n");

			bw.write("\n\t/*\n\t* Method code is generator by code generator for helping purpose to avoid duplication of works and speed up some of repeated task.\n");
			bw.write("\t* Two question marks ?? shows test method specific parameters which developer will write correctly.\n");
			bw.write("\t* Uncomment and fill @ITafExcelDataProviderInputs if method uses parameters from Excel sheet data provider.\n");
			bw.write("\t* Script base logic class method are generated with boolean return type, developer is free to modify and return other data type for completing test code.\n");
			bw.write("\t* Script base logic class methods are also generated with no parameters and developer is free to modify it as desired.\n");
			bw.write("\t*/\n\n");

			String methodName = null;
			for(int i=0;i < storyLines.length;i++)
			{
				
				methodName = constructMethodName((String)storyLines[i]);
				methodName = methodName.trim();
				methodName = lowerFirstLetter(methodName);
				
				if(methodName.length() > 255)
					methodName = methodName.substring(254);
				
				bw.write("\n\t/*\n");
				bw.write("\t* Please write method description here.\n");
				bw.write("\t* "+storyLines[i]+".\n");
				
				bw.write("\t*/\n");
				bw.write("\t@Test( priority = "+(i+1)+", enabled = true, description = \""+((String)storyLines[i]).replace("\"","\\\"")+"\")\n");
				bw.write("\t//@Test(dataProvider = \"tafDataProvider\", dataProviderClass = TafExcelDataProvider.class, groups = { \"??\", \"??\"}, priority = "+(i+1)+", enabled = true, description = \""+((String)storyLines[i]).replace("\"","\\\"")+"\")\n");
				bw.write("\t//@ITafExcelDataProviderInputs(excelFile = \"??\", excelsheet = \"??\", dataKey = \"??\")\n");
				bw.write("\tpublic void "+methodName+"()\n");
				bw.write("\t{\n");
				bw.write("\t\ttry\n");
				bw.write("\t\t{\n");
				bw.write("\t\t\tboolean returnValue = "+replaceColRefrence(lowerFirstLetter(storyClassName))+"."+methodName+"();\n");				
				bw.write("\t\t\tAssert.assertTrue(returnValue,\""+((String)storyLines[i]).replace("\"","\\\"")+" failed.\");\n");
				bw.write("\t\t}\n");
				bw.write("\t\tcatch (Throwable e)\n");
				bw.write("\t\t{\n");
				bw.write("\t\t\t//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. \n");				
				bw.write("\t\t\t//Example: "+replaceColRefrence(lowerFirstLetter(storyClassName))+".logAndCreateADefect(e, \"Three Coloumn SocialBox with id  \", idofSocialBox, \"Result of Social Box is present or not  \", String.valueOf(value), String.valueOf(true),defectName, \"isSocialBoxRenderedOnPage\"\n");
				bw.write("\t\t\t//Please remove this three comment lines when proper values are filled in. \n");
				bw.write("\t\t\t"+replaceColRefrence(lowerFirstLetter(storyClassName))+"."+"logAndCreateADefect(e, \" \",\" \", \" \", String.valueOf(\"\"), String.valueOf(true), \"defectName\", \""+methodName+"\");\n");
				bw.write("\t\t}\n");
				bw.write("\t}\n\n");
			}
			//class end
			bw.write("}\n");
			bw.close();		
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred: " +exp.getMessage(),exp);
		}
		
		log.info("...writing TestNG Script Logic Class end");
	}	
	
	/**
	* This method makes the entry of TestNG Script project related geberated test class
	* into TestNG suite XML file. File name is sb-testng.xml. TestNG provide the feature of
	* group the test cases (classes) in to one file. When started it executes all the test class
	* specified in suite xml file. This feature enables the facility of grouping the different class
	* for smoke testing , regression testing and so on. This method makes entry of generated test class
	* in TestNG suite xml file. Suite xml file is configurable and configured in TestNG Script project
	* pom.xml file
	*/
	public void makeSBTestNGXMLFileEntry()
	{
		log.info("...Making class entry in sb-testng.xml file started");
		try
		{
			ArrayList allLinesInSBTestNGXMLFile = new ArrayList();
			String sbtestngFileNameAndPath = locationOfTestNGProject+"src"+ java.io.File.separator+"test"+java.io.File.separator+"resources"+java.io.File.separator+"sb-testng.xml";

			
			String fileInputLine = null;
			boolean entryAlreadyDone = false;
			
			File file = new File(sbtestngFileNameAndPath);
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			packageName = packageName.replace("com.","test.");
			String tempClassNameWithPackage = packageName+".Test"+storyClassName;
				
			//read all line in one big string
			while ((fileInputLine = bufferedReader.readLine()) != null) 
			{
				//fileInputLine = fileInputLine.trim();
				
				if(fileInputLine.indexOf(tempClassNameWithPackage) > -1 )
				{
					entryAlreadyDone = true;
					log.fatal("Entry in sb-testng.xmlfile already exist.It will not be modified or replaced. Please check it for correctness");
					break;
				}
				
				allLinesInSBTestNGXMLFile.add(fileInputLine);
			}		
			bufferedReader.close();
						
			if(entryAlreadyDone == false)
			{
				Object allLinesFromSBTestNGXML[] = allLinesInSBTestNGXMLFile.toArray();
				
				file = new File(sbtestngFileNameAndPath);
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(int i=0;i < allLinesFromSBTestNGXML.length;i++)
				{
					if( ((String)allLinesFromSBTestNGXML[i]).indexOf("</suite>") > -1 ) 
						break;
					
					bw.write((String)allLinesFromSBTestNGXML[i]+"\n");
				}
				
				bw.write("\n\t<test name=\"Test"+storyClassName+"\" preserve-order=\"true\">");
				bw.write("\n\t\t<classes>");
				bw.write("\n\t\t\t<class name=\""+tempClassNameWithPackage+"\" />");
				bw.write("\n\t\t</classes>");
				bw.write("\n\t</test>\n");
				bw.write("\n</suite>");
								
				bw.close();
			}
		}
		catch(Exception exp)
		{
			log.fatal("Exception occurred : "+exp.getMessage(),exp);
		}
		finally
		{
			log.info("...Making class entry in sb-testng.xml file end");
		}
	}
	

	/**
	* Following is utility method to generate method name from test case string by
	* removing all special characters and it also makes first letter of word in upper case (capital)
	* so that generated method name is more human readable 
	*/
	public String constructMethodName(String pInputLine)
	{
		if(pInputLine == null ) return null;
		
		pInputLine = pInputLine.trim();
		
		for(int i=0;i<10;i++)
		{
			pInputLine = pInputLine.replace(".","");			
			//pInputLine = pInputLine.replaceAll("_","");			
			pInputLine = pInputLine.replace("-","");			
			pInputLine = pInputLine.replace("(","");
			pInputLine = pInputLine.replace(")","");			
			pInputLine = pInputLine.replace("[","");			
			pInputLine = pInputLine.replace("]","");			
			pInputLine = pInputLine.replace("{","");			
			pInputLine = pInputLine.replace("}","");			
			pInputLine = pInputLine.replace(",","");			
			pInputLine = pInputLine.replace("\"","");			
			pInputLine = pInputLine.replace("'","");			
			pInputLine = pInputLine.replace("@","");			
			pInputLine = pInputLine.replace("#","");			
			pInputLine = pInputLine.replace("&","");			
			pInputLine = pInputLine.replace("\\*","");			
			pInputLine = pInputLine.replace(":","");			
			pInputLine = pInputLine.replace(";","");			
			pInputLine = pInputLine.replace("?","");			
			pInputLine = pInputLine.replace("\\","");			
			pInputLine = pInputLine.replace("/","");			
			pInputLine = pInputLine.replace("%","");			
			pInputLine = pInputLine.replace("^","");			
			pInputLine = pInputLine.replace("$","");			
		}
				
		String methodNameWords[] = pInputLine.split(" ");
		
		pInputLine = "";
		
		for(int i = 0;methodNameWords != null && i < methodNameWords.length;i++ )
		{			
			methodNameWords[i] = methodNameWords[i].trim();
			if(methodNameWords[i].length() > 0 )
			{
				methodNameWords[i] = upperFirstLetter(methodNameWords[i]);
				pInputLine = pInputLine + methodNameWords[i];
			}
		}
		
		for(int i=0;i<10;i++)
		{
			pInputLine = pInputLine.replace(" ","");			
		}
				
		return pInputLine;
	}
	
	/**
	* This is utility method which makes first character of given string or word in lower case
	*
	*/
	public static String lowerFirstLetter(String s) 
	{		
		return s.substring(0, 1).toLowerCase() +s.substring(1);
	}

	/**
	* This is utility method which makes first character of given string or word in upper case
	*
	*/	
	public static String upperFirstLetter(String s) 
	{		
		return s.substring(0, 1).toUpperCase() +s.substring(1);
	}
	
	/**
	* This method reads the configuration file StoryGenerator.properties in which
	* configuration settings are configured in key=value pair. Read configuration settings
	* are then stored in global variables
	*/
	public void readPropertiesFile()
	{
		try
		{
			File propertiesFile = new File("StoryGenerator.properties");
		
			FileReader configReader = new FileReader(propertiesFile.getAbsolutePath());
		
			configProperties.load(configReader);
			
			packageName = (String)configProperties.get("packageName");
			storyClassName = (String)configProperties.get("storyName");
			locationOfTestNGProject = (String)configProperties.get("locationOfTestNGProject");
		}
		catch(Exception exp)
		{
			log.fatal("Error occurred while reading properties file : "+exp.getMessage(),exp);
		}
	}
	
	/**
	* This method parse/detect all input parameters given on command line at execution of this
	* utility. Parsed values are then stored in global variables.
	*/
	public void parseCommandLineArguments(String commandLineArguments[])
	{
		for(int i=0;i<commandLineArguments.length;i++)
		{
			if(commandLineArguments[i] != null && commandLineArguments[i].equalsIgnoreCase("--packageName") == true )
				packageName = new String(commandLineArguments[i+1]);
			
			if(commandLineArguments[i] != null && commandLineArguments[i].equalsIgnoreCase("--storyName") == true )
				storyClassName = new String(commandLineArguments[i+1]);

			if(commandLineArguments[i] != null && commandLineArguments[i].equalsIgnoreCase("--locationOfTestNGProject") == true )
				locationOfTestNGProject = new String(commandLineArguments[i+1]);

			if(commandLineArguments[i] != null && commandLineArguments[i].equalsIgnoreCase("--pageURL") == true )
				pageURL = new String(commandLineArguments[i+1]);						
		}
	}	
	
	/**
	* This is utility method which breaks a continous string at boundry of capital letter
	* 
	*/
	public String breakOnCapLetter(String pInputString)
	{
		if(pInputString == null) return null;
		java.lang.StringBuffer newString = new java.lang.StringBuffer();
		String returnString = null;
		
		for(int i=0;i<pInputString.length();i++)
		{
			if(pInputString.charAt(i) >= 'A' && pInputString.charAt(i) <= 'Z' )
			{
				newString.append(" ");
				newString.append(pInputString.charAt(i));
			}
			else
			{
				if(i == 0 )
					newString.append(Character.toUpperCase(pInputString.charAt(i)));
				else
				{					
					newString.append(pInputString.charAt(i));
				}
			}
		}
		
		returnString = newString.toString();
		
		newString.delete(0,newString.length());
		newString = null;
		return returnString;
	}	
	
	public String replaceColRefrence(String pClassOrStoryName)
	{
		if(pClassOrStoryName == null ) return null;
		
		String tempStr = new String(pClassOrStoryName);
		
		tempStr = tempStr.replace("Col1","");
		tempStr = tempStr.replace("Col2","");
		tempStr = tempStr.replace("Col3","");
		
		tempStr = tempStr.replace("COL1","");
		tempStr = tempStr.replace("COL2","");
		tempStr = tempStr.replace("COL3","");

		tempStr = tempStr.replace("col1","");
		tempStr = tempStr.replace("col2","");
		tempStr = tempStr.replace("col3","");

		tempStr = tempStr.replace("Column1","");
		tempStr = tempStr.replace("Column2","");
		tempStr = tempStr.replace("Column3","");

		tempStr = tempStr.replace("COLUMN1","");
		tempStr = tempStr.replace("COLUMN2","");
		tempStr = tempStr.replace("COLUMN3","");
		return tempStr;
	}
}
