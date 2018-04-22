package com.queryholic.practice.observer.child;

import com.queryholic.practice.observer.enums.Feeling;
import com.queryholic.practice.observer.parents.Mom;
import com.queryholic.practice.observer.pattern.observer.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstSon extends Child {
    public FirstSon(String name) {
        super(name);
    }

    @Override
    public void update(Subject subject) {
        Mom mom = (Mom) subject;
        Feeling daddyFeeling = mom.getHusband().getFeeling();

        if (daddyFeeling.equals(Feeling.BAD)) {
            System.out.println("[" + this.name + "]" + " 열심히 공부한다.");
        } else {
            super.update(subject);
        }

    }
}
