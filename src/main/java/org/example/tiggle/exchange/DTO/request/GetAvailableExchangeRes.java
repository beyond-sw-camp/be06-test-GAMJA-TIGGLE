package org.example.tiggle.exchange.DTO.request;

public class GetAvailableExchangeRes {
    private Integer seatId;
    private Integer state;

    public GetAvailableExchangeRes(Integer seatId, Integer state) {
        this.seatId = seatId;
        this.state = state;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
