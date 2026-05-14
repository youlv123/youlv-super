/*
package com.ruoyi.work;

import cn.nesc.wm.common.page.Page;
import cn.nesc.wm.common.util.DateTimeUtils;
import cn.nesc.wm.opt.mot.infrustracture.db.mapper.ReviewImportTimpMapper;
import cn.nesc.wm.opt.mot.infrustracture.remote.CallCenterCustomerReviewDataListener;
import cn.nesc.wm.opt.mot.infrustracture.remote.DcManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.WmVisitReviewManager;
import cn.nesc.wm.opt.mot.infrustracture.remote.model.*;
import cn.nesc.wm.opt.mot.service.*;
import cn.nesc.wm.opt.mot.service.model.*;
import cn.nesc.wm.opt.mot.util.EasyExcelExportUtil;
import cn.nesc.wm.opt.mot.util.MotConstants;
import cn.nesc.wm.opt.mot.util.TaskConstants;
import cn.nesc.wm.opt.mot.web.adapter.*;
import cn.nesc.wm.opt.mot.web.enums.*;
import cn.nesc.wm.opt.mot.web.model.*;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.tencent.cloud.task.sdk.client.model.ExecutableTaskData;
import com.tencent.cloud.task.sdk.common.protocol.message.ShardingArgs;
import com.tencent.cloud.task.sdk.common.protocol.message.TaskExecuteMeta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.common.api.BaseResponse;
import test.common.api.ResponseUtil;
import test.common.exception.ExceptionUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customerReview/v2")
@Api("客户回访新接口")
@Slf4j
public class CallCenterCustomerReviewController {


    @Resource
    ClientConsultDataAssertService clientConsultDataAssertService;

    @Resource
    private CallCenterCustomerReviewService callCenterCustomerReviewService;

    @Resource
    private SimpleTaskInfoApplicationService simpleTaskInfoApplicationService;

    @Resource
    private ProcedureService procedureService;


    @Resource
    private ReviewImportTimpMapper reviewImportTimpMapper;

    @Resource
    private WmVisitReviewManager visitReviewManager;

    @Resource
    private DcManager dcManager;

    @Resource
    private EasyExcelExportUtil easyExcelExportUtil;

    @Resource
    private CustomerReviewService customerReviewService;

    */
/**
     * 普通任务回访查询
     *//*

    @PostMapping("/queryCallCenterCustomerReview")
    @ApiOperation(httpMethod = "POST", value = "普通任务回访/原有客户回访查询")
    public BaseResponse<Page<CallCenterCustomerReviewVo>> queryCallCenterCustomerReview(@RequestBody QueryCallCenterCustomerReviewVo vo) throws ParseException {
        //入参非空校验
        if (Objects.isNull(vo)) {
            return ResponseUtil.ok();
        }
        if (vo.getGenTimeStart() != null) {
            Date genTimeStart = vo.getGenTimeStart();
            String genTimeStartString = DateTimeUtils.parseDateToString(genTimeStart, DateTimeUtils.DATE_PATTERN_DATE) + " 00:00:00";
            Date newGenTimeStart = DateTimeUtils.parseStringToDate(genTimeStartString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setGenTimeStart(newGenTimeStart);
        }
        if (vo.getGenTimeEnd() != null) {
            Date genTimeEnd = vo.getGenTimeEnd();
            String genTimeEndString = DateTimeUtils.parseDateToString(genTimeEnd, DateTimeUtils.DATE_PATTERN_DATE) + " 23:59:59";
            Date newGenTimeEnd = DateTimeUtils.parseStringToDate(genTimeEndString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setGenTimeEnd(newGenTimeEnd);
        }
        if (vo.getReviewTimeStart() != null) {
            Date reviewTimeStart = vo.getReviewTimeStart();
            String reviewTimeStartString = DateTimeUtils.parseDateToString(reviewTimeStart, DateTimeUtils.DATE_PATTERN_DATE) + " 00:00:00";
            Date newReviewTimeStart = DateTimeUtils.parseStringToDate(reviewTimeStartString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setReviewTimeStart(newReviewTimeStart);
        }
        if (vo.getReviewTimeEnd() != null) {
            Date reviewTimeEnd = vo.getReviewTimeEnd();
            String reviewTimeEndString = DateTimeUtils.parseDateToString(reviewTimeEnd, DateTimeUtils.DATE_PATTERN_DATE) + " 23:59:59";
            Date newReviewTimeEnd = DateTimeUtils.parseStringToDate(reviewTimeEndString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setReviewTimeEnd(newReviewTimeEnd);
        }
        QueryCallCenterCustomerReviewDto dto = new QueryCallCenterCustomerReviewDto();
        QueryCallCenterCustomerReviewVoAdapter.adapt(vo, dto);
        dto.setSearchType("1");
        //获取数据
        Page<CallCenterCustomerReviewVo> voPage = callCenterCustomerReviewService.queryCallCenterCustomerReview(dto);
        return ResponseUtil.ok(voPage);
    }

    */
