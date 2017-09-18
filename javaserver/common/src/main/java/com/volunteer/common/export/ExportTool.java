package com.volunteer.common.export;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExportTool {

	public static String bulidHeader(Map<String, FieldDef> template) {
		StringBuilder builder = new StringBuilder();
		Set<String> keySet = template.keySet();
		for (String key : keySet) {
			builder.append(",");
			builder.append(template.get(key).displayName);
		}
		return builder.substring(1);
	}

	public static void buildCsv(OutputStream ouputStream, List<?> list, Map<String, FieldDef> template) throws Exception {
		StringBuilder builder = new StringBuilder();

		String headerStr = bulidHeader(template);
		builder.append(headerStr);
		builder.append("\n");

		Set<String> keySet = template.keySet();

		if (null != list && !list.isEmpty()) {
			for (Object obj : list) {
				Map<String, String> map = BeanUtils.describe(obj);
				int i = 0;
				for (String key : keySet) {
					if (i != 0) {
						builder.append(",");
					}
					Object v= map.get(key);
					String value = "";
					if (null != v) {
						value = String.valueOf(v);
					}
					FieldDef fieldDef = template.get(key);
					value = fieldDef.getValue(value);
					builder.append(value + "\t");
					i++;
				}
				builder.append("\n");
			}
		}

		try {
			ouputStream.write(builder.toString().getBytes("GBK"));
			ouputStream.flush();
			ouputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
