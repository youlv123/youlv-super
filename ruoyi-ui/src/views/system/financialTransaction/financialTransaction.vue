<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="代码" prop="financialProductCode">
        <el-input
          v-model="queryParams.financialProductCode"
          placeholder="请输入代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="交易类型" prop="transType">
        <el-select v-model="queryParams.transType" placeholder="请选择交易类型" clearable>
          <el-option
            v-for="dict in dict.type.transaction_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="交易时间">
        <el-date-picker
          v-model="daterangeTransTime"
          style="width: 240px"
          value-format="yyyy-MM-dd HH:mm:ss"
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
          v-hasPermi="['system:financialTransaction:add']"
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
          v-hasPermi="['system:financialTransaction:edit']"
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
          v-hasPermi="['system:financialTransaction:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:financialTransaction:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="financialTransactionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="transactionId" />
      <el-table-column label="代码" align="center" prop="financialProductCode" />
      <el-table-column label="理财名称" align="center" prop="financialProductName" />
      <el-table-column label="理财分组" align="center" prop="financialGroup">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.financial_group" :value="scope.row.financialGroup"/>
        </template>
      </el-table-column>
      <el-table-column label="交易类型" align="center" prop="transType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.transaction_type" :value="scope.row.transType"/>
        </template>
      </el-table-column>
      <el-table-column label="交易时间" align="center" prop="transTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易份额" align="center" prop="transShares" />
      <el-table-column label="交易金额" align="center" prop="transPrincipal" />
      <el-table-column label="交易时基金净值" align="center" prop="transNetValue" />
      <el-table-column label="交易手续费" align="center" prop="transFee" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:financialTransaction:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:financialTransaction:remove']"
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

    <!-- 添加或修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="代码" prop="financialProductCode">
          <el-input v-model="form.financialProductCode" placeholder="请输入代码" />
        </el-form-item>
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
        <el-form-item label="交易类型" prop="transType">
          <el-select v-model="form.transType" placeholder="请选择交易类型">
            <el-option
              v-for="dict in dict.type.transaction_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易时间" prop="transTime">
          <el-date-picker clearable
            v-model="form.transTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易份额" prop="transShares">
          <el-input v-model="form.transShares" placeholder="请输入交易份额" />
        </el-form-item>
        <el-form-item label="交易金额" prop="transPrincipal">
          <el-input v-model="form.transPrincipal" placeholder="请输入交易金额" />
        </el-form-item>
        <el-form-item label="交易时基金净值" prop="transNetValue">
          <el-input v-model="form.transNetValue" placeholder="请输入交易时基金净值" />
        </el-form-item>
        <el-form-item label="交易手续费" prop="transFee">
          <el-input v-model="form.transFee" placeholder="请输入交易手续费" />
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
import { listFinancialTransaction, getFinancialTransaction, delFinancialTransaction, addFinancialTransaction, updateFinancialTransaction } from "@/api/system/financialTransaction";

export default {
  name: "FinancialTransaction",
  dicts: ['financial_group', 'transaction_type'],
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
      // 理财交易记录（记录所有买入/卖出操作，关联持仓明细）表格数据
      financialTransactionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeTransTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        financialProductCode: null,
        financialProductName: null,
        financialGroup: null,
        transType: null,
        transTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        financialProductCode: [
          { required: true, message: "代码不能为空", trigger: "blur" }
        ],
        financialProductName: [
          { required: true, message: "理财名称不能为空", trigger: "blur" }
        ],
        transType: [
          { required: true, message: "交易类型不能为空", trigger: "change" }
        ],
        transTime: [
          { required: true, message: "交易时间不能为空", trigger: "blur" }
        ],
        transShares: [
          { required: true, message: "交易份额不能为空", trigger: "blur" }
        ],
        transPrincipal: [
          { required: true, message: "交易金额不能为空", trigger: "blur" }
        ],
        transNetValue: [
          { required: true, message: "交易时基金净值不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询理财交易记录（记录所有买入/卖出操作，关联持仓明细）列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeTransTime && '' != this.daterangeTransTime) {
        this.queryParams.params["beginTransTime"] = this.daterangeTransTime[0];
        this.queryParams.params["endTransTime"] = this.daterangeTransTime[1];
      }
      listFinancialTransaction(this.queryParams).then(response => {
        this.financialTransactionList = response.rows;
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
        transactionId: null,
        financialProductCode: null,
        financialProductName: null,
        financialGroup: null,
        transType: null,
        transTime: null,
        transShares: null,
        transPrincipal: null,
        transNetValue: null,
        transFee: null,
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
      this.daterangeTransTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.transactionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加理财交易记录（记录所有买入/卖出操作，关联持仓明细）";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const transactionId = row.transactionId || this.ids
      getFinancialTransaction(transactionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改理财交易记录（记录所有买入/卖出操作，关联持仓明细）";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.transactionId != null) {
            updateFinancialTransaction(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFinancialTransaction(this.form).then(response => {
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
      const transactionIds = row.transactionId || this.ids;
      this.$modal.confirm('是否确认删除理财交易记录（记录所有买入/卖出操作，关联持仓明细）编号为"' + transactionIds + '"的数据项？').then(function() {
        return delFinancialTransaction(transactionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/financialTransaction/export', {
        ...this.queryParams
      }, `financialTransaction_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
