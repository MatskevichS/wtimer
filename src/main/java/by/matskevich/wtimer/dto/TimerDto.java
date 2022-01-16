package by.matskevich.wtimer.dto;

import by.matskevich.wtimer.domain.Timer.Status;

import java.io.Serializable;
import java.time.LocalTime;

public class TimerDto implements Serializable {

    private static final long serialVersionUID = 1000001L;

    private LocalTime time;
    private Integer days;
    private Status status;

    public TimerDto(LocalTime time, Integer days, Status status) {
        this.time = time;
        this.days = days;
        this.status = status;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
