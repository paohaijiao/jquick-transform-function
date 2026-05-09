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
package com.github.paohaijiao.function.extra;

/**
 * packageName com.github.paohaijiao.function.extra
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCastInvoker extends JQuickBaseMethodInvoker {

    public JQuickCastInvoker() {
        super("cast", "强制类型转换 - 用法: cast(value, targetClass)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        Object value = args.get(0);
        String className = asString(args.get(1));
        if (value == null) return null;
        try {
            Class<?> targetClass = Class.forName(className);
            if (targetClass.isInstance(value)) {
                return value;
            }
            // 尝试转换常用类型
            if (targetClass == String.class) {
                return value.toString();
            }
            if (targetClass == Integer.class || targetClass == int.class) {
                return asInt(value);
            }
            if (targetClass == Long.class || targetClass == long.class) {
                return asLong(value);
            }
            if (targetClass == Double.class || targetClass == double.class) {
                return asDouble(value);
            }
            if (targetClass == Boolean.class || targetClass == boolean.class) {
                return asBoolean(value);
            }

            throw new ClassCastException("无法将 " + value.getClass().getName() + " 转换为 " + className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("找不到目标类: " + className);
        }
    }
}