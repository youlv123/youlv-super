//package com.ruoyi.work;
//
//import cn.easyes.core.biz.PageInfo;
//import cn.easyes.core.biz.PageSerializable;
//import cn.easyes.core.conditions.LambdaEsQueryWrapper;
//import cn.nesc.wm.common.page.Page;
//import cn.nesc.wm.task.controller.model.TaskStatisticalVo;
//import cn.nesc.wm.task.infrustracture.db.adapter.QueryTaskStatisticalAdapter;
//import cn.nesc.wm.task.infrustracture.db.adapter.TaskStaffStatisticsAdapter;
//import cn.nesc.wm.task.infrustracture.db.mapper.EventRuleMapper;
//import cn.nesc.wm.task.infrustracture.db.model.EventRule;
//import cn.nesc.wm.task.infrustracture.db.model.QueryTaskStatisticalPo;
//import cn.nesc.wm.task.infrustracture.db.model.TaskMotStatistics;
//import cn.nesc.wm.task.infrustracture.db.model.TaskStaffStatistics;
//import cn.nesc.wm.task.infrustracture.es.mapper.EsTaskStaffStatisticsMapper;
//import cn.nesc.wm.task.infrustracture.oracle.model.MotHisStatistics;
//import cn.nesc.wm.task.service.model.*;
//import cn.nesc.wm.task.util.*;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import io.lettuce.core.ScriptOutputType;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.metrics.ParsedSum;
//import org.springframework.stereotype.Component;
//import test.common.exception.ExceptionUtil;
//import test.common.json.JsonUtil;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Component
//@Slf4j
//public class TaskStaffStatisticsManagerImpl implements TaskStaffStatisticsManager {
//    @Resource
//    private EsTaskStaffStatisticsMapper esTaskStaffStatisticsMapper;
//
//    @Resource
//    private EventRuleMapper eventRuleMapper;
//
//    @Resource
//    private EsHttpClient esHttpClient;
//
//    private final String templateName="allOrganId.ftlh";
//    private final String indexName="wm_task_staff_statistics";
//    private final String esKey="aggregations";
//
//    @Override
//    public String saveStaffStatistics(TaskStaffStatisticsEntity saveEntity) {
//        TaskStaffStatistics savePo = TaskStaffStatisticsAdapter.entityToPo(saveEntity);
//        esTaskStaffStatisticsMapper.insert(savePo);
//        return savePo.getId().toString();
//    }
//
//    @Override
//    public void saveStaffStatisticsBatch(List<TaskStaffStatisticsEntity> results) {
//        if (CollectionUtils.isEmpty(results)) {
//            return;
//        }
//        List<TaskStaffStatistics> pos = results.stream()
//                .map(TaskStaffStatisticsAdapter::entityToPo)
//                .collect(Collectors.toList());
//        esTaskStaffStatisticsMapper.insertBatch(pos);
//    }
//
//    @Override
//    public void delete(Date statisticalDate) {
//        if (Objects.isNull(statisticalDate)) {
//            return;
//        }
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        queryWrapper.eq(TaskStaffStatistics::getStatisticsDate, new Date(DateUtil.beginOfDay(statisticalDate).getTime()));
//        esTaskStaffStatisticsMapper.delete(queryWrapper);
//    }
//
//    @Override
//    public Long countByDate(Date statisticalDate) {
//        if (Objects.isNull(statisticalDate)) {
//            return 0L;
//        }
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        queryWrapper.eq(TaskStaffStatistics::getStatisticsDate, new Date(DateUtil.beginOfDay(statisticalDate).getTime()));
//        return esTaskStaffStatisticsMapper.selectCount(queryWrapper);
//    }
//
//    @Override
//    public Page<TaskStaffStatisticsEntity> listStatistics(QueryTaskStatisticalDto queryDto) {
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = buildQueryWrapper(queryPo);
//        if (queryDto.getSortDirection() != null && queryDto.getSortField() != null) {
//            if ("desc".equals(queryDto.getSortDirection())) {
//                queryWrapper.orderByDesc(queryDto.getSortField());
//            } else {
//                queryWrapper.orderByAsc(queryDto.getSortField());
//            }
//        } else {
//            queryWrapper.orderByAsc(TaskStaffStatistics::getOrganId);
//        }
//        PageInfo<TaskStaffStatistics> pageList = esTaskStaffStatisticsMapper.pageQuery(queryWrapper, queryDto.getPageNo(), queryDto.getPageSize());
//        List<TaskStaffStatistics> pos = Optional.ofNullable(pageList)
//                .map(PageSerializable::getList)
//                .orElse(new ArrayList<>());
//        Long total = Optional.ofNullable(pageList)
//                .map(PageSerializable::getTotal)
//                .orElse(0L);
//        Page<TaskStaffStatistics> pageInfo = Page.createPage(queryDto.getPageNo(), queryDto.getPageSize(), total, pos);
//        List<TaskStaffStatisticsEntity> listStatistics = pos.stream().map(TaskStaffStatisticsAdapter::poToEntity).collect(Collectors.toList());
//        return Page.convertPage(pageInfo, listStatistics);
//    }
//
//    @Override
//    public TaskStaffStatisticsEntity staffListStatisticsCal(QueryTaskStatisticalDto queryDto) {
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = buildQueryWrapper(queryPo);
//        queryWrapper.groupBy(TaskStaffStatistics::getOrganId).sum(TaskStaffStatistics::getMotTotalNum).
//                sum(TaskStaffStatistics::getDoneNum).sum(TaskStaffStatistics::getMessageDealNum).
//                sum(TaskStaffStatistics::getPhoneDealNum).sum(TaskStaffStatistics::getMailDealNum).
//                sum(TaskStaffStatistics::getTalkDealNum).sum(TaskStaffStatistics::getAuspiciousDealNum).
//                sum(TaskStaffStatistics::getTapeDealNum).sum(TaskStaffStatistics::getOtherDealNum).
//                sum(TaskStaffStatistics::getUndoNum).sum(TaskStaffStatistics::getMotNum);
//
//        SearchResponse pos = esTaskStaffStatisticsMapper.search(queryWrapper);
//        List<TaskStaffStatisticsEntity> staffEntites = new ArrayList<TaskStaffStatisticsEntity>();
//
//        Terms terms = pos.getAggregations().get("organIdTerms");
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            Set<String> keys = bucket.getAggregations().getAsMap().keySet();
//            TaskStaffStatisticsEntity entity = new TaskStaffStatisticsEntity();
//            TaskStaffStatisticsSum esEntity = new TaskStaffStatisticsSum();
//            JSONObject jsonEntity = new JSONObject();
//            jsonEntity.put("organId", bucket.getKeyAsString());
//            for (String key : keys
//            ) {
//
//                ParsedSum sum = (ParsedSum) bucket.getAggregations().getAsMap().get(key);
//                jsonEntity.put(key, sum.getValue());
//            }
//            esEntity = jsonEntity.toJavaObject(TaskStaffStatisticsSum.class);
//            entity = TaskStaffStatisticsEntity.builder().organId(bucket.getKeyAsString()).
//                    motTotalNum(esEntity.getMotTotalNumSum()).
//                    doneNum(esEntity.getDoneNumSum()).
//                    mailDealNum(esEntity.getMailDealNumSum()).
//                    messageDealNum(esEntity.getMessageDealNumSum()).
//                    phoneDealNum(esEntity.getPhoneDealNumSum()).
//                    talkDealNum(esEntity.getTalkDealNumSum()).
//                    auspiciousDealNum(esEntity.getAuspiciousDealNumSum()).
//                    tapeDealNum(esEntity.getTapeDealNumSum()).
//                    otherDealNum(esEntity.getOtherDealNumSum()).
//                    undoNum(esEntity.getUndoNumSum()).
//                    motNum(esEntity.getMotNumSum()).build();
//            staffEntites.add(entity);
//        }
//
//        Long motTotalNum = 0L;
//        Long doneNum = 0L;
//        Long mailDealNum = 0L;
//        Long messageDealNum = 0L;
//        Long phoneDealNum = 0L;
//        Long talkDealNum = 0L;
//        Long tapeDealNum = 0L;
//        Long auspiciousDealNum = 0L;
//        Long otherDealNum = 0L;
//        Long undoNum = 0L;
//        Long motNmu = 0L;
//
//        for (int i = 0; i < staffEntites.size(); i++) {
//            motTotalNum += staffEntites.get(i).getMotTotalNum();
//            doneNum += staffEntites.get(i).getDoneNum();
//            mailDealNum += staffEntites.get(i).getMailDealNum();
//            messageDealNum += staffEntites.get(i).getMessageDealNum();
//            phoneDealNum += staffEntites.get(i).getPhoneDealNum();
//            talkDealNum += staffEntites.get(i).getTalkDealNum();
//            tapeDealNum += staffEntites.get(i).getTapeDealNum();
//            auspiciousDealNum += staffEntites.get(i).getAuspiciousDealNum();
//            otherDealNum += staffEntites.get(i).getOtherDealNum();
//            undoNum += staffEntites.get(i).getUndoNum();
//            motNmu += staffEntites.get(i).getMotNum();
//        }
//
//        TaskStaffStatisticsEntity entity = new TaskStaffStatisticsEntity();
//        entity = TaskStaffStatisticsEntity.builder().motTotalNum(motTotalNum).
//                doneNum(doneNum).phoneDealNum(phoneDealNum).
//                messageDealNum(messageDealNum).mailDealNum(mailDealNum).
//                talkDealNum(talkDealNum).auspiciousDealNum(auspiciousDealNum).
//                tapeDealNum(tapeDealNum).otherDealNum(otherDealNum).
//                undoNum(undoNum).motNum(motNmu).build();
//
//        return entity;
//    }
//
//    @Override
//    public TaskStatisticalVo listStatisticsCal(QueryTaskStatisticalDto queryDto) {
//
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        //构建ES查询参数
//        Map<String, String> params = buildQueryParams(queryPo);
//        //使用模板构建ES查询语句
//        String requestBody = FreemarkerUtil.process(templateName, params);
//        System.out.println(requestBody);
//        //查询
//        String res = esHttpClient.search(indexName, requestBody);
//        System.out.println(res);
//        JSONObject parse = JSON.parseObject(res);
//        //拿到aggregations内容
//        String aggregations = parse.getString(esKey);
//        System.out.println(aggregations);
//        ObjectMapper objectMapper = new ObjectMapper();
//        //返回值转换成map
//        Map<String, Map<String, Double>> aggregationsMap = new HashMap<>();
//        try {
//            aggregationsMap = objectMapper.readValue(aggregations, new TypeReference<Map<String, Map<String, Double>>>() {
//            });
//        } catch (IOException e) {
//            log.error("TaskStaffStatisticsManagerImpl Translation  error = {}", e);
//            throw ExceptionUtil.createException(TaskConstants.ERROR_JSONTRANSLATION_FAIL);
//        }
//
//        TaskStatisticalVo result = new TaskStatisticalVo();
//        //事件数
//        Long motNum = aggregationsMap.get("motNumCount").get("value").longValue();
//        //任务总数
//        Long motTotalNum = aggregationsMap.get("motTotalNumCount").get("value").longValue();
//        //完成数
//        Long doneNum = aggregationsMap.get("doneNumCount").get("value").longValue();
//        //未完成数
//        Long undoNum = aggregationsMap.get("undoNumCount").get("value").longValue();
//        //完成率
//        String donePercent = "-";
//        //短信完成率
//        String messageDealPercent = "-";
//        //短信完成数
//        Long messageDealNum = aggregationsMap.get("messageDealNumCount").get("value").longValue();
//        //电话完成数
//        Long phoneDealNum = aggregationsMap.get("phoneDealNumCount").get("value").longValue();
//        //电话完成率
//        String phoneDealPercent = "-";
//        //邮件完成数
//        Long mailDealNum = aggregationsMap.get("mailDealNumCount").get("value").longValue();
//        //邮件完成率
//        String mailDealPercent = "-";
//        //面会完成数
//        Long talkDealNum = aggregationsMap.get("talkDealNumCount").get("value").longValue();
//        //面会完成率
//        String talkDealPercent = "-";
//        //吉时语完成数
//        Long auspiciousDealNum = aggregationsMap.get("auspiciousDealNumCount").get("value").longValue();
//        //吉时语完成率
//        String auspiciousDealPercent = "-";
//        //录音电话完成数
//        Long tapeDealNum = aggregationsMap.get("tapeDealNumCount").get("value").longValue();
//        //录音电话完成率
//        String tapeDealPercent = "-";
//        //其他完成数
//        Long otherDealNum = aggregationsMap.get("otherDealNumCount").get("value").longValue();
//        //其他完成率
//        String otherDealPercent = "-";
//        Long doneNumOther = aggregationsMap.get("doneNumOtherCount").get("value").longValue();
//
//        if (motTotalNum > 0) {
//            donePercent = String.format("%.2f", ((doneNum.doubleValue() / motTotalNum.doubleValue()) * 100)) + "%";
//        }
//        if (doneNumOther > 0) {
//            messageDealPercent = String.format("%.2f", ((messageDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            phoneDealPercent = String.format("%.2f", ((phoneDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            mailDealPercent = String.format("%.2f", ((mailDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            talkDealPercent = String.format("%.2f", ((talkDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            auspiciousDealPercent = String.format("%.2f", ((auspiciousDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            tapeDealPercent = String.format("%.2f", ((tapeDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//            otherDealPercent = String.format("%.2f", ((otherDealNum.doubleValue() / doneNumOther.doubleValue()) * 100)) + "%";
//        }
//        result.setMotNum(motNum);
//        result.setMotTotalNum(motTotalNum);
//        result.setDoneNum(doneNum);
//        result.setUndoNum(undoNum);
//        result.setDonePercent(donePercent);
//        result.setMessageDealPercent(messageDealPercent);
//        result.setMessageDealNum(messageDealNum);
//        result.setPhoneDealNum(phoneDealNum);
//        result.setPhoneDealPercent(phoneDealPercent);
//        result.setMailDealNum(mailDealNum);
//        result.setMailDealPercent(mailDealPercent);
//        result.setTalkDealNum(talkDealNum);
//        result.setTalkDealPercent(talkDealPercent);
//        result.setAuspiciousDealPercent(auspiciousDealPercent);
//        result.setAuspiciousDealNum(auspiciousDealNum);
//        result.setTapeDealNum(tapeDealNum);
//        result.setTapeDealPercent(tapeDealPercent);
//        result.setOtherDealNum(otherDealNum);
//        result.setOtherDealPercent(otherDealPercent);
//        return result;
//    }
//
//    @Override
//    public List<TaskStaffStatisticsEntity> listStatisticsByOrganId(QueryTaskStatisticalDto queryDto) {
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = buildQueryWrapper(queryPo);
//        queryWrapper.groupBy(TaskStaffStatistics::getOrganId).sum(TaskStaffStatistics::getMotTotalNum).
//                sum(TaskStaffStatistics::getDoneNum).sum(TaskStaffStatistics::getMessageDealNum).
//                sum(TaskStaffStatistics::getPhoneDealNum).sum(TaskStaffStatistics::getMailDealNum).
//                sum(TaskStaffStatistics::getTalkDealNum).sum(TaskStaffStatistics::getAuspiciousDealNum).
//                sum(TaskStaffStatistics::getTapeDealNum).sum(TaskStaffStatistics::getOtherDealNum).
//                sum(TaskStaffStatistics::getUndoNum).sum(TaskStaffStatistics::getMotNum);
//        SearchResponse pos = esTaskStaffStatisticsMapper.search(queryWrapper);
//        List<TaskStaffStatisticsEntity> midEntities = new ArrayList<>();
//        Terms terms = pos.getAggregations().get("organIdTerms");
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            Set<String> keys = bucket.getAggregations().getAsMap().keySet();
//            TaskStaffStatisticsEntity entity = new TaskStaffStatisticsEntity();
//            TaskStaffStatisticsSum esEntity = new TaskStaffStatisticsSum();
//            JSONObject jsonEntity = new JSONObject();
//            jsonEntity.put("organId", bucket.getKeyAsString());
//            for (String key : keys
//            ) {
//
//                ParsedSum sum = (ParsedSum) bucket.getAggregations().getAsMap().get(key);
//                jsonEntity.put(key, sum.getValue());
//            }
//            esEntity = jsonEntity.toJavaObject(TaskStaffStatisticsSum.class);
//            entity = TaskStaffStatisticsEntity.builder().organId(bucket.getKeyAsString()).
//                    motTotalNum(esEntity.getMotTotalNumSum()).
//                    doneNum(esEntity.getDoneNumSum()).
//                    mailDealNum(esEntity.getMailDealNumSum()).
//                    messageDealNum(esEntity.getMessageDealNumSum()).
//                    phoneDealNum(esEntity.getPhoneDealNumSum()).
//                    talkDealNum(esEntity.getTalkDealNumSum()).
//                    auspiciousDealNum(esEntity.getAuspiciousDealNumSum()).
//                    tapeDealNum(esEntity.getTapeDealNumSum()).
//                    motNum(esEntity.getMotNumSum()).
//                    otherDealNum(esEntity.getOtherDealNumSum()).
//                    undoNum(esEntity.getUndoNumSum()).build();
//            midEntities.add(entity);
//        }
//
//        return midEntities;
//    }
//
//    @Override
//    public Page<String> pageOrgan(QueryTaskStatisticalDto queryDto) {
//        if (StringUtils.isNotEmpty(queryDto.getOrganId())) {
//            return Page.singletonPage(queryDto.getOrganId());
//        }
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = buildOrganQueryWrapper(queryPo);
//        queryWrapper.distinct(TaskStaffStatistics::getOrganId);
//        queryWrapper.orderByAsc(TaskStaffStatistics::getOrganId);
//        PageInfo<TaskStaffStatistics> page = esTaskStaffStatisticsMapper.pageQuery(queryWrapper
//                , queryDto.getPageNo(), queryDto.getPageSize());
//
//        List<TaskStaffStatistics> items = Optional.ofNullable(page)
//                .map(PageSerializable::getList)
//                .orElse(new ArrayList<>());
//        Long total = Optional.ofNullable(page)
//                .map(PageSerializable::getTotal)
//                .orElse(0L);
//        Page<TaskStaffStatistics> pageInfo = Page.createPage(queryDto.getPageNo(), queryDto.getPageSize(), total, items);
//        List<String> organIds = pageInfo.getList().stream()
//                .map(TaskStaffStatistics::getOrganId)
//                .collect(Collectors.toList());
//
//        return Page.convertPage(pageInfo, organIds);
//    }
//
//    @Override
//    public Page<String> pageStaff(QueryTaskStatisticalDto queryDto) {
//
//        if (StringUtils.isNotEmpty(queryDto.getStaffId())) {
//            return Page.singletonPage(queryDto.getStaffId());
//        }
//        QueryTaskStatisticalPo queryPo = QueryTaskStatisticalAdapter.dtoToPo(queryDto);
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = buildQueryWrapper(queryPo);
//        queryWrapper.distinct(TaskStaffStatistics::getStaffId);
//        if (queryDto.getSortDirection() != null && queryDto.getSortField() != null) {
//            if ("desc".equals(queryDto.getSortDirection())) {
//                queryWrapper.orderByDesc(queryDto.getSortField());
//            } else {
//                queryWrapper.orderByAsc(queryDto.getSortField());
//            }
//        } else {
//            queryWrapper.orderByAsc(TaskStaffStatistics::getStaffId);
//        }
//        PageInfo<TaskStaffStatistics> page = esTaskStaffStatisticsMapper.pageQuery(queryWrapper
//                , queryDto.getPageNo(), queryDto.getPageSize());
//
//        List<TaskStaffStatistics> items = Optional.ofNullable(page)
//                .map(PageSerializable::getList)
//                .orElse(new ArrayList<>());
//        Long total = Optional.ofNullable(page)
//                .map(PageSerializable::getTotal)
//                .orElse(0L);
//        Page<TaskStaffStatistics> pageInfo = Page.createPage(queryDto.getPageNo(), queryDto.getPageSize(), total, items);
//        List<String> staffIds = pageInfo.getList().stream()
//                .map(TaskStaffStatistics::getStaffId)
//                .collect(Collectors.toList());
//
//        return Page.convertPage(pageInfo, staffIds);
//    }
//
//    @Override
//    public Long removeAll() {
//        return esTaskStaffStatisticsMapper.selectCount(new LambdaEsQueryWrapper<>());
//    }
//
//    @Override
//    public Integer saveBatch(List<TaskStaffStatistics> to) {
//        if (CollectionUtils.isEmpty(to)) {
//            return 0;
//        }
//        return esTaskStaffStatisticsMapper.insertBatch(to);
//    }
//
//    private LambdaEsQueryWrapper<TaskStaffStatistics> buildQueryWrapper(QueryTaskStatisticalPo queryPo) {
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        if (queryPo.getEndDate() != null) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String staticsDate = sdf.format(queryPo.getEndDate());
//            try {
//                queryWrapper.eq(TaskStaffStatistics::getStatisticsDate, sdf.parse(staticsDate));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        if (StringUtils.isNotEmpty(queryPo.getStaffId())) {
//            queryWrapper.eq(TaskStaffStatistics::getStaffId, queryPo.getStaffId());
//        } else {
//            queryWrapper.notIn(TaskStaffStatistics::getStaffId, "0", "-1");
//        }
////
////        if ("1".equals(queryPo.getYybFlag())) {
////            queryWrapper.ne(TaskStaffStatistics::getStaffId, "0");
////        }
//        if (StringUtils.isNotEmpty(queryPo.getReportType())) {
//            queryWrapper.eq(TaskStaffStatistics::getReportType, queryPo.getReportType());
//        }
//        //时间维度，如果为空默认按日
////        if (StringUtils.isEmpty(queryPo.getReportType())) {
////            queryWrapper.eq(TaskStaffStatistics::getReportType, "01");
////        }
//        if (StringUtils.isNotEmpty(queryPo.getOrganId())) {
//            queryWrapper.eq(TaskStaffStatistics::getOrganId, queryPo.getOrganId());
//        }
//        //执行类型，如果为空默认必做任务
////        if (StringUtils.isEmpty(queryPo.getExeType())) {
////            queryWrapper.eq(TaskStaffStatistics::getExeType, "00");
////        }
//
//        if (StringUtils.isNotEmpty(queryPo.getExeType())) {
//            queryWrapper.eq(TaskStaffStatistics::getExeType, queryPo.getExeType());
//        }
////        if (StringUtils.isNotEmpty(queryPo.getOrganId())) {
////            queryWrapper.eq(TaskStaffStatistics::getOrganId, queryPo.getOrganId());
////        }
////
////        if (StringUtils.isNotEmpty(queryPo.getExeType())) {
////            queryWrapper.eq(TaskStaffStatistics::getExeType, queryPo.getExeType());
////        }
////        if (CollectionUtils.isNotEmpty(queryPo.getStaffIds())) {
////            queryWrapper.in(TaskStaffStatistics::getStaffId, queryPo.getStaffIds());
////        }
////        if (CollectionUtils.isNotEmpty(queryPo.getOrganIds())) {
////            queryWrapper.in(TaskStaffStatistics::getOrganId, queryPo.getOrganIds());
////        }
//        if (queryPo.getControlFlag()) {
//            if (CollectionUtils.isNotEmpty(queryPo.getControlStaffIds())) {
//                queryWrapper.in(TaskStaffStatistics::getStaffId, queryPo.getControlStaffIds());
//            }
//            if (CollectionUtils.isNotEmpty(queryPo.getControlOrganIds())) {
//                queryWrapper.in(TaskStaffStatistics::getOrganId, queryPo.getControlOrganIds());
//            }
//        }
//        return queryWrapper;
//    }
//
//    private Map<String, String> buildQueryParams(QueryTaskStatisticalPo queryPo) {
//        Map<String, String> params = new HashMap<>();
//        if (queryPo.getEndDate() != null) {
//            SimpleDateFormat formatter = new SimpleDateFormat(Constance.FORMAT_ES_DATE_TIME_MILLISECOND);
//            // 设置时区为UTC
//            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//            String strDate = formatter.format(queryPo.getEndDate());
//            params.put("statisticsDate", strDate);
//        }
//
//        if (StringUtils.isNotEmpty(queryPo.getReportType())) {
//            params.put("reportType", queryPo.getReportType());
//        }
//
//        if (StringUtils.isNotEmpty(queryPo.getOrganId())) {
//            params.put("organId", queryPo.getOrganId());
//        }
//        if (StringUtils.isNotEmpty(queryPo.getExeType())) {
//            params.put("exeType", queryPo.getExeType());
//        }
//
//        return params;
//    }
//
//    private LambdaEsQueryWrapper<TaskStaffStatistics> buildOrganQueryWrapper(QueryTaskStatisticalPo queryPo) {
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        if (queryPo.getBeginDate() != null) {
//            queryWrapper.ge(TaskStaffStatistics::getStatisticsDate, new Date(queryPo.getBeginDate().getTime()));
//        }
//        if (queryPo.getEndDate() != null) {
//            queryWrapper.lt(TaskStaffStatistics::getStatisticsDate, new Date(queryPo.getEndDate().getTime()));
//        }
//        if (StringUtils.isNotEmpty(queryPo.getOrganId())) {
//            queryWrapper.eq(TaskStaffStatistics::getOrganId, queryPo.getOrganId());
//        }
//        if (StringUtils.isNotEmpty(queryPo.getStaffId())) {
//            queryWrapper.eq(TaskStaffStatistics::getStaffId, queryPo.getStaffId());
//        }
//        if (StringUtils.isNotEmpty(queryPo.getExeType())) {
//            queryWrapper.eq(TaskStaffStatistics::getExeType, queryPo.getExeType());
//        }
//        if (CollectionUtils.isNotEmpty(queryPo.getStaffIds())) {
//            queryWrapper.in(TaskStaffStatistics::getStaffId, queryPo.getStaffIds());
//        }
//        if (CollectionUtils.isNotEmpty(queryPo.getOrganIds())) {
//            queryWrapper.in(TaskStaffStatistics::getOrganId, queryPo.getOrganIds());
//        }
//        if (queryPo.getControlFlag()) {
//            if (CollectionUtils.isNotEmpty(queryPo.getControlStaffIds())) {
//                queryWrapper.in(TaskStaffStatistics::getStaffId, queryPo.getControlStaffIds());
//            }
//            if (CollectionUtils.isNotEmpty(queryPo.getControlOrganIds())) {
//                queryWrapper.in(TaskStaffStatistics::getOrganId, queryPo.getControlOrganIds());
//            }
//        }
//        return queryWrapper;
//    }
//
//    public Date selectMaxStatisticsDate() {
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        queryWrapper.orderByDesc(TaskStaffStatistics::getStatisticsDate);
//        PageInfo<TaskStaffStatistics> lastStatistical = esTaskStaffStatisticsMapper.pageQuery(queryWrapper, 1, 10);
//        TaskStaffStatistics entity = lastStatistical.getList().get(0);
//        return entity.getStatisticsDate();
//    }
//
//    @Override
//    public Integer countByInfo(TaskMotStatisticsCompareDto dto) {
//        //根据传递的条件查询es是否存在对应数据
//        LambdaEsQueryWrapper<TaskStaffStatistics> queryWrapper = new LambdaEsQueryWrapper<>();
//        if (StringUtils.isNotBlank(dto.getOrganId())) {
//            queryWrapper.eq(TaskStaffStatistics::getOrganId, dto.getOrganId());
//        }
//        if (StringUtils.isNotBlank(dto.getStaffId())) {
//            queryWrapper.eq(TaskStaffStatistics::getStaffId, dto.getStaffId());
//        }
//        if (StringUtils.isNotBlank(dto.getExeType())) {
//            queryWrapper.eq(TaskStaffStatistics::getExeType, dto.getExeType());
//        }
//        if (StringUtils.isNotBlank(dto.getReportType())) {
//            queryWrapper.eq(TaskStaffStatistics::getReportType, dto.getReportType());
//        }
//        if (dto.getEventId() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getEventId, dto.getEventId());
//        }
//        if (dto.getDoneNum() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getDoneNum, dto.getDoneNum());
//        }
//        if (dto.getAuspiciousDealNum() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getAuspiciousDealNum, dto.getAuspiciousDealNum());
//        }
//        if (dto.getMotTotalNum() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getMotTotalNum, dto.getMotTotalNum());
//        }
//        if (dto.getMailDealNum() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getMailDealNum, dto.getMailDealNum());
//        }
//        if (dto.getMessageDealNum() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getMessageDealNum, dto.getMessageDealNum());
//        }
//        if (dto.getStatisticsDate() != null) {
//            queryWrapper.eq(TaskStaffStatistics::getStatisticsDate, dto.getStatisticsDate());
//        }
//        PageInfo<TaskStaffStatistics> taskMots = esTaskStaffStatisticsMapper.pageQuery(queryWrapper, 1,
//                10);
//        if (taskMots == null) {
//            return 0;
//        }
//        List<TaskStaffStatistics> list = taskMots.getList();
//        if (list != null) {
//            return list.size();
//        }
//        return 0;
//    }
//
//}
