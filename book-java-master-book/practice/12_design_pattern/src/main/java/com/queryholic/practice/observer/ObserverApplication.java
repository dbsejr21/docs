package com.queryholic.practice.observer;

import com.queryholic.practice.observer.child.Child;
import com.queryholic.practice.observer.child.FirstSon;
import com.queryholic.practice.observer.child.LastDaughter;
import com.queryholic.practice.observer.child.SecondSon;
import com.queryholic.practice.observer.enums.Feeling;
import com.queryholic.practice.observer.enums.Week;
import com.queryholic.practice.observer.parents.Daddy;
import com.queryholic.practice.observer.parents.Mom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 옵저버 패턴 연습
 * - 아내는 남편이 퇴근하는 모습을 보고, 남편 기분이 좋은지 안좋은지 아이들에게 통지한다.
 * - 아이들은 아빠의 기분이 안좋으면 각자 처신한다.
 * - 큰 아들: 열심히 공부한다.
 * - 둘째 아들: 방 청소를 한다.
 * - 막내 딸: 설거지 한다.
 */
@SpringBootApplication
public class ObserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverApplication.class, args);

        Daddy daddy = new Daddy();
        Mom mom = new Mom(daddy);

        Child firstSon = new FirstSon("첫째 아들");
        Child secondSon = new SecondSon("둘째 아들");
        Child lastDaughter = new LastDaughter("막내 딸");

        mom.addObserver(firstSon);
        mom.addObserver(secondSon);
        mom.addObserver(lastDaughter);

        for (Week week : Week.values()) {
            System.out.println("====" + week.toString() + " Start. =====");
            if (week.equals(Week.THURSDAY)) {
                daddy.setFeeling(Feeling.BAD);
            } else {
                daddy.setFeeling(Feeling.GOOD);
            }

            mom.execute();
            System.out.println("====" + week.toString() + " End. =====");
        }


    }
}
