package APiController;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PetroAPP_APIsController  extends BaseController {

    static RequestSpecification petroAppSpec = baseRequestUrl("http://hiring.petroapp.com/api.php");

    public static Response AuthenticateUser(HashMap<String, String> queries, String endPoint) {
        return given().spec(petroAppSpec)
                .log().all() // Log request details for debugging
                .formParams(queries) // Form data
                .when()
                .post(endPoint);
    }
    public static Response GetCars(String SessionIDValue, String endPoint) {
        return given().spec(petroAppSpec)
                .header("Session-ID", SessionIDValue)
                .log().all()
                .when().get(endPoint);
    }
//    public static Response CreateNewUser(HashMap<String, String> queries, String endPoint) {
//        return given().spec(usersSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .post(endPoint);
//    }
//
//    public static Response LoginUser(HashMap<String, String> queries, String endPoint) {
//        return given().spec(usersSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .post(endPoint);
//    }
//
//    public static Response UpdateProfile(HashMap<String, String> queries, String token, String endPoint) {
//        return given().spec(usersSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .header("x-auth-token", token)
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .patch(endPoint);
//    }
//
//    public static Response ChangePassword(HashMap<String, String> queries, String token, String endPoint) {
//        return given().spec(usersSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .header("x-auth-token", token)
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .post(endPoint);
//    }
//
//    public static Response CreateNote(HashMap<String, String> queries, String token, String endPoint) {
//        return given().spec(notesSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .header("x-auth-token", token)
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .post(endPoint);
//    }
//

//
//    public static Response updateNote(HashMap<String, String> queries, String token, String endPoint) {
//        return given().spec(notesSpec).contentType("application/x-www-form-urlencoded; charset=utf-8")
//                .header("x-auth-token", token)
//                .log().all() // Log request details for debugging
//                .formParams(queries) // Form data
//                .when()
//                .put(endPoint);
//    }
//
//    public static Response DeleteNote(String token, String endPoint) {
//        return given().spec(notesSpec)
//                .header("x-auth-token", token)
//                .log().all()
//                .when().delete(endPoint);
//    }
}