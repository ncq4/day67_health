package com.itheima.pojo;

import org.springframework.context.annotation.Primary;

import java.io.Serializable;

public class Illness implements Serializable{
    private Integer id;
    private String code;
    private String disease_name;
    private String clinic_department;
    private String classroom;
    private String warning_level;
    private String heal_deadline;
    private String attention;

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClinic_department() {
        return clinic_department;
    }

    public void setClinic_department(String clinic_department) {
        this.clinic_department = clinic_department;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getWarning_level() {
        return warning_level;
    }

    public void setWarning_level(String warning_level) {
        this.warning_level = warning_level;
    }

    public String getHeal_deadline() {
        return heal_deadline;
    }

    public void setHeal_deadline(String heal_deadline) {
        this.heal_deadline = heal_deadline;
    }
}
