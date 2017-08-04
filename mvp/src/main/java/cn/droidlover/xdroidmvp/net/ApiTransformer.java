package cn.droidlover.xdroidmvp.net;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/4 下午9:14
 */
public class ApiTransformer<T extends IModel> implements ObservableTransformer<T, T>, FlowableTransformer<T, T> {
    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream.flatMap(new Function<T, Publisher<T>>() {
            @Override
            public Publisher<T> apply(T model) throws Exception {

                if (model == null || model.isNull()) {
                    return Flowable.error(new NetError(model.getErrorMsg(), NetError.NoDataError));
                } else if (model.isAuthError()) {
                    return Flowable.error(new NetError(model.getErrorMsg(), NetError.AuthError));
                } else if (model.isBizError()) {
                    return Flowable.error(new NetError(model.getErrorMsg(), NetError.BusinessError));
                } else {
                    return Flowable.just(model);
                }
            }
        });
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.flatMap(new Function<T, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(T model) throws Exception {

                if (model == null || model.isNull()) {
                    return Observable.error(new NetError(model.getErrorMsg(), NetError.NoDataError));
                } else if (model.isAuthError()) {
                    return Observable.error(new NetError(model.getErrorMsg(), NetError.AuthError));
                } else if (model.isBizError()) {
                    return Observable.error(new NetError(model.getErrorMsg(), NetError.BusinessError));
                } else {
                    return Observable.just(model);
                }
            }
        });
    }
}
