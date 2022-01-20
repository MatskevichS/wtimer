package by.matskevich.wtimer.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PastTime implements Cloneable {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);
    private final Integer TICK_AMOUNT = 100_000_000;

    private LocalTime time;
    private Integer days;

    {
        time = LocalTime.of(0, 0, 0);
        days = 0;
    }

    public PastTime() {
    }

    public PastTime(LocalTime time, Integer days) {
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

    public void tick() {
        time = time.plusNanos(TICK_AMOUNT);

        if (time.equals(MAX_TIME)) {
            days++;
        }
    }

    @Override
    public String toString() {
        if (days > 0) {
            return days + " д., " + time.format(TIME_FORMAT);
        }
        return time.format(TIME_FORMAT);
    }

    @Override
    public PastTime clone() throws CloneNotSupportedException {
        return (PastTime) super.clone();
    }
}
