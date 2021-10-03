package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import pojo.Parameters;
import utilities.APIConstant;
import utilities.Authorization;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoriesSteps {

    private ResponseOptions<Response> response;
    private String token;

    @Given("I perform authentication operation")
    public void iPerformAuthenticationOperationFor(DataTable table) {
        var data = table.cells();
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("grant_type", data.get(1).get(0));

        Authorization authorization = new Authorization();
        token = authorization.Authorization(queryParams);
    }

    @And("I perform GET BY ID operation {string}")
    public void iPerformGETOperationFor(String uri, DataTable table) {
        var data = table.cells();
        Map<String,String> pathParams = new HashMap<>();
        pathParams.put("categoryId", data.get(1).get(0));

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(uri, APIConstant.ApiMethods.GET, token);
        response = restAssuredExtension.ExecuteAPIWithPathParams(pathParams);
    }

    @Then("I should see categories with ID {string}")
    public void iShouldSeeCategoriesWithID(String arg0) {
        ResponseBody body = response.getBody();
        System.out.println("Response Body for categoryId:709 is: " + body.asString());
        assertThat(response.statusCode(), equalTo(200));
    }

    @And("I perform GET operation for {string}")
    public void iPerformGETOperationByIDFor(String uri) {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(uri, APIConstant.ApiMethods.GET, token);
        response = restAssuredExtension.ExecuteAPICall();
    }

    @Then("I should see Ids of Allegro categories")
    public void iShouldSeeIdsOfAllegroCategories(){
        ResponseBody body = response.getBody();
        System.out.println("Response Body for all categories is: " + body.asString());
        assertThat(response.statusCode(), equalTo(200));
    }

    @And("I perform GET operation for parameters supported by category {string}")
    public void iPerformGETOperationForParametersSupportedByCategory(String uri, DataTable table) {
        var data = table.cells();
        Map<String,String> pathParams = new HashMap<>();
        pathParams.put("categoryId", "709");

        RestAssuredExtension restAssuredExtension = new RestAssuredExtension(uri, APIConstant.ApiMethods.GET, token);
        response = restAssuredExtension.ExecuteAPIWithPathParams(pathParams);
    }

    @Then("I should see Id of parameters supported by a category")
    public void iShouldSeeIdOfParametersSupportedByACategory() {
        ResponseBody body = response.getBody();
        System.out.println("Response Body of parameters supported by a category is: " + body.asString());
        assertThat(response.statusCode(), equalTo(200));

        var parameters = response.getBody().as(Parameters.class);
        assertThat(parameters.getParameters().get(0).getName(), equalTo("Stan"));
    }
}
