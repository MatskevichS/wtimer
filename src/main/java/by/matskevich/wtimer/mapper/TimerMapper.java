package by.matskevich.wtimer.mapper;

import by.matskevich.wtimer.domain.PastTime;
import by.matskevich.wtimer.domain.Timer;
import by.matskevich.wtimer.dto.PastTimeDto;
import by.matskevich.wtimer.dto.TimerDto;

import java.util.LinkedList;

public class TimerMapper {

    public static TimerDto mapDomainToDto(Timer timer) {
        PastTimeDto pastTimeDto = PastTimeMapper.mapDomainToDto(timer.getPastTime());
        LinkedList<PastTimeDto> timeStampsDto = PastTimeMapper.mapDomainToDto(timer.getTimeStamps());
        return new TimerDto(pastTimeDto, timer.getStatus(), timeStampsDto);
    }

    public static Timer mapDtoToDomain(TimerDto timerDto) {
        PastTime pastTime = PastTimeMapper.mapDtoToDomain(timerDto.getPastTime());
        LinkedList<PastTime> timeStamps = PastTimeMapper.mapDtoToDomain(timerDto.getTimeStamps());
        return new Timer(pastTime, timerDto.getStatus(), timeStamps);
    }
}
