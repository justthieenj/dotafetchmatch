package io.github.justthieenj.dotafetchmatch.page;

import io.github.justthieenj.arrakeenselenium.utils.ReflectUtils;

public class PageFactory {
    private static Dotabuff dotabuffPage;

    public static Dotabuff dotabuff() {
        return dotabuffPage = ReflectUtils.initObject(dotabuffPage, Dotabuff.class);
    }
}
