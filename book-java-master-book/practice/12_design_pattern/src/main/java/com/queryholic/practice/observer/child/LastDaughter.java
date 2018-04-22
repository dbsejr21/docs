package com.queryholic.practice.observer.child;

import com.queryholic.practice.observer.enums.Feeling;
import com.queryholic.practice.observer.parents.Mom;
import com.queryholic.practice.observer.pattern.observer.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LastDaughter extends Child {

    public LastDaughter(String name) {
        super(name);
    }

    @Override
    public void update(Subject subject) {
        Mom mom = (Mom) subject;
        Feeling daddyFeeling = mom.getHusband().getFeeling();

        if (daddyFeeling.equals(Feeling.BAD)) {
            System.out.println("[" + this.name + "]" + " 설거지 한다.");
        } else {
            super.update(subject);
        }

    }
}