/**
     * 前端页面创建实时和预约回访
     *//*

    @PostMapping("/createCallCenterAppointment")
    @ApiOperation(httpMethod = "POST", value = "创建实时和预约回访")
    public BaseResponse<Long> createCallCenterAppointment(@RequestBody CallCenterAddAppointmentVo vo) {
        //入参非空校验

        if (Objects.isNull(vo) && Objects.isNull(vo.getCustomerReviewId()) && Objects.isNull(vo.getControlFlag()) && Objects.isNull(vo.getAppointmentStaffId()) && Objects.isNull(vo.getAppointment())) {
            throw ExceptionUtil.createException(MotConstants.ERROR_DEL_TEMPLATE_ID_NOT_NULL);
        }
        //获取数据
        BaseResponse<Long> response = callCenterCustomerReviewService.createCallCenterAppointment(vo);
        return response;
    }

    */
/**
     * 创建普通任务回访
     *//*

    @PostMapping("/createCallCenterCustomerReview")
    @ApiOperation(httpMethod = "POST", value = "创建普通任务回访")
    public BaseResponse<Long> createCallCenterCustomerReview(@RequestBody CallCenterCreateCustomerReviewVo vo) {
        //入参非空校验

        if (Objects.isNull(vo)) {
            throw ExceptionUtil.createException(MotConstants.ERROR_CREATE_CUSTOMER_REVIEW_VO_NOT_NULL);
        }
        //获取数据
        Long customerReviewId = callCenterCustomerReviewService.createCallCenterCustomerReview(vo);
        return ResponseUtil.ok(customerReviewId);

    }

    */
/**
     * 查询回访任务摘要
     *//*

    @PostMapping("/queryCallCenterReviewAbstract")
    @ApiOperation(httpMethod = "POST", value = "查询回访任务摘要")
    public BaseResponse<ReviewAbstractVo> queryReviewAbstract(@RequestBody QueryReviewAbstractVo vo) throws ParseException {
        //入参非空校验
        if (Objects.isNull(vo) || StringUtils.isEmpty(vo.getCustomerId())) {
            return ResponseUtil.ok();
        }
        //获取数据
        ReviewAbstractVo reviewAbstractVo = callCenterCustomerReviewService.queryCallCenterReviewAbstract(vo);
        return ResponseUtil.ok(reviewAbstractVo);
    }


    */
/**
     * 前端页面删除回访
     *//*

    @PostMapping("/deleteCallCenterCustomerReview")
    @ApiOperation(httpMethod = "POST", value = "删除回访")
    public BaseResponse<Integer> deleteCallCenterCustomerReview(@RequestBody DeleteCallCenterCustomerReviewVo vo) {
        //入参非空校验

        if (Objects.isNull(vo) || CollectionUtils.isEmpty(vo.getCustomerReviewIds()) || StringUtils.isBlank(vo.getDeleteFlag())) {
            throw ExceptionUtil.createException(MotConstants.ERROR_DEL_TEMPLATE_ID_NOT_NULL);
        }
        //获取数据
        BaseResponse<Integer> response = callCenterCustomerReviewService.deleteCallCenterCustomerReview(vo);
        return ResponseUtil.ok(response.getData());

    }


    */
