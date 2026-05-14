<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="人员id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入人员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入人员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企微人员id" prop="wxId">
        <el-input
          v-model="queryParams.wxId"
          placeholder="请输入企微人员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="消息推送应用id" prop="applicationId">
        <el-select v-model="queryParams.applicationId" placeholder="请选择消息推送应用id" clearable>
          <el-option
            v-for="dict in dict.type.wx_application_id"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="微信消息类型" prop="messageType">
        <el-select v-model="queryParams.messageType" placeholder="请选择微信消息类型" clearable>
          <el-option
            v-for="dict in dict.type.wx_message_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="微信消息id" prop="wxMsgid">
        <el-input
          v-model="queryParams.wxMsgid"
          placeholder="请输入微信消息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推送标识" prop="pushFlag">
        <el-select v-model="queryParams.pushFlag" placeholder="请选择推送标识" clearable>
          <el-option
            v-for="dict in dict.type.wx_push_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:pushWxMessageRecord:add']"
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
          v-hasPermi="['system:pushWxMessageRecord:edit']"
        >修改</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:pushWxMessageRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:pushWxMessageRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pushWxMessageRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="pushId" width="100"/>
      <el-table-column label="人员id" align="center" prop="userId" width="100"/>
      <el-table-column label="人员姓名" align="center" prop="userName" width="100"/>
      <el-table-column label="企微人员id" align="center" prop="wxId" width="300"/>
      <el-table-column label="消息推送应用id" align="center" prop="applicationId" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_application_id" :value="scope.row.applicationId"/>
        </template>
      </el-table-column>
      <el-table-column label="企微任务表主键ID" align="center" prop="wxTaskId" width="160"/>
<!--      <el-table-column label="微信消息内容" align="center" prop="wxMessageContent" />-->
      <el-table-column label="微信消息内容" align="center" width="800">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.wxMessageContent||scope.row.wxMessageContent.length <= 50">
            {{ scope.row.wxMessageContent }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.wxMessageContent.substring(0, 50) + '...' }}
            <a @click="handleShowContent(scope.row)"><span style="color: blue;">查看详情</span>
            </a>
          </span>
        </template>
      </el-table-column>

      <el-table-column label="微信消息类型" align="center" prop="messageType" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_message_type" :value="scope.row.messageType"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="微信消息id" align="center" prop="wxMsgid" />-->
      <el-table-column label="推送标识" align="center" prop="pushFlag" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_push_flag" :value="scope.row.pushFlag"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="推送错误信息" align="center" prop="errorLog" />-->

      <el-table-column label="推送错误信息" align="center" width="550">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.errorLog||scope.row.errorLog.length <= 50">
            {{ scope.row.errorLog }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.errorLog.substring(0, 50) + '...' }}
            <a @click="handleShowDetail(scope.row)"><span style="color: blue;">查看详情</span>
            </a>
          </span>
        </template>
      </el-table-column>

<!--      <el-table-column label="创建人" align="center" prop="createdBy" />-->
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="更新人" align="center" prop="updatedBy" />
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
            v-hasPermi="['system:pushWxMessageRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:pushWxMessageRecord:remove']"
          >删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改企业微信推送消息记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog :visible="dialogVisible" @close="dialogVisible = false">
  <pre style="white-space: pre-wrap; word-wrap: break-word;">
    <code v-html="templateDetail"></code>
  </pre>
    </el-dialog>

  </div>
</template>

<script>
import { listPushWxMessageRecord, getPushWxMessageRecord, delPushWxMessageRecord, addPushWxMessageRecord, updatePushWxMessageRecord } from "@/api/system/pushWxMessageRecord";

export default {
  name: "PushWxMessageRecord",
  dicts: ['wx_application_id', 'wx_push_flag', 'wx_message_type'],
  data() {
    return {
      templateDetail: '',  // 用来存储详情
      dialogVisible: false, // 控制弹窗显示与隐藏
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
      // 企业微信推送消息记录表格数据
      pushWxMessageRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        wxId: null,
        applicationId: null,
        messageType: null,
        wxMsgid: null,
        pushFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询企业微信推送消息记录列表 */
    getList() {
      this.loading = true;
      listPushWxMessageRecord(this.queryParams).then(response => {
        this.pushWxMessageRecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleShowDetail(row) {
      let code = row.errorLog;
      let highlightedCode = this.$Prism.highlight(code, this.$Prism.languages.html);
      this.templateDetail = highlightedCode;
      this.dialogVisible = true;
    },

    handleShowContent(row) {
      let code = row.wxMessageContent;
      let highlightedCode = this.$Prism.highlight(code, this.$Prism.languages.html);
      this.templateDetail = highlightedCode;
      this.dialogVisible = true;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pushId: null,
        userId: null,
        userName: null,
        wxId: null,
        applicationId: null,
        wxTaskId: null,
        wxMessageContent: null,
        messageType: null,
        wxMsgid: null,
        pushFlag: null,
        errorLog: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        updatedDate: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.pushId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企业微信推送消息记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pushId = row.pushId || this.ids
      getPushWxMessageRecord(pushId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业微信推送消息记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pushId != null) {
            updatePushWxMessageRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPushWxMessageRecord(this.form).then(response => {
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
      const pushIds = row.pushId || this.ids;
      this.$modal.confirm('是否确认删除企业微信推送消息记录编号为"' + pushIds + '"的数据项？').then(function() {
        return delPushWxMessageRecord(pushIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/pushWxMessageRecord/export', {
        ...this.queryParams
      }, `pushWxMessageRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
