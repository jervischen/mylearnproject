package com.example.demo.guava;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * @author Chen Xiao
 * @since 2020-03-17 11:48
 */
public class OptionalLearn {

    @Test
    public void optionalTest(){
        Optional<String> optional = Optional.fromNullable(null);
        System.out.println(optional.orNull());
        System.out.println(optional.get());
    }
}
