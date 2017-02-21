package com.v2solutions.taf.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class LatestReportsCopyAndZipUtil {
	public static String rootPath = System.getProperty("user.dir");
	  static String[] temp  = new String[20];
	  static  int count = 0;
	  static String verifivationSummaryData = "";
	  public static String filename;
	  public static File[] srcFolder = new File[3];
	  public static File destFolder;
	  public static String Latestfile;
	  public static String Latestresultsfolder;
	  public static String execution_Reports_LogsPath = rootPath+ "/executionreports/";
	  public static String DotZip = "[.]"+"[zip]";
	  
	  
	  
	  public static SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy_hh-mm-ss_aaa(zzz)");
	  public static final java.util.Date curDate = new java.util.Date();
	  public static final String strDate = sdf.format(curDate);
	  public static final String strActDate = strDate.toString();
	  
	 public static String FILE_DIR = rootPath+"/target";
	 public static String FILE_TEXT_SRT = "QA-EngineerFailAnalysis";
	 private static final String FILE_TEXT_STRT = "allResult";
	 static File f1 = null;
	 static File f2 = null;

	public static void copyReports() throws ParseException, IOException {
		srcFolder[0] = new File(rootPath + "/logs");
		srcFolder[1] = new File(rootPath + "/test-output/Automation_Execution_Suite");
		srcFolder[2] = new File(rootPath + "/screenshots");
		destFolder = new File(execution_Reports_LogsPath+""+ strActDate);
				
		for (int i = 0; i <= srcFolder.length - 1; i++) {
			if (!srcFolder[i].exists()) {
				System.out.println(srcFolder[i]+"Directory does not exist.");
			} else {
				try {
					copyFolder(srcFolder[i], destFolder);
					System.out.println(srcFolder[i]+"Directory got copied.");
				} catch (IOException e) {
					System.out.println(":"+e.getMessage());
				}
			}
		}
		
		try{ 
		f1 = new File(destFolder+"\\"+getTheNewestFile(FILE_DIR,FILE_TEXT_STRT).getName());
		f2 = new File(destFolder+"\\"+getTheNewestFile(FILE_DIR,FILE_TEXT_SRT).getName());
		   
		copyFileUsingFileStreams(getTheNewestFile(FILE_DIR,FILE_TEXT_SRT),f2);
		copyFileUsingFileStreams(getTheNewestFile(FILE_DIR,FILE_TEXT_STRT),f1);
		}catch(NullPointerException ne){
			System.out.println(":"+ne.getMessage());
		}
	}

	public static void copyFolder(File src, File dest) throws IOException {
		
		
		if (src.isDirectory()) {
			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
				System.out.println("Directory copied from " + src + "  to "+ dest);
			}
			// list all the directory contents
			String files[] = src.list();
			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}
		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void newestFile() {
		
		
		File dir = new File(execution_Reports_LogsPath);
		File[] files = dir.listFiles();
		Arrays.sort(files, new Comparator() {
			public int compare(Object o1, Object o2) {
				return compare((File) o1, (File) o2);
			}
			private int compare(File f1, File f2) {
				long result = f2.lastModified() - f1.lastModified();
				if (result > 0) {
					return 1;
				} else if (result < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		Latestfile = (files[0].getAbsolutePath());
	}
	

	public static File getTheNewestFile(String filePath, String strt) {
	    File theNewestFile = null;
	    File dir = new File(filePath);
	    FileFilter fileFilter = new WildcardFileFilter(strt+ "*.xml");
	    File[] files = dir.listFiles(fileFilter);

	    if (files.length > 0) {
	        /** The newest file comes first **/
	        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	        theNewestFile = files[0];
	    }

	    return theNewestFile;
	}
	
	
 public static void getResultFolderPath(){
	
		String Latest1=Latestfile;
		String First = Latest1;
		String[] FirstSplit = First.split(DotZip);
		Latestresultsfolder = FirstSplit[0];
 }
	
 	public static void zippingFolder() {
 		
		try {
			// Source Folder == > Path to Zip a particular folder
			File inFolder = new File(Latestfile);
			// Destination Folder == > Output zip file name
			File outFolder = new File(Latestfile + ".zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[5000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(
						inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(":"+e.getMessage());
		}
	}
	

 	private static void copyFileUsingFileStreams(File source, File dest)throws IOException {
 		
	    InputStream input = null;
	
	    OutputStream output = null;
	
	    try {
	
	        input = new FileInputStream(source);
	
	        output = new FileOutputStream(dest);
	
	        byte[] buf = new byte[1024];
	
	        int bytesRead;
	
	        while ((bytesRead = input.read(buf)) > 0) {
	
	            output.write(buf, 0, bytesRead);
	
	        }
	
	    } finally {
		        input.close();
	
	        output.close();
	
	    }
	
	}
  
}

 
