package alice.code.creator.common.framework.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转日期的转换器
 * @author contact@liuxp.me
 */
public class StringToDateConverter implements Converter<String, Date> {

    private static final String dateFormat      = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                SimpleDateFormat formatter;
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(dateFormat);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.matches("^\\d+$")) {
                Long longDate = new Long(source);
                return new Date(longDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}


