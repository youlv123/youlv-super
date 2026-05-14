<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务分组" prop="taskGroup">
        <el-select v-model="queryParams.taskGroup" placeholder="请选择任务分组" clearable>
          <el-option
            v-for="dict in dict.type.wx_message_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_job_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['system:wxTask:add']"
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
          v-hasPermi="['system:wxTask:edit']"
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
          v-hasPermi="['system:wxTask:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:wxTask:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wxTaskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="wxTaskId" width="100"/>
      <el-table-column label="任务名称" align="center" prop="taskName" width="200"/>
      <el-table-column label="任务分组" align="center" prop="taskGroup" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_message_type" :value="scope.row.taskGroup"/>
        </template>
      </el-table-column>
      <el-table-column label="cron表达式" align="center" prop="cron" width="150"/>

      <el-table-column label="任务状态" align="center" prop="taskStatus" width="120">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_job_status" :value="scope.row.taskStatus"/>
        </template>
      </el-table-column>


<!--      <el-table-column label="任务状态" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <el-switch-->
<!--            v-model="scope.row.status"-->
<!--            active-value="0"-->
<!--            inactive-value="1"-->
<!--            @change="handleStatusChange(scope.row)"-->
<!--          ></el-switch>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="人员id" align="center" prop="userId" width="120"/>
      <el-table-column label="人员姓名" align="center" prop="userName" width="120"/>
      <el-table-column label="企微人员id" align="center" prop="wxId" width="300"/>
      <el-table-column label="消息推送应用id" align="center" prop="applicationId" width="120">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_application_id" :value="scope.row.applicationId"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="微信消息内容" align="center" prop="wxMessageContent" />-->

      <el-table-column label="微信消息内容" align="center" width="550">
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
            v-hasPermi="['system:wxTask:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:wxTask:remove']"
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

    <!-- 添加或修改企微消息推送任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务分组" prop="taskGroup">
          <el-select v-model="form.taskGroup" placeholder="请选择任务分组">
            <el-option
              v-for="dict in dict.type.wx_message_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="cron表达式" prop="cron">
          <el-input v-model="form.cron" placeholder="请输入cron表达式" />
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
          <el-select v-model="form.taskStatus" placeholder="请选择任务状态">
            <el-option
              v-for="dict in dict.type.sys_job_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="人员id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入人员id" />
        </el-form-item>
        <el-form-item label="人员姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入人员姓名" />
        </el-form-item>
        <el-form-item label="企微人员id" prop="wxId">
          <el-input v-model="form.wxId" placeholder="请输入企微人员id" />
        </el-form-item>
        <el-form-item label="消息推送应用id" prop="applicationId">
          <el-select v-model="form.applicationId" placeholder="请选择消息推送应用id">
            <el-option
              v-for="dict in dict.type.wx_application_id"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="微信消息内容">
          <editor v-model="form.wxMessageContent" :min-height="192"/>
        </el-form-item>
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
import { listWxTask, getWxTask, delWxTask, addWxTask, updateWxTask } from "@/api/system/wxTask";

export default {
  name: "WxTask",
  dicts: ['wx_application_id', 'sys_job_status', 'wx_message_type'],
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
      // 企微消息推送任务表格数据
      wxTaskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        taskGroup: null,
        taskStatus: null,
        userId: null,
        userName: null,
        wxId: null,
        applicationId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskName: [
          { required: true, message: "任务名称不能为空", trigger: "blur" }
        ],
        taskGroup: [
          { required: true, message: "任务分组不能为空", trigger: "change" }
        ],
        cron: [
          { required: true, message: "cron表达式不能为空", trigger: "blur" }
        ],
        wxId: [
          { required: true, message: "企微人员id不能为空", trigger: "blur" }
        ],
        applicationId: [
          { required: true, message: "消息推送应用id不能为空", trigger: "change" }
        ],
        wxMessageContent: [
          { required: true, message: "微信消息内容不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询企微消息推送任务列表 */
    getList() {
      this.loading = true;
      listWxTask(this.queryParams).then(response => {
        this.wxTaskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        wxTaskId: null,
        taskName: null,
        taskGroup: null,
        cron: null,
        taskStatus: null,
        userId: null,
        userName: null,
        wxId: null,
        applicationId: null,
        wxMessageContent: null,
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
      this.ids = selection.map(item => item.wxTaskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企微消息推送任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wxTaskId = row.wxTaskId || this.ids
      getWxTask(wxTaskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企微消息推送任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wxTaskId != null) {
            updateWxTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWxTask(this.form).then(response => {
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
      const wxTaskIds = row.wxTaskId || this.ids;
      this.$modal.confirm('是否确认删除企微消息推送任务编号为"' + wxTaskIds + '"的数据项？').then(function() {
        return delWxTask(wxTaskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/wxTask/export', {
        ...this.queryParams
      }, `wxTask_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
