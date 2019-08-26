package substringSearch;

/**
 * 暴力匹配字符串算法
 * TODO: 看一下 indexOf 的实现
 *
 * @author suchao
 * @date 2019/1/2
 */
public class BruteForceSearch {

    /**
     * 在文本字符串 txt 中查找模式字符串 pat 第一次出现的位置
     * 程序使用了一个指针 i 跟踪文本，一个指针 j 跟踪模式。
     *
     * @param pat 模式串
     * @param txt 主串
     * @return 首先出现的位置，在不匹配时返回 -1
     */
    public static int search(String pat, String txt) {
        int mainLength = txt.length();
        int patLength = pat.length();
        for (int i = 0; i <= mainLength - patLength; i++) {
            int j;
            for (j = 0; j < patLength; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            if (j == patLength) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 暴力子字符匹配算法的另一种实现（显式回退）
     * 在文本字符串 txt 中查找模式字符串 pat 第一次出现的位置
     * 程序使用了一个指针 i 跟踪文本，一个指针 j 跟踪模式。
     *
     * @param pat 模式串
     * @param txt 主串
     * @return 首先出现的位置，在不匹配时返回 -1
     */
    public static int search2(String pat, String txt) {
        int mainLength = txt.length();
        int patLength = pat.length();
        int i, j;
        for (i = 0, j = 0; i < mainLength && j < patLength; i++) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
            } else {
                i -= j;
                j = 0;
            }
        }
        if (j == patLength) {
            return i - patLength;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String txt = "abcdeffdfd";
        System.out.println(txt.indexOf("bc"));
        System.out.println(search("bc", txt));
        System.out.println(search2("bc", txt));
    }
}
