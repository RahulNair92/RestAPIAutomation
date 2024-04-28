package shopBasicAuth;

import io.restassured.response.Response;

import library.BaseTest;
import library.Endpoints;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import requestModel.Product;
import responseModel.ProductResponse;

public class CreateProduct extends BaseTest {

    @Test
    public void createAndVerifyNewProduct(){
        String randomId = RandomStringUtils.randomAlphabetic(3);
        String productName = "Pizza"+ randomId;
        System.out.println(productName);

        Product newProduct = new Product();
        newProduct.name = productName;
        newProduct.description = "refreshing drink";
        newProduct.price = 20f;

        Response response = rs
                .auth().basic(prop.getProperty("StoreManagerUsername"),
                        prop.getProperty("StoreManagerPassword"))
                .body(newProduct)
                .post(Endpoints.CreateProduct);

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertTrue(response.header("Location")
                .contains(Endpoints.CreateProduct));

        String productId = response.header("Location")
                .replace(Endpoints.CreateProduct+"/", "");
        System.out.println(productId);


        Response getResponse = rs
                .auth().basic(prop.getProperty("StoreManagerUsername"),
                        prop.getProperty("StoreManagerPassword"))
                .pathParam("id", productId)
                .get(Endpoints.GetProductById);
        /*ProductResponse pr = getResponse.as(ProductResponse.class);
        String productNameResponse = pr.name;*/
        String productNameResponse = getResponse.body().jsonPath().get("name");
        float price = getResponse.body().jsonPath().get("price");
        Assert.assertEquals(productNameResponse, productName);
        Assert.assertEquals(price, 20.0f);
        System.out.println(getResponse.getBody().prettyPrint());

        }

}
