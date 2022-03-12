package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {
        //注意这里 Escape.this.xxx, 这个this，就是在Escape对象还没有构造完成之时(这是在InnerClass对象的构造方法里)，负责构造的线程就已经看到了this的引用
        //这称为逸出
        //应该使用工厂方法+私有构造方法，来完成对象创建和监听器注册
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
