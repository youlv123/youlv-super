<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模板名称" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="请输入模板名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['weixin:templates:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['weixin:templates:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['weixin:templates:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['weixin:templates:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templatesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主键ID" align="center" prop="templateId"/>
      <el-table-column label="模板名称" align="center" prop="templateName"/>
      <!--      <el-table-column label="模板内容" align="center" prop="templateContent" />-->

      <el-table-column label="模板内容" align="center">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.templateContent||scope.row.templateContent.length <= 50">
            {{ scope.row.templateContent }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.templateContent.substring(0, 50) + '...' }}
            <a @click="handleShowDetail(scope.row)"><span style="color: blue;">查看详情</span>
            </a>
          </span>
        </template>
      </el-table-column>



      <!-- 自定义列，用于展示 HTML 内容 -->
      <el-table-column label="HTML效果展示" align="center">
        <template slot-scope="scope">
          <!-- 使用 v-html 指令将 HTML 内容渲染到页面上 -->
          <div v-html="scope.row.templateContent"></div>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createdBy"/>
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy"/>
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
            v-hasPermi="['weixin:templates:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['weixin:templates:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="form.templateName" placeholder="请输入模板名称"/>
        </el-form-item>
        <el-form-item label="模板内容" prop="templateContent">
          <el-input v-model="form.templateContent" type="textarea" placeholder="请输入内容"/>
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
import {listTemplates, getTemplates, delTemplates, addTemplates, updateTemplates} from "@/api/weixin/templates";

export default {
  name: "Templates",
  data() {
    return {
      templateDetail: '',  // 用来存储模板详情
      dialogVisible: false, // 控制弹窗显示与隐藏
      highLightDetail: false,  // 控制是否显示高亮详情
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
      // 模板表格数据
      templatesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 模板内容时间范围
      daterangeCreatedDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateName: null,
        templateContent: null,
        createdBy: null,
        createdDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateName: [
          {required: true, message: "模板名称不能为空", trigger: "blur"}
        ],
        templateContent: [
          {required: true, message: "模板内容不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleCloseDialog() {
      this.dialogVisible = false;
    },
    handleShowDetail(row) {
      let code = row.templateContent;
      let highlightedCode = this.$Prism.highlight(code, this.$Prism.languages.html);
      this.templateDetail = highlightedCode;
      this.dialogVisible = true;
    },
    /** 查询模板列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreatedDate && '' != this.daterangeCreatedDate) {
        this.queryParams.params["beginCreatedDate"] = this.daterangeCreatedDate[0];
        this.queryParams.params["endCreatedDate"] = this.daterangeCreatedDate[1];
      }
      listTemplates(this.queryParams).then(response => {
        this.templatesList = response.rows;
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
        templateId: null,
        templateName: null,
        templateContent: null,
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
      this.daterangeCreatedDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.templateId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加模板";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const templateId = row.templateId || this.ids
      getTemplates(templateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.templateId != null) {
            updateTemplates(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTemplates(this.form).then(response => {
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
      const templateIds = row.templateId || this.ids;
      this.$modal.confirm('是否确认删除模板编号为"' + templateIds + '"的数据项？').then(function () {
        return delTemplates(templateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('weixin/templates/export', {
        ...this.queryParams
      }, `templates_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
