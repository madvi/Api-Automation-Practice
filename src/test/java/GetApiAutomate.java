import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetApiAutomate {

    @Test
    public void getUsersListAndExtractResponse(){
        //Response is an interface from restAssured
        Response res = given()
            .param("page","2")
                .when()
                   .get("https://reqres.in/api/users?page=2")
                .then()
                    .log().body()
                    .assertThat()
                    .statusCode(200)
                    //to get the list by any attribute present in the data[] array
                    .body("data.id", Matchers.hasItems(7,8,9,10,11,12))

                    //to get the 1st value of any attribute present in data[] array
                    .body("data.email[0]",Matchers.equalTo("michael.lawson@reqres.in"))
                    .body("data.first_name",Matchers.hasItem("Michael"))
                    .body("data.last_name",Matchers.hasItem("Lawson"))

                    //to assert for the size of any array
                    .body("data.size",Matchers.equalTo(6))
                    .extract()
                //return type of this method is responseOptions
                    .response();
        //Whatever response extracted from here will be assigned to object created and that obj can be used
        System.out.println("Response = " + res.asString());

        //To extract the single value from the response
        //We need to get value from response using jsonpath or xmlpath, create an object to get the value
        //need to pass response string while creating an object
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println("Data name = " + jsonPath.getString("data[0].email"));

        //to use hamcrest assertion library
        //assertThat(res,equalTo("michael.lawson@reqres.in"));


    }
}
