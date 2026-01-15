package steps;

import enums.PathApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import pojo.singleusers.ResponseApiSingleUsers;
import utilities.BaseTest;

import static net.serenitybdd.rest.SerenityRest.given;

public class SingleUserApiSteps extends UIInteractions {
    private String actualFirstName;
    private ResponseApiSingleUsers responseApiSingleUsers;

    @Steps
    LoginSteps loginSteps;

    @Given("User is already get single users with user id {string}")
    public void userIsAlreadyGetSingleUsersWithUserId(String id) {
        String token = loginSteps.getTokenFromLogin();
        BaseTest baseTest = new BaseTest();

        responseApiSingleUsers =
                given().
                        spec(baseTest.getSpecRequest(token)).
                        when().
                        get(PathApi.SingleUsers + "/" + id).
                        then().
                        spec(baseTest.getSpecResponse(200)).
                        extract().response().as(ResponseApiSingleUsers.class);
    }

    @When("User get data from response api single users")
    public void userGetDataFromResponseApiSingleUsers() {
        actualFirstName = responseApiSingleUsers.getData().getFirst_name();
    }

    @Then("User get first name {string}")
    public void userGetFirstName(String expectedFirstName) {
        Assert.assertEquals("Verify response field first_name: ", expectedFirstName, actualFirstName);
    }
}
