package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class POSTProfileSteps {

    private static ResponseOptions<Response> response;

    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) {
        List<List<String>> data = table.raw();

        //Set body
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", data.get(1).get(0));

        //Path params
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("profileNo", data.get(1).get(1));

        //Perform post operation
        response = RestAssuredExtension.postOpsWithBodyAndPathParams(url, pathParams, body);
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"), Matchers.<Object>equalTo(name));
    }

    @Given("^I ensure to Perform POST operation for \"([^\"]*)\" with body as$")
    public void iEnsureToPerformPOSTOperationForWithBodyAs(String url, DataTable table) {
        List<List<String>> data = table.raw();

        //set body
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        RestAssuredExtension.postOpsWithBody(url, body);
    }

    @And("^I Perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable table) {
        List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("postId", data.get(1).get(0));

        RestAssuredExtension.deleteOpsWithPathParams(url, pathParams);
    }

    @And("^I perform GET operation with path parameter for \"([^\"]*)\"$")
    public void iPerformGETOperationWithPathParameterFor(String url, DataTable table) {
        List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("postId", data.get(1).get(0));

        response = RestAssuredExtension.getOpsWithPathParams(url, pathParams);
    }

    @Then("^I \"([^\"]*)\" see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String condition, String title) {
        if (condition.equalsIgnoreCase("should not")) {
            assertThat((String) response.getBody().jsonPath().get("title"), IsNot.not(title));
        } else {
            assertThat((String) response.getBody().jsonPath().get("title"), is(title));
        }
    }

    @And("^I Perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable table) {
        List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("postId", data.get(1).get(0));

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        response = RestAssuredExtension.putOpsWithBodyAndPathParams(url, body, pathParams);
    }
}
