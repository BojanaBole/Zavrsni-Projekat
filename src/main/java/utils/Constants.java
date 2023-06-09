package utils;

import java.io.File;

public class Constants {

    public static final String GET_ALL_POSTS = "post";
    public static final String GET_POSTS_BY_USER = "/user/{id}/post";

    public static final String GET_BY_TAG = "/tag/{id}/post";
    public static final String GET_POST_BY_ID = "/post/{id}";
    public static final  String DELETE_POST = "/post/{id}";

    public  static final String CREATE_POST = "/post/create" ;

    public  static final String UPDATE_POST = "/post/{id}" ;


    public static final String PROJECT_ROOT = System.getProperty("post.dir");
}
