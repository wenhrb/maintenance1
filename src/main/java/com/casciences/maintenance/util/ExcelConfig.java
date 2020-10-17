package com.casciences.maintenance.util;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijie7
 * @date 2018/1/4
 * @Description
 * @modified By
 */
public class ExcelConfig {
    /**
     * 读取配置文件
     */
    private static final String fileName = "excelFile.properties";
    private static final ReentrantLock mainLock = new ReentrantLock();

    private static Logger logger = LoggerFactory.getLogger(ExcelConfig.class);

    private static ExcelConfig me;
    private static Properties properties;
    private static String localIp;

    static {
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            logger.error("初始化配置文件失败", e.getMessage());
        }
    }

    private ExcelConfig() {
        init();
    }

    public static ExcelConfig getMe() {
        mainLock.lock();
        try {
            return me == null ? me = new ExcelConfig() : me;
        } finally {
            mainLock.unlock();
        }
    }


    private static void init(String filePath) {
        InputStream stream = null;
        mainLock.lock();
        try {
            if (properties != null) {
                properties.clear();
            } else {
                properties = new Properties();
            }
            stream = new FileInputStream(filePath);
            BufferedReader bf = new BufferedReader(new InputStreamReader(stream));
            properties.load(bf);
            logger.info("配置文件加载成功...");
        } catch (FileNotFoundException e) {
            logger.error("properties file not found!!!　", e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            logger.error("读取配置文件出错", e.getMessage());
        } finally {
            mainLock.unlock();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void init() {
        init(Resources.getResource(fileName).getPath());
    }

    public void put(String key, String val) {
        properties.put(key, val);
    }

    public Properties getAll() {
        return properties;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
