package cn.droidlover.xdroidmvp.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cn.droidlover.xdroidmvp.log.XLog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/3 下午5:27
 */
public class RetryFunction implements Function<Observable<Throwable>, ObservableSource<?>> {
    private int retryTimes;
    private int retryInterval;

    /**
     * @param retryTimes    重连次数
     * @param retryInterval 重连间隔
     */
    public RetryFunction(int retryTimes, int retryInterval) {
        this.retryInterval = retryInterval;
        this.retryTimes = retryTimes;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                if (!(throwable instanceof ConnectException
                        || throwable instanceof SocketTimeoutException
                        || throwable instanceof TimeoutException)) {

                    return Observable.error(throwable);
                }

                return Observable
                        .just(throwable)
                        .zipWith(Observable.range(1, retryTimes), new BiFunction<Throwable, Integer, Object>() {
                            @Override
                            public Integer apply(Throwable throwable, Integer integer) {
                                return integer;
                            }
                        })
                        .flatMap(new Function<Object, Observable<? extends Long>>() {
                            @Override
                            public Observable<? extends Long> apply(Object integer) {
                                XLog.e("get error, it will try after " + retryInterval
                                        + " millisecond, retry count " + integer);
                                return Observable.timer(retryInterval, TimeUnit.SECONDS);
                            }
                        });
            }
        });

    }
}