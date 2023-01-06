package com.example.java8to11.함수형패키지.함수형인터페이스;

import java.util.function.Function;

//이런식으로 자체적으로 interface를 구현할 수 있다
public class Plus10 implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
