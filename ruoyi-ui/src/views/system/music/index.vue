<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="音乐名称" prop="musicName">
        <el-input
          v-model="queryParams.musicName"
          placeholder="请输入音乐名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="歌手" prop="singer">
        <el-input
          v-model="queryParams.singer"
          placeholder="请输入歌手"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="音乐类型" prop="musicType">
        <el-select v-model="queryParams.musicType" placeholder="请选择音乐类型" clearable>
          <el-option
            v-for="dict in dict.type.music_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否使用" prop="useFlag">
        <el-select v-model="queryParams.useFlag" placeholder="请选择是否使用" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
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
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:music:add']"
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
          v-hasPermi="['system:music:edit']"
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
          v-hasPermi="['system:music:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:music:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="musicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="musicId" />
      <el-table-column label="音乐名称" align="center" prop="musicName" />
      <el-table-column label="歌手" align="center" prop="singer" />
      <el-table-column label="音乐类型" align="center" prop="musicType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.music_type" :value="scope.row.musicType"/>
        </template>
      </el-table-column>
          <el-table-column label="网易云音乐链接" align="center" prop="cloudmusicUrl" width="340">
            <template slot-scope="scope">
              <iframe :src="scope.row.cloudmusicUrl" frameborder="no" border="0" marginwidth="0" marginheight="0" width="330" height="86"></iframe>
            </template>
          </el-table-column>
      <el-table-column label="qq音乐链接" align="center" prop="qqmusicUrl" />
      <el-table-column label="云盘链接" align="center" prop="cloudUrl" />
      <el-table-column label="是否使用" align="center" prop="useFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.useFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="使用视频链接" align="center" prop="useVideoUrl" />
      <el-table-column label="使用时间" align="center" prop="useDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.useDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="灵感备注信息" align="center" prop="remark" />-->
      <el-table-column label="灵感备注信息" align="center">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.remark||scope.row.remark.length <= 50">
            {{ scope.row.remark }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.remark.substring(0, 50) + '...' }}
            <a @click="handleShowDetail(scope.row)"><span style="color: blue;">查看详情</span>
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
            v-hasPermi="['system:music:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:music:remove']"
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

    <!-- 添加或修改背景音乐对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="音乐名称" prop="musicName">
          <el-input v-model="form.musicName" placeholder="请输入音乐名称" />
        </el-form-item>
        <el-form-item label="歌手" prop="singer">
          <el-input v-model="form.singer" placeholder="请输入歌手" />
        </el-form-item>
        <el-form-item label="音乐类型" prop="musicType">
          <el-select v-model="form.musicType" placeholder="请选择音乐类型">
            <el-option
              v-for="dict in dict.type.music_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="网易云音乐链接" prop="cloudmusicUrl">
          <el-input v-model="form.cloudmusicUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="qq音乐链接" prop="qqmusicUrl">
          <el-input v-model="form.qqmusicUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="云盘链接" prop="cloudUrl">
          <el-input v-model="form.cloudUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否使用" prop="useFlag">
          <el-select v-model="form.useFlag" placeholder="请选择是否使用">
            <el-option
              v-for="dict in dict.type.sys_yes_no"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用视频链接" prop="useVideoUrl">
          <el-input v-model="form.useVideoUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="使用时间" prop="useDate">
          <el-date-picker clearable
            v-model="form.useDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择使用时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="灵感备注信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listMusic, getMusic, delMusic, addMusic, updateMusic } from "@/api/system/music";

export default {
  name: "Music",
  dicts: ['music_type', 'sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      templateDetail: '',  // 用来存储详情
      dialogVisible: false, // 控制弹窗显示与隐藏
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
      // 背景音乐表格数据
      musicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 灵感备注信息时间范围
      daterangeUseDate: [],
      // 灵感备注信息时间范围
      daterangeCreatedDate: [],
      // 灵感备注信息时间范围
      daterangeUpdatedDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        musicName: null,
        singer: null,
        musicType: null,
        useFlag: null,
        createdBy: null,
        updatedBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        musicName: [
          { required: true, message: "音乐名称不能为空", trigger: "blur" }
        ],
        musicType: [
          { required: true, message: "音乐类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询背景音乐列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeUseDate && '' != this.daterangeUseDate) {
        this.queryParams.params["beginUseDate"] = this.daterangeUseDate[0];
        this.queryParams.params["endUseDate"] = this.daterangeUseDate[1];
      }
      if (null != this.daterangeCreatedDate && '' != this.daterangeCreatedDate) {
        this.queryParams.params["beginCreatedDate"] = this.daterangeCreatedDate[0];
        this.queryParams.params["endCreatedDate"] = this.daterangeCreatedDate[1];
      }
      if (null != this.daterangeUpdatedDate && '' != this.daterangeUpdatedDate) {
        this.queryParams.params["beginUpdatedDate"] = this.daterangeUpdatedDate[0];
        this.queryParams.params["endUpdatedDate"] = this.daterangeUpdatedDate[1];
      }
      listMusic(this.queryParams).then(response => {
        this.musicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleShowDetail(row) {
      let code = row.remark;
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
        musicId: null,
        musicName: null,
        singer: null,
        musicType: null,
        cloudmusicUrl: null,
        qqmusicUrl: null,
        cloudUrl: null,
        useFlag: null,
        useVideoUrl: null,
        useDate: null,
        remark: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        userId: null,
        userName: null,
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
      this.daterangeUseDate = [];
      this.daterangeCreatedDate = [];
      this.daterangeUpdatedDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.musicId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加背景音乐";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const musicId = row.musicId || this.ids
      getMusic(musicId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改背景音乐";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.musicId != null) {
            updateMusic(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMusic(this.form).then(response => {
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
      const musicIds = row.musicId || this.ids;
      this.$modal.confirm('是否确认删除背景音乐编号为"' + musicIds + '"的数据项？').then(function() {
        return delMusic(musicIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/music/export', {
        ...this.queryParams
      }, `music_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
