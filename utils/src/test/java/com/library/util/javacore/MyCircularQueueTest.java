package com.library.util.javacore;

import com.library.utils.javacore.MyCircularQueue;
import org.junit.Assert;
import org.junit.Test;

/**
 * 循环队列测试类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2021-08-19
 */
public class MyCircularQueueTest {
    @Test
    public void test1() {
        MyCircularQueue queue = new MyCircularQueue(5);
        Assert.assertTrue(queue.enQueue(1));
        Assert.assertTrue(queue.enQueue(2));
        Assert.assertTrue(queue.enQueue(3));
        Assert.assertTrue(queue.enQueue(4));
        Assert.assertTrue(queue.enQueue(5));
        Assert.assertFalse(queue.enQueue(6));

        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertTrue(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertTrue(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertTrue(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertTrue(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertTrue(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
        Assert.assertFalse(queue.deQueue());
        System.out.println(queue.Rear()+" - "+queue.Front());
    }
}