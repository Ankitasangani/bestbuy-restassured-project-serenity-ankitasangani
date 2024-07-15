package com.bestbuy.testbase.crudtest;


import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.testbase.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class StoresCURDTestWithSteps extends TestBase {
    static String name = "MADE" + TestUtils.getRandomValue();
    static String type = "Furniture" + TestUtils.getRandomValue();
    static String address = "Bullring" + TestUtils.getRandomValue();
    static String address2 = "ground Floor";
    static String city = "Birmingham";
    static String state = "West Midlands";
    static String zip = TestUtils.getRandomValue();
    static double lat = 75.89855;
    static double lng = -26.55651;
    static String hours = "Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";

    static int id;

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {

        ValidatableResponse response = storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        ValidatableResponse response = storeSteps.getStoreInfoById(id);
    }

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        name = name + "Updated";
        type = type + "Updated";
        address = address + "Updated";
        address2 = address2 + "Updated";
        city = city + "Updated";
        state = state + "Updated";
        zip = zip + "Updated";

        ValidatableResponse response = storeSteps.updateStore(id, name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);

    }

    @Title("Delete the store and verify if the store is deleted")
    @Test
    public void test004() {
        storeSteps.deleteStore(id).statusCode(200);

        storeSteps.getStoreById(id).statusCode(404);
    }
}
