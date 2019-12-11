package com.wy.shopping.common.service.content;

import com.wy.shopping.common.service.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wy
 * @date 2019/10/28
 * @description
 */
@Slf4j
@Component
public class SpringContext implements ApplicationContextAware {

    /**
     * 业务中定义的几种profile
     */
    private static final String[] PROFILES = {"dev", "test", "prod"};

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> typeClass) {
        return context.getBean(typeClass);
    }

    /**
     * 获取当前环境的profile，例如 dev，test，prod
     * @return profile
     */
    public static String getActiveProfile() {
        final String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        // 只匹配需要的profile，如果逻辑编译正确只有一个profile
        final List<String> activeProfileList = Arrays.stream(activeProfiles)
                .filter(s -> StringUtils.containsAny(s, PROFILES))
                .collect(Collectors.toList());
        if (activeProfileList.size() != 1) {
            log.warn("Profile Error! Current Profile = {}", JsonUtil.toJson(activeProfileList));
            return "";
        }
        return activeProfileList.get(0);
    }
}