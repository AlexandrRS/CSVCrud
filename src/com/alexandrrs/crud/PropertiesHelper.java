package com.alexandrrs.crud;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Created by Alexandr on 14.08.2015.
 */
public class PropertiesHelper {

    public static final String PROPERTIES_FILENAME = "csvcrud.cfg";
    public static final String PROPERTIES_COMMENT = "CRUD Properties file";

    private static final String CSV_DATE_FORMAT_PROPERTY = "CsvDateFormat";
    private static final String USER_DATE_FORMAT_PROPERTY = "UserDateFormat";
    private static final String CSV_PATH_PROPERTY = "CsvPath";

    public static final String CSV_DATE_FORMAT_DEFAULT_VALUE = "dd/MM/yyyy";
    public static final String USER_DATE_FORMAT_DEFAULT_VALUE = "dd/MM/yyyy";
    public static final String CSV_PATH_DEFAULT_VALUE = "csvcrud.csv";

    private static Properties properties = getProperties();

    private static SimpleDateFormat csvDateFormat = new SimpleDateFormat(properties.getProperty(CSV_DATE_FORMAT_PROPERTY));
    private static SimpleDateFormat UserDateFormat = new SimpleDateFormat(properties.getProperty(USER_DATE_FORMAT_PROPERTY));
    private static File csvFile = new File(properties.getProperty(CSV_PATH_PROPERTY));

    private PropertiesHelper() {
    }

    public static File getCsvFile() {
        return csvFile;
    }

    public static void setCsvFile(File csvFile) {
        PropertiesHelper.csvFile = csvFile;
    }

    public static SimpleDateFormat getCsvDateFormat() {
        return csvDateFormat;
    }

    public static void setCsvDateFormat(SimpleDateFormat csvDateFormat) {
        PropertiesHelper.csvDateFormat = csvDateFormat;
    }

    public static SimpleDateFormat getUserDateFormat() {
        return UserDateFormat;
    }

    public static void setUserDateFormat(SimpleDateFormat userDateFormat) {
        UserDateFormat = userDateFormat;
    }

    private static Properties getProperties()
    {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILENAME);
            properties.load(fileInputStream);
            fileInputStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            ConsoleHelper.writeMessage("Configuration file is not found. New config will be created");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Config file read error. New Config will be created");
        }
        properties.setProperty(CSV_PATH_PROPERTY, CSV_PATH_DEFAULT_VALUE);
        properties.setProperty(CSV_DATE_FORMAT_PROPERTY, CSV_DATE_FORMAT_DEFAULT_VALUE);
        properties.setProperty(USER_DATE_FORMAT_PROPERTY, USER_DATE_FORMAT_DEFAULT_VALUE);
        saveProperties(properties);
        return properties;
    }
    private static void saveProperties(Properties properties)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PROPERTIES_FILENAME);
            properties.store(fileOutputStream, PROPERTIES_COMMENT);
            fileOutputStream.close();
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Can't write configuration file.");
        }
    }
}
