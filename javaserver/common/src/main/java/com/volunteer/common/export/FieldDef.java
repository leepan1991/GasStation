package com.volunteer.common.export;

import org.apache.commons.lang3.StringUtils;

public class FieldDef {
	public String displayName;
	public FieldValueDealer fieldDealer;

	public FieldDef(String displayName, FieldValueDealer fieldDealer) {
		super();
		this.displayName = displayName;
		this.fieldDealer = fieldDealer;
	}

	public FieldDef(String displayName) {
		super();
		this.displayName = displayName;
	}
	
	public String getValue(String value) {
		if (null != fieldDealer) {
			value = fieldDealer.getFieldValue(value);
		}
		if (null == value) {
			return StringUtils.EMPTY;
		}
		return value;
	}
}
