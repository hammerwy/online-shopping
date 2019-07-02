package com.wy.shopping.controller.annotation;

import com.wy.shopping.common.service.annotation.DbHint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Getter
@Setter
@DbHint(value = "232323")
public class TestClass {

    @Value("dfdfd")
    public String field = "11";
}
