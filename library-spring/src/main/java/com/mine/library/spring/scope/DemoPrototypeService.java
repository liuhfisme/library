package com.mine.library.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by liuff on 2017/3/27.
 * <p>多例模式</p>
 */
@Service
@Scope("prototype") //声明Scope为Prototype
public class DemoPrototypeService {
}
