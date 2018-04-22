package com.queryholic.practice.observer.parents;

import com.queryholic.practice.observer.enums.Feeling;
import com.queryholic.practice.observer.pattern.observer.Subject;
import lombok.Getter;

public class Mom extends Subject {
    @Getter
    private Daddy husband;

    public Mom(Daddy husband) {
        this.husband = husband;
    }

    @Override
    public void execute() {
        if (husband.getFeeling().equals(Feeling.BAD)) {
            System.out.println("[엄마] 아빠 기분이 안좋구나ㅠㅠ");
        } else {
            System.out.println("[엄마] 아빠 기분이 좋구나!!");
        }

        notifyObservers();
    }
}
