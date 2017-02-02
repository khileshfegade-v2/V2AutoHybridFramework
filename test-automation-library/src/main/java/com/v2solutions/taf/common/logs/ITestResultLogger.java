package com.v2solutions.taf.common.logs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.v2solutions.taf.util.LogUtil;

/**This class represent 'Excel Logger Thread' which will log reasons for errors and skips.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class ITestResultLogger implements Runnable {

	public static final long SLEEP_TIME = 1000;

	private static Log log = LogUtil.getLog(ITestResultLogger.class);

	private Thread logger;
	private Vector<ContextResultCapsule> failedTestMethodResults;
	private Vector<ContextResultCapsule> skippedTestMethodResults;
	private Vector<ContextResultCapsule> passedTestMethodResults;

	//	private ITestContext testContext;

	private boolean runFlag = false;
	private boolean shutDownFlag = false;

	private ExcelCompatibleXMLRow failAanalysisQAEngineerExcelLog;
	private ExcelCompatibleXMLRow failAanalysisClientExcelLog;

	private ExcelCompatibleXMLRow finalTestResultExcelLog;
	private ExcelCompatibleXMLRow passedResultsExcelLog;
	private ExcelCompatibleXMLRow skippedResultsExcelLog;
	private ExcelCompatibleXMLRow failedResultsExcelLog;


	//	private QAEngineerExcelCompatibleXMLRowsWithLog4j qaExcelLog;
	//	private ClientExcelCompatibleXMLRowsWithLog4j customerExcelLog;

	private SkippedLogsWithLog4j skippedLogs; 

	public ITestResultLogger() {
		log.info("****Excel Logger Thread instantiated!");
	}



	public void addFailedTestResult(ITestResult tr, ITestContext context) {
		if(tr != null) {
			ContextResultCapsule capsule = new ContextResultCapsule(tr, context);
			failedTestMethodResults.add(capsule);
			synchronized(this) {
				notify();
			}
		}
	}

	public void addPassedFailedTestResult(ITestResult tr, ITestContext context) {
		if(tr != null) {
			ContextResultCapsule capsule = new ContextResultCapsule(tr, context);
			passedTestMethodResults.add(capsule);
			synchronized(this) {
				notify();
			}
		}
	}


	public void addSkippedTestResult(ITestResult tr, ITestContext context) {
		if(tr != null) {
			ContextResultCapsule capsule = new ContextResultCapsule(tr, context);
			skippedTestMethodResults.add(capsule);
			synchronized(this) {
				notify();
			}
		}
	}


	public void startLogging() {
		if(logger == null) {
			try {
				//				qaExcelLog = new QAEngineerExcelCompatibleXMLRowsWithLog4j();
				//				customerExcelLog = new ClientExcelCompatibleXMLRowsWithLog4j();

				FailAnalysisQAEngineerExcelCompatibleXMLWorkbook failAnalysisQAEngineerExcelCompatibleXMLFile =
						new FailAnalysisQAEngineerExcelCompatibleXMLWorkbook();

				FailAnalysisClientExcelCompatibleXMLWorkbook failAnalysisClientExcelCompatibleXMLFile =
						new FailAnalysisClientExcelCompatibleXMLWorkbook();

				FinalResultExcelCompatibleXMLWorksheet finalResultExcelCompatibleXMLWorksheet = 
						new FinalResultExcelCompatibleXMLWorksheet();

				PassedTestResultExcelCompatibleXMLWorksheet passedTestResultExcelCompatibleXMLWorksheet = 
						new PassedTestResultExcelCompatibleXMLWorksheet();

				SkippedTestResultExcelCompatibleXMLWorksheet skippedTestResultExcelCompatibleXMLWorksheet =
						new SkippedTestResultExcelCompatibleXMLWorksheet();

				FailedTestResultExcelCompatibleXMLWorksheet failedTestResultExcelCompatibleXMLWorksheet =
						new FailedTestResultExcelCompatibleXMLWorksheet();

				failAanalysisQAEngineerExcelLog = new ExcelCompatibleXMLRow(null, failAnalysisQAEngineerExcelCompatibleXMLFile);
				failAanalysisClientExcelLog = new ExcelCompatibleXMLRow(null, failAnalysisClientExcelCompatibleXMLFile);
				finalTestResultExcelLog = new ExcelCompatibleXMLRow(null, finalResultExcelCompatibleXMLWorksheet);
				passedResultsExcelLog = new ExcelCompatibleXMLRow(null, passedTestResultExcelCompatibleXMLWorksheet);
				skippedResultsExcelLog = new ExcelCompatibleXMLRow(null, skippedTestResultExcelCompatibleXMLWorksheet);
				failedResultsExcelLog = new ExcelCompatibleXMLRow(null, failedTestResultExcelCompatibleXMLWorksheet);

				skippedLogs = new SkippedLogsWithLog4j();

				failedTestMethodResults = new Vector<ContextResultCapsule>();
				skippedTestMethodResults = new Vector<ContextResultCapsule>();
				passedTestMethodResults = new Vector<ContextResultCapsule>();

				//				qaExcelLog.openOutputStream();
				failAanalysisQAEngineerExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write failed analysis for qa-engineer logs.");

				//				customerExcelLog.openOutputStream();
				failAanalysisClientExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write failed analysis for customer logs.");

				finalTestResultExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write for Final Results.");

				passedResultsExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write for Passed Results.");

				skippedResultsExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write for Skipped Results.");

				failedResultsExcelLog.setLogger();
				log.info("****Excel Logger Thread started to write for Failed Results.");

				skippedLogs.openOutputStream();				

				runFlag = true;
				logger = new Thread(this);
				logger.setDaemon(false);
				logger.start();
			}
			catch(Throwable e) {
				e.printStackTrace();
				log.error("****Error while starting Excel Logger Thread.", e);
			}
		}
	}

	public void stopLogging() {
		shutDownFlag = true;
		try {
			logger.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		log.info("****Excel Logger Thread stopped logging.");
	}

	public void run() {
		ContextResultCapsule capsule = null;
		ITestResult tr = null;
		ITestContext context = null;
		log.info("****Excel Logger Thread started.");
		while(runFlag) {
			try {
				synchronized(this) {
					wait(SLEEP_TIME);
				}
			}
			catch(InterruptedException e) {
				log.error("****Error while waiting for new ITestResult.");
			}


			if(failedTestMethodResults.size() > 0) {
				capsule = failedTestMethodResults.remove(0);
				tr = capsule.getITestResult();
				context = capsule.getTestContext();
				synchronized(this) {
					try {
						logFailedResult(tr, context);
					}
					catch(Exception logEx) {
						logEx.printStackTrace();
						log.error("****Error while logging to Excel.", logEx);
					}
				}
			}

			if(this.skippedTestMethodResults.size() > 0) {
				capsule = skippedTestMethodResults.remove(0);
				tr = capsule.getITestResult();
				context = capsule.getTestContext();
				synchronized(this) {
					try {
						logSkippedResult(tr, context);
					}
					catch(Exception logEx) {
						logEx.printStackTrace();
						log.error("****Error while logging for skipped method.", logEx);
					}
				}
			}

			if(this.passedTestMethodResults.size() > 0) {
				capsule = passedTestMethodResults.remove(0);
				tr = capsule.getITestResult();
				context = capsule.getTestContext();
				synchronized(this) {
					try {
						this.logPassedResult(tr, context);
					}
					catch(Exception logEx) {
						logEx.printStackTrace();
						log.error("****Error while logging for passed method.", logEx);
					}
				}
			}


			if(shutDownFlag == true) {
				if((failedTestMethodResults.size() == 0) && (skippedTestMethodResults.size() == 0) && (passedTestMethodResults.size() == 0)) {
					runFlag = false;
					log.info("****Going to stop Excel Logger Thread.");
				}
			}
		}
		log.info("****Excel Logger Thread is stopped.");
	}

	public void logFinalResult(ITestContext testContext) {
		synchronized(this) {
			try {
				if(testContext != null) {
					String testName = testContext.getName();
					int failed = testContext.getFailedTests().size();
					int passed = testContext.getPassedTests().size();
					int skipped = testContext.getSkippedTests().size();

					String passedStr = String.valueOf(passed);
					String failedStr = String.valueOf(failed);
					String skippedStr = String.valueOf(skipped);

					ArrayList<String> finalResult = new ArrayList<String>();
					finalResult.add(testName);
					finalResult.add(passedStr);
					finalResult.add(failedStr);
					finalResult.add(skippedStr);

					this.finalTestResultExcelLog.appendRow(finalResult);
				}
			}
			catch(Exception logEx) {
				logEx.printStackTrace();
				log.error("****Error while logging final result to Excel.", logEx);
			}
		}



	}

	private void logPassedResult(ITestResult tr, ITestContext context) throws IOException {
		PassedITestResultParser parser = new PassedITestResultParser(tr, context);
		ArrayList<ArrayList> rows = parser.getExcelCompatibleLogs();
		for(int i = 0; i < rows.size(); i++) {
			ArrayList<String> row = rows.get(i);
			this.passedResultsExcelLog.appendRow(row);
		}
		PassedITestResultParser.incrementSerialNumber();
	}

	private void logSkippedResult(ITestResult tr, ITestContext context) throws IOException {
		this.skippedLogs.log(tr);
		SkippedITestResultParser sirp = new SkippedITestResultParser(tr, context);
		ArrayList arr = sirp.getExcelCompatibleLogs();
		this.skippedResultsExcelLog.appendRow(arr);
		sirp.incrementSerialNumber();
	}

	private void logFailedResult(ITestResult tr, ITestContext context) throws IOException {
		FailedITestResultParser irp = null;
		ArrayList qaLogs = null;
		ArrayList customerLogs = null;
		ArrayList qaLog = null;
		int srNo = FailedITestResultParser.getSerialNumber();
		irp = new FailedITestResultParser(tr, context);

		log.info("****Excel Logger Thread going to log for all failed test methods.");
		ArrayList failedLogs = irp.getFailedLogs();
		for(int i = 0; i < failedLogs.size(); i++) {
			ArrayList failedLogsRow = (ArrayList)failedLogs.get(i);
			this.failedResultsExcelLog.appendRow(failedLogsRow);
		}

		log.info("****Excel Logger Thread going to log for qa-engineer and customer.");
		qaLogs = irp.getQaLogs();
		log.info("****Retrieved qa logs. "+qaLogs);
		customerLogs = irp.getCustomerLogs();
		log.info("****Retrieved customer logs. "+customerLogs);

		for(int i = 0; i < qaLogs.size(); i++) {
			qaLog = (ArrayList)qaLogs.get(i);
			this.failAanalysisQAEngineerExcelLog.appendRow(qaLog);
		}
		log.info("****Added qa logs to xml file.");
		this.failAanalysisClientExcelLog.appendRow(customerLogs);
		log.info("****Added customer log to xml file.");
		FailedITestResultParser.incrementSerialNumber();
		log.info("****Incremented srNo of failed test method.");
	}

}
