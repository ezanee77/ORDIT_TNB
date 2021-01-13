package com.tnb.ordit;

public class Ergo {

    public String id, name, staffid, job, result;

    public Ergo(String id, String name, String staffid, String job, String result) {
        this.id = id;
        this.name = name;
        this.staffid = staffid;
        this.job = job;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStaffid() {
        return staffid;
    }

    public String getJob() {
        return job;
    }

    public String getResult() {
        return result;
    }
}
