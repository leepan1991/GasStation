package com.volunteer.manager.controller;

import com.volunteer.common.export.ExportTemplate;
import com.volunteer.common.export.ExportTool;
import com.volunteer.manager.view.CSVReportView;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.GasFillingLog;
import com.volunteer.service.AbstractService;
import com.volunteer.service.GasFillingLogService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */
@Controller
@RequestMapping("gasFillingLog")
public class GasFillingLogController extends AbstractController<GasFillingLog> {

    @Autowired
    private GasFillingLogService gasFillingLogService;

    @RequestMapping("export")
    public ModelAndView export(HttpServletRequest request, TableParameter parameter, GasFillingLog gasFillingLog) throws Exception {
        String dateTime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		parameter.setPageSize(-1);
        List<GasFillingLog> list = this.gasFillingLogService.listPagedWhenExport(parameter, gasFillingLog);
        File path = new File(request.getSession().getServletContext().getRealPath("/") + "/upload");
        if (!path.exists()) {
            path.mkdir();
        }
        String filepath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + dateTime + ".csv";
        File file = new File(filepath);
        OutputStream ouputStream = new FileOutputStream(file);
        ExportTool.buildCsv(ouputStream, list, ExportTemplate.achievementForHos_TEMPLATE);
        return new ModelAndView(new CSVReportView(dateTime + "-明细", file, true));
    }


    @Override
    public AbstractService<GasFillingLog> getAbstractService() {
        return gasFillingLogService;
    }
}
