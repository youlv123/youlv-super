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
      <el-form-item label="代码" prop="financialProductCode">
        <el-input
          v-model="queryParams.financialProductCode"
          placeholder="请输入代码"
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:financialTotal:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:financialTotal:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:financialTotal:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:financialTotal:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="financialTotalList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="financialTotalId" />
      <el-table-column label="理财名称" align="center" prop="financialProductName" />
      <el-table-column label="代码" align="center" prop="financialProductCode" />
      <el-table-column label="理财分组" align="center" prop="financialGroup">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.financial_group" :value="scope.row.financialGroup"/>
        </template>
      </el-table-column>
      <el-table-column label="最新净值" align="center" prop="latestNetValue" />
      <el-table-column label="持仓成本价" align="center" prop="currentHoldingCost" />
      <el-table-column label="当前持有份额" align="center" prop="currentHoldingShares" />
      <el-table-column label="当前持有金额" align="center" prop="currentHoldingAmount" />
      <el-table-column label="当前持有盈亏" align="center" prop="currentHoldingProfit" />
      <el-table-column label="总盈亏" align="center" prop="totalProfit" />
      <el-table-column label="当前持有收益率" align="center" prop="currentHoldingYield" />
      <el-table-column label="历史收益率" align="center" prop="hisTotalYield" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:financialTotal:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:financialTotal:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改理财汇总对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="理财名称" prop="financialProductName">
          <el-input v-model="form.financialProductName" placeholder="请输入理财名称" />
        </el-form-item>
        <el-form-item label="代码" prop="financialProductCode">
          <el-input v-model="form.financialProductCode" placeholder="请输入代码" />
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
        <el-form-item label="最新净值" prop="latestNetValue">
          <el-input v-model="form.latestNetValue" placeholder="请输入最新净值" />
        </el-form-item>
        <el-form-item label="持仓成本价" prop="currentHoldingCost">
          <el-input v-model="form.currentHoldingCost" placeholder="请输入持仓成本价" />
        </el-form-item>
        <el-form-item label="当前持有份额" prop="currentHoldingShares">
          <el-input v-model="form.currentHoldingShares" placeholder="请输入当前持有份额" />
        </el-form-item>
        <el-form-item label="当前持有金额" prop="currentHoldingAmount">
          <el-input v-model="form.currentHoldingAmount" placeholder="请输入当前持有金额" />
        </el-form-item>
        <el-form-item label="当前持有盈亏" prop="currentHoldingProfit">
          <el-input v-model="form.currentHoldingProfit" placeholder="请输入当前持有盈亏" />
        </el-form-item>
        <el-form-item label="总盈亏" prop="totalProfit">
          <el-input v-model="form.totalProfit" placeholder="请输入总盈亏" />
        </el-form-item>
        <el-form-item label="当前持有收益率" prop="currentHoldingYield">
          <el-input v-model="form.currentHoldingYield" placeholder="请输入当前持有收益率" />
        </el-form-item>
        <el-form-item label="历史收益率" prop="hisTotalYield">
          <el-input v-model="form.hisTotalYield" placeholder="请输入历史收益率" />
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
import { listFinancialTotal, getFinancialTotal, delFinancialTotal, addFinancialTotal, updateFinancialTotal } from "@/api/system/financialTotal";

export default {
  name: "FinancialTotal",
  dicts: ['financial_group'],
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
      // 理财汇总表格数据
      financialTotalList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        financialProductName: null,
        financialProductCode: null,
        financialGroup: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        financialProductName: [
          { required: true, message: "理财名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询理财汇总列表 */
    getList() {
      this.loading = true;
      listFinancialTotal(this.queryParams).then(response => {
        this.financialTotalList = response.rows;
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
        financialTotalId: null,
        financialProductName: null,
        financialProductCode: null,
        financialGroup: null,
        latestNetValue: null,
        currentHoldingCost: null,
        currentHoldingShares: null,
        currentHoldingAmount: null,
        currentHoldingProfit: null,
        totalProfit: null,
        currentHoldingYield: null,
        hisTotalYield: null,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.financialTotalId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加理财汇总";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const financialTotalId = row.financialTotalId || this.ids
      getFinancialTotal(financialTotalId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改理财汇总";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.financialTotalId != null) {
            updateFinancialTotal(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFinancialTotal(this.form).then(response => {
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
      const financialTotalIds = row.financialTotalId || this.ids;
      this.$modal.confirm('是否确认删除理财汇总编号为"' + financialTotalIds + '"的数据项？').then(function() {
        return delFinancialTotal(financialTotalIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/financialTotal/export', {
        ...this.queryParams
      }, `financialTotal_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
