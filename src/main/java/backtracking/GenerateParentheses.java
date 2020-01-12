package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author suchao
 * @date 2020/1/11
 * @link https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParentheses {

    /*
        剪枝的原因：左括号始终都可以放，但是左括号最大个数有限制
                    右括号只有在左边有没有配对的左括号时，才可以放
     */

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        helper(0, 0, n, "", result);
        return result;
    }

    private void helper(int left, int right, int max, String word, List<String> result) {
        // terminator
        if (left >= max && right >= max) {
            result.add(word);
        }
        // process and drill down
        if (left < max) {
            helper(left + 1, right, max, word + "(", result);
        }
        if (left > right) {
            helper(left, right + 1, max, word + ")", result);
        }
    }
}
