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

    @Test
    public void testMatch5() {
        // \\5:表示引用第五组, ['\"]:表示可以选'或者"
//       Matcher matcher = Pattern.compile(".*?(\\[\\s*(.*?)\\s*]\\(\\s*(\\S*?)(\\s+(['\"])(.*?)\\5)?\\s*?\\))").matcher("[添加链接描述](abc 'hello')");
//       Matcher matcher = Pattern.compile(".*?(!\\[\\s*(.*?)\\s*]\\(\\s*(\\S*?)(\\s+(['\"])(.*?)\\5)?\\s*?\\))").matcher("![添加链接描述](abc 'hello')");
//       Matcher matcher = Pattern.compile(".*?(\\[\\s*(.*?)\\s*]\\s*\\[\\s*(.*?)\\s*])").matcher("[添加链接描述][hello]");
//       Matcher matcher = Pattern.compile("^\\s*\\[\\s*(.*?)\\s*]:\\s*(\\S+?)(\\s+(['\"])(.*?)\\4)?\\s*$").matcher("[添加链接描述]: a 'b'");
//       Matcher matcher = Pattern.compile(".*?(!\\[\\s*(.*?)\\s*]\\s*\\[\\s*(.*?)\\s*])").matcher("![添加链接描述][hello]");
       Matcher matcher = Pattern.compile("^\\s*!\\[\\s*(.*?)\\s*]:\\s*(\\S+?)(\\s+(['\"])(.*?)\\4)?\\s*$").matcher("![添加链接描述]:a 'b");
        if (matcher.find()) {
            String title0 = matcher.group(0); // 第0组括号的内容,即完整的正则表达式
            String title1 = matcher.group(1);// 第1组括号的内容,对应正则的(\\[\\s*(.*?)\\s*]\(\\s*(\\S*?)(\\s+(['"])(.*?)\5)?\\s*?\\))
            String title = matcher.group(2);// 第2组括号内的内容,即[]内的描述,对应正则的(.*?)
            String link = matcher.group(3); // 第3组括号内的内容,即链接,接受任意非空字符,即abc,对应正则的(\\S*?)
            String group5 = matcher.group(5);
            if (matcher.groupCount() > 6) {
                String hint = matcher.group(6); // 第6组括号的内容,即hello
                System.out.println("title2:"+title+",link:"+link+",hint:"+hint+",title1:"+title1+",title0:"+title0);
            }else{
                System.out.println("title2:"+title+",link:"+link+",title1:"+title1+",title0:"+title0);
            }
            System.out.println("group5:"+group5);

        }
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


    @Test
    public void test6() {
        Matcher matcher = Pattern.compile("[^*_]*(([*_])\\2\\2([^*_].*?)\\2\\2\\2)").matcher("***a***");
        if (matcher.find()) {
            System.out.println("title:"+matcher.group());
        }

    }
}