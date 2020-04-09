## ReentrantLock 总结构

ReentrantLock实现了 Lock 接口，同时持有Sync的成员变量；值得一提的是其Sync这个抽象静态内部类，
这个类的父类就是鼎鼎大名的AQS（ AbstractQueuedSynchronizer ），在ReentrantLock中，
公平锁和非公平锁也是通过实现Sync这个抽象静态内部类来实现的（ NonfairSync，FairSync）

## 实现的思想

简单说：AbstractQueuedSynchronizer 中的成员变量 state（int）标识锁状态，同时使用一个 FIFO 
的Queue保持等待线程，并使等待线程进入自旋操作？（CLH锁的变体）

## 