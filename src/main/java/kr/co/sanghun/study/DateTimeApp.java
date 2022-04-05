package kr.co.sanghun.study;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeApp {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        long time = date.getTime();

        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);

        // Month가 0부터 시작하기 때문에 주의해야한다.
        Calendar sanghunBirthDay = new GregorianCalendar(1984, 9, 21);
        Calendar sanghunBirthDay2 = new GregorianCalendar(1984, Calendar.SEPTEMBER, 21);

        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        // Server Zone 설정 기준
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthDay = LocalDateTime.of(1984, Month.SEPTEMBER, 21, 0, 0, 0);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate thisYearBirthday = LocalDate.of(2022, Month.SEPTEMBER, 21);
        System.out.println(thisYearBirthday);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now2, plus);
        System.out.println(between.getSeconds());

        LocalDateTime now1 = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now1.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("09/21/1984", MMddyyyy);
        System.out.println(parse);

        Date date1 = new Date();
        Instant instant1 = date1.toInstant();
        Date newDate = Date.from(instant1);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZoneId zoneId1 = ZoneId.systemDefault();
        System.out.println(zoneId1);
        ZonedDateTime from = gregorianCalendar.toInstant().atZone(zoneId1);
        GregorianCalendar from1 = GregorianCalendar.from(from);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

        LocalDateTime now3 = LocalDateTime.now();
        LocalDateTime plus3 = now.plus(10, ChronoUnit.DAYS);

    }
}
