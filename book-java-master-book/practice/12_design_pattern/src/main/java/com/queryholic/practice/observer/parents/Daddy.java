package com.queryholic.practice.observer.parents;

import com.queryholic.practice.observer.enums.Feeling;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Daddy {
    private Feeling feeling = Feeling.GOOD;
}
