package by.matskevich.wtimer.controller;

import by.matskevich.wtimer.domain.Timer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static by.matskevich.wtimer.service.TimerService.readPastTime;
import static by.matskevich.wtimer.service.TimerService.savePastTime;

public class TimerController {

    @FXML
    private Label timeLabel;
    @FXML
    private Button timerActionBtn;
    private Timer timer;

    public void cancel() {
        timer.cancel();
    }

    @FXML
    protected void initialize() {
        timer = readPastTime();
        if (timer.isStart()) {
           setResumeBtn();
        } else {
            setStartBtn();
        }
        timer.setTimeField(timeLabel);
        timer.start();
        timeLabel.setText(timer.getPastTime());

        timerActionBtn.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1.5))));
    }

    @FXML
    protected void onTimerActionBtnClick() {
        if (timer.isStart()) {
            if (timer.isPause()) {
                resumeTimer();
            } else {
                pauseTimer();
            }
        } else {
            startTimer();
        }
    }

    @FXML
    protected void onDroppingBtnClick() {
        timer.dropping();
        setStartBtn();
    }

    @FXML
    protected void onMarkBtnClick() {

    }

    private void startTimer() {
        timer.setStart(true);
        setPauseBtn();
    }

    private void pauseTimer() {
        timer.setPause(true);
        savePastTime(timer);
        setResumeBtn();
    }

    private void resumeTimer() {
        timer.setPause(false);
        setPauseBtn();
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