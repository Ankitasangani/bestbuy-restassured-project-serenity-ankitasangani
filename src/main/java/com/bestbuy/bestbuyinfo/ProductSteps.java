package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps {

    @Step("Creating product with name:{0}, type: {1}, price: {2}, upc: {3}, shipping: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse createProduct(String name, String type, double price,
                                             String upc, double shipping, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, upc, shipping, description, manufacturer, model, url, image);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

    @Step("Getting the product information with id : {0}")
    public ValidatableResponse getProductInfoById(int id) {

        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Updating product with name:{0}, type: {1}, price: {2}, upc: {3}, shipping: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse updateProduct(int id, String name, String type, double price,
                                             String upc, double shipping, String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = ProductPojo.getProductPojo(name, type, price, upc, shipping, description, manufacturer, model, url, image);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(productPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting Product information with productId: {0}")
    public ValidatableResponse deleteProduct(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

}