/**
     * 应用层-电话回访 普通任务回访-回访导入
     * I won‘t be back
     *//*

    @PostMapping("/importCustomerReview")
    @ApiOperation(httpMethod = "POST", value = "电话回访 普通任务回访-回访导入")
    public BaseResponse<Map<String, Object>> importCustomerReview(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            throw ExceptionUtil.createException(MotConstants.ERROR_IN_DTO_NOT_NULL);
        }
        //获取批次号
        Map<String, String> reviewTypeMap = getVisitDictMap(VisitTypeFlagEnum.CALL_CENTER_VISIT.getDesc());
        long batchNo = System.currentTimeMillis();
        CallCenterCustomerReviewDataListener callCenterCustomerReviewDataListener = new CallCenterCustomerReviewDataListener(this.reviewImportTimpMapper, this.dcManager, MotConstants.IMPORT_VISIT, batchNo, reviewTypeMap);
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ImportCustomerReviewDto.class, callCenterCustomerReviewDataListener).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
        StringBuffer errorDataList = callCenterCustomerReviewDataListener.getErrorMsg();
        int count = callCenterCustomerReviewDataListener.getRowNum();
        Map<String, Object> resultMap = new HashMap<>();
        String message = "";
        if (StringUtils.isBlank(errorDataList)) {
            message = "数据校验正常！" + "本次将导入" + count + "条到普通回访任务中。";
            resultMap.put("flag", "1");
        } else {
            message = errorDataList.toString();
            resultMap.put("flag", "2");
        }
        resultMap.put("batchId", batchNo);
        resultMap.put("checkMessage", message);
        return ResponseUtil.ok(resultMap);
    }

    */
/**
     * 应用层-电话回访 普通任务回访-回访导入确认
     *//*

    @PostMapping("/importCallCenterCustomerReviewSave")
    @ApiOperation(httpMethod = "POST", value = "电话回访 普通任务回访-回访导入确认")
    public BaseResponse<Integer> importCallCenterCustomerReviewSave(@RequestBody ImportCustomerReviewVo vo) {
        // 入参非空校验
        if (Objects.isNull(vo) || Objects.isNull(vo.getBatchNo())) {
            throw ExceptionUtil.createException(MotConstants.ERROR_IN_DTO_NOT_NULL);
        }
        //导入确认
        int count = callCenterCustomerReviewService.importCallCenterCustomerReviewSave(vo.getBatchNo());
        return ResponseUtil.ok(count);
    }


    */
/**
     * 根据回访类型查所有回访细类
     *
     * @param type
     * @return
     *//*

    private Map<String, String> getVisitDictMap(String type) {

        QueryReviewTypeDictInVo vo = new QueryReviewTypeDictInVo();

        List<String> highTypeList = new ArrayList<>();
        highTypeList.add(type);
        vo.setHighTypeList(highTypeList);
        BaseResponse<List<QueryReviewTypeDictOutVo>> response = customerReviewService.queryReviewCategoryAndReviewType(vo);

        List<QueryReviewTypeDictOutVo> list = Optional.ofNullable(response)
                .map(BaseResponse::getData)
                .orElse(new ArrayList<>());
        //获取字典map
        Map<String, String> map = list.stream().collect(Collectors
                .toMap(QueryReviewTypeDictOutVo::getDdKey, QueryReviewTypeDictOutVo::getDdValue, (k1, k2) -> k1));
        return map;
    }


    */
/**
     * 应用层-电话回访 普通任务回访-工单导入
     *//*

    @PostMapping("/importReviewTicket")
    @ApiOperation(httpMethod = "POST", value = "电话回访 普通任务回访-工单导入")
    public BaseResponse<Map<String, Object>> importReviewTicket(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        // 入参非空校验
        if (Objects.isNull(file)) {
            throw ExceptionUtil.createException(MotConstants.ERROR_IN_DTO_NOT_NULL);
        }
        //获取批次号
        Map<String, String> reviewTypeMap = getVisitDictMap(VisitTypeFlagEnum.CALL_CENTER_VISIT.getDesc());
        long batchNo = System.currentTimeMillis();
        CallCenterCustomerReviewDataListener callCenterCustomerReviewDataListener = new CallCenterCustomerReviewDataListener(this.reviewImportTimpMapper, this.dcManager, MotConstants.IMPORT_VISIT, batchNo, reviewTypeMap);
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ImportCustomerReviewDto.class, callCenterCustomerReviewDataListener).build()) {
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        }
        StringBuffer errorDataList = callCenterCustomerReviewDataListener.getErrorMsg();
        int count = callCenterCustomerReviewDataListener.getRowNum();
        Map<String, Object> resultMap = new HashMap<>();
        String message = "";
        if (StringUtils.isBlank(errorDataList)) {
            message = "数据校验正常！" + "本次将导入" + count + "条到普通回访任务中。";
            resultMap.put("flag", "1");
        } else {
            message = errorDataList.toString();
            resultMap.put("flag", "2");
        }
        resultMap.put("batchId", batchNo);
        resultMap.put("checkMessage", message);
        return ResponseUtil.ok(resultMap);


    }


    */
