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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIdCardInfoInvoker extends JQuickBaseMethodInvoker {

    public JQuickIdCardInfoInvoker() {
        super("idCardInfo", "提取身份证信息 - 用法: idCardInfo(idCard, field?) field: birthday/age/gender/region");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        String idCard = asString(args.get(0));
        String field = args.size() > 1 ? asString(args.get(1)) : null;
        if (idCard == null) return null;
        idCard = idCard.trim().toUpperCase();
        // 15位转18位
        if (idCard.length() == 15) {
            idCard = convert15To18(idCard);
        }
        if (idCard.length() != 18 || !idCard.matches("^\\d{17}[0-9X]$")) {
            throw new IllegalArgumentException("身份证号格式错误");
        }
        Map<String, Object> info = extractInfo(idCard);
        if (field != null) {
            return info.get(field.toLowerCase());
        }
        return info;
    }

    private Map<String, Object> extractInfo(String idCard) {
        Map<String, Object> info = new HashMap<>();
        // 出生日期
        String birthStr = idCard.substring(6, 14);
        LocalDate birthday = LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        info.put("birthday", birthday);
        // 年龄
        int age = Period.between(birthday, LocalDate.now()).getYears();
        info.put("age", age);
        // 性别 (第17位，奇数为男，偶数为女)
        int genderCode = Integer.parseInt(idCard.substring(16, 17));
        info.put("gender", genderCode % 2 == 1 ? "男" : "女");
        info.put("genderCode", genderCode % 2 == 1 ? "M" : "F");
        // 省份代码
        String provinceCode = idCard.substring(0, 2);
        info.put("provinceCode", provinceCode);
        info.put("province", getProvinceName(provinceCode));

        return info;
    }

    private String convert15To18(String idCard15) {
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] checkCodes = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        String idCard17 = idCard15.substring(0, 6) + "19" + idCard15.substring(6);
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (idCard17.charAt(i) - '0') * weights[i];
        }
        int mod = sum % 11;
        return idCard17 + checkCodes[mod];
    }

    private String getProvinceName(String code) {
        Map<String, String> provinces = new HashMap<>();
        provinces.put("11", "北京"); provinces.put("12", "天津"); provinces.put("13", "河北");
        provinces.put("14", "山西"); provinces.put("15", "内蒙古"); provinces.put("21", "辽宁");
        provinces.put("22", "吉林"); provinces.put("23", "黑龙江"); provinces.put("31", "上海");
        provinces.put("32", "江苏"); provinces.put("33", "浙江"); provinces.put("34", "安徽");
        provinces.put("35", "福建"); provinces.put("36", "江西"); provinces.put("37", "山东");
        provinces.put("41", "河南"); provinces.put("42", "湖北"); provinces.put("43", "湖南");
        provinces.put("44", "广东"); provinces.put("45", "广西"); provinces.put("46", "海南");
        provinces.put("50", "重庆"); provinces.put("51", "四川"); provinces.put("52", "贵州");
        provinces.put("53", "云南"); provinces.put("54", "西藏"); provinces.put("61", "陕西");
        provinces.put("62", "甘肃"); provinces.put("63", "青海"); provinces.put("64", "宁夏");
        provinces.put("65", "新疆"); provinces.put("71", "台湾"); provinces.put("81", "香港");
        provinces.put("82", "澳门");
        return provinces.getOrDefault(code, "未知");
    }
}
