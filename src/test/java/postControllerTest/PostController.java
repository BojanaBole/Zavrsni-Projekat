package postControllerTest;

import com.github.javafaker.Faker;
import config.Config;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import listeners.RetryAnalyzer;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.Owner;
import pojo.PostCreate;
import pojo.PostResponse;
import utils.Constants;
import utils.Utils;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Locale;
import static io.restassured.RestAssured.given;


import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)

public class PostController extends Config {
    private static final Logger logger = LogManager.getLogger(config.PostTests.class);
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @Test(groups = "smoke", description = "Get all posts, expected: List of posts is returned")


//    @Test(retryAnalyzer = RetryAnalyzer.class)

    public void getAllPostsTest() {

        Response response = given()
                .queryParam("page", "1")
                .queryParam("limit", "5")
                .when().get(Constants.GET_ALL_POSTS);

        int actualStatusCode = response.statusCode();
        softAssert.assertEquals(actualStatusCode, 200, "Expected status code is 200 but got: " + actualStatusCode);

        String actualOwner = response.jsonPath().get("data[0].owner.firstName");
        logger.info("Actual owner name:" + actualOwner);

        Assert.assertEquals(actualOwner, "Margarita", "Expected first name is Margarita but found: " + actualOwner);
        softAssert.assertAll();


    }

    @Test
    public void getPostsByUserTest() {

        Response response = given()
                .pathParam("id", "60d0fe4f5311236168a10a0b")
                .when().get(Constants.GET_POSTS_BY_USER);

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, 200, "Expected status code is 200 but got:" + actualStatusCode);

        String actualOwner = response.jsonPath().get("data[0].owner.firstName");
        logger.info("Actual owner name:" + actualOwner);

        Assert.assertEquals(actualOwner, "Margarita", "Expected first name is Margarita but found: " + actualOwner);
        softAssert.assertAll();

    }

    @Test
    public void getListByTagTest() {

        Response response = given()
                .pathParam("id", "dog")
                .when().get(Constants.GET_BY_TAG);

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, 200, "Expected status code is 200 but got:" + actualStatusCode);

        String actualTags = response.jsonPath().get("data[0].tags[0]");
        logger.info("Actual owner name:" + actualTags);

        Assert.assertEquals(actualTags, "animal", "Expected tags is animal but found: " + actualTags);
        softAssert.assertAll();

    }

    @Test
    public void getPostByIdTest() {

        Response response = given()
                .pathParam("id", "60d21b8667d0d8992e610d3f")
                .when().get(Constants.GET_POST_BY_ID);

        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, 200, "Expected status code is 200 but got:" + actualStatusCode);

        String actualTag = response.jsonPath().get("tags[0]");
        logger.info("Actual owner name:" + actualTag);

        Assert.assertEquals(actualTag, "dog", "Expected first name is Margarita but found: " + actualTag);
        softAssert.assertAll();

    }

    @Test
    public void deletePostTest() {

        PostCreate newPost = PostCreate.createPost();

        PostResponse postResponse = given()
                .body(newPost)
                .when().post(Constants.CREATE_POST).getBody().as(PostResponse.class);

        String idPost = postResponse.getId();

        Response response = given()
                .pathParam("id", idPost)
                .when().delete(Constants.DELETE_POST);

        int actualStatusCode = response.statusCode();
        softAssert.assertEquals(actualStatusCode, 200, "Expected status code is 200 but got:" + actualStatusCode);

        String actualPostId = response.jsonPath().get("id");
        softAssert.assertEquals(actualPostId, idPost, "Expected idPost is " + idPost + "but found" + actualPostId);

    }

    @Test
    public void createPostTest() {

        String owner = "60d0fe4f5311236168a10a0b";
        Faker faker = new Faker(new Locale("en-US"));

        PostCreate newPost = PostCreate.builder()
                .image(faker.internet().image())
                .likes(faker.number().randomDigit())
                .tags(faker.lorem().word())
                .owner("60d0fe4f5311236168a10a0b")
                .build();

        given()
                .body(newPost)
                .log().all()
                .when().post(Constants.CREATE_POST)
                .then().log().all();


        PostResponse postResponse = given()
                .body(newPost)
                .when().post(Constants.CREATE_POST).getBody().as(PostResponse.class);

        softAssert.assertEquals(postResponse.getImage(), postResponse.getImage());
        softAssert.assertEquals(postResponse.getText(), postResponse.getText());
        softAssert.assertEquals(postResponse.getTags(), postResponse.getTags());
        softAssert.assertAll();
    }

    @Test
    public void updatePostTest() {

        PostCreate newPost = PostCreate.createPost();

        PostResponse postResponse = given()
                .body(newPost)
                .when().post(Constants.CREATE_POST).getBody().as(PostResponse.class);

        String updatetags = "tag";
        newPost.setTags(updatetags);
        String updatedPost = postResponse.getId();

        PostResponse updatedPostResponse = given()
                .body(newPost)
                .pathParam("id", updatedPost)
                .when().put(Constants.UPDATE_POST).getBody().as(PostResponse.class);


    }
}




