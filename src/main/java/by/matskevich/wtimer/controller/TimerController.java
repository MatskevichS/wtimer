package by.matskevich.wtimer.controller;

import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.service.TimerService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static by.matskevich.wtimer.domain.Timer.Status.START;
import static by.matskevich.wtimer.service.TimerService.readPastTime;

public class TimerController {

    @FXML
    private Label timeLabel;
    @FXML
    private Button timerActionBtn;

    public Timer timer;
    TimerService timerService = new TimerService();

    public void cancel() {
        timerService.stopTick();
    }

    @FXML
    protected void initialize() {
        timer = readPastTime();
        if (START.equals(timer.getStatus())) {
           setResumeBtn();
        } else {
            setStartBtn();
        }
        timer.setTimeField(timeLabel);
        timeLabel.setText(timer.getPastTime());

        timerActionBtn.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1.5))));
    }

    @FXML
    protected void onTimerActionBtnClick() {
        switch (timer.getStatus()) {
            case NONE: timerService.startTimer(timer);
                setPauseBtn();
                break;
            case START: timerService.pauseTimer(timer);
                setResumeBtn();
                break;
            case PAUSE: timerService.resumeTimer(timer);
                setPauseBtn();
                break;
        }
    }

    @FXML
    protected void onDroppingBtnClick() {
        timerService.stopTick();
        timer.dropping();
        setStartBtn();
    }

    @FXML
    protected void onMarkBtnClick() {

    }

    private void setStartBtn() {
        timerActionBtn.setText("Начать");
        timerActionBtn.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(20), new Insets(0))));
    }

    private void setResumeBtn() {
        timerActionBtn.setText("Возобновить");
        timerActionBtn.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(20), new Insets(0))));
    }

    private void setPauseBtn() {
        timerActionBtn.setText("Приостановить");
        timerActionBtn.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(20), new Insets(0))));
    }
}