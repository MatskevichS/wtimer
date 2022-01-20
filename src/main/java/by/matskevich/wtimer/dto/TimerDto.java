package by.matskevich.wtimer.dto;

import by.matskevich.wtimer.domain.Timer.Status;

import java.io.Serializable;
import java.util.LinkedList;

public class TimerDto implements Serializable {

    private static final long serialVersionUID = 1000002L;

    private PastTimeDto pastTime;
    private Status status;
    private LinkedList<PastTimeDto> timeStamps;

    public TimerDto(PastTimeDto pastTime, Status status, LinkedList<PastTimeDto> timeStamps) {
        this.pastTime = pastTime;
        this.status = status;
        this.timeStamps = timeStamps;
    }

    public PastTimeDto getPastTime() {
        return pastTime;
    }

    public void setPastTime(PastTimeDto pastTime) {
        this.pastTime = pastTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LinkedList<PastTimeDto> getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(LinkedList<PastTimeDto> timeStamps) {
        this.timeStamps = timeStamps;
    }
}
