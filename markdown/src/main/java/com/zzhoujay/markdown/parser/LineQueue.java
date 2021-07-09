package com.zzhoujay.markdown.parser;

/**
 * Created by zhou on 16-7-2.
 * 封装所有行的队列
 */
public class LineQueue {

    private Line root; // 根节点
    private Line curr; // 当前节点
    private Line last; // 尾节点

    public LineQueue(Line root) {
        this.root = root;
        curr = root;
        last = root;
        while (last.nextLine() != null) {
            last = last.nextLine();
        }
    }

    private LineQueue(LineQueue queue, Line curr) {
        this.root = queue.root;
        this.last = queue.last;
        this.curr = curr;
    }

    /**
     * 获取下一行
     * @return
     */
    public Line nextLine() {
        return curr.nextLine();
    }

    /**
     * 获取上一行
     * @return
     */
    public Line prevLine() {
        return curr.prevLine();
    }

    /**
     * 获取当前行
     * @return
     */
    public Line currLine() {
        return curr;
    }

    /**
     * 将当前行指向下一行
     * @return true成功,false失败
     */
    public boolean next() {
        if (curr.nextLine() == null) {
            return false;
        }
        curr = curr.nextLine();
        return true;
    }

    /**
     * 将当前行指向上一行
     * @return true成功,false失败
     */
    public boolean prev() {
        if (curr.prevLine() == null) {
            return false;
        }
        curr = currLine().prevLine();
        return true;
    }

    /**
     * 判断是否是最后一行
     * @return
     */
    public boolean end() {
        return curr.nextLine() == null;
    }

    /**
     * 判断是否是第一行
     * @return
     */
    public boolean start() {
        return curr == root;
    }

    /**
     * 在队列末尾添加新行
     * @param line
     */
    public void append(Line line) {
        last.add(line);
        last = line;
    }

    /**
     * 在当前行位置添加一行
     * @param line
     */
    public void insert(Line line) {
        if (curr == last) {
            append(line);
        } else {
            curr.addNext(line);
        }
    }

    /**
     * 移除当前行,并返回,然后更新当前行
     * @return
     */
    public Line removeCurrLine() {
        Line newCurrLine; // 标记为新的当前行
        if (curr == last) {
            // 如果当前行是最后一行,则获取前一行给newCurrLine
            newCurrLine = last.prevLine();
        } else {
            // 获取下一行赋值给newCurrLine
            newCurrLine = curr.nextLine();
            if (curr == root) {
                // 如果当前行是首行,则更新首行
                root = newCurrLine;
            }
        }
        // 清空当前行
        curr.remove();
        Line r = curr;
        // 重新标记当前行
        curr = newCurrLine;
        return r;
    }

    /**
     * 移除当前行的下一个节点
     */
    public void removeNextLine() {
        curr.removeNext();
    }

    /**
     * 移除当前行的前一个节点
     */
    public void removePrevLine() {
        if (root == curr.prevLine()) {
            // 如果当前行的前一个节点是首行,则更新首行为当前行
            root = curr;
        }
        // 移除当前行的前一个节点
        curr.removePrev();
    }

    /**
     * 从当前队列复制出一个一样的队列
     * @return
     */
    public LineQueue copy() {
        return new LineQueue(this, curr);
    }

    /**
     * 从当前队列复制出一个一样的队列,并将当前行标记为下一行
     * @return
     */
    public LineQueue copyNext() {
        if (end()) {
            return null;
        }
        return new LineQueue(this, curr.nextLine());
    }

    /**
     * 重置当前行为首行
     */
    public void reset() {
        curr = root;
    }

    /**
     * 判断当前行/首行/尾行是否为空
     * @return
     */
    public boolean empty() {
        return curr == null || root == null || last == null;
    }

    @Override
    public String toString() {
        Line t = root;
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while (t != null) {
            sb.append(t.toString()).append(",");
            t = t.nextLine();
            len++;
        }
        return "{" + sb.toString() + "}";

    }


}
