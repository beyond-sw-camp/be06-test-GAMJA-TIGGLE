package org.example.tiggle.reservation.DTO;

import java.util.Date;

public class EmptySeatResponse {
    private Integer seatNumber;
    private String section;
    private Date date;
    private String round;
    private String programName;
    private Boolean state;

    public EmptySeatResponse(Integer seatNumber, String section, Date date, String round, String programName, Boolean state) {
        this.seatNumber = seatNumber;
        this.section = section;
        this.date = date;
        this.round = round;
        this.programName = programName;
        this.state = state;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EmptySeatResponse{" +
                "seatNumber=" + seatNumber +
                ", section='" + section + '\'' +
                ", date=" + date +
                ", round='" + round + '\'' +
                ", programName='" + programName + '\'' +
                ", state=" + state +
                '}';
    }
}
