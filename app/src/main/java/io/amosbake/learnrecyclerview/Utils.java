package io.amosbake.learnrecyclerview;

import java.util.Collection;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 16:31
 */
public class Utils {

    /**
     * 判断集合是否为空
     *
     * @param list
     * @return 是否为空
     */
    public static boolean isCollectionNullOrEmpty(Collection list) {
        return list == null && list.isEmpty();
    }
}
