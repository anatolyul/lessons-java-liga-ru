package ru.hofftech.console.packages.util;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class YamlConfigLoader {
    public static <T> T loadConfig(String resourcePath, Class<T> clazz) {
        Yaml yaml = new Yaml(new Constructor(clazz));
        try (InputStream inputStream = YamlConfigLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Не найден файл конфигурации: " + resourcePath);
            }
            return yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки файла конфигурации", e);
        }
    }
}