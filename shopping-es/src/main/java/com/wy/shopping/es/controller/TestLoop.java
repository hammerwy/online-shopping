package com.wy.shopping.es.controller;

import org.apache.commons.lang3.StringUtils;

import java.nio.channels.Selector;

/**
 * @author wy
 * @date 2019/12/19
 * @description
 */
public class TestLoop {

    @Override
    protected void finalize() throws Throwable {


    }

    public void outsideLoop() {
        Object o;
        int i = 0;
        while (++i < 100) {
            o = new Object();
            o.toString();
        }
    }

    public void intsideLoop() {
        int i = 0;
        while (++i < 100) {
            Object o = new Object();
            o.toString();
        }
    }

    public String testThrow(String var) {
        try {
            if (StringUtils.isEmpty(var)) {
                throw new RuntimeException("eeeee");
            }
            return var;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("fianl");
            return "fianl-ret";
        }
    }

    public static void main(String[] args) {
    }
}
