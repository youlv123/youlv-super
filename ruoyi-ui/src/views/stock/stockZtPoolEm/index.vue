<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="股票代码" prop="stockCode">
        <el-input
          v-model="queryParams.stockCode"
          placeholder="请输入股票代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="股票名称" prop="stockName">
        <el-input
          v-model="queryParams.stockName"
          placeholder="请输入股票名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务日期" prop="bizDate">
        <el-input
          v-model="queryParams.bizDate"
          placeholder="请输入业务日期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属行业" prop="industry">
        <el-input
          v-model="queryParams.industry"
          placeholder="请输入所属行业"
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
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['stock:stockZtPoolEm:add']"
        >新增</el-button>
      </el-col>-->
<!--      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['stock:stockZtPoolEm:edit']"
        >修改</el-button>
      </el-col>-->
<!--      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['stock:stockZtPoolEm:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['stock:stockZtPoolEm:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stockZtPoolEmList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="ztId" width="180"/>
      <el-table-column label="序号" align="center" prop="serialNo" width="80"/>
      <el-table-column label="股票代码" align="center" prop="stockCode" width="100"/>
      <el-table-column label="股票名称" align="center" prop="stockName" width="100"/>
      <el-table-column label="涨跌幅" align="center" prop="changePercent" />
      <el-table-column label="最新价" align="center" prop="latestPrice" width="100"/>
      <el-table-column label="成交额" align="center" prop="turnover" />
      <el-table-column label="流通市值" align="center" prop="circulatingMarketValue" width="180"/>
      <el-table-column label="总市值" align="center" prop="totalMarketValue" width="180"/>
      <el-table-column label="换手率" align="center" prop="turnoverRate" />
      <el-table-column label="封板资金" align="center" prop="sealedFunds" width="180"/>
      <el-table-column label="首次封板时间" align="center" prop="firstSealedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstSealedTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后封板时间" align="center" prop="lastSealedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastSealedTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="业务日期" align="center" prop="bizDate" />
      <el-table-column label="炸板次数" align="center" prop="breakNum" />
      <el-table-column label="涨停统计" align="center" prop="priceLimitStatistics" />
      <el-table-column label="连板数" align="center" prop="continuousNum" />
      <el-table-column label="所属行业" align="center" prop="industry" />
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
<!--        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['stock:stockZtPoolEm:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['stock:stockZtPoolEm:remove']"
          >删除</el-button>
        </template>-->
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改涨停板行情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="serialNo">
          <el-input v-model="form.serialNo" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="股票代码" prop="stockCode">
          <el-input v-model="form.stockCode" placeholder="请输入股票代码" />
        </el-form-item>
        <el-form-item label="股票名称" prop="stockName">
          <el-input v-model="form.stockName" placeholder="请输入股票名称" />
        </el-form-item>
        <el-form-item label="涨跌幅" prop="changePercent">
          <el-input v-model="form.changePercent" placeholder="请输入涨跌幅" />
        </el-form-item>
        <el-form-item label="最新价" prop="latestPrice">
          <el-input v-model="form.latestPrice" placeholder="请输入最新价" />
        </el-form-item>
        <el-form-item label="成交额" prop="turnover">
          <el-input v-model="form.turnover" placeholder="请输入成交额" />
        </el-form-item>
        <el-form-item label="流通市值" prop="circulatingMarketValue">
          <el-input v-model="form.circulatingMarketValue" placeholder="请输入流通市值" />
        </el-form-item>
        <el-form-item label="总市值" prop="totalMarketValue">
          <el-input v-model="form.totalMarketValue" placeholder="请输入总市值" />
        </el-form-item>
        <el-form-item label="换手率" prop="turnoverRate">
          <el-input v-model="form.turnoverRate" placeholder="请输入换手率" />
        </el-form-item>
        <el-form-item label="封板资金" prop="sealedFunds">
          <el-input v-model="form.sealedFunds" placeholder="请输入封板资金" />
        </el-form-item>
        <el-form-item label="首次封板时间" prop="firstSealedTime">
          <el-date-picker clearable
            v-model="form.firstSealedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择首次封板时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后封板时间" prop="lastSealedTime">
          <el-date-picker clearable
            v-model="form.lastSealedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后封板时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="业务日期" prop="bizDate">
          <el-input v-model="form.bizDate" placeholder="请输入业务日期" />
        </el-form-item>
        <el-form-item label="炸板次数" prop="breakNum">
          <el-input v-model="form.breakNum" placeholder="请输入炸板次数" />
        </el-form-item>
        <el-form-item label="涨停统计" prop="priceLimitStatistics">
          <el-input v-model="form.priceLimitStatistics" placeholder="请输入涨停统计" />
        </el-form-item>
        <el-form-item label="连板数" prop="continuousNum">
          <el-input v-model="form.continuousNum" placeholder="请输入连板数" />
        </el-form-item>
        <el-form-item label="所属行业" prop="industry">
          <el-input v-model="form.industry" placeholder="请输入所属行业" />
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
import { listStockZtPoolEm, getStockZtPoolEm, delStockZtPoolEm, addStockZtPoolEm, updateStockZtPoolEm } from "@/api/stock/stockZtPoolEm";

export default {
  name: "StockZtPoolEm",
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
      // 涨停板行情表格数据
      stockZtPoolEmList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stockCode: null,
        stockName: null,
        bizDate: null,
        industry: null,
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
    /** 查询涨停板行情列表 */
    getList() {
      this.loading = true;
      listStockZtPoolEm(this.queryParams).then(response => {
        this.stockZtPoolEmList = response.rows;
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
        ztId: null,
        serialNo: null,
        stockCode: null,
        stockName: null,
        changePercent: null,
        latestPrice: null,
        turnover: null,
        circulatingMarketValue: null,
        totalMarketValue: null,
        turnoverRate: null,
        sealedFunds: null,
        firstSealedTime: null,
        lastSealedTime: null,
        bizDate: null,
        breakNum: null,
        priceLimitStatistics: null,
        continuousNum: null,
        industry: null,
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
      this.ids = selection.map(item => item.ztId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加涨停板行情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ztId = row.ztId || this.ids
      getStockZtPoolEm(ztId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改涨停板行情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ztId != null) {
            updateStockZtPoolEm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStockZtPoolEm(this.form).then(response => {
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
      const ztIds = row.ztId || this.ids;
      this.$modal.confirm('是否确认删除涨停板行情编号为"' + ztIds + '"的数据项？').then(function() {
        return delStockZtPoolEm(ztIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('stock/stockZtPoolEm/export', {
        ...this.queryParams
      }, `stockZtPoolEm_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
