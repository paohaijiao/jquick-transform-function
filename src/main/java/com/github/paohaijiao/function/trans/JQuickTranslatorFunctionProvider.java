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
package com.github.paohaijiao.function.trans;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import com.github.paohaijiao.param.JContext;
import com.github.paohaijiao.value.ValueResolver;

import java.util.List;
import java.util.Map;

/**
 * 码值翻译函数
 * 用法: translate(context, code, dictType, defaultValue?)
 *
 * code参数支持两种形式:
 * 1. 直接值: "M", "F", "1"
 * 2. OGNL路径: "user.gender", "order.status" 会从context中解析
 *
 * 字典数据存储在JContext中，路径为: dict.{dictType}
 * 例如: dict.gender 存储性别字典
 *
 * 字典格式为 Map<String, String>，key为码值，value为显示文本
 */
@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickTranslatorFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickTranslatorFunctionProvider() {
        super("translate", "码值翻译 - 用法: translate(context, code, dictType, defaultValue?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 3, 4);
        JContext context = getContext(args, 0);
        Object codeObj = args.get(1);
        String dictType = asString(args.get(2));
        String defaultValue = args.size() > 3 ? asString(args.get(3)) : null;
        if (context == null) {
            throw new IllegalArgumentException("JContext不能为空");
        }
        String code = resolveCode(context, codeObj);

        if (code == null || code.isEmpty()) {
            return defaultValue != null ? defaultValue : "";
        }

        @SuppressWarnings("unchecked")
        Map<String, String> dict = context.get( dictType, Map.class);
        if (dict != null && dict.containsKey(code)) {
            return dict.get(code);
        }
        return defaultValue != null ? defaultValue : code;
    }

    /**
     * 解析code参数的实际值
     * @param context JContext上下文
     * @param codeObj code参数
     * @return 解析后的码值
     */
    private String resolveCode(JContext context, Object codeObj) {
        if (codeObj == null) {
            return null;
        }

        String codeStr = codeObj.toString();

        // 判断是否为OGNL路径表达式（包含.或需要从context中取值）
        if (isPathExpression(codeStr)) {
            try {
                // 通过ValueResolver从context中解析值
                Object resolved = ValueResolver.evaluate(codeStr, context, null);
                if (resolved != null) {
                    return resolved.toString();
                }
                return null;
            } catch (Exception e) {
                // 解析失败，返回原字符串
                return codeStr;
            }
        }

        // 直接值
        return codeStr;
    }

    /**
     * 判断是否为路径表达式
     * 包含点号 或 符合OGNL表达式特征
     */
    private boolean isPathExpression(String str) {
        if (str == null) {
            return false;
        }
        // 包含点号
        if (str.contains(".")) {
            return true;
        }
        // 包含方法调用
        if (str.contains("(") && str.contains(")")) {
            return true;
        }
        // 包含数组访问
        if (str.contains("[") && str.contains("]")) {
            return true;
        }
        return false;
    }

    private JContext getContext(List<Object> args, int index) {
        Object obj = args.get(index);
        if (obj instanceof JContext) {
            return (JContext) obj;
        }
        throw new IllegalArgumentException("第一个参数必须是JContext类型");
    }
}