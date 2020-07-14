package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {

    private String name;
    private String expires_in;
    private String snippet;
    private String url;



    public String getName() {
        return name;
    }
    public void setUrl(String newurl) {
       this.url=newurl;
    }
    public void setName(String _name) {
        this.name = _name;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public void setExpiresin(String _expires_in) {
        this.expires_in = _expires_in;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }


}
