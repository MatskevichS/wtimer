package by.matskevich.wtimer.service;

import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.dto.TimerDto;
import by.matskevich.wtimer.thread.SaveThread;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TimerService {

    public static void savePastTime(Timer timer) {
        new SaveThread(timer).start();
    }

    public static Timer readPastTime() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("auto_save.dat"))) {
            TimerDto timerDto = (TimerDto) ois.readObject();
            return new Timer(timerDto.getTime(), timerDto.getDays(), timerDto.isStart(), timerDto.isPause());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Timer();
        }
    }
}
