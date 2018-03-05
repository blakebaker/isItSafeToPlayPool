package com.pool.pool;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@RestController
public class TrueController {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(TrueController.class);

    @GetMapping("/")
    private String returnTrue() {
        return "Yes go play pool!!";
    }

    @GetMapping("/now")
    private String isItSafeNow() throws Exception {
        LocalDate today = LocalDate.now(ZoneId.of("America/Los_Angeles"));
        LocalTime now = LocalTime.now(ZoneId.of("America/Los_Angeles"));
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return checkSafety(dayOfWeek, now);
    }

    @GetMapping("/date/{date}")
    private String isItSafeAtDate(@PathVariable String date) {
        log.info("the date entered is {}", date);
        try {
            LocalDateTime theDateTime = LocalDateTime.parse(date);
            LocalDate theDate = theDateTime.toLocalDate();
            LocalTime theTime = theDateTime.toLocalTime();
            DayOfWeek dayOfWeek = theDate.getDayOfWeek();
            return checkSafety(dayOfWeek, theTime);
        } catch (Exception e) {
            return ("Yes go play pool!! However, if you need a more accurate safety check, please enter the date in this"
                    + " format - yyyy-mm-ddThh:mm:ss");
        }
    }

    private String checkSafety(DayOfWeek dayOfWeek, LocalTime theTime) throws Exception {
        if (dayOfWeek == null || theTime == null) {
            throw new RuntimeException();
        }
        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return "If you are lame enough to be in office on a " + dayOfWeek + ", Yes go play pool!!";
        }
        if (theTime.isAfter(LocalTime.of(18, 0))) {
            return "It is after 6 PM on a weekday. Yes, DEFINITELY go play pool!!";
        }
        if (theTime.isAfter(LocalTime.NOON) && theTime.isBefore(LocalTime.of(13, 30))) {
            return "It is lunch-time. Yes go play pool!! But you might have to fight the QA team for the table";
        }
        return "Yes, regardless of consequences, go play pool!!";
    }
}
