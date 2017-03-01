package com.github.congyh.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工程配置类-单例
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class AppConfig {

    private static AppConfig INSTANCE = new AppConfig();

    public Properties getProps() {
        return props;
    }

    private final Properties props = new Properties();

    private AppConfig() {
        loadConfig();
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    /**
     * 获取应用配置信息
     *
     * <p>注意: 工程根路径是"/", 如果不加默认就是相对路径
     */
    private void loadConfig() {
        try (InputStream in = AppConfig.class.getResourceAsStream("/config.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
