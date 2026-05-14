<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="理财名称" prop="financialProductName">
        <el-input
          v-model="queryParams.financialProductName"
          placeholder="请输入理财名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="理财分组" prop="financialGroup">
        <el-select v-model="queryParams.financialGroup" placeholder="请选择理财分组" clearable>
          <el-option
            v-for="dict in dict.type.financial_group"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="理财状态" prop="financialStatus">
        <el-select v-model="queryParams.financialStatus" placeholder="请选择理财状态" clearable>
          <el-option
            v-for="dict in dict.type.financial_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="购买平台" prop="purchasePlatform">
        <el-input
          v-model="queryParams.purchasePlatform"
          placeholder="请输入购买平台"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购买时间">
        <el-date-picker
          v-model="daterangePurchaseTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="确认时间">
        <el-date-picker
          v-model="daterangeConfirmTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="到期时间">
        <el-date-picker
          v-model="daterangeExpirationDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="赎回时间">
        <el-date-picker
          v-model="daterangeRedemptionTime"
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
          v-hasPermi="['system:financialRecord:add']"
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
          v-hasPermi="['system:financialRecord:edit']"
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
          v-hasPermi="['system:financialRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:financialRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="financialRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="financialRecordId" width="60"/>
<!--      <el-table-column label="推送任务表主键ID" align="center" prop="wxTaskId" />-->
      <el-table-column label="理财名称" align="center" prop="financialProductName" width="180"/>
      <el-table-column label="理财分组" align="center" prop="financialGroup" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.financial_group" :value="scope.row.financialGroup"/>
        </template>
      </el-table-column>
      <el-table-column label="到期提醒cron表达式" align="center" prop="cron" width="150" />
<!--      <el-table-column label="默认到期提醒" align="center" prop="defaultCron" />-->
      <el-table-column label="理财状态" align="center" prop="financialStatus" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.financial_status" :value="scope.row.financialStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="购买平台" align="center" prop="purchasePlatform" width="100"/>
      <el-table-column label="购买时间" align="center" prop="purchaseTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="确认时间" align="center" prop="confirmTime" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.confirmTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="持有时间" align="center" prop="holdingTime" width="100"/>
      <el-table-column label="到期时间" align="center" prop="expirationDate" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="赎回时间" align="center" prop="redemptionTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.redemptionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center" prop="amount" width="100"/>
      <el-table-column label="收益率" align="center" prop="yield" width="80"/>
      <el-table-column label="赎回路径" align="center" prop="redemptionPath" width="250"/>
      <el-table-column label="盈利" align="center" prop="profit" width="100"/>
      <el-table-column label="备注" align="center" prop="remark" width="250"/>
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="更新时间" align="center" prop="updatedDate" width="150">
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
            v-hasPermi="['system:financialRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:financialRecord:remove']"
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

    <!-- 添加或修改理财记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="理财名称" prop="financialProductName">
          <el-input v-model="form.financialProductName" placeholder="请输入理财名称" />
        </el-form-item>
        <el-form-item label="理财分组" prop="financialGroup">
          <el-select v-model="form.financialGroup" placeholder="请选择理财分组">
            <el-option
              v-for="dict in dict.type.financial_group"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="到期提醒cron表达式" prop="cron">
          <el-input v-model="form.cron" placeholder="请输入到期提醒cron表达式" />
        </el-form-item>
        <el-form-item label="默认到期提醒" prop="defaultCron">
          <el-input v-model="form.defaultCron" placeholder="请输入默认到期提醒" />
        </el-form-item>
        <el-form-item label="理财状态" prop="financialStatus">
          <el-select v-model="form.financialStatus" placeholder="请选择理财状态">
            <el-option
              v-for="dict in dict.type.financial_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="购买平台" prop="purchasePlatform">
          <el-input v-model="form.purchasePlatform" placeholder="请输入购买平台" />
        </el-form-item>
        <el-form-item label="购买时间" prop="purchaseTime">
          <el-date-picker clearable
            v-model="form.purchaseTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择购买时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="确认时间" prop="confirmTime">
          <el-date-picker clearable
                          v-model="form.confirmTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择确认时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="持有时间" prop="holdingTime">
          <el-input v-model="form.holdingTime" placeholder="请输入持有时间" />
        </el-form-item>
        <el-form-item label="到期时间" prop="expirationDate">
          <el-date-picker clearable
            v-model="form.expirationDate"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择到期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="赎回时间" prop="redemptionTime">
          <el-date-picker clearable
                          v-model="form.redemptionTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择赎回时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="收益率" prop="yield">
          <el-input v-model="form.yield" placeholder="请输入收益率" />
        </el-form-item>
        <el-form-item label="赎回路径" prop="redemptionPath">
          <el-input v-model="form.redemptionPath" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="盈利" prop="profit">
          <el-input v-model="form.profit" placeholder="请输入盈利" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listFinancialRecord, getFinancialRecord, delFinancialRecord, addFinancialRecord, updateFinancialRecord } from "@/api/system/financialRecord";

