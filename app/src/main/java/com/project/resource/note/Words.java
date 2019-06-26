package com.project.resource.note;

import org.litepal.crud.DataSupport;

/**
 * @author
 * @date 2019/05/03
 */
public class Words extends DataSupport {
    private String head;
    private String body;
    private int priority = 1;

    public Words(String head, String body){
        this.head =head;
        this.body =body;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public int getPriority() {
        return priority;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
