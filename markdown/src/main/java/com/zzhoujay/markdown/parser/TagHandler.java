package com.zzhoujay.markdown.parser;

/**
 * Created by zhou on 16-7-10.
 * TagHandler
 */
public interface TagHandler extends TagFinder, QueueConsumer, TagGetter {
    /**
     * 目标行是否存在h1~h6标题
     *
     * @param line
     * @return
     */
    boolean h(Line line);

    /**
     * 目标行是否存在h1标题
     *
     * @param line
     * @return
     */
    boolean h1(Line line);

    /**
     * 目标行是否存在h2标题
     *
     * @param line
     * @return
     */
    boolean h2(Line line);

    /**
     * 目标行是否存在h3标题
     *
     * @param line
     * @return
     */
    boolean h3(Line line);

    /**
     * 目标行是否存在h4标题
     *
     * @param line
     * @return
     */
    boolean h4(Line line);

    /**
     * 目标行是否存在h5标题
     *
     * @param line
     * @return
     */
    boolean h5(Line line);

    /**
     * 目标行是否存在h6标题
     *
     * @param line
     * @return
     */
    boolean h6(Line line);

    /**
     * 目标行是否存在引用
     *
     * @param line
     * @return
     */
    boolean quota(Line line);

    /**
     * 目标行是否存在无序列表
     *
     * @param line
     * @return
     */
    boolean ul(Line line);

    /**
     * 目标行是否存在有序列表
     *
     * @param line
     * @return
     */
    boolean ol(Line line);

    boolean gap(Line line);

    boolean em(Line line);

    /**
     * 目标行是否存在斜体
     *
     * @param line
     * @return
     */
    boolean italic(Line line);

    boolean emItalic(Line line);

    /**
     * 目标行是否存在代码
     *
     * @param line
     * @return
     */
    boolean code(Line line);

    boolean email(Line line);

    /**
     * 目标行是否存在删除线
     *
     * @param line
     * @return
     */
    boolean delete(Line line);


    boolean autoLink(Line line);

    boolean link(Line line);

    boolean link2(Line line);

    boolean linkId(String line);

    boolean image(Line line);

    boolean image2(Line line);

    boolean imageId(String line);

    /**
     * 是否有tag存在目标行
     * @param line
     * @return
     */
    boolean inline(Line line);

    boolean codeBlock1(Line line);

    boolean codeBlock2(Line line);

}