export default {
  name: "FinancialRecord",
  dicts: ['financial_status', 'financial_group'],
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
      // 理财记录表格数据
      financialRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangePurchaseTime: [],
      // 备注时间范围
      daterangeConfirmTime: [],
      // 备注时间范围
      daterangeExpirationDate: [],
      // 备注时间范围
      daterangeRedemptionTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        financialProductName: null,
        financialGroup: null,
        financialStatus: null,
        purchasePlatform: null,
        purchaseTime: null,
        confirmTime: null,
        expirationDate: null,
        redemptionTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        financialProductName: [
          { required: true, message: "理财名称不能为空", trigger: "blur" }
        ],
        financialStatus: [
          { required: true, message: "理财状态不能为空", trigger: "change" }
        ],
        purchasePlatform: [
          { required: true, message: "购买平台不能为空", trigger: "blur" }
        ],
        purchaseTime: [
          { required: true, message: "购买时间不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        redemptionPath: [
          { required: true, message: "赎回路径不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询理财记录列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePurchaseTime && '' != this.daterangePurchaseTime) {
        this.queryParams.params["beginPurchaseTime"] = this.daterangePurchaseTime[0];
        this.queryParams.params["endPurchaseTime"] = this.daterangePurchaseTime[1];
      }
      if (null != this.daterangeConfirmTime && '' != this.daterangeConfirmTime) {
        this.queryParams.params["beginConfirmTime"] = this.daterangeConfirmTime[0];
        this.queryParams.params["endConfirmTime"] = this.daterangeConfirmTime[1];
      }
      if (null != this.daterangeExpirationDate && '' != this.daterangeExpirationDate) {
        this.queryParams.params["beginExpirationDate"] = this.daterangeExpirationDate[0];
        this.queryParams.params["endExpirationDate"] = this.daterangeExpirationDate[1];
      }
      if (null != this.daterangeRedemptionTime && '' != this.daterangeRedemptionTime) {
        this.queryParams.params["beginRedemptionTime"] = this.daterangeRedemptionTime[0];
        this.queryParams.params["endRedemptionTime"] = this.daterangeRedemptionTime[1];
      }
      listFinancialRecord(this.queryParams).then(response => {
        this.financialRecordList = response.rows;
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
        financialRecordId: null,
        wxTaskId: null,
        financialProductName: null,
        financialGroup: null,
        cron: null,
        defaultCron: null,
        financialStatus: null,
        purchasePlatform: null,
        purchaseTime: null,
        confirmTime: null,
        holdingTime: null,
        expirationDate: null,
        redemptionTime: null,
        amount: null,
        yield: null,
        redemptionPath: null,
        profit: null,
        remark: null,
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
      this.daterangePurchaseTime = [];
      this.daterangeConfirmTime = [];
      this.daterangeExpirationDate = [];
      this.daterangeRedemptionTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.financialRecordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加理财记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const financialRecordId = row.financialRecordId || this.ids
      getFinancialRecord(financialRecordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改理财记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.financialRecordId != null) {
            updateFinancialRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFinancialRecord(this.form).then(response => {
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
      const financialRecordIds = row.financialRecordId || this.ids;
      this.$modal.confirm('是否确认删除理财记录编号为"' + financialRecordIds + '"的数据项？').then(function() {
        return delFinancialRecord(financialRecordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/financialRecord/export', {
        ...this.queryParams
      }, `financialRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
