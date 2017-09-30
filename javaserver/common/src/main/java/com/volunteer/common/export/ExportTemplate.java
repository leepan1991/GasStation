package com.volunteer.common.export;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExportTemplate {

	public static Map<String, FieldDef> achievementForHos_TEMPLATE = new LinkedHashMap<String, FieldDef>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("id", new FieldDef("序号"));
			put("code", new FieldDef("气瓶使用登记代码"));
//			put("gasBottleId", new FieldDef("气瓶标识码"));
			put("bottleCode", new FieldDef("瓶身码"));
			put("fillingTime", new FieldDef("充装时间"));
			put("fillingCustomerName", new FieldDef("充装工"));
			put("inspector", new FieldDef("充装前后检查员"));
//			put("siteName", new FieldDef("站点各称"));
//			put("deviceName", new FieldDef("设备名称"));
			put("medium", new FieldDef("充装介质", new FieldValueDealer() {
				@Override
				public String getFieldValue(String value) {
					if (StringUtils.equals("0", value)) {
						return "工业氧气";
					} else if (StringUtils.equals("1", value)) {
						return "工业氮气";
					} else if (StringUtils.equals("2", value)) {
						return "工业氩气";
					} else if (StringUtils.equals("3", value)) {
						return "液体二氧化碳";
					} else if (StringUtils.equals("4", value)) {
						return "液体氧";
					} else if (StringUtils.equals("5", value)) {
						return "液体氮";
					} else if (StringUtils.equals("6", value)) {
						return "液体氩";
					} else if (StringUtils.equals("7", value)) {
						return "液化石油气";
					} else if (StringUtils.equals("8", value)) {
						return "丙烷气";
					} else if (StringUtils.equals("9", value)) {
						return "乙炔气";
					} else if (StringUtils.equals("10", value)) {
						return "混合气";
					}
					return "";
				}
			}));
			put("manufacturer", new FieldDef("制造单位"));
			put("manufactureDate", new FieldDef("制造年月"));
			put("workPressure", new FieldDef("公称工作压力(Mpa)"));
			put("gasBottleVolume", new FieldDef("容积(L)"));
			put("gasBottleWeight", new FieldDef("瓶重"));
			put("wallThickness", new FieldDef("设计壁厚(mm)"));
			put("lastCheckTime", new FieldDef("最近一次检验日期"));
			put("nextCheckTime", new FieldDef("下次检验日期"));
			put("invalidatedDate", new FieldDef("报废日期"));
		}
	};
	
	public static Map<String, FieldDef> GAS_BOTTLE_TEMPLATE = new LinkedHashMap<String, FieldDef>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("id", new FieldDef("序号"));
			put("gasBottleRegCode", new FieldDef("气瓶使用登记代码"));
//			put("gasBottleId", new FieldDef("气瓶标识码"));
			put("gasBottleCode", new FieldDef("瓶身码"));
//			put("siteName", new FieldDef("站点各称"));
//			put("deviceName", new FieldDef("设备名称"));
			put("medium", new FieldDef("充装介质", new FieldValueDealer() {
				//工业氧气，工业氮气，工业氩气，液体二氧化碳，液体氧，液体氮，液体氩，液化石油气，丙烷气，乙炔气
				@Override
				public String getFieldValue(String value) {
					if (StringUtils.equals("0", value)) {
						return "工业氧气";
					} else if (StringUtils.equals("1", value)) {
						return "工业氮气";
					} else if (StringUtils.equals("2", value)) {
						return "工业氩气";
					} else if (StringUtils.equals("3", value)) {
						return "液体二氧化碳";
					} else if (StringUtils.equals("4", value)) {
						return "液体氧";
					} else if (StringUtils.equals("5", value)) {
						return "液体氮";
					} else if (StringUtils.equals("6", value)) {
						return "液体氩";
					} else if (StringUtils.equals("7", value)) {
						return "液化石油气";
					} else if (StringUtils.equals("8", value)) {
						return "丙烷气";
					} else if (StringUtils.equals("9", value)) {
						return "乙炔气";
					} else if (StringUtils.equals("10", value)) {
						return "混合气";
					}
					return "";
				}
			}));
			put("manufacturer", new FieldDef("制造单位"));
			put("manufactureDate", new FieldDef("制造年月"));
			put("workPressure", new FieldDef("公称工作压力(Mpa)"));
			put("gasBottleVolume", new FieldDef("容积(L)"));
			put("gasBottleWeight", new FieldDef("瓶重"));
			put("wallThickness", new FieldDef("设计壁厚(mm)"));
			put("lastCheckTime", new FieldDef("最近一次检验日期"));
			put("nextCheckTime", new FieldDef("下次检验日期"));
			put("invalidatedDate", new FieldDef("报废日期"));
		}
	};

}
