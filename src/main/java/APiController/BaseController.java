package APiController;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseController {
    static final String CONTENT_TYPE_JSON = "application/json";
    static final String ACCEPT_JSON = "application/json";
    public static RequestSpecification baseRequestUrl(String url) {
        return BaseUrlMethod(url);
    }

    private static RequestSpecification BaseUrlMethod(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
//                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .addHeader("Accept", ACCEPT_JSON)
                .build();
    }
}
