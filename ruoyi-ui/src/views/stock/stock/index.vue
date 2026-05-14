<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="交易日期" prop="transactionDate">
        <el-date-picker clearable
          v-model="queryParams.transactionDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交易日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="交易类型" prop="transactionType">
        <el-select v-model="queryParams.transactionType" placeholder="请选择交易类型" clearable>
          <el-option
            v-for="dict in dict.type.transaction_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="盈利标识" prop="profitFlag">
        <el-select v-model="queryParams.profitFlag" placeholder="请选择盈利标识" clearable>
          <el-option
            v-for="dict in dict.type.profit_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="证券代码" prop="stockCode">
        <el-input
          v-model="queryParams.stockCode"
          placeholder="请输入证券代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证券名称" prop="stockName">
        <el-input
          v-model="queryParams.stockName"
          placeholder="请输入证券名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="游资名称" prop="floatingCapitalName">
        <el-select v-model="queryParams.floatingCapitalName" placeholder="请选择游资名称" clearable>
          <el-option
            v-for="dict in dict.type.floating_capital_name"
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
          v-hasPermi="['stock:stock:add']"
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
          v-hasPermi="['stock:stock:edit']"
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
          v-hasPermi="['stock:stock:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stock:stock:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockList" @selection-change="handleSelectionChange" :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="5" align="center" />
      <el-table-column label="主键ID" align="center" prop="stockId" width="90"/>
      <el-table-column label="交易日期" align="center" prop="transactionDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transactionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易类型" align="center" prop="transactionType" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.transaction_type" :value="scope.row.transactionType"/>
        </template>
      </el-table-column>
      <el-table-column label="盈利标识" align="center" prop="profitFlag" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.profit_flag" :value="scope.row.profitFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="证券代码" align="center" prop="stockCode" width="80"/>
      <el-table-column label="证券名称" align="center" prop="stockName" width="100"/>
      <el-table-column label="证券数量" align="center" prop="stockQuantity" width="95"/>
      <el-table-column label="可卖数量" align="center" prop="sellableQuantity" width="95"/>
      <el-table-column label="成本价" align="center" prop="costPrice" width="95"/>
      <el-table-column label="当前价" align="center" prop="currentPrice" width="95"/>
      <el-table-column label="盈亏比例" align="center" prop="profitLossRatio" width="80"/>
      <el-table-column label="参考盈亏" align="center" prop="referenceProfitLoss" width="90"/>
      <el-table-column label="参考市值" align="center" prop="referenceMarketValue" width="90"/>
      <el-table-column label="交易市场" align="center" prop="tradingMarket" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.trading_market" :value="scope.row.tradingMarket"/>
        </template>
      </el-table-column>
      <el-table-column label="可用金额" align="center" prop="availableAmount" width="100"/>
      <el-table-column label="股票市值" align="center" prop="stockValue" width="100"/>
      <el-table-column label="总资产" align="center" prop="totalAssets" width="100"/>
      <el-table-column label="游资名称" align="center" prop="floatingCapitalName" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.floating_capital_name" :value="scope.row.floatingCapitalName"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="备注信息" align="center" prop="remark" />-->

      <el-table-column label="备注信息" align="center" width="380">
        <template slot-scope="scope">
          <!-- 如果remark为空（null、undefined、""），则不显示任何内容或显示占位符 -->
          <span v-if="!scope.row.remark||scope.row.remark.length <= 20">
            {{ scope.row.remark }}
          </span>
          <!-- 如果remark不为空且长度大于50，显示截断的内容加查看详情链接 -->
          <span v-else>
            {{ scope.row.remark.substring(0, 20) + '...' }}
            <a @click="handleShowDetail(scope.row)"><span style="color: blue;">查看详情</span>
            </a>
          </span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stock:stock:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stock:stock:remove']"
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

    <!-- 添加或修改股票交割单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="交易日期" prop="transactionDate">
          <el-date-picker clearable
            v-model="form.transactionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易类型" prop="transactionType">
          <el-select v-model="form.transactionType" placeholder="请选择交易类型">
            <el-option
              v-for="dict in dict.type.transaction_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="盈利标识" prop="profitFlag">
          <el-select v-model="form.profitFlag" placeholder="请选择盈利标识">
            <el-option
              v-for="dict in dict.type.profit_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="证券代码" prop="stockCode">
          <el-input v-model="form.stockCode" placeholder="请输入证券代码" />
        </el-form-item>
        <el-form-item label="证券名称" prop="stockName">
          <el-input v-model="form.stockName" placeholder="请输入证券名称" />
        </el-form-item>
        <el-form-item label="证券数量" prop="stockQuantity">
          <el-input v-model="form.stockQuantity" placeholder="请输入证券数量" />
        </el-form-item>
        <el-form-item label="可卖数量" prop="sellableQuantity">
          <el-input v-model="form.sellableQuantity" placeholder="请输入可卖数量" />
        </el-form-item>
        <el-form-item label="成本价" prop="costPrice">
          <el-input v-model="form.costPrice" placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="当前价" prop="currentPrice">
          <el-input v-model="form.currentPrice" placeholder="请输入当前价" />
        </el-form-item>
        <el-form-item label="盈亏比例" prop="profitLossRatio">
          <el-input v-model="form.profitLossRatio" placeholder="请输入盈亏比例" />
        </el-form-item>
        <el-form-item label="参考盈亏" prop="referenceProfitLoss">
          <el-input v-model="form.referenceProfitLoss" placeholder="请输入参考盈亏" />
        </el-form-item>
        <el-form-item label="参考市值" prop="referenceMarketValue">
          <el-input v-model="form.referenceMarketValue" placeholder="请输入参考市值" />
        </el-form-item>
        <el-form-item label="交易市场" prop="tradingMarket">
          <el-select v-model="form.tradingMarket" placeholder="请选择交易市场">
            <el-option
              v-for="dict in dict.type.trading_market"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="可用金额" prop="availableAmount">
          <el-input v-model="form.availableAmount" placeholder="请输入可用金额" />
        </el-form-item>
        <el-form-item label="股票市值" prop="stockValue">
          <el-input v-model="form.stockValue" placeholder="请输入股票市值" />
        </el-form-item>
        <el-form-item label="总资产" prop="totalAssets">
          <el-input v-model="form.totalAssets" placeholder="请输入总资产" />
        </el-form-item>
        <el-form-item label="游资名称" prop="floatingCapitalName">
          <el-select v-model="form.floatingCapitalName" placeholder="请选择游资名称">
            <el-option
              v-for="dict in dict.type.floating_capital_name"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
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
import { listStock, getStock, delStock, addStock, updateStock } from "@/api/stock/stock";

