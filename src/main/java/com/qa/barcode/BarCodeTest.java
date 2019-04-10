package com.qa.barcode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeTest {
	
	@Test
	public void barCodeTest() throws IOException, NotFoundException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\eclipse-workspace\\BarCodeAutomation\\src\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://barcode.tec-it.com/en");
		
		String  barCodeUrl = driver.findElement(By.tagName("img")).getAttribute("src");
		System.out.println(barCodeUrl);
		
		URL url = new URL(barCodeUrl);
		BufferedImage bufferedImage = ImageIO.read(url);
		
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result = new MultiFormatReader().decode(binaryBitmap);
		System.out.println(result.getText());
		
	}
	
}
