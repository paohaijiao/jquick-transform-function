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
package com.github.paohaijiao.function.business;

/**
 * packageName com.github.paohaijiao.function.business
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickPhoneInfoFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickPhoneInfoFunctionProvider() {
        super("phoneInfo", "获取手机号信息 - 用法: phoneInfo(phone, field?) field: carrier/prefix/location");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        String phone = asString(args.get(0));
        String field = args.size() > 1 ? asString(args.get(1)) : null;

        if (phone == null) return null;
        phone = phone.trim().replaceAll("\\s", "");

//        if (!phone.matches("^1[3-9]\\d{9}$")) {
//            throw new IllegalArgumentException("手机号格式错误");
//        }

        Map<String, Object> info = extractInfo(phone);

        if (field != null) {
            return info.get(field.toLowerCase());
        }
        return info;
    }

    private Map<String, Object> extractInfo(String phone) {
        Map<String, Object> info = new HashMap<>();

        // 前三位识别运营商
        String prefix = phone.substring(0, 3);
        info.put("prefix", prefix);
        info.put("carrier", getCarrier(prefix));
        info.put("masked", maskPhone(phone));

        return info;
    }

    private String getCarrier(String prefix) {
        // 移动: 134,135,136,137,138,139,147,148,150,151,152,157,158,159,172,178,182,183,184,187,188,198
        // 联通: 130,131,132,145,146,155,156,166,167,171,175,176,185,186,196
        // 电信: 133,149,153,173,174,177,180,181,189,190,191,193,199
        // 广电: 192
        Map<String, String> carriers = new HashMap<>();
        // 移动
        String[] mobile = {"134", "135", "136", "137", "138", "139", "147", "148", "150", "151", "152", "157", "158", "159", "172", "178", "182", "183", "184", "187", "188", "198"};
        for (String p : mobile) carriers.put(p, "中国移动");
        // 联通
        String[] unicom = {"130", "131", "132", "145", "146", "155", "156", "166", "167", "171", "175", "176", "185", "186", "196"};
        for (String p : unicom) carriers.put(p, "中国联通");
        // 电信
        String[] telecom = {"133", "149", "153", "173", "174", "177", "180", "181", "189", "190", "191", "193", "199"};
        for (String p : telecom) carriers.put(p, "中国电信");
        // 广电
        carriers.put("192", "中国广电");

        return carriers.getOrDefault(prefix, "未知");
    }

    private String maskPhone(String phone) {
        if (phone.length() != 11) return phone;
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}
