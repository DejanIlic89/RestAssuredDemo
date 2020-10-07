package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Address;
import pojo.Location;
import pojo.LoginBody;
import pojo.Posts;
import utilities.APIConstant;
import utilities.RestAssuredExtension;
import utilities.RestAssuredExtensionV2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GETPostSteps {

    private static ResponseOptions<Response> response;

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) {
        // With builder pattern
        Posts post = response.getBody().as(Posts.class);
        assertThat(post.getAuthor(), equalTo(authorName));
    }

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.getOpsWithToken(url, TestContext.token);
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BBDStyledMethod.performContainsCollection();
    }

    @Then("^I should verity GET Parameter$")
    public void iShouldVerityGETParameter() {
        BBDStyledMethod.performQueryParameter();
    }

    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String arg0) {
        BBDStyledMethod.performPOSTWithBodyParameter();
    }

    @And("^I perform GET operation with query parameter for address \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterForAddress(String uri, DataTable table)  {
        List<List<String>> data = table.raw();

        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("id", data.get(1).get(0));

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, APIConstant.ApiMethods.GET, TestContext.token);
        response = restAssuredExtensionV2.executeWithQueryParams(queryParams);
    }

    @Then("^I should see the street name as \"([^\"]*)\" for the \"([^\"]*)\" address$")
    public void iShouldSeeTheStreetNameAsForTheAddress(String streetName, final String type) {
        Location[] locations = response.getBody().as(Location[].class);

        // Filter the address based on the type of addresses
        Address address = locations[0].getAddress().stream().filter(x -> x.getType().equals(type)).findFirst().orElse(null);

        assertThat(address.getStreet(), equalTo(streetName));
    }

    @Then("^I should see the author name as \"([^\"]*)\" with json validation$")
    public void iShouldSeeTheAuthorNameAsWithJsonValidation(String arg0) throws Throwable {
        //returns the body as string
        String responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("post.json"));
    }
}
