package com.jzqh.utils;

import java.util.Collection;

public class CollectionsUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }
}
