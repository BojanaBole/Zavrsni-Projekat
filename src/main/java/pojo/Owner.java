package pojo;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

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


    public class Owner {
        private String id = "60d0fe4f5311236168a10a0b";
        public String title;
        public String firstName;
        public String lastName;
        public String picture;

    }


