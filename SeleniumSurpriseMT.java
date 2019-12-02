package com.hk.crawler;

import cn.hutool.core.thread.ThreadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        ChromeDriver driver = new ChromeDriver();
        while (flag) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                driver.get(url);
                ThreadUtil.sleep(5000L);
                //砸红包
                WebElement element3 = driver.findElement(By.xpath("//div[@class='egg-stage']/div[2]"));
                element3.click();
                ThreadUtil.sleep(2000L);
                driver.get(url);
                ThreadUtil.sleep(3000L);
                //点分享
                WebElement element = driver.findElement(By.xpath("//div[@class='home-header-share-tip']"));
                element.click();
                ThreadUtil.sleep(2000L);
                //分享到
                WebElement element1 = driver.findElement(By.xpath("//div[@class='share-item'][1]"));
                element1.click();
                ThreadUtil.sleep(1000L);
                WebElement element2 = driver.findElement(By.xpath("//div[@class='egg-stage']/div[2]"));
                element2.click();
                ThreadUtil.sleep(2000L);
                String text = driver.findElement(By.xpath("//div[@class='coupon-value-number']/span")).getText();
                if (Integer.valueOf(text) >= maxSurprise) {
                    flag = false;
                    ThreadUtil.sleep(1000L * 60 * 60);
                } else {
                    ThreadUtil.sleep(2000L);
                }
                driver.manage().deleteAllCookies();
            }catch (Exception e){
                e.printStackTrace();
                driver.close();
                driver = new ChromeDriver();
            }finally {
            }
        }
    }
}
