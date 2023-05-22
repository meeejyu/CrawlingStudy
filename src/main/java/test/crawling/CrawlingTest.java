package test.crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CrawlingTest {
    
    public static final String DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
	public static final String DRIVER_PATH = "/Users/jeonhyeonjin/miju/selenium/chromedriver"; //드라이버 경로
	
	public static void main(String[] args) {
		//드라이버 설정
		try {
			System.setProperty(DRIVER_ID, DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//크롬 설정을 해주기 위한 클래스
		ChromeOptions options = new ChromeOptions();

		options.addArguments("headless"); // 브라우저 창이 열리지 않고 백그라운드에서 실행
		options.addArguments("--remote-allow-origins=*"); // Chrome 브라우저에 원격에서 접근 가능하도록 설정
		
		// Chrome 브라우저를 제어하기 위한 클래스
		ChromeDriver driver = new ChromeDriver(options);

		//이동을 원하는 url
		String url = "https://meetyougo.tistory.com";
		
		//WebDriver을 해당 url로 이동한다.
		driver.get(url);
		
		//브라우저 이동시 생기는 로드시간을 기다린다.
		//HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		WebElement element1 = driver.findElement(By.className("tags"));

		// div 태그안에 클래스 이름이 'tags'인 a태그를 가진 모든 element를 받아온다.
		List<WebElement> element2 = element1.findElements(By.tagName("a"));

		int count = 0;
		for (int i = 0; i < element2.size(); i++) {
			System.out.println(++count + "번 태그: "+ element2.get(i).getText());
		}
		
		try {
			//드라이버가 null이 아니라면
			if(driver != null) {
				//드라이버 연결 종료
				driver.close(); //드라이버 연결 해제
				
				//프로세스 종료
				driver.quit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    }
}