/**
     * 应用层-电话回访 普通任务回访-工单导入确认
     *//*

    @PostMapping("/importReviewTicketSave")
    @ApiOperation(httpMethod = "POST", value = "电话回访 普通任务回访-工单导入确认")
    public BaseResponse<Integer> importReviewTicketSave(@RequestBody ImportCustomerReviewVo vo) {
        // 入参非空校验
        if (Objects.isNull(vo) || Objects.isNull(vo.getBatchNo())) {
            throw ExceptionUtil.createException(MotConstants.ERROR_IN_DTO_NOT_NULL);
        }
        //导入确认
        int count = callCenterCustomerReviewService.importReviewTicketSave(vo.getBatchNo());
        return ResponseUtil.ok(count);
    }

    */
/**
     * 普通任务回访查询 分页导出任务数据及详细完成数据
     *//*

    @PostMapping("/exportPageCustomerReview")
    @ApiOperation(httpMethod = "POST", value = "导出分页查询回访列表")
    public void exportPageCustomerReview(HttpServletResponse response, @RequestBody QueryCallCenterCustomerReviewVo vo) throws ParseException, IOException {
        if (Objects.isNull(vo)) {
            throw ExceptionUtil.createException(TaskConstants.ERROR_CONTROL_FLAG_NOT_NULL);
        }

        if (vo.getGenTimeStart() != null) {
            Date genTimeStart = vo.getGenTimeStart();
            String genTimeStartString = DateTimeUtils.parseDateToString(genTimeStart, DateTimeUtils.DATE_PATTERN_DATE) + " 00:00:00";
            Date newGenTimeStart = DateTimeUtils.parseStringToDate(genTimeStartString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setGenTimeStart(newGenTimeStart);
        }
        if (vo.getGenTimeEnd() != null) {
            Date genTimeEnd = vo.getGenTimeEnd();
            String genTimeEndString = DateTimeUtils.parseDateToString(genTimeEnd, DateTimeUtils.DATE_PATTERN_DATE) + " 23:59:59";
            Date newGenTimeEnd = DateTimeUtils.parseStringToDate(genTimeEndString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setGenTimeEnd(newGenTimeEnd);
        }
        if (vo.getReviewTimeStart() != null) {
            Date reviewTimeStart = vo.getReviewTimeStart();
            String reviewTimeStartString = DateTimeUtils.parseDateToString(reviewTimeStart, DateTimeUtils.DATE_PATTERN_DATE) + " 00:00:00";
            Date newReviewTimeStart = DateTimeUtils.parseStringToDate(reviewTimeStartString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setReviewTimeStart(newReviewTimeStart);
        }
        if (vo.getReviewTimeEnd() != null) {
            Date reviewTimeEnd = vo.getReviewTimeEnd();
            String reviewTimeEndString = DateTimeUtils.parseDateToString(reviewTimeEnd, DateTimeUtils.DATE_PATTERN_DATE) + " 23:59:59";
            Date newReviewTimeEnd = DateTimeUtils.parseStringToDate(reviewTimeEndString, DateTimeUtils.DATE_PATTERN_TIME);
            vo.setReviewTimeEnd(newReviewTimeEnd);
        }
        QueryCallCenterCustomerReviewDto dto = new QueryCallCenterCustomerReviewDto();
        QueryCallCenterCustomerReviewVoAdapter.adapt(vo, dto);
        dto.setSearchType("1");
        //获取数据
        Page<CallCenterCustomerReviewVo> voPage = callCenterCustomerReviewService.queryCallCenterCustomerReview(dto);
        List<ExportCallCenterCustomerReviewDTO> exportList = new ArrayList<>();
        trans(voPage.getList(), exportList);
*/
/*

        //这里文件名如果涉及中文一定要使用URL编码，否则会乱码
        String fileName = URLEncoder.encode("客服中心客户回访.xlsx", StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), ExportCallCenterCustomerReviewDTO.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("客服中心客户回访导出详细数据").build();
*//*


        EasyExcelExportUtil.export(response, "客服中心客户回访", "客服中心客户回访导出详细数据", exportList, ExportCallCenterCustomerReviewDTO.class);
  */
