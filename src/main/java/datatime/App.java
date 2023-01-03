package datatime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class App {

    public static void main(String[] args) throws InterruptedException {
//        BeforeDateFunction();
        java8DateHuman();
    }

    public static void BeforeDateFunction() throws InterruptedException {
        Date date = new Date(); //기본적으로 timestamp ==> 기계 관점에서의 시간
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(time);
        System.out.println(after3Seconds); //현재시간으로 바뀐다 => mutable 하다 => multi-thread 환경에서 어렵다다
    }

    public static void java8DateMachine() throws InterruptedException {
        Instant instant = Instant.now();
        System.out.println(instant); //기준시 UTC

        //기계용 시간
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime); //내 시스템 시간
    }

    public static void java8DateHuman() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now(); ///서버시간
        LocalDateTime birthDay =
                LocalDateTime.of(1982,Month.JULY,15,0,0,0);

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        //System.out.println(nowInKorea); //한국시간

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        //System.out.println(zonedDateTime); //한국시간

        //시간 얼마나 남았는지
        LocalDate today = LocalDate.now();
        //순수 월간 날짜를 비교한다
        //10월 16일 - 1월 1일 => 일만 봤을 때 15일차이
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.OCTOBER, 16);
        Period period = Period.between(today, thisYearBirthday);
        //System.out.println(period.getDays());


        Instant nowInstant2 = Instant.now();
        Instant plus = nowInstant2.plus(10,ChronoUnit.SECONDS); //인스턴스를 새로 생성하는 것이다.

        Duration between = Duration.between(nowInstant2, plus);
//        System.out.println(between.getSeconds()); // 10초차이

        //formatting 해보자
        LocalDateTime nowFormat = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        System.out.println(nowFormat.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);
    }

}