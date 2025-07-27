package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class BaseTest {
    private static final EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();
    private static final String BASE_URL = EnvironmentSpecificConfiguration.from(env).getProperty("base.url.api");
    private static final String X_API_KEY = EnvironmentSpecificConfiguration.from(env).getProperty("api.key");
    private static final int MAX_TIMEOUT_MILLISECONDS = 10000;

    public RequestSpecification getSpecRequest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setAccept(ContentType.JSON);
        requestSpecBuilder.addHeader("x-api-key", X_API_KEY);
        requestSpecBuilder.setConfig(getDefaultConfig());

        return requestSpecBuilder.build();
    }

    public ResponseSpecification getSpecResponse(Integer expectStatusCode) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(expectStatusCode);

        return responseSpecBuilder.build();
    }

    /**
     * Set Config Log Only Enable If Validation Fails,
     * Set Config Connection Timeout
     *
     * @return RestAssuredConfig
     */
    public RestAssuredConfig getDefaultConfig() {
        LogConfig logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails();

        return RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", MAX_TIMEOUT_MILLISECONDS)
                        .setParam("http.connection.timeout", MAX_TIMEOUT_MILLISECONDS))
                .logConfig(logConfig);
    }
}
