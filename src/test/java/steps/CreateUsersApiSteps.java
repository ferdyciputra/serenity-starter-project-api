package steps;

import enums.PathApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.steps.UIInteractions;
import org.junit.Assert;
import pojo.createusers.RequestApiCreateUsers;
import pojo.createusers.ResponseApiCreateUsers;
import utilities.BaseTest;

import static net.serenitybdd.rest.SerenityRest.given;

public class CreateUsersApiSteps extends UIInteractions {
    private String actualName;
    private String actualJob;
    private ResponseApiCreateUsers responseApiCreateUsers;

    @Given("User is already create users with name {string} and job {string}")
    public void userIsAlreadyCreateUsersWithNameAndJob(String name, String job) {
        BaseTest baseTest = new BaseTest();
        RequestApiCreateUsers requestApiCreateUsers = new RequestApiCreateUsers(name, job);

        responseApiCreateUsers =
                given().
                        spec(baseTest.getSpecRequest()).
                        body(requestApiCreateUsers).
                        when().
                        post(PathApi.CreateUsers.toString()).
                        then().
                        spec(baseTest.getSpecResponse(201)).
                        extract().response().as(ResponseApiCreateUsers.class);
    }

    @When("User get data from response api create users")
    public void userGetDataFromResponse() {
        actualName = responseApiCreateUsers.getName();
        actualJob = responseApiCreateUsers.getJob();
    }

    @Then("User get name {string} and job {string} as result")
    public void userGetNameAndJobAsResult(String expectedName, String expectedJob) {
        Assert.assertEquals("Verify response field name: ", expectedName, actualName);
        Assert.assertEquals("Verify response field job: ", expectedJob, actualJob);
    }
}
