package model;

import java.io.Serializable;

/**
 * Created by Max TOMPOUCE on 20/01/2016.
 */
public class Exercice implements Serializable {
    private String title;
    private String desc;
    private String length;
    private String row;

    public Exercice() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Exercice{");
        sb.append("title='").append(title).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", length='").append(length).append('\'');
        sb.append(", row='").append(row).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
