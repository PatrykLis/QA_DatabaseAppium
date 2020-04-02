import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.jdbc.Connection;

public class SeleniumAndSQL {

	public static void main(String[] args) throws SQLException {
		String host="localhost";
		String port="3306";
		
		Connection con= (Connection) DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/qadata", "root", "password");
//in password put yours passoword 
		Statement s=con.createStatement();
		ResultSet rs=s.executeQuery("select * from credentials where scenario = 'zerobalancecard'");
		
		while(rs.next())
		{
			//before using firefox you must use a geckodriver
			System.setProperty("webdriver.gecko.driver","C:\\Users\\kesil\\Downloads\\geckodriver.exe");
			WebDriver driver= new FirefoxDriver();
			driver.get("https://poczta.wp.pl");
			
			
		driver.findElement(By.xpath(".//*[@id='login']")).sendKeys(rs.getString("username"));
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(rs.getString("password"));
	}

}
}