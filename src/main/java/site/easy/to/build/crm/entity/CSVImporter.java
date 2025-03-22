package site.easy.to.build.crm.entity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter<T> {

    private final Class<T> type;

    public CSVImporter(Class<T> type) {
        this.type = type;
    }

    public List<T> parseCSV(MultipartFile file) {
        List<T> objects = new ArrayList<>();
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create().setHeader().build());
        ) {
            for (CSVRecord record : csvParser) {
                T obj = type.getDeclaredConstructor().newInstance();

                for (String column : record.toMap().keySet()) {
                    String value = record.get(column);
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(column, type);
                        Method setter = pd.getWriteMethod();
                        if (setter != null) {
                            Object convertedValue = convertValue(value, pd.getPropertyType());
                            setter.invoke(obj, convertedValue);
                        }
                    } catch (Exception e) {
                        System.err.println("Erreur de mapping : " + e.getMessage());
                    }
                }
                objects.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur de lecture CSV: " + e.getMessage());
        }
        return objects;
    }

    private Object convertValue(String value, Class<?> targetType) {
        if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(value);
        } else if (targetType == Double.class || targetType == double.class) {
            return Double.parseDouble(value);
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.parseBoolean(value);
        }else if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(value);
        } else if (targetType == Float.class || targetType == float.class) {
            return Float.parseFloat(value);
        }
        else if (targetType == Short.class || targetType == short.class) {
            return Short.parseShort(value);
        } else if (targetType == Byte.class || targetType == byte.class) {
            return Byte.parseByte(value);
        } else if (targetType == Character.class || targetType == char.class) {
            return value.charAt(0);
        }else if (targetType == Date.class) {
            return Date.valueOf(value);
        }
        else {
            return value; // Par défaut String
        }
    }
}

