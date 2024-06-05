package org.example.tiggle.exchange.DTO.request;

public class GetAvailableExchangeReq {
    private Integer programId;
    private Integer times;
    private String section;


    @Override
    public String toString() {
        return "GetAvailableExchangeReq{" +
                "programId=" + programId +
                ", times=" + times +
                ", section='" + section + '\'' +
                '}';
    }


    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
