package com.project.resource.englishnew;

import org.w3c.dom.Text;

/**
 * @author
 * @date 2019/05/29
 */
public class EnglishNews {
    private String title;

    private String author;

    private String imageURL;

    private String url;

    private String publishedAt;

    private String source;


    public EnglishNews(String title, String imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

    public EnglishNews(String title, String imageURL, String url, String publishedAt, String source,String author) {
        this.title = title;
        this.imageURL = imageURL;
        this.author = author;
        this.publishedAt = publishedAt;
        this.source = source;
        this.url = url;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getUrl(){
        return url;
    }

    public String getPublishedAt(){
        return publishedAt;
    }

    public String getSource(){
        return source;
    }
}
