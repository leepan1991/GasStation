package com.volunteer.manager.controller;

import com.volunteer.common.export.ExportTemplate;
import com.volunteer.common.export.ExportTool;
import com.volunteer.manager.view.CSVReportView;
import com.volunteer.model.GasBottleUpdateCheckTime;
import com.volunteer.model.ResponseData;
import com.volunteer.model.TableParameter;
import com.volunteer.pojo.GasBottle;
import com.volunteer.service.AbstractService;
import com.volunteer.service.GasBottleService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("gasBottle")
public class GasBottleController extends AbstractController<GasBottle> {

    @Autowired
    private GasBottleService gasBottleService;

    @RequestMapping("export")
    public ModelAndView export(HttpServletRequest request, TableParameter parameter, GasBottle gasBottle) throws Exception {
        String dateTime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		parameter.setPageSize(-1);
        List<GasBottle> list = this.gasBottleService.listPagedWhenExport(parameter, gasBottle);
        File path = new File(request.getSession().getServletContext().getRealPath("/") + "/upload");
        if (!path.exists()) {
            path.mkdir();
        }
        String filepath = request.getSession().getServletContext().getRealPath("/") + "/upload/" + dateTime + ".csv";
        File file = new File(filepath);
        OutputStream ouputStream = new FileOutputStream(file);
        ExportTool.buildCsv(ouputStream, list, ExportTemplate.GAS_BOTTLE_TEMPLATE);
        return new ModelAndView(new CSVReportView(dateTime + "-明细", file, true));
    }

    @ResponseBody
    @RequestMapping(value = "updateNextCheckTime")
    public ResponseData updateNextCheckTime(@RequestBody GasBottleUpdateCheckTime entity) {
        return this.response("更新成功", this.gasBottleService.updateNextCheckTime(entity.getIds(), entity.getNextCheckDate()));
    }

    @Override
    public AbstractService<GasBottle> getAbstractService() {
        return gasBottleService;
    }
}
