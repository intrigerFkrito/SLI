package com.project.resource.note;

import org.litepal.crud.DataSupport;

/**
 * @author
 * @date 2019/06/03
 */
public class Notes extends DataSupport {
    private String head;
    private String body;
    private int priority = 1;

    public Notes (String head, String body){
        this.head = head;
        this.body =body;
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

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
