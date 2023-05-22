package test.crawling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class HairInfo {
    
	public static final String DRIVER_PATH = "/Users/jeonhyeonjin/miju/selenium/chromedriver"; //드라이버 경로

    private static final String DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID

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

        String local = "부평 미용실";
        String url = "https://map.naver.com/v5/search/" + local;

        driver.get(url);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // 검색 결과 로딩을 위한 대기 시간

        // 검색 페이지에서 결과 리스트의 각 요소를 선택하고 정보 가져오기
        WebElement element = driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame(element);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // 검색 결과 로딩을 위한 대기 시간

        WebElement element2 = driver.findElement(By.tagName("html"));

        WebElement element3 = element2.findElement(By.className("place_on_pcmap"));        

        WebElement element4 = element3.findElement(By.className("place_didmount"));        
        
        WebElement element5 = element4.findElement(By.className("mFg6p"));        
        
        WebElement element6 = element5.findElement(By.className("XUrfU"));
        
        WebElement element7 = element6.findElement(By.className("Ryr1F"));   
        
        // ul 태그

        WebElement element8 = element7.findElement(By.tagName("ul"));

        int count = 0;

        List<WebElement> element9 = element8.findElements(By.tagName("li"));

        Map<Integer, String> map = new HashMap<>();
        
        for (WebElement el9 : element9) {

            WebElement element10 = el9.findElement(By.className("QTjRp"));        

            WebElement element11 = element10.findElement(By.className("JpTbw"));        
    
            WebElement element12 = element11.findElement(By.className("G9H9r"));        
    
            WebElement element13 = element12.findElement(By.className("tWIhh")); 
            
            List<WebElement> elements = element13.findElements(By.className("O_Uah"));
            
            for(WebElement e : elements) {
                map.put(count, e.getText());
                count = count+1;
            }
        }

        driver.switchTo().defaultContent();

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

        // 미용실 정보 출력
        System.out.println(map.toString());
    }
}

