package by.matskevich.wtimer.mapper;

import by.matskevich.wtimer.domain.PastTime;
import by.matskevich.wtimer.dto.PastTimeDto;

import java.util.LinkedList;

public class PastTimeMapper {

    public static PastTimeDto mapDomainToDto(PastTime pastTime) {
        return new PastTimeDto(pastTime.getTime(), pastTime.getDays());
    }

    public static LinkedList<PastTimeDto> mapDomainToDto(LinkedList<PastTime> timeStamps) {
        LinkedList<PastTimeDto> timeStampsDto = new LinkedList<>();
        for (PastTime pastTime : timeStamps) {
            timeStampsDto.add(mapDomainToDto(pastTime));
        }
        return timeStampsDto;
    }

    public static PastTime mapDtoToDomain(PastTimeDto pastTimeDto) {
        return new PastTime(pastTimeDto.getTime(), pastTimeDto.getDays());
    }

    public static LinkedList<PastTime> mapDtoToDomain(LinkedList<PastTimeDto> timeStampsDto) {
        LinkedList<PastTime> timeStamps = new LinkedList<>();
        for (PastTimeDto pastTimeDto : timeStampsDto) {
            timeStamps.add(mapDtoToDomain(pastTimeDto));
        }
        return timeStamps;
    }
}
