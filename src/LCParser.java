import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class LCParser {

	public static void main(String[] args) throws Exception {
		LCParser lcp = new LCParser();
		lcp.filterLoans();
	
	}
	
	WebDriver driver;
	LinkedHashSet<String> goodLoanList = new LinkedHashSet<String>();
	LinkedHashSet<String> goodLoanListWithoutCreditCheck = new LinkedHashSet<String>();
	
	public LCParser()
	{
		String exePath = "C:\\dev\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		driver = new ChromeDriver();
		
	}
	

	
	public void filterLoans() throws Exception
	{
		driver.get("https://www.lendingclub.com/account/gotoLogin.action");
		WebElement username = driver.findElement(By.name("login_email"));
		username.sendKeys("xxx");
		WebElement password = driver.findElement(By.name("login_password"));
		password.sendKeys("xxx");
		WebElement submitButton = driver.findElement(By.id("master_accountSubmit"));
		submitButton.click();
		Thread.sleep(1000);
		driver.get("https://www.lendingclub.com/foliofn/tradingInventory.action?mode=search&search_from_rate=0.12&search_to_rate=0.29&fil_search_term=term_36&fil_search_term=term_60&search_loan_term=term_36&search_loan_term=term_60&opr_min=0.00&opr_max=Any&loan_status=loan_status_issued&loan_status=loan_status_current&remp_min=6&remp_max=15&askp_min=0.00&askp_max=Any&credit_score_min=600&credit_score_max=850&ytm_min=12&ytm_max=Any&credit_score_trend=UP&credit_score_trend=DOWN&credit_score_trend=FLAT&markup_dis_min=-100&markup_dis_max=0&ona_min=25&ona_max=Any");
		new Select(fluentWait(By.id("yui-pg0-0-rpp45"))).selectByValue("250");
		Thread.sleep(2000);
		fluentWait(By.cssSelector("a[href*='yui-dt0-href-ytm']")).click();
		Thread.sleep(2000);
		fluentWait(By.cssSelector("a[href*='yui-dt0-href-ytm']")).click();
		Thread.sleep(2000);
		List<WebElement> pages = driver.findElements(By.className("yui-pg-page"));
		pages.remove(0);
		//int count = 0;
		int pageCount = 1;
		int loanCount = 1;
		for(WebElement page: pages)
		{
			
			List<WebElement> links = driver.findElements(By.cssSelector("a[href*='/foliofn/browseNotesLoanPerf.action?showfoliofn=true&loan_id']"));		
			
			for(WebElement we: links) {
				String url = we.getAttribute("href");
				String loan_id = url.substring(url.indexOf("loan_id"), url.indexOf("&order"));
				System.out.println("loan " + loanCount +" " + loan_id);
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
				we.sendKeys(selectLinkOpeninNewTab);
				String originalHandle = driver.getWindowHandle();
				driver.getWindowHandles();

			    for(String handle : driver.getWindowHandles()) {
			        if (!handle.equals(originalHandle)) {
			            driver.switchTo().window(handle);
			        }
			    }
				checkIfIndividualLoanIsWorthy();
				Thread.sleep((int)(Math.random() * 2000));
				driver.close();
				driver.switchTo().window(originalHandle);
				loanCount++;
			}
			//Stop after 1 pages of results..that is usually plenty....and we have a bug here
			pageCount++;
			if(pageCount > 1)
				break;
			page.click();
			Thread.sleep(2000);
		}
		System.out.println(goodLoanList.size());
		System.out.println(goodLoanList);
		System.out.println(goodLoanListWithoutCreditCheck.size());
		System.out.println(goodLoanListWithoutCreditCheck);
	}
	
	private boolean checkIfIndividualLoanIsWorthy() throws InterruptedException {
		boolean isLoanGood = false;
		List<WebElement> paymentHistory = driver.findElements(By.cssSelector("td[headers*='yui-dt0-th-status']"));
		ArrayList<String> statusList = new ArrayList<String>();
		int completedInGracePeriodCount = 0;
		int completedCount = 0;
		int completedLateCount = 0;
		int scheduledCount = 0;
		int notRecievedCount = 0;
		boolean isPending = false;
		boolean isPaymentPlan = false;
		boolean enoughHistory = false;
		boolean lastThreePaidAreCompleted = false;
		boolean paymentHistoryIsGood = false;
		
		if(paymentHistory.size() >= 10)
		{
			for(int i = 0; i<10; i++) {	
				String status = paymentHistory.get(i).findElement(By.className("yui-dt-liner")).getText();
				statusList.add(status);
				
				if(status.contains("Plan")) {
					isPaymentPlan = true;
					break;
				}
				if (status.startsWith("Pending")) {
					isPending = true;
					break;
				}
				if (status.startsWith("Completed - Late")) {
					completedLateCount++;
				}
				else if (("Completed").equals(status)) {
					completedCount++;
				}
				else if (("Scheduled").equals(status)) {
					scheduledCount++;
				}
				else if (("Completed - In Grace Period").equals(status)) {
					completedInGracePeriodCount++;
				}
				else if (("Not Received").equals(status)) {
					notRecievedCount++;
				}
			
				//System.out.println(status);
			}
			
			if(!isPending && !isPaymentPlan && statusList.get(1).equals("Completed") && statusList.get(2).equals("Completed")  && statusList.get(3).equals("Completed")) {
				lastThreePaidAreCompleted = true;
			}
			
			if(lastThreePaidAreCompleted && 
					completedCount >= 7 && 
					completedLateCount <=1 &&
					completedInGracePeriodCount <=1) {
				paymentHistoryIsGood = true;
			}
			
			if(paymentHistoryIsGood) {
				
				String loanIdContainer = driver.findElement(By.cssSelector("div[class='data-container data-summary']")).findElement(By.tagName("h3")).getText();
				String result = loanIdContainer.substring(loanIdContainer.lastIndexOf(" ") + 1, loanIdContainer.length() - 1);
				goodLoanListWithoutCreditCheck.add(result);
				boolean creditCheckPass = checkCreditScoreTrend();
						
				isLoanGood = creditCheckPass && paymentHistoryIsGood;
				if(isLoanGood) {
					//String loanIdContainer = driver.findElement(By.cssSelector("div[class='data-container data-summary']")).findElement(By.tagName("h3")).getText();
					//String result = loanIdContainer.substring(loanIdContainer.lastIndexOf(" ") + 1, loanIdContainer.length() - 1);
					goodLoanList.add(result);
				}			
			}
		}		
		return isLoanGood;
	}
	
	private boolean checkCreditScoreTrend() throws InterruptedException {
		boolean isCreditScoreTrendGood = false;
		fluentWait(By.id("stats_graphs")).click();
		
		WebElement chartData = fluentWait(By.name("flashvars"));
		String chartValue = chartData.getAttribute("value");
		Pattern pat = Pattern.compile("(%20[0-9][0-9][0-9]-[0-9][0-9][0-9])");
        Matcher m = pat.matcher(chartValue);
        ArrayList<Integer> chartRanges = new ArrayList<Integer>();
        while (m.find()) {
        	String range = m.group().substring(3);
        	String[] ranges = range.split("-");
        	Integer minRange = Integer.valueOf(ranges[0]);
        	Integer maxRange = Integer.valueOf(ranges[1]);
        	
            chartRanges.add((minRange + maxRange) / 2);
        }    
        Collections.reverse(chartRanges);
        int slope = 0;
        if(chartRanges.size() >= 8) {
        	slope = chartRanges.get(7) - chartRanges.get(0);
        }
        else {
        	slope = chartRanges.get(chartRanges.size() - 1) - chartRanges.get(0);
        }
        if(slope < 30) {
        	isCreditScoreTrendGood = true;
        }  
		return isCreditScoreTrendGood;
	}
	
	private WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(1, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
	

}
