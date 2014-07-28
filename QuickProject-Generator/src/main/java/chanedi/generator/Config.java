package chanedi.generator;

import chanedi.utils.ReflectUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by jijy on 2014/7/9.
 */
@Data
public class Config {

    public static final String CONFIG_FILE_NAME = "config.properties";
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private String rootPath; // TODO 默认值
    private String fileExtension = "java";

    private Config() {
        super();
    }

    public static Config getInstance(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(path + "/" + CONFIG_FILE_NAME));

        Config config = new Config();

        Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            Method setter = null;
            try {
                setter = ReflectUtils.getBeanSetter(Config.class, key);
                setter.invoke(config, value);
            } catch (Exception e) {
                logger.warn("config.properties中有无法处理的配置项：{}, 已忽略");
                continue;
            }
        }

        return config;
    }

}