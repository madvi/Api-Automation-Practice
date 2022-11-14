import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetApiAutomate {

    @Test
    public void getUsersList(){
        given()
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
                    .body("data.size",Matchers.equalTo(6));
    }
}
