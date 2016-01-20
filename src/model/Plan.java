package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Max TOMPOUCE on 20/01/2016.
 */
public class Plan implements Serializable{
    private String title;
    private String desc;
    private String domain;
    private String totalTime;
    private List<Exercise> exercises;


    public Plan() {

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Plan{");
        sb.append("title='").append(title).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", domain='").append(domain).append('\'');
        sb.append(", totalTime='").append(totalTime).append('\'');
        sb.append(", exercises=").append(exercises);
        sb.append('}');
        return sb.toString();
    }
}
