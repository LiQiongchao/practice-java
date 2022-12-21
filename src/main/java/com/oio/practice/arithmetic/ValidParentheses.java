package com.oio.practice.arithmetic;


// import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentheses {

    public static void main(String[] args) {
//        String s = "([)]";
        String s = "((";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        int length = s.length();
        // 长度一定是偶数
        if (length % 2 != 0) {
            return false;
        }

        // 创建括号对
        Map<Character, Character> paris = new HashMap<Character, Character>();
        paris.put(')', '(');
        paris.put(']', '[');
        paris.put('}', '{');

        // 使用栈来实现
        Deque<Character> list = new LinkedList<Character>();
        for (int i = 0; i < length; i++) {
            char word = s.charAt(i);
            if (paris.containsKey(word)) {
                // peek 返回栈顶的值，不删除栈中的值
                if (list.isEmpty() || list.peek() != paris.get(word)) {
                    return false;
                }
                // 返回栈顶的值并删除栈中的值
                list.pop();
            } else
                // 从栈顶添加元素
                list.push(word);
        }
        return list.isEmpty();
    }

}
