package org.example.tiggle.exchange.DTO.request;

public class PostExchangeReq {
    Integer programId;
    Integer times;
    String section;
    Integer seatId;

    public PostExchangeReq(Integer programId, Integer times, String section, Integer seatId) {
        this.programId = programId;
        this.times = times;
        this.section = section;
        this.seatId = seatId;
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

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }
}
