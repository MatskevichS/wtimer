package by.matskevich.wtimer.controller;

import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.service.TimerService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import static by.matskevich.wtimer.domain.Timer.Status.PAUSE;
import static by.matskevich.wtimer.service.TimerService.readPastTime;

public class TimerController {

    private StringBuilder ACTION_BTN_STYLE;

    @FXML
    private Label timeLabel;
    @FXML
    private Button timerActionBtn;
    @FXML
    private ListView<String> timeStamps;

    public Timer timer;
    TimerService timerService = new TimerService();

    public void cancel() {
        timerService.cancelTick();
    }

    @FXML
    protected void initialize() {
        ACTION_BTN_STYLE = new StringBuilder("-fx-background-insets: 0; -fx-background-radius: 20;" +
                                             " -fx-border-color: GRAY; -fx-border-width: 1.5; -fx-border-radius: 20;");

        timer = readPastTime();
        if (PAUSE.equals(timer.getStatus())) {
           setResumeBtn();
        } else {
            setStartBtn();
        }
        timer.setTimeField(timeLabel);
        timeLabel.setText(timer.getPastTime().toString());
        timeStamps.getItems().setAll(timerService.convertTimeStampsToStringList(timer.getTimeStamps()));
    }

    @FXML
    protected void onTimerActionBtnClick() {
        switch (timer.getStatus()) {
            case NONE: timerService.startTimer(timer);
                setPauseBtn();
                break;
            case START: timerService.pauseTimer(timer);
                setResumeBtn();
                saveTimeStamp();
                break;
            case PAUSE: timerService.resumeTimer(timer);
                setPauseBtn();
                break;
        }
    }

    @FXML
    protected void onDroppingBtnClick() {
        timerService.cancelTick();
        timer.dropping();
        setStartBtn();
        timeStamps.getItems().clear();
        timer.getTimeStamps().clear();
    }

    @FXML
    protected void onMarkBtnClick() {

    }

    private void saveTimeStamp() {
        try {
            timer.getTimeStamps().addFirst(timer.getPastTime().clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        timeStamps.getItems().setAll(timerService.convertTimeStampsToStringList(timer.getTimeStamps()));
    }

    private void setStartBtn() {
        timerActionBtn.setText("Начать");
        timerActionBtn.setStyle(getActionBtnStyle("LIGHTGREEN"));
    }

    private void setResumeBtn() {
        timerActionBtn.setText("Возобновить");
        timerActionBtn.setStyle(getActionBtnStyle("LIGHTGRAY"));
    }

    private void setPauseBtn() {
        timerActionBtn.setText("Приостановить");
        timerActionBtn.setStyle(getActionBtnStyle("WHITESMOKE"));
    }

    private String getActionBtnStyle(String backgroundColor) {
        return ACTION_BTN_STYLE.append(" -fx-background-color: ")
                               .append(backgroundColor)
                               .append(";")
                               .toString();
    }
}