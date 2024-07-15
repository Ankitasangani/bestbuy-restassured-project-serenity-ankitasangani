package com.bestbuy.testbase.crudtest;


import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.testbase.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTestWithSteps extends TestBase {
    static String name = "Samsung" + TestUtils.getRandomValue();
    static String type = "Mobile" + TestUtils.getRandomValue();
    static Double price = 900.00;
    static String upc = TestUtils.getRandomValue();
    static Double shipping = 3.99;
    static String description = "IP68 dust and water resistance";
    static String manufacturer = "Samsung";
    static String model = "Samsung";
    static String url = "https://www.samsung.com/uk/smartphones/galaxy-s24/buy/";
    static String image = "https://www.samsung.com/uk/smartphones/galaxy-s24/buy//images/overview/5x-zoom/pro-zoom_endframe__bpmc72f8qwgi_large.jpg";
    static int id;

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProductInfoById(id).statusCode(200);
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "Updated";
        type = type + "Updated";
        description = description + "Updated";
        manufacturer = manufacturer + "Updated";
        model = model + "Updated";
        url = url + "Updated";
        image = image + "Updated";

        ValidatableResponse response = productSteps.updateProduct(id, name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(200);

    }

    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test004() {
        ValidatableResponse response = productSteps.deleteProduct(id).statusCode(200);

        ValidatableResponse response1 = productSteps.getProductInfoById(id).statusCode(404);
    }

}
