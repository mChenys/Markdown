package com.zzhoujay.markdowndemo;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMatch() {
        Matcher matcher = Pattern.compile("[^*_]*(([*_])([^*_].*?)\\2)").matcher("*斜体样式*");
        while (matcher.find()) {
            int start = matcher.start(); // 0
            int end = matcher.end(); // 6
            String group = matcher.group(); // *斜体样式*
            System.out.println("start:" + start + ",end:" + end + ",group:" + group);
        }
    }

    @Test
    public void testMatch2() {
        Matcher matcher = Pattern.compile("[^*_]*(([*_])([^*_].*?)\\2)").matcher("*_斜__体样式");
        while (matcher.find()) {
            int start = matcher.start(1); // 0
            int end = matcher.end(1); // 4
            String group = matcher.group(); // _斜_
            System.out.println("start:" + start + ",end:" + end + ",group:" + group);
        }
    }
}