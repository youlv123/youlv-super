<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摘要" prop="digest">
        <el-input
          v-model="queryParams.digest"
          placeholder="请输入摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入文章描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" 是否打开评论" prop="needOpenComment">
        <el-select v-model="queryParams.needOpenComment" placeholder="请选择 是否打开评论" clearable>
          <el-option
            v-for="dict in dict.type.wx_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="评论范围" prop="onlyFansCanComment">
        <el-select v-model="queryParams.onlyFansCanComment" placeholder="请选择评论范围" clearable>
          <el-option
            v-for="dict in dict.type.only_fans_can_comment"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文章类型" prop="articleType">
        <el-select v-model="queryParams.articleType" placeholder="请选择文章类型" clearable>
          <el-option
            v-for="dict in dict.type.article_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="自动审核标识" prop="automaticAuditFlag">
        <el-select v-model="queryParams.automaticAuditFlag" placeholder="请选择自动审核标识" clearable>
          <el-option
            v-for="dict in dict.type.audit_status_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="人工审核标识" prop="manualAuditFlag">
        <el-select v-model="queryParams.manualAuditFlag" placeholder="请选择人工审核标识" clearable>
          <el-option
            v-for="dict in dict.type.audit_status_type"
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
          v-hasPermi="['weixin:article:add']"
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
          v-hasPermi="['weixin:article:edit']"
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
          v-hasPermi="['weixin:article:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['weixin:article:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="articleId" />
      <el-table-column label="文章标题" align="center" prop="title" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="摘要" align="center" prop="digest" />
      <el-table-column label="文章描述" align="center" prop="description" />
<!--      <el-table-column label="图文具体内容" align="center" prop="content" />-->

      <el-table-column label="图文具体内容" align="center">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.content||scope.row.content.length <= 50">
            {{ scope.row.content }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.content.substring(0, 50) + '...' }}
            <a @click="handleShowDetail(scope.row)"><span style="color: blue;">查看详情</span>
            </a>
          </span>
        </template>
      </el-table-column>



      <!-- 自定义列，用于展示 HTML 内容 -->
      <el-table-column label="HTML效果展示" align="center">
        <template slot-scope="scope">
          <div @click="openArticleModal(scope.row.content)" style="color: blue; cursor: pointer;">点击查看</div>
        </template>
      </el-table-column>



      <el-table-column label="原文地址" align="center" prop="contentSourceUrl" />
      <el-table-column label="封面图片素材id" align="center" prop="thumbMediaId" />
      <el-table-column label="文章封面主键id" align="center" prop="coverImage" />
      <el-table-column label=" 是否打开评论" align="center" prop="needOpenComment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_yes_no" :value="scope.row.needOpenComment"/>
        </template>
      </el-table-column>
      <el-table-column label="评论范围" align="center" prop="onlyFansCanComment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.only_fans_can_comment" :value="scope.row.onlyFansCanComment"/>
        </template>
      </el-table-column>
      <el-table-column label="文章类型" align="center" prop="articleType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_type" :value="scope.row.articleType"/>
        </template>
      </el-table-column>
      <el-table-column label="文章字数" align="center" prop="articleWordCount" />
      <el-table-column label="文章发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所使用模板id" align="center" prop="templateId" />
      <el-table-column label="阅读量" align="center" prop="readCount" />
      <el-table-column label="点赞量" align="center" prop="likeCount" />
      <el-table-column label="评论量" align="center" prop="commentCount" />
      <el-table-column label="分享量" align="center" prop="shareCount" />
      <el-table-column label="自动审核标识" align="center" prop="automaticAuditFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.audit_status_type" :value="scope.row.automaticAuditFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="自动审核意见" align="center" prop="automaticAuditOpinion" />
      <el-table-column label="人工审核标识" align="center" prop="manualAuditFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.audit_status_type" :value="scope.row.manualAuditFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="人工审核意见" align="center" prop="manualAuditOpinion" />
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
            v-hasPermi="['weixin:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['weixin:article:remove']"
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

    <!-- 添加或修改微信公众号文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="摘要" prop="digest">
          <el-input v-model="form.digest" placeholder="请输入摘要" />
        </el-form-item>
        <el-form-item label="文章描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入文章描述" />
        </el-form-item>
        <el-form-item label="原文地址" prop="contentSourceUrl">
          <el-input v-model="form.contentSourceUrl" placeholder="请输入原文地址" />
        </el-form-item>
        <el-form-item label=" 是否打开评论" prop="needOpenComment">
          <el-select v-model="form.needOpenComment" placeholder="请选择 是否打开评论">
            <el-option
              v-for="dict in dict.type.wx_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评论范围" prop="onlyFansCanComment">
          <el-select v-model="form.onlyFansCanComment" placeholder="请选择评论范围">
            <el-option
              v-for="dict in dict.type.only_fans_can_comment"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章类型" prop="articleType">
          <el-select v-model="form.articleType" placeholder="请选择文章类型">
            <el-option
              v-for="dict in dict.type.article_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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

    <el-dialog :visible="articleModalVisible" @update:visible="articleModalVisible = arguments[0]">
      <div v-html="articleContent"></div>
    </el-dialog>

  </div>
</template>

<script>
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/weixin/article";

export default {
  name: "Article",
  dicts: ['article_type', 'audit_status_type', 'wx_yes_no', 'only_fans_can_comment'],
  data() {
    return {
      articleModalVisible: false,
      articleContent: '<p>这是一篇公众号文章的内容。</p><p>高亮蓝色显示的四个字。</p>',
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
      // 微信公众号文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文章类型时间范围
      daterangeCreatedDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        author: null,
        digest: null,
        description: null,
        needOpenComment: null,
        onlyFansCanComment: null,
        articleType: null,
        automaticAuditFlag: null,
        manualAuditFlag: null,
        createdBy: null,
        createdDate: null,
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
    openArticleModal(content) {
      this.articleContent = content;
      this.articleModalVisible = true;
    },
    handleShowDetail(row) {
      let code = row.content;
      let highlightedCode = this.$Prism.highlight(code, this.$Prism.languages.html);
      this.templateDetail = highlightedCode;
      this.dialogVisible = true;
    },
    /** 查询微信公众号文章列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreatedDate && '' != this.daterangeCreatedDate) {
        this.queryParams.params["beginCreatedDate"] = this.daterangeCreatedDate[0];
        this.queryParams.params["endCreatedDate"] = this.daterangeCreatedDate[1];
      }
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
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
        articleId: null,
        title: null,
        author: null,
        digest: null,
        description: null,
        content: null,
        contentSourceUrl: null,
        thumbMediaId: null,
        coverImage: null,
        needOpenComment: null,
        onlyFansCanComment: null,
        articleType: null,
        articleWordCount: null,
        publishTime: null,
        templateId: null,
        readCount: null,
        likeCount: null,
        commentCount: null,
        shareCount: null,
        automaticAuditFlag: null,
        automaticAuditOpinion: null,
        manualAuditFlag: null,
        manualAuditOpinion: null,
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
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加微信公众号文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const articleId = row.articleId || this.ids
      getArticle(articleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信公众号文章";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.articleId != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
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
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否确认删除微信公众号文章编号为"' + articleIds + '"的数据项？').then(function() {
        return delArticle(articleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('weixin/article/export', {
        ...this.queryParams
      }, `article_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
