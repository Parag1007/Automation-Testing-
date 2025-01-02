import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Confi {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("configuration.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
//		String property = properties.getProperty("url");
//		System.out.println(property);
		
		
		
		
		
		

	}

}
