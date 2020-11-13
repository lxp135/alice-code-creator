package alice.code.creator.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BeanConvertUtils {

	/**
	 * 将普通Java对象的可读属性名与属性值以键值对的方式保存到HashMap
	 * @param bean Java对象
	 * @return HashMap，key是Java对象的可读属性名
	 * @throws Exception
	 */
	public static Map<String, Object> bean2Map(Object bean) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (bean != null) {
			Class<? extends Object> clazz = bean.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null) {
				for (PropertyDescriptor descriptor : propertyDescriptors) {
					if (descriptor != null) {
						String propertyName = descriptor.getName();
						if (propertyName != null && !propertyName.equalsIgnoreCase("class")) {
							Method readMethod = descriptor.getReadMethod();
							Object value = readMethod.invoke(bean, new Object[0]);
							if (value != null) {
								map.put(propertyName, value);
							}
						}
					}
				}
			}
		}
		return map;
	}
}
