package by.matskevich.wtimer.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class PastTimeDto implements Serializable {

    private static final long serialVersionUID = 1000001L;

    private LocalTime time;
    private Integer days;

    public PastTimeDto(LocalTime time, Integer days) {
        this.time = time;
        this.days = days;
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
}
