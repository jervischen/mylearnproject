package com.example.demo.crawler;

import java.io.*;
import java.util.Properties;

/**
 * @author Chen Xiao
 * @since 2020-04-17 14:50
 */
public class ConfigProperties extends Properties {
    private static final long serialVersionUID = 1L;

    public ConfigProperties() {
    }

    public ConfigProperties(String name) throws IOException {
        load(new FileInputStream(name));
    }

    public ConfigProperties(File file) throws IOException {
        load(new FileInputStream(file));
    }

    public ConfigProperties(InputStream inStream) throws IOException {
        load(inStream);
    }

    public ConfigProperties(Reader reader) throws IOException {
        load(reader);
    }

    public void load(String name) throws IOException {
        load(new FileInputStream(name));
    }

    public void load(File file) throws IOException {
        load(new FileInputStream(file));
    }

    @Override
    public void load(InputStream inStream) throws IOException {
        try {
            super.load(inStream);
        } finally {
            close(inStream);
        }
    }

    public void load(InputStream inStream, String charset) throws IOException {
        try {
            super.load(new InputStreamReader(inStream, charset));
        } finally {
            close(inStream);
        }
    }

    @Override
    public void load(Reader reader) throws IOException {
        try {
            super.load(reader);
        } finally {
            close(reader);
        }
    }

    @Override
    public String getProperty(String key) {
        String property = super.getProperty(key);
        if (property == null) {
            //_log.info("MyProperties: Missing property for key - " + key);
            return null;
        }
        return property.trim();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String property = super.getProperty(key, defaultValue);
        if (property == null) {
            //_log.warn("MyProperties: Missing defaultValue for key - " + key);
            return null;
        }

        return property.trim();
    }

    private void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
