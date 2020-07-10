package com.hamzabelkhiria.miniprojectfreelance.Models;

public class Skill {
    private int id;
    private String skill_description;
    private int userid;

    public Skill() {
    }

    public Skill(String skill_description) {
        this.skill_description = skill_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill_description() {
        return skill_description;
    }

    public void setSkill_description(String skill_description) {
        this.skill_description = skill_description;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
