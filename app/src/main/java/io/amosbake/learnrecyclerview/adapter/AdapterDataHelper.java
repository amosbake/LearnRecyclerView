package io.amosbake.learnrecyclerview.adapter;

import java.util.Collection;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 16:39
 */
public interface AdapterDataHelper<T> {
    void addData(T data);

    void setDatas(Collection<T> data);

    void addDatas(Collection<T> data);
}
