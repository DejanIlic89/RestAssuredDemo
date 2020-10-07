package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import pojo.LoginBody;
import utilities.APIConstant;
import utilities.RestAssuredExtensionV2;

import java.util.List;

public class POSTAuthSteps {

    @Given("^I perform authentication operation for \"([^\"]*)\" with body$")
    public void iPerformAuthenticationOperationForWithBody(String uri, DataTable table) {
        List<List<String>> data = table.raw();

        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(data.get(1).get(0));
        loginBody.setPassword(data.get(1).get(1));

        RestAssuredExtensionV2 restAssuredExtensionV2 = new RestAssuredExtensionV2(uri, APIConstant.ApiMethods.POST, null);
        TestContext.token = restAssuredExtensionV2.authenticate(loginBody);
    }

}
