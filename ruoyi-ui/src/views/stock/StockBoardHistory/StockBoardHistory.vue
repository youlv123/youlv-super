<template>
  <div class="app-container">
    <!-- 搜索表单（与通用代码字段、样式完全对齐） -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="板块代码" prop="boardCode">
        <el-input
          v-model="queryParams.boardCode"
          placeholder="请输入板块代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="板块名称" prop="boardName">
        <el-input
          v-model="queryParams.boardName"
          placeholder="请输入板块名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="周期" prop="period">
        <el-select
          v-model="queryParams.period"
          placeholder="请选择周期"
          clearable
          @change="handleQuery"
        >
          <el-option label="日线" value="day"></el-option>
          <el-option label="周线" value="week"></el-option>
          <el-option label="月线" value="month"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易日起" prop="bizDateStart">
        <el-date-picker
          v-model="queryParams.bizDateStart"
          type="date"
          value-format="yyyyMMdd"
          placeholder="请选择开始日期"
          clearable
        />
      </el-form-item>
      <el-form-item label="交易日止" prop="bizDateEnd">
        <el-date-picker
          v-model="queryParams.bizDateEnd"
          type="date"
          value-format="yyyyMMdd"
          placeholder="请选择结束日期"
          clearable
        />
      </el-form-item>
      <el-form-item label="开盘价" prop="openPrice">
        <el-input
          v-model="queryParams.openPrice"
          placeholder="请输入开盘价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收盘价" prop="closingPrice">
        <el-input
          v-model="queryParams.closingPrice"
          placeholder="请输入收盘价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="涨跌幅(%)" prop="changePercent">
        <el-input
          v-model="queryParams.changePercent"
          placeholder="请输入涨跌幅"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 功能按钮栏（与通用代码权限、样式对齐） -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:stockBoardDataHistEm:add']"
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
          v-hasPermi="['system:stockBoardDataHistEm:edit']"
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
          v-hasPermi="['system:stockBoardDataHistEm:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:stockBoardDataHistEm:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-line-chart"
          size="mini"
          @click="toggleChart"
        >{{ showChart ? '隐藏图表' : '显示图表' }}</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 图表区域（新增，与表格布局兼容） -->
    <div v-show="showChart && stockBoardDataHistEmList.length > 0" class="mb8">
      <el-card shadow="hover" class="mb6">
        <div slot="header" class="flex justify-between items-center">
          <span>板块价格趋势图</span>
          <el-select
            v-model="activeIndicator"
            size="mini"
            @change="updateCharts"
          >
            <el-option label="开盘价" value="openPrice"></el-option>
            <el-option label="收盘价" value="closingPrice"></el-option>
            <el-option label="最高价" value="highestPrice"></el-option>
            <el-option label="最低价" value="lowestPrice"></el-option>
          </el-select>
        </div>
        <div class="h-[400px] mt4">
          <canvas id="priceChart"></canvas>
        </div>
      </el-card>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">成交量趋势（单位：手）</div>
            <div class="h-[300px] mt4">
              <canvas id="volumeChart"></canvas>
            </div>
          </el-card>
        </el-col>
        <!-- 新增：涨跌额趋势图（占12列，与成交量并列） -->
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">涨跌额趋势（单位：元）</div>
            <div class="h-[300px] mt4">
              <canvas id="priceRiseFallChart"></canvas> <!-- 涨跌额图表canvas -->
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">成交额趋势（单位：元）</div>
            <div class="h-[300px] mt4">
              <canvas id="turnoverChart"></canvas>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 数据表格（与通用代码字段、样式完全对齐） -->
    <el-table
      v-loading="loading"
      :data="stockBoardDataHistEmList"
      @selection-change="handleSelectionChange"
      border
      fit
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="boardDataHistId" width="100" />
      <el-table-column label="板块代码" align="center" prop="boardCode" width="120" />
      <el-table-column label="板块名称" align="center" prop="boardName" width="150" />
      <el-table-column label="板块类型" align="center" prop="boardType" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.boardType === '概念板块' ? 'info' : 'primary'">
            {{ scope.row.boardType }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="开盘价(元)" align="center" prop="openPrice" width="100" />
      <el-table-column label="收盘价(元)" align="center" prop="closingPrice" width="100" />
      <el-table-column label="最高价(元)" align="center" prop="highestPrice" width="100" />
      <el-table-column label="最低价(元)" align="center" prop="lowestPrice" width="100" />
      <el-table-column label="涨跌幅(%)" align="center" prop="changePercent" width="100">
        <template slot-scope="scope">
          <span :class="scope.row.changePercent && scope.row.changePercent.startsWith('-') ? 'text-red-500' : 'text-green-500'">
            {{ scope.row.changePercent }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="涨跌额(元)" align="center" prop="priceRiseFall" width="100" />
      <el-table-column label="成交量(手)" align="center" prop="volume" width="120" />
      <el-table-column label="成交额(元)" align="center" prop="turnover" width="150">
        <template slot-scope="scope">
          {{ formatNumber(scope.row.turnover) }}
        </template>
      </el-table-column>
      <el-table-column label="振幅(%)" align="center" prop="amplitude" width="100" />
      <el-table-column label="换手率(%)" align="center" prop="turnoverRate" width="100" />
      <el-table-column label="周期" align="center" prop="period" width="80">
        <template slot-scope="scope">
          <el-tag size="mini" type="success">{{ scope.row.period === 'day' ? '日线' : scope.row.period === 'week' ? '周线' : '月线' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="交易日" align="center" prop="bizDate" width="120" />
      <el-table-column label="创建人" align="center" prop="createdBy" width="100" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="140">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:stockBoardDataHistEm:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:stockBoardDataHistEm:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件（与通用代码完全一致） -->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增/修改对话框（与通用代码字段、校验完全对齐） -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="板块代码" prop="boardCode">
          <el-input v-model="form.boardCode" placeholder="请输入板块代码" />
        </el-form-item>
        <el-form-item label="板块名称" prop="boardName">
          <el-input v-model="form.boardName" placeholder="请输入板块名称" />
        </el-form-item>
        <el-form-item label="板块类型" prop="boardType">
          <el-select v-model="form.boardType" placeholder="请选择板块类型">
            <el-option label="概念板块" value="概念板块"></el-option>
            <el-option label="行业板块" value="行业板块"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开盘价" prop="openPrice">
          <el-input v-model="form.openPrice" placeholder="请输入开盘价（元）" />
        </el-form-item>
        <el-form-item label="收盘价" prop="closingPrice">
          <el-input v-model="form.closingPrice" placeholder="请输入收盘价（元）" />
        </el-form-item>
        <el-form-item label="最高价" prop="highestPrice">
          <el-input v-model="form.highestPrice" placeholder="请输入最高价（元）" />
        </el-form-item>
        <el-form-item label="最低价" prop="lowestPrice">
          <el-input v-model="form.lowestPrice" placeholder="请输入最低价（元）" />
        </el-form-item>
        <el-form-item label="涨跌幅(%)" prop="changePercent">
          <el-input v-model="form.changePercent" placeholder="请输入涨跌幅（如+1.23或-0.56）" />
        </el-form-item>
        <el-form-item label="涨跌额(元)" prop="priceRiseFall">
          <el-input v-model="form.priceRiseFall" placeholder="请输入涨跌额（元）" />
        </el-form-item>
        <el-form-item label="成交量(手)" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入成交量（手）" />
        </el-form-item>
        <el-form-item label="成交额(元)" prop="turnover">
          <el-input v-model="form.turnover" placeholder="请输入成交额（元）" />
        </el-form-item>
        <el-form-item label="振幅(%)" prop="amplitude">
          <el-input v-model="form.amplitude" placeholder="请输入振幅（%）" />
        </el-form-item>
        <el-form-item label="换手率(%)" prop="turnoverRate">
          <el-input v-model="form.turnoverRate" placeholder="请输入换手率（%）" />
        </el-form-item>
        <el-form-item label="周期" prop="period">
          <el-select v-model="form.period" placeholder="请选择周期">
            <el-option label="日线" value="day"></el-option>
            <el-option label="周线" value="week"></el-option>
            <el-option label="月线" value="month"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易日" prop="bizDate">
          <el-date-picker
            v-model="form.bizDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交易日"
            clearable
          />
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
// 引入通用API（与通用代码一致）
import {
  listStockBoardDataHistEm,
  getStockBoardDataHistEm,
  delStockBoardDataHistEm,
  addStockBoardDataHistEm,
  updateStockBoardDataHistEm
} from "@/api/stock/stockBoardDataHistEm";
// 引入图表库（保持功能完整性）
import Chart from 'chart.js/auto';
// 引入工具函数（与通用代码对齐）
// import { parseTime, download } from "@/utils/index";

export default {
  name: "StockBoardDataHistEm",
  data() {
    return {
      // 基础状态（与通用代码完全一致）
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      stockBoardDataHistEmList: [],
      title: "",
      open: false,
      showChart: true, // 图表显示状态
      activeIndicator: "openPrice", // 默认显示开盘价
      // 图表实例（用于销毁和更新）
      priceChart: null,
      volumeChart: null,
      turnoverChart: null,
      priceRiseFallChart: null,
      // 查询参数（与通用代码字段对齐，新增日期范围）
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        boardCode: null,
        boardName: null,
        boardType: null,
        openPrice: null,
        closingPrice: null,
        highestPrice: null,
        lowestPrice: null,
        changePercent: null,
        priceRiseFall: null,
        volume: null,
        turnover: null,
        amplitude: null,
        turnoverRate: null,
        period: null,
        bizDate: null,
        bizDateStart: null, // 新增：日期范围起始
        bizDateEnd: null,   // 新增：日期范围结束
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        updatedDate: null
      },

      // 表单参数（与通用代码完全一致）
      form: {
        boardDataHistId: null,
        boardCode: null,
        boardName: null,
        boardType: null,
        openPrice: null,
        closingPrice: null,
        highestPrice: null,
        lowestPrice: null,
        changePercent: null,
        priceRiseFall: null,
        volume: null,
        turnover: null,
        amplitude: null,
        turnoverRate: null,
        period: null,
        bizDate: null,
        createdBy: "system", // 默认为system，与通用代码一致
        createdDate: new Date(),
        updatedBy: "system",
        updatedDate: new Date()
      },

      // 表单校验（与通用代码完全一致）
      rules: {
        boardCode: [
          { required: true, message: "板块代码不能为空", trigger: "blur" }
        ],
        boardName: [
          { required: true, message: "板块名称不能为空", trigger: "blur" }
        ],
        boardType: [
          { required: true, message: "板块类型不能为空", trigger: "change" }
        ],
        period: [
          { required: true, message: "周期不能为空", trigger: "change" }
        ],
        bizDate: [
          { required: true, message: "交易日不能为空", trigger: "blur" }
        ],
        openPrice: [
          { required: true, message: "开盘价不能为空", trigger: "blur" }
        ],
        closingPrice: [
          { required: true, message: "收盘价不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    // 初始化时加载数据（与通用代码一致）
    this.getList();
    // 初始化默认日期范围（最近30天）
    this.initDefaultDateRange();
  },
  mounted() {
    // 初始化图表（确保DOM渲染完成）
    this.$nextTick(() => {
      this.initCharts();
    });
  },
  beforeDestroy() {
    // 销毁图表实例（避免内存泄漏）
    this.destroyCharts();
  },
  methods: {
    // 初始化默认日期范围（新增功能）
    initDefaultDateRange() {
      const endDate = new Date();
      const startDate = new Date();
      startDate.setDate(startDate.getDate() - 30);
      this.queryParams.bizDateStart = this.formatDate(startDate);
      this.queryParams.bizDateEnd = this.formatDate(endDate);
    },

    // 格式化日期为yyyy-MM-dd（新增功能）
    formatDate(date) {
      return this.parseTime(date, '{y}{m}{d}');
    },
// 新增：自己实现日期格式化，支持 {y}-{m}-{d} 格式
    parseTime(date, fmt = '{y}{m}{d}') {
      if (!date) return '';
      // 如果是字符串，先转成Date对象
      if (typeof date === 'string') {
        date = new Date(date.replace(/-/g, '/')); // 兼容IE浏览器
      }
      const o = {
        'y': date.getFullYear(),
        'm': date.getMonth() + 1, // 月份从0开始，+1
        'd': date.getDate(),
        'h': date.getHours(),
        'i': date.getMinutes(),
        's': date.getSeconds()
      };
      // 替换格式化字符
      for (const k in o) {
        const match = new RegExp(`\\{${k}\\}`, 'g');
        if (match.test(fmt)) {
          // 补0（如月份1→01，日期5→05）
          const value = o[k] < 10 ? `0${o[k]}` : o[k];
          fmt = fmt.replace(match, value);
        }
      }
      return fmt;
    },
    initCharts() {
      // 1. 价格图表（确保id和模板中的canvas id完全一致）
      const priceCanvas = document.getElementById('priceChart');
      if (!priceCanvas) {
        console.error("价格图表canvas未找到！请检查模板中canvas的id是否为'priceChart'");
        return;
      }
      const priceCtx = priceCanvas.getContext('2d');
      this.priceChart = new Chart(priceCtx, {
        type: 'line',
        data: {
          labels: [], // 后续用交易日填充
          datasets: [{
            label: '开盘价（元）',
            data: [], // 后续用有效价格数据填充
            borderColor: '#1890ff',
            borderWidth: 2,
            tension: 0.2,
            pointRadius: 3,
            pointHoverRadius: 5
          }]
        },
        options: this.getChartOptions('价格（元）')
      });

      // 2. 成交量图表（同理检查id）
      const volumeCanvas = document.getElementById('volumeChart');
      if (!volumeCanvas) {
        console.error("成交量图表canvas未找到！请检查模板中canvas的id是否为'volumeChart'");
        this.destroyCharts(); // 销毁已创建的价格图表，避免内存泄漏
        return;
      }
      const volumeCtx = volumeCanvas.getContext('2d');
      this.volumeChart = new Chart(volumeCtx, {
        type: 'bar',
        data: {
          labels: [],
          datasets: [{
            label: '成交量（手）',
            data: [],
            backgroundColor: 'rgba(54, 162, 235, 0.7)',
            borderColor: 'rgba(54, 162, 235, 1)',
            borderWidth: 1,
            borderRadius: 2
          }]
        },
        options: this.getChartOptions('成交量（手）', true)
      });

      // 3. 成交额图表（同理检查id）
      const turnoverCanvas = document.getElementById('turnoverChart');
      if (!turnoverCanvas) {
        console.error("成交额图表canvas未找到！请检查模板中canvas的id是否为'turnoverChart'");
        this.destroyCharts(); // 销毁已创建的图表
        return;
      }
      const turnoverCtx = turnoverCanvas.getContext('2d');
      this.turnoverChart = new Chart(turnoverCtx, {
        type: 'line',
        data: {
          labels: [],
          datasets: [{
            label: '成交额（元）',
            data: [],
            borderColor: '#9c27b0',
            backgroundColor: 'rgba(156, 39, 176, 0.1)',
            borderWidth: 2,
            tension: 0.2,
            fill: true
          }]
        },
        options: this.getChartOptions('成交额（元）')
      });

      // 新增：涨跌额趋势图初始化
      const riseFallCanvas = document.getElementById('priceRiseFallChart');
      if (!riseFallCanvas) {
        console.warn("涨跌额图表DOM不存在，跳过初始化");
        return;
      }
      const riseFallCtx = riseFallCanvas.getContext('2d');
      this.priceRiseFallChart = new Chart(riseFallCtx, {
        type: 'line', // 折线图（适合展示正负趋势）
        data: {
          labels: [], // 后续用交易日填充
          datasets: [{
            label: '涨跌额（元）',
            data: [], // 后续用涨跌额数据填充
            borderColor: '#FAAD14', // 橙色（区分其他图表）
            backgroundColor: 'rgba(250, 173, 20, 0.1)', // 浅色背景填充
            borderWidth: 2,
            tension: 0.2, // 平滑曲线
            pointBackgroundColor: '#FAAD14', // 数据点颜色
            pointRadius: 3,
            pointHoverRadius: 5,
            fill: true // 填充曲线下方区域（增强视觉效果）
          }]
        },
        options: this.getChartOptions('涨跌额（元）', false) // 涨跌额有正负，beginAtZero设为false
      });

      // 最后更新图表数据（用表格中的有效数据）
      this.updateCharts();
    },


    // 图表通用配置（新增功能）
    getChartOptions(yTitle, isBar = false) {
      return {
        responsive: true,
        maintainAspectRatio: false,
        interaction: { mode: 'index', intersect: false },
        plugins: {
          legend: { position: 'top' },
          tooltip: { backgroundColor: 'rgba(0,0,0,0.8)', padding: 10, cornerRadius: 4 }
        },
        scales: {
          x: { grid: { display: false }, ticks: { maxRotation: 45, minRotation: 45 } },
          y: {
            beginAtZero: isBar,
            grid: { color: 'rgba(0,0,0,0.05)' },
            title: { display: true, text: yTitle }
          }
        },
        animation: { duration: 800, easing: 'easeOutQuart' }
      };
    },

    // 更新图表数据（核心：从表格数据提取，与后端字段对齐）
    updateCharts() {
      if (!this.stockBoardDataHistEmList.length) return;
      // 原有：排序数据、提取交易日labels、价格数据、成交量数据、成交额数据...
      const sortedData = [...this.stockBoardDataHistEmList].sort((a, b) => {
        return new Date(a.bizDate) - new Date(b.bizDate);
      });
      // 提取时间轴（交易日）
      const labels = this.stockBoardDataHistEmList.map(item => item.bizDate).sort();
      // 提取价格数据（根据当前选中的指标）
      const priceData = this.stockBoardDataHistEmList.map(item => {
        return item[this.activeIndicator] ? Number(item[this.activeIndicator]) : 0;
      });
      // 提取成交量数据
      const volumeData = this.stockBoardDataHistEmList.map(item => item.volume ? Number(item.volume) : 0);
      // 提取成交额数据
      const turnoverData = this.stockBoardDataHistEmList.map(item => item.turnover ? Number(item.turnover) : 0);

      const priceRiseFallData = sortedData.map(item => {
        const value = item.priceRiseFall;
        // 空值/非数字时返回0，字符串转数字（支持正负值，如"-0.56"→-0.56）
        return value && !isNaN(Number(value)) ? Number(value) : 0;
      });

      // 更新价格图表
      if (this.priceChart) {
        this.priceChart.data.labels = labels;
        this.priceChart.data.datasets[0].data = priceData;
        this.priceChart.data.datasets[0].label = this.getIndicatorLabel();
        this.priceChart.data.datasets[0].borderColor = this.getIndicatorColor();
        this.priceChart.update();
      }

      // 更新成交量图表
      if (this.volumeChart) {
        this.volumeChart.data.labels = labels;
        this.volumeChart.data.datasets[0].data = volumeData;
        this.volumeChart.update();
      }

      // 更新成交额图表
      if (this.turnoverChart) {
        this.turnoverChart.data.labels = labels;
        this.turnoverChart.data.datasets[0].data = turnoverData;
        this.turnoverChart.data.datasets[0].borderColor = '#9c27b0';
        this.turnoverChart.data.datasets[0].backgroundColor = 'rgba(156, 39, 176, 0.1)';
        this.turnoverChart.update();
      }
      // 新增：更新涨跌额图表数据
      if (this.priceRiseFallChart) {
        this.priceRiseFallChart.data.labels = labels;
        this.priceRiseFallChart.data.datasets[0].data = priceRiseFallData;
        this.priceRiseFallChart.update(); // 刷新图表
      }
    },

    // 获取当前指标的中文标签（新增功能）
    getIndicatorLabel() {
      const labelMap = {
        openPrice: '开盘价（元）',
        closingPrice: '收盘价（元）',
        highestPrice: '最高价（元）',
        lowestPrice: '最低价（元）'
      };
      return labelMap[this.activeIndicator] || '价格（元）';
    },

    // 获取当前指标的颜色（新增功能）
    getIndicatorColor() {
      const colorMap = {
        openPrice: '#1890ff',
        closingPrice: '#52c41a',
        highestPrice: '#faad14',
        lowestPrice: '#f5222d'
      };
      return colorMap[this.activeIndicator] || '#1890ff';
    },

    // 销毁图表实例（新增功能）
    destroyCharts() {
      if (this.priceChart) this.priceChart.destroy();
      if (this.volumeChart) this.volumeChart.destroy();
      if (this.turnoverChart) this.turnoverChart.destroy();
      // 新增：销毁涨跌额图表
      if (this.priceRiseFallChart) this.priceRiseFallChart.destroy();
    },

    // 切换图表显示/隐藏（新增功能）
    toggleChart() {
      this.showChart = !this.showChart;
      // 显示时重新初始化图表（避免DOM重新渲染后图表失效）
      if (this.showChart) {
        this.$nextTick(() => {
          this.initCharts();
          console.log("图表DOM是否存在:", !!document.getElementById('priceChart')); // 验证
        });
      }
    },

    // 格式化大数字显示（新增功能）
    formatNumber(num) {
      if (!num) return '0';
      num = Number(num);
      if (num >= 100000000) {
        return (num / 100000000).toFixed(2) + '亿';
      } else if (num >= 10000) {
        return (num / 10000).toFixed(2) + '万';
      }
      return num.toString();
    },

    // ---------------------- 以下与通用代码完全一致 ----------------------
    /** 查询板块数据历史列表 */
    getList() {
      this.loading = true;
      listStockBoardDataHistEm(this.queryParams).then(response => {
        this.stockBoardDataHistEmList = response.rows;
        this.total = response.total;
        this.loading = false;

        // 修正：确保firstItem在数组有数据时才定义
        console.log("表格数据:", this.stockBoardDataHistEmList);
        if (this.stockBoardDataHistEmList.length > 0) {
          // 关键：在if内部定义firstItem，避免数组为空时未定义
          const firstItem = this.stockBoardDataHistEmList[0];
          console.log("第一个数据项（验证字段）:", {
            bizDate: firstItem.bizDate,       // 交易日（x轴）
            openPrice: firstItem.openPrice,   // 开盘价（价格数据）
            volume: firstItem.volume,         // 成交量
            turnover: firstItem.turnover,     // 成交额
            // 验证数值是否有效（避免图表渲染空值）
            openPriceIsValid: !isNaN(Number(firstItem.openPrice)),
            volumeIsValid: !isNaN(Number(firstItem.volume))
          });
        } else {
          console.log("表格数据为空，无法渲染图表");
          return; // 数据为空时，不执行后续图表初始化
        }

        // 数据有效时，初始化图表（确保DOM已渲染）
        this.$nextTick(() => {
          this.destroyCharts(); // 先销毁旧图表，避免重复创建
          this.initCharts();    // 初始化新图表
        });
      }).catch(error => {
        console.error("获取表格数据失败:", error);
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
        boardDataHistId: null,
        boardCode: null,
        boardName: null,
        boardType: null,
        openPrice: null,
        closingPrice: null,
        highestPrice: null,
        lowestPrice: null,
        changePercent: null,
        priceRiseFall: null,
        volume: null,
        turnover: null,
        amplitude: null,
        turnoverRate: null,
        period: null,
        bizDate: null,
        createdBy: "system",
        createdDate: new Date(),
        updatedBy: "system",
        updatedDate: new Date()
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
      // 重置时保留默认日期范围
      this.initDefaultDateRange();
      this.handleQuery();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.boardDataHistId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加板块数据历史";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const boardDataHistId = row.boardDataHistId || this.ids;
      getStockBoardDataHistEm(boardDataHistId).then(response => {
        this.form = response.data;
        // 格式化日期（适配日期选择器）
        this.form.createdDate = this.form.createdDate ? new Date(this.form.createdDate) : new Date();
        this.form.updatedDate = this.form.updatedDate ? new Date(this.form.updatedDate) : new Date();
        this.open = true;
        this.title = "修改板块数据历史";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 格式化数值字段（避免字符串类型提交）
          this.form.openPrice = this.form.openPrice ? String(this.form.openPrice) : null;
          this.form.closingPrice = this.form.closingPrice ? String(this.form.closingPrice) : null;
          this.form.turnover = this.form.turnover ? String(this.form.turnover) : null;

          if (this.form.boardDataHistId != null) {
            updateStockBoardDataHistEm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStockBoardDataHistEm(this.form).then(response => {
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
      const boardDataHistIds = row.boardDataHistId || this.ids;
      this.$modal.confirm(`是否确认删除板块数据历史编号为"${boardDataHistIds}"的数据项？`).then(() => {
        return delStockBoardDataHistEm(boardDataHistIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('system/stockBoardDataHistEm/export', {
        ...this.queryParams
      }, `stockBoardDataHistEm_${new Date().getTime()}.xlsx`);
    }
  }
};
</script>

<style scoped>
/* 适配Element UI样式，新增图表区域间距 */
.mb8 {
  margin-bottom: 8px;
}
.mb6 {
  margin-bottom: 6px;
}
.mt4 {
  margin-top: 4px;
}
.text-red-500 {
  color: #F56C6C;
}
.text-green-500 {
  color: #67C23A;
}
</style>
