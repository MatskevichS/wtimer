package by.matskevich.wtimer.service;

import by.matskevich.wtimer.domain.PastTime;
import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.dto.TimerDto;
import by.matskevich.wtimer.mapper.TimerMapper;
import by.matskevich.wtimer.thread.SaveThread;
import by.matskevich.wtimer.thread.TickThread;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static by.matskevich.wtimer.domain.Timer.Status.PAUSE;
import static by.matskevich.wtimer.domain.Timer.Status.START;

public class TimerService {

    private TickThread tickThread;
    ReentrantLock locker = new ReentrantLock();

    public static void savePastTime(Timer timer) {
        new SaveThread(timer).start();
    }

    public static Timer readPastTime() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("auto_save.dat"))) {
            TimerDto timerDto = (TimerDto) ois.readObject();
            return TimerMapper.mapDtoToDomain(timerDto);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return new Timer();
        }
    }

    public void cancelTick() {
        if (tickThread != null) {
            tickThread.cancel();
        }
    }

    public void startTimer(Timer timer) {
        timer.setStatus(START);
        tickThread = new TickThread(timer, locker);
        tickThread.start();
    }

    public void pauseTimer(Timer timer) {
        tickThread.cancel();
        timer.setStatus(PAUSE);
        savePastTime(timer);
    }

    public void resumeTimer(Timer timer) {
        timer.setStatus(START);
        tickThread = new TickThread(timer, locker);
        tickThread.start();
    }

    public List<String> convertTimeStampsToStringList(LinkedList<PastTime> timeStamps) {
        List<String> result = new ArrayList<>();
        for (PastTime pastTime : timeStamps) {
            int elementNumber = timeStamps.size() - timeStamps.indexOf(pastTime);
            result.add(elementNumber + ". " + pastTime);
        }
        return result;
    }
}
