package by.matskevich.wtimer.thread;

import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.dto.TimerDto;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveThread extends Thread {

    private final Timer timer;

    public SaveThread(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("auto_save.dat"))) {
            TimerDto timerDto = new TimerDto(timer.getTime(), timer.getDays(), timer.getStatus());
            oos.writeObject(timerDto);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
