package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class StoreSteps {

    @Step("Creating store with name:{0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lang: {8}, hours: {9}")
    public ValidatableResponse createStore(String name, String type, String address,
                                           String address2, String city, String state, String zip, double lat, double lng, String hours) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address,
                address2, city, state, zip, lat, lng, hours);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(EndPoints.GET_ALL_STORES)
                .then();
    }

    @Step("Getting the Store information with id : {0}")
    public ValidatableResponse getStoreInfoById(int id) {

        return SerenityRest.given()
                .when()
                .pathParam("id", id)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().statusCode(200);
    }

    @Step("Updating store with name:{0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lang: {8}, hours: {9}")
    public ValidatableResponse updateStore(int id, String name, String type, String address,
                                           String address2, String city, String state, String zip, double lat, double lng, String hours) {

        StorePojo storePojo = StorePojo.getStorePojo(name, type, address,
                address2, city, state, zip, lat, lng, hours);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(storePojo)
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }

    @Step("Deleting store information with id: {0}")
    public ValidatableResponse deleteStore(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store information with id: {0}")
    public ValidatableResponse getStoreById(int id) {
        return SerenityRest.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(404);
    }
}
