package com.nokard.chat.utils;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public class DateUtils {

    public static LocalDateTime fromUnix(long time) {
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Long toUnix(LocalDateTime time) {
        if(time == null) return null;
        return time.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
    public static Long toUnix(Timestamp time) {
        if(time == null) return null;
        return time.toLocalDateTime().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static long nowUnix() {
        return toUnix(LocalDateTime.now());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(TimeZone.getDefault().toZoneId())
                .toLocalDateTime();
    }
}
