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
		System.out.println("hello world");
//		String test = "lang=EN&debugMode=0&scaleMode=noScale&DOMId=dialogChart&registerWithJS=1&chartWidth=840&chartHeight=440&InvalidXMLText=Invalid data.&dataXML=%3Cchart%20baseFont%3D%22Arial%20Black%22%20baseFontColor%3D%22666666%22%20bgAlpha%3D%220%22%20bgColor%3D%22FFFFFF%22%20formatNumberScale%3D%220%22%20showBorder%3D%220%22%20showValues%3D%220%22%20anchorBgColor%3D%221F6CD6%22%20anchorRadius%3D%224%22%20canvasBorderThickness%3D%220%22%20labelDisplay%3D%22Rotate%22%20labelStep%3D%222%22%20numDivLines%3D%220%22%20numVDivLines%3D%2223%22%20showAlternateHGridColor%3D%220%22%20showAlternateVGridColor%3D%220%22%20showLimits%3D%220%22%20showShadow%3D%220%22%20slantLabels%3D%221%22%20vDivLineAlpha%3D%22100%22%20vDivLineColor%3D%22F3EAD0%22%20vDivLineThickness%3D%222%22%20yAxisMaxValue%3D%221444%22%20lineColor%3D%221F6CD6%22%20lineThickness%3D%222%22%20legendBorderColor%3D%22FFFFFF%22%20legendMarkerCircle%3D%221%22%20legendPosition%3D%22BOTTOM%22%20legendShadow%3D%220%22%20showLegend%3D%221%22%3E%0A%20%20%3Cstyles%3E%0A%20%20%20%20%3Cdefinition%3E%0A%20%20%20%20%20%20%3Cstyle%20name%3D%22tt_font_style%22%20type%3D%22font%22%20Color%3D%22000000%22%20bgColor%3D%22ffffff%22%20borderColor%3D%22000000%22%20isHTML%3D%221%22%2F%3E%0A%20%20%20%20%20%20%3Cstyle%20name%3D%22legend_font_style%22%20type%3D%22font%22%20font%3D%22Arial%22%20size%3D%2211%22%2F%3E%0A%20%20%20%20%20%20%3Cstyle%20name%3D%22trend_values_font_style%22%20type%3D%22font%22%20isHTML%3D%221%22%2F%3E%0A%20%20%20%20%3C%2Fdefinition%3E%0A%20%20%20%20%3Capplication%3E%0A%20%20%20%20%20%20%3Capply%20styles%3D%22tt_font_style%22%20toObject%3D%22TOOLTIP%22%2F%3E%0A%20%20%20%20%20%20%3Capply%20styles%3D%22trend_values_font_style%22%20toObject%3D%22TRENDVALUES%22%2F%3E%0A%20%20%20%20%20%20%3Capply%20styles%3D%22legend_font_style%22%20toObject%3D%22LEGEND%22%2F%3E%0A%20%20%20%20%3C%2Fapplication%3E%0A%20%20%3C%2Fstyles%3E%0A%20%20%3CtrendLines%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22499-%22%20startValue%3D%2257.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22520-524%22%20startValue%3D%22152.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22545-549%22%20startValue%3D%22247.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22570-574%22%20startValue%3D%22342.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22595-599%22%20startValue%3D%22437.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22620-624%22%20startValue%3D%22532.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22645-649%22%20startValue%3D%22627.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22670-674%22%20startValue%3D%22722.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22695-699%22%20startValue%3D%22817.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22720-724%22%20startValue%3D%22912.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22745-749%22%20startValue%3D%221007.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22770-774%22%20startValue%3D%221102.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22795-799%22%20startValue%3D%221197.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22820-824%22%20startValue%3D%221292.0%22%2F%3E%0A%20%20%20%20%3Cline%20alpha%3D%2230%22%20color%3D%22666666%22%20displayvalue%3D%22845-850%22%20startValue%3D%221387.0%22%2F%3E%0A%20%20%3C%2FtrendLines%3E%0A%20%20%3Ccategories%3E%0A%20%20%20%20%3Ccategory%20label%3D%2205%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2206%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2207%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2208%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2209%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2210%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2211%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2212%2F14%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2201%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2202%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2203%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2204%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2205%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2206%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2207%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2208%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2208%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2209%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2210%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2211%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2212%2F15%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2201%2F16%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2202%2F16%22%2F%3E%0A%20%20%20%20%3Ccategory%20label%3D%2204%2F16%22%2F%3E%0A%20%20%3C%2Fcategories%3E%0A%20%20%3Cdataset%20seriesName%3D%22Borrower%20CREDIT%20Score%20Range%22%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2005%2F14%7Bbr%7DScore%20Range%3A%20660-664%22%20value%3D%22684.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2006%2F14%7Bbr%7DScore%20Range%3A%20675-679%22%20value%3D%22741.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2007%2F14%7Bbr%7DScore%20Range%3A%20670-674%22%20value%3D%22722.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2008%2F14%7Bbr%7DScore%20Range%3A%20670-674%22%20value%3D%22722.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2009%2F14%7Bbr%7DScore%20Range%3A%20665-669%22%20value%3D%22703.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2010%2F14%7Bbr%7DScore%20Range%3A%20670-674%22%20value%3D%22722.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2011%2F14%7Bbr%7DScore%20Range%3A%20675-679%22%20value%3D%22741.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2012%2F14%7Bbr%7DScore%20Range%3A%20655-659%22%20value%3D%22665.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2001%2F15%7Bbr%7DScore%20Range%3A%20655-659%22%20value%3D%22665.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2002%2F15%7Bbr%7DScore%20Range%3A%20635-639%22%20value%3D%22589.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2003%2F15%7Bbr%7DScore%20Range%3A%20635-639%22%20value%3D%22589.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2004%2F15%7Bbr%7DScore%20Range%3A%20645-649%22%20value%3D%22627.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2005%2F15%7Bbr%7DScore%20Range%3A%20635-639%22%20value%3D%22589.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2006%2F15%7Bbr%7DScore%20Range%3A%20590-594%22%20value%3D%22418.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2007%2F15%7Bbr%7DScore%20Range%3A%20620-624%22%20value%3D%22532.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2008%2F15%7Bbr%7DScore%20Range%3A%20615-619%22%20value%3D%22513.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2008%2F15%7Bbr%7DScore%20Range%3A%20615-619%22%20value%3D%22513.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2009%2F15%7Bbr%7DScore%20Range%3A%20580-584%22%20value%3D%22380.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2010%2F15%7Bbr%7DScore%20Range%3A%20575-579%22%20value%3D%22361.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2011%2F15%7Bbr%7DScore%20Range%3A%20560-564%22%20value%3D%22304.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2012%2F15%7Bbr%7DScore%20Range%3A%20555-559%22%20value%3D%22285.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2001%2F16%7Bbr%7DScore%20Range%3A%20560-564%22%20value%3D%22304.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2002%2F16%7Bbr%7DScore%20Range%3A%20555-559%22%20value%3D%22285.0%22%2F%3E%0A%20%20%20%20%3Cset%20toolText%3D%22Borrower%20CREDIT%20Score%20Range%20for%3A%2004%2F16%7Bbr%7DScore%20Range%3A%20555-559%22%20value%3D%22285.0%22%2F%3E%0A%20%20%3C%2Fdataset%3E%0A%3C%2Fchart%3E&ChartNoDataText=No data.&dataURL=";
//		Pattern pat = Pattern.compile("(%20[0-9][0-9][0-9]-[0-9][0-9][0-9])");
//        Matcher m = pat.matcher(test);
//        ArrayList<Integer> chartRanges = new ArrayList<Integer>();
//        while (m.find()) {
//        	String range = m.group().substring(3);
//        	System.out.println(range);
//        	String[] ranges = range.split("-");
//        	Integer minRange = Integer.valueOf(ranges[0]);
//        	Integer maxRange = Integer.valueOf(ranges[1]);
//        	
//            chartRanges.add((minRange + maxRange) / 2);
//        }    
//        Collections.reverse(chartRanges);
//        int slope = 0;
//        if(chartRanges.size() >= 8) {
//        	slope = chartRanges.get(7) - chartRanges.get(0);
//        }
//        else {
//        	slope = chartRanges.get(chartRanges.size() - 1) - chartRanges.get(0);
//        }
//        if(slope > 30) {
//        	System.out.println("creditTrendssbad");
//        }
//        System.out.println(chartRanges);
		//System.out.println(result + "#");
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
		//Thread.sleep(2000);
		new Select(fluentWait(By.id("yui-pg0-0-rpp45"))).selectByValue("250");
		Thread.sleep(2000);
		fluentWait(By.cssSelector("a[href*='yui-dt0-href-ytm']")).click();
		Thread.sleep(2000);
		fluentWait(By.cssSelector("a[href*='yui-dt0-href-ytm']")).click();
		Thread.sleep(2000);
		List<WebElement> pages = driver.findElements(By.className("yui-pg-page"));
		pages.remove(0);
		int pageCount = 1;
		for(WebElement page: pages)
		{
			List<WebElement> links = driver.findElements(By.cssSelector("a[href*='/foliofn/browseNotesLoanPerf.action?showfoliofn=true&loan_id']"));		
			
			for(WebElement we: links) {
				
				String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
				we.sendKeys(selectLinkOpeninNewTab);
				String originalHandle = driver.getWindowHandle();
				driver.getWindowHandles();

			    for(String handle : driver.getWindowHandles()) {
			        if (!handle.equals(originalHandle)) {
			            driver.switchTo().window(handle);
			            //driver.close();
			        }
			    }
				checkIfIndividualLoanIsWorthy();
				driver.close();
				driver.switchTo().window(originalHandle);
				//count++;
				//if (count == 100) break;
			}
			//Stop after 2 pages of results..that is usually plenty.
			pageCount++;
			if(pageCount > 2)
				break;
			page.click();
			Thread.sleep(2000);
		}
		System.out.println(goodLoanList.size());
		System.out.println(goodLoanList);
		System.out.println(goodLoanListWithoutCreditCheck.size());
		System.out.println(goodLoanListWithoutCreditCheck);
	}
	
	private boolean checkIfIndividualLoanIsWorthy() {
		boolean isLoanGood = false;
		List<WebElement> paymentHistory = driver.findElements(By.cssSelector("td[headers*='yui-dt0-th-status']"));
		ArrayList<String> statusList = new ArrayList<String>();
		int completedInGracePeriodCount = 0;
		int completedCount = 0;
		int completedLateCount = 0;
		int scheduledCount = 0;
		boolean lastThreePaidAreCompleted = false;
		boolean paymentHistoryIsGood = false;
		
		for(int i = 0; i<7; i++) {			
			String status = paymentHistory.get(i).findElement(By.className("yui-dt-liner")).getText();
			statusList.add(status);
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
		
			//System.out.println(status);
		}
		
		if(statusList.get(1).equals("Completed") && statusList.get(2).equals("Completed")  && statusList.get(2).equals("Completed")) {
			lastThreePaidAreCompleted = true;
		}
		
		if(lastThreePaidAreCompleted && completedCount >= 5 && completedLateCount <=1) {
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
		
		return isLoanGood;
	}
	
	private boolean checkCreditScoreTrend() {
		boolean isCreditScoreTrendGood = false;
		driver.findElement(By.id("stats_graphs")).click();
		WebElement chartData = driver.findElement(By.name("flashvars"));
		String chartValue = chartData.getAttribute("value");
		Pattern pat = Pattern.compile("(%20[0-9][0-9][0-9]-[0-9][0-9][0-9])");
        Matcher m = pat.matcher(chartValue);
        ArrayList<Integer> chartRanges = new ArrayList<Integer>();
        while (m.find()) {
        	String range = m.group().substring(3);
        	System.out.println(range);
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
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
	

}
