import java.lang.reflect.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class BDDGenerator
{
	Class testngClass = null;
	String testNGClassName = null;
	String originalTestNGClassName = null;
	String onlyClassName = null;
	String onlyPackageName = null;
	String methodName = null;
	String htmlPage = null;
	String testClassDirectoryName = null;
	String specFileDirectoryName = null;

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Date todaysDate = null;
	Calendar cal = Calendar.getInstance();
			
	public static String lowerFirstLetter(String s) 
	{
		return s.substring(0, 1).toLowerCase() +s.substring(1);
	}
	
	public void writeBDDFactoryClassFile()
	{
		try
		{
			File file = new File(testClassDirectoryName+"\\"+onlyClassName+"ClassFactory.java");
			
			//file.deleteOnExit();
			
			if (!file.exists()) 
			{
				file.createNewFile();
			}		
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("package "+onlyPackageName+";\n\n");
			
			bw.write("/**\n");
			bw.write("*\tThis class is generated with code generating utility. \n");
			bw.write("*\tPurpose of this class when using TestNG API through Concordion is to provide single instance of test class\n");
			bw.write("*\tinstead of multiple (one instance per method test). This class is generated per TestNG class and kept\n");
			bw.write("*\tin respective folder. TestNG API's provide @Factory annotation which provides means for feeding test classes\n");
			bw.write("*\tto TestNG runner for test execution.\n");
			bw.write("*\t\n");
			bw.write("*\tCompany: v2solutions\n");
			bw.write("*\tAuthor: v2Solutions\n");
			bw.write("*\tGeneration Date: "+sdf.format(todaysDate)+"\n");			
			bw.write("*/\n\n");

			bw.write("import org.testng.annotations.*;\n\n");

			bw.write("public class "+onlyClassName+"ClassFactory \n");
			bw.write("{\n");
			System.out.println("Input class name: "+originalTestNGClassName);
			bw.write("\tstatic "+originalTestNGClassName+" temp = new "+originalTestNGClassName+"();\n\n");

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
			exp.printStackTrace();
		}
	}
	public void writeBDDTestFile()
	{
		try
		{
			File file = new File(testClassDirectoryName+"\\BDD"+onlyClassName+".java");
			
			//file.deleteOnExit();
			
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			
				
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("package "+onlyPackageName+";\n\n");
				
			bw.write("/**\n");
			bw.write("*\tThis class is generated with code generating utility.\n"); 
			bw.write("*\tThis is act as Concordion proxy glue code. What that means is, TestNG test methods \n");
			bw.write("*\tare called from Concordion runner class directly. In other word, Concordion implementation\n");
			bw.write("*\thas no code of itself and it calls already implemented code in TestNG class.\n");
			bw.write("*\tEach of method in following class is embedded in html file which is called by Concordion.\n");
			bw.write("*\tInternally each method executed TestNG method by calling 'callMethod ' function (from Base class).\n");
			bw.write("*\tCode of initializing TestNG and calling its test methods/function (API's) is common to\n");
			bw.write("*\tall proxy glue code and hence kept in base class called BDDTestNGBaseClass. That's why\n");
			bw.write("*\tfollowing class is extended from BDDTestNGBaseClass java class\n");
			bw.write("*\n");
			bw.write("*\tCompany: v2solutions\n");
			bw.write("*\tAuthor: v2solutions\n");
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
			bw.write("public class BDD"+onlyClassName+" extends BDDTestNGBaseClass\n{\n");
			
			bw.write("\tpublic BDD"+onlyClassName+"()\n");
			bw.write("\t{\n");
			bw.write("\t\tsuper(\""+onlyPackageName+"."+onlyClassName+"ClassFactory\");\n");
			bw.write("\t}\n");
			
			Method[] classMethods = testngClass.getDeclaredMethods();
			
			for(int i=0;i < classMethods.length;i++)
			{
				methodName = classMethods[i].getName();
								
				if(methodName != null && (methodName.equalsIgnoreCase("loadUrl") == true || methodName.equalsIgnoreCase("prepareBeforeClass") == true || methodName.equalsIgnoreCase("close") == true) ) continue;
				
				bw.write("\tpublic boolean "+BDDGenerator.lowerFirstLetter(methodName)+"() throws Throwable\n");
				bw.write("\t{\n");
				bw.write("\t\treturn callMethod(\""+methodName+"\");\n");
				bw.write("\t}\n");
			}
			bw.write("}\n");
						
			bw.close();		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public void writeBDDSpecs()
	{
		try
		{
			File file = new File(specFileDirectoryName+"\\BDD"+onlyClassName+".html");
			
			//file.deleteOnExit();
			
			if (!file.exists()) 
			{
				file.createNewFile();
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
			bw.write("\t\t<title>BDD"+onlyClassName+"</title>\n");
			
			bw.write("\t</head>\n");
			bw.write("\t<body style='margin-top:0;margin-left:15;margin-right:0;'>\n");
			bw.write("\t\t\t<center><table width=\"100%\" border=\"0\"><tr align=\"center\" valign=\"center\" style=\"background: linear-gradient(to right, #005CE6, #E6F0FF);\"><td><h1><font color=\"white\">Acceptance Tests</font></h1></td></tr></table></center>\n");
			bw.write("\t\t\t<br/><button onclick=\"goBack()\">Go Back</button><br/>\n");
			bw.write("\t\t\t<h2><u>    BDD "+breakOnCapLetter(onlyClassName)+"</u></h2>\n");
			
			
			Method[] classMethods = testngClass.getDeclaredMethods();
			
			for(int i=0;i < classMethods.length;i++)
			{
				methodName = classMethods[i].getName();				

				if(methodName != null && (methodName.equalsIgnoreCase("loadUrl") == true || methodName.equalsIgnoreCase("prepareBeforeClass") == true || methodName.equalsIgnoreCase("close") == true) ) continue;

				bw.write("\t\t\t<div concordion:assertTrue=\""+BDDGenerator.lowerFirstLetter(methodName)+"()\" style=\"font-family: Calibri,Verdana,Geneva,sans-serif;font-size: 18px;font-style: normal;font-variant: normal;font-weight: 400;line-height: 20px;\">"+breakOnCapLetter(methodName)+"</div>\n");
			}
			bw.write("\t\t\t<h2><u>End</u></h2>\n");
			bw.write("\t\t\t<div><button onclick=\"goBack()\">Go Back</button></div><br />\n");
			bw.write("\t</body>\n");
			bw.write("</html>\n");
				
			bw.close();		
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public BDDGenerator(String pClassName)
	{				
		try
		{
			
			todaysDate = cal.getTime();       
			testNGClassName = pClassName;
			originalTestNGClassName = testNGClassName;
			testngClass = Class.forName(testNGClassName);
			
			System.out.println("Input class name: "+originalTestNGClassName);
			testNGClassName = testNGClassName.replace("test.","com.");
			
			onlyClassName = testNGClassName.substring(testNGClassName.lastIndexOf(".")+1);
			onlyPackageName = testNGClassName.substring(0,testNGClassName.lastIndexOf("."));
			
			testClassDirectoryName = onlyPackageName;
			specFileDirectoryName = onlyPackageName;
			
			testClassDirectoryName = "src\\test\\java\\" + testClassDirectoryName.replace(".","\\");
			specFileDirectoryName = "src\\test\\specs\\"+specFileDirectoryName.replace(".","\\");
			
			java.io.File testClassDirectoryFile = new java.io.File(testClassDirectoryName);
			java.io.File specDirectoryFile = new java.io.File(specFileDirectoryName);
			
			try {testClassDirectoryFile.mkdirs();}catch(Exception exp){exp.printStackTrace();exp = null;}
			try {specDirectoryFile.mkdirs();}catch(Exception exp){exp.printStackTrace();exp = null;}
			
			if(testClassDirectoryFile.exists() == true  )
				System.out.println(testClassDirectoryFile+" Directory created successfully");
			else
			{
				System.out.println(testClassDirectoryFile+" Directory creation failed");
				return;
			}	
			if(specDirectoryFile.exists() == true  )
				System.out.println(specDirectoryFile+" Directory created successfully");
			else
			{
				System.out.println(specDirectoryFile+" Directory creation failed");
				return;
			}	
			
			System.out.println("******************************** "+testClassDirectoryName);
			System.out.println("******************************** "+specFileDirectoryName);
			
			writeBDDTestFile();
			writeBDDFactoryClassFile();
			writeBDDSpecs();

			htmlPage = onlyPackageName+"."+"BDD"+onlyClassName;
			htmlPage = htmlPage.replace('.','/') + ".html";

			//following code add story link to main html Overview.html
			String stringToAdd = "\n\t\t\t\t<tr style=\"background: linear-gradient(to right, #EBEBEB,#494A4D);\"><td><li><a concordion:run=\"concordion\" href=\""+htmlPage+"\"><Font face=\"System\" size=\"3\" color=\"black\">BDD"+onlyClassName+"</Font></a></li></td></tr>\n";
			
			//open html file in read mode
			java.io.RandomAccessFile rd = new java.io.RandomAccessFile(new File("src\\test\\specs\\Overview.html"),"r");
			String fileInputLine = null;
			String wholeHTMLAsString = "";
			
			//read all line in one big string
			while ((fileInputLine = rd.readLine()) != null) 
			{
				//concat each line to big string
				wholeHTMLAsString = wholeHTMLAsString + "\n"+ fileInputLine;
				fileInputLine = null;
			}
			
			rd.close();
			
			//insert new string with replace
			wholeHTMLAsString = wholeHTMLAsString.replace("<tr><td><hr></hr></td></tr>", stringToAdd+"\t\t\t\t<tr><td><hr></hr></td></tr>");
			
			
			File file = new File("src\\test\\specs\\Overview.html");
			 
			// if file doesnt exists, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}
			
			//open html file again with write
			rd = new java.io.RandomAccessFile(new File("src\\test\\specs\\Overview.html"),"rw");
			
			//write inserted/modified html
			rd.writeUTF(wholeHTMLAsString);
			
			//close the html file
			rd.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public static void main(String arguments[])
	{
		if(arguments.length == 0 )
		{
			System.out.println("Please provide TestNG test class name as parameter, see  example command line below\n");
			System.out.println("\tjava BDDGenerator test.v2solutions.homepage.TestContactUsPage");
			return;
		}
		
		new BDDGenerator(arguments[0]);
		
		//System.out.println("New String: "+BDDGenerator.breakOnCapLetter("validateDimensionsOfExternalRibbonAdInEventListSection"));
	}
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
}