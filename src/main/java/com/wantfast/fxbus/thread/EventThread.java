package com.wantfast.fxbus.thread;

import io.reactivex.Scheduler;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Executor;

public enum EventThread {
    /**
     * {@link Scheduler} which will execute actions on the JavaFx UI thread.
     */
    FX_THREAD,

    /**
     * Creates and returns a {@link Scheduler} that creates a new {@link Thread} for each unit of work.
     * <p/>
     * Unhandled errors will be delivered to the scheduler Thread's {@link Thread.UncaughtExceptionHandler}.
     */
    NEW_THREAD,

    /**
     * Creates and returns a {@link Scheduler} intended for IO-bound work.
     * <p/>
     * The implementation is backed by an {@link Executor} thread-pool that will grow as needed.
     * <p/>
     * This can be used for asynchronously performing blocking IO.
     * <p/>
     * Do not perform computational work on this scheduler. Use computation() instead.
     * <p/>
     * Unhandled errors will be delivered to the scheduler Thread's {@link Thread.UncaughtExceptionHandler}.
     */
    IO,

    /**
     * Creates and returns a {@link Scheduler} intended for computational work.
     * <p/>
     * This can be used for event-loops, processing callbacks and other computational work.
     * <p/>
     * Do not perform IO-bound work on this scheduler. Use io() instead.
     * <p/>
     * Unhandled errors will be delivered to the scheduler Thread's {@link Thread.UncaughtExceptionHandler}.
     */
    COMPUTATION,

    /**
     * Creates and returns a {@link Scheduler} that queues work on the current thread to be executed after the
     * current work completes.
     */
    TRAMPOLINE,

    /**
     * Converts an {@link Executor} into a new Scheduler instance.
     */
    EXECUTOR;

    public static Scheduler getScheduler(EventThread thread) {
        Scheduler scheduler;
        switch (thread) {
            case FX_THREAD:
                scheduler = JavaFxScheduler.platform();
                break;
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case TRAMPOLINE:
                scheduler = Schedulers.trampoline();
                break;
            case EXECUTOR:
                scheduler = Schedulers.from(ThreadHandler.DEFAULT.getExecutor());
                break;
            default:
                scheduler = Schedulers.newThread();
                break;
        }
        return scheduler;
    }
}
