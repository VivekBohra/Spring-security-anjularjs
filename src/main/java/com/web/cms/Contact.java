package com.web.cms;

public class Contact {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_number;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String flast_name) {
        this.last_name = flast_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    @Override
    public String toString() {
        return "Task [first_name=" + first_name + ", last_name=" + last_name
                + ", email=" + email + ", mobile_number="
                + mobile_number + "]";
    }

}
