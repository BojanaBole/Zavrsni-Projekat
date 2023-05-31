package pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.*;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor



public class PostCreate {


    private String image;
    private int likes;
    private String tags;
    private String text;
    private String owner;

    public  static PostCreate createPost() {
        Faker faker = new Faker(new Locale("en-US"));

        PostCreate newPost = PostCreate.builder()
                .image(faker.internet().image())
                .likes(faker.number().randomDigit())
                .tags(faker.lorem().word())
                .owner("60d0fe4f5311236168a10a0b")
                .build();


        return newPost;
    }
}



