package com.lts.ordit_tnb;

import android.widget.CheckBox;

public class Complaint {

    public String complaintID, name, staffID, bodyPain, otherDesc;

    public Complaint(String complaintID, String name, String staffID, String bodyPain, String otherDesc) {
        this.complaintID = complaintID;
        this.name = name;
        this.staffID = staffID;
        this.bodyPain = bodyPain;
        this.otherDesc = otherDesc;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public String getName() {
        return name;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getBodyPain() {
        return bodyPain;
    }

    public String getOtherDesc() {
        return otherDesc;
    }
}
