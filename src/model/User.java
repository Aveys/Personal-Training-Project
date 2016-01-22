package model;

import com.google.appengine.api.datastore.Email;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Max TOMPOUCE on 21/01/2016.
 */
public class User implements Serializable{
    private String email;
    private Plan plan;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", plan=").append(plan);
        sb.append('}');
        return sb.toString();
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
