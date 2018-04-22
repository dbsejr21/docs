package com.queryholic.practice.observer.child;

import com.queryholic.practice.observer.pattern.observer.Observer;
import com.queryholic.practice.observer.pattern.observer.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Child implements Observer {
    protected String name;

    public Child(String name) {
        this.name = name;
    }

    /**
     * 기본적으로 Child는 놀고 있다.
     *
     * @param subject
     */
    @Override
    public void update(Subject subject) {
        System.out.println("[" + this.name + "]" + " 놀고있당.");
    }
}
