/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.function.string;

/**
 * packageName com.github.paohaijiao.function.string
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSimilarityFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickSimilarityFunctionProvider() {
        super("similarity", "计算字符串相似度（百分比）- 用法: similarity(str1, str2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String s1 = asString(args.get(0));
        String s2 = asString(args.get(1));

        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";

        if (s1.isEmpty() && s2.isEmpty()) return 100.0;
        if (s1.isEmpty() || s2.isEmpty()) return 0.0;

        // 使用简单版相似度：最长公共子串长度 / 最长字符串长度
        int maxLen = Math.max(s1.length(), s2.length());
        int lcs = longestCommonSubstring(s1, s2);

        return (double) lcs / maxLen * 100;
    }

    private int longestCommonSubstring(String s1, String s2) {
        int max = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}