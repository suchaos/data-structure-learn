package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * 使用前后栈实现浏览器的前进后退
 *
 * @author suchao
 * @date 2019/6/26
 */
public class SampleBrowser {
    private Deque<String> backStack;
    private Deque<String> forwardStack;
    private String currentPage;

    public SampleBrowser() {
        this.backStack = new ArrayDeque<>();
        this.forwardStack = new ArrayDeque<>();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            backStack.push(currentPage);
        }
        this.currentPage = Objects.requireNonNull(url);
        forwardStack.clear();
    }

    public void back() {
        if (backStack.isEmpty()) {
            System.out.println("无法后退");
            return;
        }
        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        showUrl(currentPage, "Back to currentPage");
    }

    public void forward() {
        if (forwardStack.isEmpty()) {
            System.out.println("无法前进");
            return;
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        showUrl(currentPage, "Forward to currentPage");
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public static void main(String[] args) {
        SampleBrowser browser = new SampleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.back();
        browser.back();
        browser.forward();
        browser.open("http://www.qq.com");
        browser.forward();
        browser.back();
        browser.forward();
        browser.back();
        browser.back();
        browser.back();
        browser.back();
    }
}