/*      if (MotConstants.EXPORT_TYPE_NOW.equals(vo.getExportType()) || MotConstants.EXPORT_TYPE_CHOICE.equals(vo.getExportType())) {//导出当前页和指定页的时候
            List<ExportPageTaskDTO> list = taskManagementService.exportPageWmTask(queryTaskDto);
            excelWriter.write(list, writeSheet);
        } else if (MotConstants.EXPORT_TYPE_ALL.equals(vo.getExportType())) {//导出所有页的时候
            List<ExportPageTaskDTO> list = taskManagementService.exportPageWmTask(queryTaskDto);
            excelWriter.write(list, writeSheet);
            // 刷新数据到浏览器
            response.getOutputStream().flush();
        } else if (MotConstants.EXPORT_TYPE_SPREAD.equals(vo.getExportType())) {//导出指定页

            for (Integer i = vo.getStartPage(); i <= vo.getEndPage(); i++) {
                queryTaskDto.setPageNum(i);
                List<ExportPageTaskDTO> list = taskManagementService.exportPageWmTask(queryTaskDto);
                excelWriter.write(list, writeSheet);
                // 刷新数据到浏览器
                response.getOutputStream().flush();
            }
        }*//*

*/
/*        excelWriter.write(exportList, writeSheet);
        // 刷新数据到浏览器
        response.getOutputStream().flush();
        excelWriter.finish();*//*


    }

    public void trans(List<CallCenterCustomerReviewVo> list, List<ExportCallCenterCustomerReviewDTO> exportList) {

        for (CallCenterCustomerReviewVo callCenterCustomerReviewVo : list) {
            ExportCallCenterCustomerReviewDTO exportDto = new ExportCallCenterCustomerReviewDTO();
            exportDto.setCustomerId(callCenterCustomerReviewVo.getCustomerId());
            exportDto.setCustomerName(callCenterCustomerReviewVo.getCustomerName());
            exportDto.setReviewCategory(callCenterCustomerReviewVo.getReviewCategory());
            exportDto.setReviewType2(callCenterCustomerReviewVo.getReviewType2());
            exportDto.setOrganId(callCenterCustomerReviewVo.getOrganId());
            exportDto.setOrganName(callCenterCustomerReviewVo.getOrganName());
            exportDto.setManagerName(callCenterCustomerReviewVo.getManagerName());
            exportDto.setGenTime(callCenterCustomerReviewVo.getGenTime());
            exportDto.setTriggerInfo(callCenterCustomerReviewVo.getTriggerInfo());
            exportDto.setSufficientFlag(callCenterCustomerReviewVo.getSufficientFlag());
            exportDto.setReviewTime(callCenterCustomerReviewVo.getReviewTime());
            exportDto.setReviewStatus2(ReviewStatusEnum.getValueByKey(callCenterCustomerReviewVo.getReviewStatus2()));
            exportDto.setReviewResult2(ReviewResultsEnum.getValueByKey(callCenterCustomerReviewVo.getReviewResult2()));
            exportDto.setReviewChannel(ReviewChannelEnum.getValueByKey(callCenterCustomerReviewVo.getReviewChannel()));
            exportDto.setTicketCount(callCenterCustomerReviewVo.getTicketCount());
            exportDto.setComment(callCenterCustomerReviewVo.getComment());
            exportDto.setCallDuration(callCenterCustomerReviewVo.getCallDuration());
            exportDto.setAppointmentTime(callCenterCustomerReviewVo.getAppointmentTime());
            exportDto.setAppointmentComment(callCenterCustomerReviewVo.getAppointmentComment());
            exportDto.setAppointmentCount(callCenterCustomerReviewVo.getAppointmentCount());
//            exportDto.setAppointmentStaffId(callCenterCustomerReviewVo.getAppointmentStaffId());
            exportDto.setAppointmentStaffName(callCenterCustomerReviewVo.getAppointmentStaffName());
            exportDto.setCustomerReviewId(callCenterCustomerReviewVo.getCustomerReviewId());
            exportDto.setMailNo(callCenterCustomerReviewVo.getMailNo());
//            exportDto.setCustomerServiceId(callCenterCustomerReviewVo.getCustomerServiceId());
            exportDto.setCustomerServiceName(callCenterCustomerReviewVo.getCustomerServiceName());
            exportDto.setCreator(callCenterCustomerReviewVo.getCreator());
            exportDto.setCreateName(callCenterCustomerReviewVo.getCreateName());
            exportDto.setReviewMode(ReviewModeEnum.getValueByKey(callCenterCustomerReviewVo.getReviewMode()));
            exportList.add(exportDto);
        }
    }

}
*/
