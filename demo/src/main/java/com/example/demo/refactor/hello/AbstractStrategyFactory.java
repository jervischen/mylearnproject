package com.example.demo.refactor.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created in 2018-11-04 15:23.
 *
 * @author chenxiao
 */
public abstract class AbstractStrategyFactory {
    public abstract MessageStrategy createStrategy(MessagetBody mb);
}
