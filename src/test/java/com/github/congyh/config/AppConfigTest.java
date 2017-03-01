package com.github.congyh.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class AppConfigTest {
    @Test
    public void PropertiesLoadTest() {
        assertEquals("asgsgsdgsdf", AppConfig.getInstance().getProps().getProperty("token"));
    }

}