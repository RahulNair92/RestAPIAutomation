package shopBasicAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import library.BaseTest;
import library.Endpoints;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllProductsTest extends BaseTest {

    @Test
    public void verifyGetAllProductsAPI(){
        Response response = rs.get(Endpoints.GetAllProducts);

        Assert.assertEquals(response.statusCode(), 200);
        System.out.println(response.getBody().prettyPrint());
    }
}
