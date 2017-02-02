package test.samples;

import org.testng.annotations.Test;

public class TestSampleScript {
	@Test
	public void testGoogleHome() {
//		driver.get("https://www.google.com");
		System.out.println("Test First test method");
	}
	@Test()
	public void testGoogleWithSearch() {
		System.out.println("Test Second test method");
//		driver.get("https://www.google.com");
//		driver.findElement(By.name("q")).sendKeys(searchData);
	}
}