export default {
  name: "Stock",
  dicts: ['transaction_type', 'trading_market', 'profit_flag', 'floating_capital_name'],
  data() {
    return {
      dialogVisible: false, // 控制弹窗显示与隐藏
      templateDetail: '',  // 用来存储详情
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
      // 股票交割单表格数据
      stockList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        transactionDate: null,
        transactionType: null,
        profitFlag: null,
        stockCode: null,
        stockName: null,
        floatingCapitalName: null,
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
    /** 查询股票交割单列表 */
    getList() {
      this.loading = true;
      listStock(this.queryParams).then(response => {
        this.stockList = response.rows;
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
    tableRowClassName({ row }) {
      if (row.profitFlag === 'N') {
        return 'green-row';
      } else if (row.profitFlag === 'Y') {
        return 'red-row';
      }
      return '';
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        stockId: null,
        transactionDate: null,
        transactionType: null,
        profitFlag: null,
        stockCode: null,
        stockName: null,
        stockQuantity: null,
        sellableQuantity: null,
        costPrice: null,
        currentPrice: null,
        profitLossRatio: null,
        referenceProfitLoss: null,
        referenceMarketValue: null,
        tradingMarket: null,
        floatingCapitalName: null,
        remark: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        updatedDate: null,
        availableAmount: null,
        stockValue: null,
        totalAssets: null
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
      this.ids = selection.map(item => item.stockId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加股票交割单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stockId = row.stockId || this.ids
      getStock(stockId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改股票交割单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stockId != null) {
            updateStock(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStock(this.form).then(response => {
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
      const stockIds = row.stockId || this.ids;
      this.$modal.confirm('是否确认删除股票交割单编号为"' + stockIds + '"的数据项？').then(function() {
        return delStock(stockIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('stock/stock/export', {
        ...this.queryParams
      }, `stock_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>.green-row {
  background-color: #e1f3d8 !important; /* 绿色背景 */
}
.red-row {
  background-color: #ffd6d6 !important; /* 红色背景 */
}
</style>
