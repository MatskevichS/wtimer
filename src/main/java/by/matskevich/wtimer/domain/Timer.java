package by.matskevich.wtimer.domain;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.util.LinkedList;

import static by.matskevich.wtimer.domain.Timer.Status.NONE;
import static by.matskevich.wtimer.service.TimerService.savePastTime;

public class Timer {

    private final Integer SAVE_RANGE_MINUTES = 5;

    private Label timeField;
    private PastTime pastTime;
    private Status status;
    private LinkedList<PastTime> timeStamps;

    {
        pastTime = new PastTime();
        status = NONE;
        timeStamps = new LinkedList<>();
    }

    public Timer() {
    }

    public Timer(PastTime pastTime, Status status, LinkedList<PastTime> timeStamps) {
        this.pastTime = pastTime;
        this.status = status;
        this.timeStamps = timeStamps;
    }

    public enum Status {
        NONE,
        START,
        PAUSE
    }

    public PastTime getPastTime() {
        return pastTime;
    }

    public void setPastTime(PastTime pastTime) {
        this.pastTime = pastTime;
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

    public LinkedList<PastTime> getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(LinkedList<PastTime> timeStamps) {
        this.timeStamps = timeStamps;
    }

    public void tick() {
        pastTime.tick();
        LocalTime time = pastTime.getTime();
        int minutes = time.getMinute();
        if (minutes > 0 && time.getSecond() == 0 && minutes % SAVE_RANGE_MINUTES == 0) {
            savePastTime(this);
        }

        Platform.runLater(() -> timeField.setText(pastTime.toString()));
    }

    public void dropping() {
        pastTime = new PastTime();
        status = NONE;
        savePastTime(this);
        timeField.setText(pastTime.toString());
    }
}
