package io.amosbake.learnrecyclerview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 16:51
 */
public class DataManager {
    public static List<String> generatrStrDatas(final String source, final int page) {
        return new ArrayList<String>() {
            {
                for (int i = 0; i < 30; i++) {
                    add("来源: " + source + " 页数: " + page + " 名称: " + "item" + i);
                }
            }
        };
    }
}
