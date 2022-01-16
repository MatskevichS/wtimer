package by.matskevich.wtimer.domain;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static by.matskevich.wtimer.domain.Timer.Status.NONE;
import static by.matskevich.wtimer.service.TimerService.savePastTime;


public class Timer {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);
    private final Integer TICK_AMOUNT = 100_000_000;
    private final Integer SAVE_RANGE_MINUTES = 10;

    private Label timeField;
    private LocalTime time;
    private Integer days;
    private Status status;

    {
        days = 0;
        time = LocalTime.of(0, 0, 0);
        status = NONE;
    }

    public Timer() {
    }

    public Timer(LocalTime time, Integer days, Status status) {
        this.time = time;
        this.days = days;
        this.status = status;
    }

    public enum Status {
        NONE,
        START,
        PAUSE
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

    public void setTimeField(Label timeField) {
        this.timeField = timeField;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void tick() {
        time = time.plusNanos(TICK_AMOUNT);

        if (time.equals(MAX_TIME)) {
            days++;
        }

        int minutes = time.getMinute();
        if (minutes > 0 && time.getSecond() == 0 && minutes % SAVE_RANGE_MINUTES == 0) {
            savePastTime(this);
        }

        Platform.runLater(() -> timeField.setText(getPastTime()));
    }

    public String getPastTime() {
        if (days > 0) {
            return days + " д., " + time.format(TIME_FORMAT);
        }
        return time.format(TIME_FORMAT);
    }

    public void dropping() {
        time = LocalTime.of(0, 0, 0);
        status = NONE;
        savePastTime(this);
        timeField.setText(getPastTime());
    }
}
