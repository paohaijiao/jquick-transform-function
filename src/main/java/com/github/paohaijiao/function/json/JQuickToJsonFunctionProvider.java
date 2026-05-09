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
package com.github.paohaijiao.function.json;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickToJsonFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JQuickToJsonFunctionProvider() {
        super("toJson", "将对象转换为JSON字符串");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        try {
            return OBJECT_MAPPER.writeValueAsString(args.get(0));
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化失败: " + e.getMessage(), e);
        }
    }
}