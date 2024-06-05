package org.example.tiggle.program.DTO.request;



public class ProgramRegisterReq {
    String programName;
    String programInfo;
    String resvationOpenDate;
    String resvationCloseDate;
    String imgUrl;
    Integer age;
    Integer runtime;
    String sallerInfo;
    String ProgramStartDate;
    String ProgramEndDate;

    //
    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramInfo() {
        return programInfo;
    }

    public void setProgramInfo(String programInfo) {
        this.programInfo = programInfo;
    }

    public String getResvationOpenDate() {
        return resvationOpenDate;
    }

    public void setResvationOpenDate(String resvationOpenDate) {
        this.resvationOpenDate = resvationOpenDate;
    }

    public String getResvationCloseDate() {
        return resvationCloseDate;
    }

    public void setResvationCloseDate(String resvationCloseDate) {
        this.resvationCloseDate = resvationCloseDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getSallerInfo() {
        return sallerInfo;
    }

    public void setSallerInfo(String sallerInfo) {
        this.sallerInfo = sallerInfo;
    }

    public String getProgramStartDate() {
        return ProgramStartDate;
    }

    public void setProgramStartDate(String programStartDate) {
        ProgramStartDate = programStartDate;
    }

    public String getProgramEndDate() {
        return ProgramEndDate;
    }

    public void setProgramEndDate(String programEndDate) {
        ProgramEndDate = programEndDate;
    }
}


