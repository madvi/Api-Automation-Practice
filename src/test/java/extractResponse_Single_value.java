import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class extractResponse_Single_value {

    @Test
    public void extractSingleValueFromResponse(){
        String email = given()
                .param("page","2")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .assertThat()
                .statusCode(200)
                .extract()

                //Whatever path is given will get stored in 'email' variable
                .response().path("data[0].email");
        //To get the response as string, we need to call the string class
        System.out.println("data email  " + email );

        //Adding assertion using hamcrest library

        assertThat(email,equalTo("michael.lawson@reqres.in"));

    }
}
