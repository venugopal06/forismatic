package com.gremlin.forismatic.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ForismaticDTO implements Serializable {
    private String quoteText;
    private String quoteAuthor;
    private String senderName;
    private String senderLink;
    private String quoteLink;
}
