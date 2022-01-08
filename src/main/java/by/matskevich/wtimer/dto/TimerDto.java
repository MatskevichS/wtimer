package by.matskevich.wtimer.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class TimerDto implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private LocalTime time;
    private Integer days;
    private boolean isStart;
    private boolean isPause;

    public TimerDto(LocalTime time, Integer days, boolean isStart, boolean isPause) {
        this.time = time;
        this.days = days;
        this.isStart = isStart;
        this.isPause = isPause;
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

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }
}
