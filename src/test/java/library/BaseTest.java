package library;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {

    public RequestSpecification rs;
    public Properties prop;
    String configPath = "src/main/resources/Environment.properties";

    @BeforeMethod
    public void setUp(){
        rs = RestAssured.given();
        rs.baseUri("http://localhost:8090");
        rs.header("Content-Type", ContentType.JSON);
        rs.header("accept", "application/json");

        try {
            prop = new Properties();
            FileInputStream fi = new FileInputStream(configPath);
            prop.load(fi);
        }catch (Exception exception){
            System.out.println("Environment file not initialized");
            exception.printStackTrace();
        }


    }
}
