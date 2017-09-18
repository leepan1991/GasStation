package com.volunteer.manager.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/** 
 * @department 成都-产品运营-商务智能-java  
 * @description 
 * @author yaoh
 * @date 2015年3月5日 下午1:18:26 
 */

public abstract class AbstractCsvView extends AbstractView {
	private static final String CONTENT_TYPE = "application/vnd.ms-csv";

	public AbstractCsvView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	/**
	 * description 
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @see AbstractView#renderMergedOutputModel(Map, HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType(getContentType());
		buildExcelDocument(model, request, response);
	}

	protected abstract void buildExcelDocument(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

}
