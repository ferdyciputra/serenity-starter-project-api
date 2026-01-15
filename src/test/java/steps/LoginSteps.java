package steps;

import enums.PathApi;
import pojo.login.RequestApiLogin;
import pojo.login.ResponseApiLogin;
import utilities.BaseTest;

import static net.serenitybdd.rest.SerenityRest.given;

public class LoginSteps {

    public String getTokenFromLogin() {
        BaseTest baseTest = new BaseTest();
        RequestApiLogin req = new RequestApiLogin();

        // Hit API
        ResponseApiLogin response =
                given().
                        spec(baseTest.getSpecRequest()).
                        body(req).
                        when().
                        post(PathApi.Login.toString()).
                        then().
                        spec(baseTest.getSpecResponse(200)).
                        extract().response().as(ResponseApiLogin.class);

        return response.getToken();
    }
}