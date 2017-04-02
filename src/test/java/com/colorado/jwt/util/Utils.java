package com.colorado.jwt.util;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

/**
 * Created by colorado on 2/04/17.
 */
public class Utils {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                MediaType.APPLICATION_JSON.getSubtype(),
                                                                Charset.forName("utf8")
                                                            );
}
