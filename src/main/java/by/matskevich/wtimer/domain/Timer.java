package by.matskevich.wtimer.domain;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static by.matskevich.wtimer.service.TimerService.savePastTime;


public class Timer extends AnimationTimer {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);
    private final Integer ONE = 1;

    private Label timeField;
    private long lastTime;
    private LocalTime time;
    private Integer days;
    private boolean isStart;
    private boolean isPause;

    {
        days = 0;
        time = LocalTime.of(0, 0, 0);
    }

    public Timer() {
    }

    public Timer(LocalTime time, Integer days, boolean isStart, boolean isPause) {
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

    public void setTimeField(Label timeField) {
        this.timeField = timeField;
    }

    @Override
    public void handle(long now) {
        if (lastTime != 0) {
            if (now > lastTime + 1_000_000_000) {
                tick();
                timeField.setText(getPastTime());
                lastTime = now;
            }
        } else {
            lastTime = now;
        }
    }

    private void tick() {
        time = time.plusSeconds(ONE);

        if (time.equals(MAX_TIME)) {
            days++;
        }

        int minutes = time.getMinute();
        if (minutes > 0 && time.getSecond() == 0 && minutes % 10 == 0) {
            savePastTime(this);
        }
    }

    public String getPastTime() {
        if (days > 0) {
            return days + " д., " + time.format(TIME_FORMAT);
        }
        return time.format(TIME_FORMAT);
    }

    public void dropping() {
        this.stop();
        time = LocalTime.of(0, 0, 0);
        isStart = false;
        isPause = false;
        savePastTime(this);
        timeField.setText(getPastTime());
    }
}
