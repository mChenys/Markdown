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
        Matcher matcher = Pattern.compile("^\\s{0,3}>\\s(.*)").matcher("> 11   > 22");
        while (matcher.find()) {
            int start = matcher.start(1);//2
            int end = matcher.end(1);//11
            String group = matcher.group(1); // 11   > 22
            System.out.println("start:" + start + ",end:" + end + ",group:" + group);
        }
    }

    @Test
    public void testMatch3() {
        int count = findCount("> > > 555", 1);//count:3
        System.out.println("count:" + count);//3
    }

    @Test
    public void testMatch4() {
        String source = "> > > 3    > 4";
        source = source.replaceFirst("^\\s{0,3}(>\\s+){3}", "");
        System.out.println("source:" + source);
    }

    public int findCount(String line, int group) {
        System.out.println("findCount====>line:" + line);
        if (line == null) {
            return 0;
        }
        // 构建一个Matcher
        Matcher matcher = Pattern.compile("^\\s{0,3}>\\s(.*)").matcher(line);
        if (matcher.find()) {
            return findCount(matcher.group(group), group) + 1;
        }
        return 0;
    }

}