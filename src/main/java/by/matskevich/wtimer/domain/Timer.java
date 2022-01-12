package by.matskevich.wtimer.domain;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static by.matskevich.wtimer.service.TimerService.savePastTime;


public class Timer extends Thread {

    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final LocalTime MAX_TIME = LocalTime.of(23, 59, 59);
    private final Integer ONE = 1;

    private Label timeField;
    private LocalTime time;
    private Integer days;
    private boolean isStart;
    private boolean isCancel;
    private boolean isPause;

    {
        days = 0;
        time = LocalTime.of(0, 0, 0);
        isCancel = false;
    }

    public Timer() {
    }

    public Timer(LocalTime time, Integer days, boolean isStart, boolean isPause) {
        this.time = time;
        this.days = days;
        this.isStart = isStart;
        this.isPause = isPause;
    }

    @Override
    public void run() {
        try {
            while (!isCancel) {
                Thread.sleep(1000);
                if (isStart && !isPause) {
                    tick();
                }
                Platform.runLater(() -> timeField.setText(getPastTime()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    public void cancel() {
        isCancel = true;
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
        time = LocalTime.of(0, 0, 0);
        isStart = false;
        isPause = false;
        savePastTime(this);
        timeField.setText(getPastTime());
    }
}
