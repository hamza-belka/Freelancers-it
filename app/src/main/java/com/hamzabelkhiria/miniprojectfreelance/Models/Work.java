package com.hamzabelkhiria.miniprojectfreelance.Models;

public class Work {

    private int workid;
    private String postermail;
    private  int posterid;
    private String description;
    private String worktitle;
    public enum  budgettypes {fixed,hourly};
    private budgettypes budgettype;
    private String budget;
    private String jobstatus;
    private String postertoken;

    public String getPostertoken() {
        return postertoken;
    }

    public void setPostertoken(String postertoken) {
        this.postertoken = postertoken;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    private String fullname;
    private String skills;

    public Work() {
    }

    public Work(String postermail, int posterid, String description, String worktitle, budgettypes budgettype,String budget,String fullname,String skills) {
        this.postermail = postermail;
        this.posterid = posterid;
        this.description = description;
        this.worktitle = worktitle;
        this.budget = budget;
        this.skills=skills;
        this.fullname=fullname;
        this.budgettype = budgettype;
    }


    public budgettypes getBudgettype() {
        return budgettype;
    }

    public void setBudgettype(budgettypes budgettype) {
        this.budgettype = budgettype;
    }

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }

    public String getPostermail() {
        return postermail;
    }

    public void setPostermail(String postermail) {
        this.postermail = postermail;
    }

    public int getPosterid() {
        return posterid;
    }

    public void setPosterid(int posterid) {
        this.posterid = posterid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorktitle() {
        return worktitle;
    }

    public void setWorktitle(String worktitle) {
        this.worktitle = worktitle;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
