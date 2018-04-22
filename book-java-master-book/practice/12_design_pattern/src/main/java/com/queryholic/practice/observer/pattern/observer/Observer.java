package com.queryholic.practice.observer.pattern.observer;

public interface Observer {
    /**
     * 감시한다.
     * @param subject
     */
    void update(Subject subject);
}
