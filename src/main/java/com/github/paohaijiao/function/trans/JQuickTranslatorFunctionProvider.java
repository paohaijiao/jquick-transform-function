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

import com.github.paohaijiao.exception.JAssert;
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
        validateArgCount(args, 2);
        JAssert.isTrue(args.get(0) instanceof JContext,"第一个参数必须是JContext");
        JContext context = (JContext)args.get(0);
        String key = (String)args.get(1);
        return context.get(key);
    }
}