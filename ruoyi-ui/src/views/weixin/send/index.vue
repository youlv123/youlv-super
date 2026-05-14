<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章发布时间">
        <el-date-picker
          v-model="daterangePublishTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="文章定时发布时间">
        <el-date-picker
          v-model="daterangeTimingPublishTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="文章表id" prop="articleId">
        <el-input
          v-model="queryParams.articleId"
          placeholder="请输入文章表id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发送状态" prop="sendFlag">
        <el-select v-model="queryParams.sendFlag" placeholder="请选择发送状态" clearable>
          <el-option
            v-for="dict in dict.type.wx_send_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="业务日期" prop="bizDate">
        <el-date-picker clearable
          v-model="queryParams.bizDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择业务日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="所属公众号" prop="belongAccount">
        <el-select v-model="queryParams.belongAccount" placeholder="请选择所属公众号" clearable>
          <el-option
            v-for="dict in dict.type.wx_gzh"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="微信侧发布状态" prop="publishStatus">
        <el-select v-model="queryParams.publishStatus" placeholder="请选择微信侧发布状态" clearable>
          <el-option
            v-for="dict in dict.type.wx_publish_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreatedDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['weixin:send:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['weixin:send:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['weixin:send:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['weixin:send:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sendList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="sendId" />
      <el-table-column label="文章发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文章定时发布时间" align="center" prop="timingPublishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timingPublishTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文章表id" align="center" prop="articleId" />
      <el-table-column label="发送状态" align="center" prop="sendFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_send_status" :value="scope.row.sendFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="发送失败原因" align="center" prop="sendFailMeassage" />
      <el-table-column label="业务日期" align="center" prop="bizDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bizDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属公众号" align="center" prop="belongAccount">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_gzh" :value="scope.row.belongAccount"/>
        </template>
      </el-table-column>
      <el-table-column label="微信素材库获取标志" align="center" prop="mediaId" />
      <el-table-column label="微信发布任务id" align="center" prop="publishId" />
      <el-table-column label="微信侧发布状态" align="center" prop="publishStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_publish_status" :value="scope.row.publishStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="图文的 article_id" align="center" prop="wxArticleId" />
      <el-table-column label="文章数量" align="center" prop="count" />
      <el-table-column label="不通过文章编号" align="center" prop="failIdx" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="更新时间" align="center" prop="updatedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['weixin:send:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['weixin:send:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改微信公众号文章发送对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章定时发布时间" prop="timingPublishTime">
          <el-date-picker clearable
            v-model="form.timingPublishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择文章定时发布时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSend, getSend, delSend, addSend, updateSend } from "@/api/weixin/send";

export default {
  name: "Send",
  dicts: ['wx_gzh', 'wx_publish_status', 'wx_send_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 微信公众号文章发送表格数据
      sendList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文章定时发布时间时间范围
      daterangePublishTime: [],
      // 文章定时发布时间时间范围
      daterangeTimingPublishTime: [],
      // 文章定时发布时间时间范围
      daterangeCreatedDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        publishTime: null,
        timingPublishTime: null,
        articleId: null,
        sendFlag: null,
        bizDate: null,
        belongAccount: null,
        publishStatus: null,
        createdBy: null,
        createdDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createdDate: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updatedDate: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询微信公众号文章发送列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePublishTime && '' != this.daterangePublishTime) {
        this.queryParams.params["beginPublishTime"] = this.daterangePublishTime[0];
        this.queryParams.params["endPublishTime"] = this.daterangePublishTime[1];
      }
      if (null != this.daterangeTimingPublishTime && '' != this.daterangeTimingPublishTime) {
        this.queryParams.params["beginTimingPublishTime"] = this.daterangeTimingPublishTime[0];
        this.queryParams.params["endTimingPublishTime"] = this.daterangeTimingPublishTime[1];
      }
      if (null != this.daterangeCreatedDate && '' != this.daterangeCreatedDate) {
        this.queryParams.params["beginCreatedDate"] = this.daterangeCreatedDate[0];
        this.queryParams.params["endCreatedDate"] = this.daterangeCreatedDate[1];
      }
      listSend(this.queryParams).then(response => {
        this.sendList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        sendId: null,
        publishTime: null,
        timingPublishTime: null,
        articleId: null,
        sendFlag: null,
        sendFailMeassage: null,
        bizDate: null,
        belongAccount: null,
        mediaId: null,
        publishId: null,
        publishStatus: null,
        wxArticleId: null,
        count: null,
        failIdx: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        updatedDate: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangePublishTime = [];
      this.daterangeTimingPublishTime = [];
      this.daterangeCreatedDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sendId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加微信公众号文章发送";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sendId = row.sendId || this.ids
      getSend(sendId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信公众号文章发送";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sendId != null) {
            updateSend(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSend(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sendIds = row.sendId || this.ids;
      this.$modal.confirm('是否确认删除微信公众号文章发送编号为"' + sendIds + '"的数据项？').then(function() {
        return delSend(sendIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('weixin/send/export', {
        ...this.queryParams
      }, `send_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
