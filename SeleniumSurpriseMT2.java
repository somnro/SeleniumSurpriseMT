package com.hk.crawler;

import cn.hutool.core.thread.ThreadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wzh
 * @Date: 2019/12/2 11:56
 * D:\install\chromedriver_win32\chromedriver.exe
 **/
public class SeleniumSurpriseMT{

    /**
     * 设置领用的最小红包，红包一般是2-5元
     */
    static int maxSurprise = 5;
    static boolean flag = true;
    static String url = "https://offsiteact.meituan.com/ad/landing/v1?f=mtovunXplNAk4MOSUebuRIJTTDLvsmh6HYawLpHtj%2Fw%2FdoMUGrSHp8bL7%2BkMszGovbpXHBtWzNVteoQpT8Zi5Q%3D%3D#/";

    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置代理,请先验证代理是否有效
//        chromeOptions.addArguments("--proxy-server=http://119.147.137.79:8008");
        //去掉提示
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
//        //开启F12模式
//        chromeOptions.addArguments("--auto-open-devtools-for-tabs");
        //开启手机模式  无效！！！
//        Map<String, Object> deviceMetrics = new HashMap<>();
//        deviceMetrics.put("width", 360);
//        deviceMetrics.put("height", 640);
//        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceMetrics", deviceMetrics);
//        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

//                mobileEmulation.put("deviceName","iPhone 6/7/8");
        mobileEmulation.put("deviceName","Nexus 5");
//        chromeOptions.setExperimentalOption("mobileEmulation",mobileEmulation);
//        Map<String, Object> chromeOptionsMap = new HashMap<String, Object>();
//        chromeOptionsMap.put("mobileEmulation", mobileEmulation);
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        //设置手机模式请求
//        chromeOptions.addArguments("---user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
//        chromeOptions.addArguments("---user-agent=Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.75 Mobile Safari/537.36");
//        chromeOptions.addArguments("---user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");

        ChromeDriver driver = new ChromeDriver(chromeOptions);
//        ChromeDriver driver = new ChromeDriver(capabilities);
        while (flag) {
            try {
                driver.get(url);
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='egg-stage']/div[2]")));

//                ThreadUtil.sleep(3000L);
                /*//会在当前窗口执行F12
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_F12);
                robot.keyRelease(KeyEvent.VK_F12);*/
                //砸红包
//                WebElement element3 = driver.findElement(By.xpath("//div[@class='egg-stage']/div[2]"));
                WebElement element3 = driver.findElement(By.xpath("//div[@class='egg egg-middle false']"));
//                Actions actions = new Actions(driver);
//                element3.sendKeys(Keys.LEFT_CONTROL,Keys.LEFT_SHIFT,"M");
                ThreadUtil.sleep(1000L);
                element3.click();
                ThreadUtil.sleep(2000L);
                driver.get(url);
                ThreadUtil.sleep(3000L);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='home-header-share-tip']")));
                //点分享
                WebElement element = driver.findElement(By.xpath("//div[@class='home-header-share-tip']"));
                element.click();
                ThreadUtil.sleep(2000L);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='share-item'][1]")));
                //分享到
                WebElement element1 = driver.findElement(By.xpath("//div[@class='share-item'][1]"));
                element1.click();
                ThreadUtil.sleep(3000L);

                WebElement element2 = driver.findElement(By.xpath("//div[@class='egg egg-middle false']"));
                element2.click();
                ThreadUtil.sleep(2000L);
                String text = driver.findElement(By.xpath("//div[@class='coupon-value-number']/span")).getText();
                System.out.println("红包 = " + text);
                if (Integer.valueOf(text) >= maxSurprise) {
                    flag = false;
                    ThreadUtil.sleep(1000L * 60 * 600);
                } else {
                    ThreadUtil.sleep(2000L);
                }
                driver.manage().deleteAllCookies();
            }catch (Exception e){
//                System.out.println(e.getMessage());
                e.printStackTrace();
                driver.manage().deleteAllCookies();
            }finally {
            }
        }
    }
}
