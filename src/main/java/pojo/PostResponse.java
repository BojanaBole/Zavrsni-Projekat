package pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
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

public class PostResponse {


        public String id;
        public String image;
        public int likes;
        public String link;
        public ArrayList<String> tags;
        public String text;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        public Date publishDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        public Date updatedDate;
        public Owner owner;
    }

