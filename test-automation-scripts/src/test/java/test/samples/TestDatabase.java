package test.samples;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.v2solutions.taf.util.DBUtil;

public class TestDatabase {

	DBUtil dbUtil = new DBUtil();
	String query = "SELECT * FROM Employee";
	ResultSet rs = null;
	@Test(enabled=false)
	public void test() throws IOException, SQLException
	{

		dbUtil.OpenDBConnection();
		rs = dbUtil.ExecuteDBQuery(query);
		while (rs.next())
		{

			String empName = rs.getString("Emp_Name");
			int id = rs.getInt("Emp_Id");
			int empAge = rs.getInt("Emp_Age");

			// print the results
			System.out.println("Employee ID :"+id);
			System.out.println("Employee name :"+empName);
			System.out.println("Employee Age :"+empAge);
		}
		dbUtil.closeStatement();
	}
}
