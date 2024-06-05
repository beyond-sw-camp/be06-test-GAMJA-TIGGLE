package org.example.tiggle.reservation.DTO;

public class EmptySeatRequest {
    private Integer programId;
    private Integer timesId;

    public EmptySeatRequest() {
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getTimesId() {
        return timesId;
    }

    public void setTimesId(Integer timesId) {
        this.timesId = timesId;
    }
}
