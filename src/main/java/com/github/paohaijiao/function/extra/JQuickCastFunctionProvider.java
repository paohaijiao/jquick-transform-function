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
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCastFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Map<String, Class<?>> PRIMITIVE_MAP = new HashMap<>();

    static {
        PRIMITIVE_MAP.put("int", int.class);
        PRIMITIVE_MAP.put("long", long.class);
        PRIMITIVE_MAP.put("double", double.class);
        PRIMITIVE_MAP.put("float", float.class);
        PRIMITIVE_MAP.put("short", short.class);
        PRIMITIVE_MAP.put("byte", byte.class);
        PRIMITIVE_MAP.put("char", char.class);
        PRIMITIVE_MAP.put("boolean", boolean.class);
    }

    public JQuickCastFunctionProvider() {
        super("cast", "强制类型转换 - 用法: cast(value, targetClass)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        Object value = args.get(0);
        String className = asString(args.get(1));
        if (value == null) return null;
        try {
            Class<?> targetClass = getClassForName(className);

            if (targetClass.isInstance(value)) {
                return value;
            }
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
            if (targetClass == Float.class || targetClass == float.class) {
                return ((Number) asDouble(value)).floatValue();
            }
            if (targetClass == Short.class || targetClass == short.class) {
                return (short) asInt(value);
            }
            if (targetClass == Byte.class || targetClass == byte.class) {
                return (byte) asInt(value);
            }
            if (targetClass == Boolean.class || targetClass == boolean.class) {
                return asBoolean(value);
            }
            throw new ClassCastException("无法将 " + value.getClass().getName() + " 转换为 " + className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("找不到目标类: " + className);
        }
    }

    private Class<?> getClassForName(String className) throws ClassNotFoundException {
        if (PRIMITIVE_MAP.containsKey(className)) {
            return PRIMITIVE_MAP.get(className);
        }
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            String wrapperName = getWrapperClassName(className);
            if (wrapperName != null) {
                return Class.forName(wrapperName);
            }
            throw e;
        }
    }

    private String getWrapperClassName(String primitiveName) {
        switch (primitiveName) {
            case "int": return "java.lang.Integer";
            case "long": return "java.lang.Long";
            case "double": return "java.lang.Double";
            case "float": return "java.lang.Float";
            case "short": return "java.lang.Short";
            case "byte": return "java.lang.Byte";
            case "char": return "java.lang.Character";
            case "boolean": return "java.lang.Boolean";
            default: return null;
        }
    }
}