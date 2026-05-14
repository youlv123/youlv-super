<template>
  <div class="container">


    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="Single Query" name="first">
        <el-form ref="singleForm" @submit.prevent="handleSingleQuery" :model="singleQueryParams" :rules="rules">
          <el-form-item prop="applicationNumber">
            <div class="input-container">
              <span>Enter Offer ID or Sequence Number</span>
              <el-input v-model="singleQueryParams.applicationNumber"
                        placeholder="Enter Offer ID or Sequence Number"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="Options">
            <el-radio-group v-model="singleQueryParams.view">
              <el-radio label="1">Original</el-radio>
              <el-radio label="2">Current</el-radio>
              <el-radio label="0">Discounting</el-radio>
              <el-radio label="3">Compare Table</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-button type="primary" native-type="submit" class="custom-width" @click.prevent="handleSingleQuery">Go
          </el-button>
        </el-form>

      </el-tab-pane>
      <el-tab-pane label="Batch Query" name="second">
        <el-form ref="batchForm" @submit.prevent="handleBatchQuery" :model="batchQueryParams" :rules="rules">
          <el-form-item label="Application Date Select">
            <el-row :gutter="20">
              <el-col :span="7">
                <el-form-item prop="startDate">
                  <el-date-picker
                    v-model="batchQueryParams.startDate"
                    type="date"
                    placeholder="Start Date"
                    style="width: 100%;"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="2" style="text-align: center; line-height: 36px;">
                —
              </el-col>
              <el-col :span="7">
                <el-form-item prop="endDate">
                  <el-date-picker
                    v-model="batchQueryParams.endDate"
                    type="date"
                    placeholder="End Date"
                    style="width: 100%;"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="Options">
            <el-radio-group v-model="batchQueryParams.view">
              <el-radio label="1">Original</el-radio>
              <el-radio label="2">Current</el-radio>
              <el-radio label="0">Discounting</el-radio>
              <el-radio label="4">All</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-button type="primary" native-type="submit" class="custom-width" @click.prevent="handleBatchQuery">
            Download
          </el-button>
        </el-form>

      </el-tab-pane>
    </el-tabs>


    <div class="table-container" v-loading="loading" v-if="showTableContent">
      <h2 align="center">Basic Information</h2>
      <el-card class="box-card">
        <el-row :gutter="20">
          <el-col :span="8">
            <div>Offer ID/Sequence Number: {{ basicInfo.offerIdNb }}</div>
          </el-col>
          <el-col :span="8">
            <div>Primary Applicant Name: {{ basicInfo.prApplcntName }}</div>
          </el-col>
          <el-col :span="8">
            <div>Tier: {{ basicInfo.tier }}</div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div>Dealer: {{ basicInfo.dealerCode }}</div>
          </el-col>
          <el-col :span="8">
            <div>CO-Applicant Name: {{ basicInfo.coAoolcntName }}</div>
          </el-col>
          <el-col :span="8">
            <div>POP: {{ basicInfo.pop }}</div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <div>Product: {{ basicInfo.dproduct }}</div>
          </el-col>
          <el-col :span="8">
            <div>Guarantor Name: {{ basicInfo.guarantorName }}</div>
          </el-col>
        </el-row>
      </el-card>

      <h2 align="center">Model Display</h2>
      <el-table :data="modelDisplay" style="width: 100%">
        <el-table-column prop="modelName" :label="modelName">
          <el-table-column prop="view" label="view" width="120" align="center"></el-table-column>
          <el-table-column prop="strategy" label="Strategy" width="120" align="center"></el-table-column>
          <el-table-column prop="score" label="Score" width="120" align="center"></el-table-column>
          <el-table-column prop="transfm" label="Transfm" width="120" align="center"></el-table-column>
          <el-table-column prop="tier" label="Tier" width="120" align="center"></el-table-column>
          <el-table-column prop="flag" label="Flag" width="120" align="center"></el-table-column>
          <el-table-column prop="amount" label="Amount" width="120" align="center"></el-table-column>
          <el-table-column prop="callTime" label="Call Time" width="120" align="center"></el-table-column>
        </el-table-column>
      </el-table>

      <h2 align="center">Model Criteria</h2>
      <el-table :data="modelCriteria" style="width: 100%" v-if="notCompareTableFlag">
        <el-table-column prop="date" label="Criteria Name" width="180" align="center"></el-table-column>
        <el-table-column prop="date" label="Criteria Description" width="180" align="center"></el-table-column>
        <el-table-column :label="dynamicLabel" align="center">
          <el-table-column prop="Value" label="Value" width="300" align="center"></el-table-column>
          <el-table-column prop="Points" label="Points" width="300" align="center"></el-table-column>
        </el-table-column>
      </el-table>

      <el-table :data="modelCriteria" style="width: 100%" v-if="isCompareTableFlag">
        <el-table-column prop="date" label="Criteria Name" width="180" align="center"></el-table-column>
        <el-table-column prop="date" label="Criteria Description" width="180" align="center"></el-table-column>
        <el-table-column label="Original" align="center">
          <el-table-column prop="Value" label="Value" width="100" align="center"></el-table-column>
          <el-table-column prop="Points" label="Points" width="100" align="center"></el-table-column>
        </el-table-column>
        <el-table-column label="Current" align="center">
          <el-table-column prop="Value" label="Value" width="100" align="center"></el-table-column>
          <el-table-column prop="Points" label="Points" width="100" align="center"></el-table-column>
        </el-table-column>
        <el-table-column label="Discounting" align="center">
          <el-table-column prop="Value" label="Value" width="100" align="center"></el-table-column>
          <el-table-column prop="Points" label="Points" width="100" align="center"></el-table-column>
        </el-table-column>
      </el-table>

      <h2 align="center">Strategies Display</h2>
      <el-table :data="strategiesDisplay" style="width: 100%">
        <el-table-column prop="date" label="View" width="192" align="center"></el-table-column>
        <el-table-column prop="date" label="ECA" width="192" align="center"></el-table-column>
        <el-table-column prop="date" label="ECR" width="192" align="center"></el-table-column>
        <el-table-column prop="date" label="UPL" width="192" align="center"></el-table-column>
        <el-table-column prop="date" label="Call Time" width="192" align="center"></el-table-column>
      </el-table>
    </div>


  </div>
</template>

<script>
import {singleQuery, batchQuery} from "@/api/test/test";

export default {
  data() {
    return {
      modelName:'222',
      // 遮罩层
      loading: true,
      showTableContent: false, // 新增的布尔值变量
      isCompareTableFlag: true, // 页面是Compare Table是否显示
      notCompareTableFlag: true, // 页面是不是Compare Table是否显示
      basicInfo: {},
      modelDisplay: [],
      modelCriteria: [],
      strategiesDisplay: [],

      activeName: 'first',
      singleQueryParams: {
        applicationNumber: '',
        view: '1'
      },
      batchQueryParams: {
        startDate: null, // 开始日期
        endDate: null,   // 结束日期
        view: '1',

      },
      rules: {
        applicationNumber: [
          {required: true, message: 'Please enter Offer ID or Sequence Number', trigger: 'blur'},
          {pattern: /^\d+$/, message: 'Please enter pure numbers.', trigger: 'blur'}
        ],
        startDate: [
          {required: true, message: 'Please select the start date.', trigger: 'blur'}
        ],
        endDate: [
          {required: true, message: 'Please select the end date.', trigger: 'blur'}
        ]
      },
      optionsMap: {
        '1': 'Original',
        '2': 'Current',
        '0': 'Discounting',
        '3': 'Compare Table'
      },
      // 其他数据
      dynamicLabel: ''
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
      if (tab.name === 'second') {
        this.showTableContent = false;

      }
    },

    handleSingleQuery() {

      this.$refs["singleForm"].validate(valid => {
        console.log("----------------------------------555")
        console.log(valid)
        if (valid) {
          this.loading = true;
          singleQuery(this.singleQueryParams).then(response => {
            console.log("------------------0");
            console.log(response);
            console.log(response.basicInfo);
            console.log("------------------1");
            console.log(response.data);
            console.log("------------------2");

            this.basicInfo = response.basicInfo;
            this.modelDisplay = response.modelDisplay;
            this.modelCriteria = response.modelCriteria;
            this.strategiesDisplay = response.strategiesDisplay;
            this.dynamicLabel = this.optionsMap[this.singleQueryParams.view]
            this.modelName = response.modelDisplay && response.modelDisplay[0] ? response.modelDisplay[0].strategy : '';
            console.log(this.dynamicLabel);
            if (this.singleQueryParams.view === '3') {
              this.isCompareTableFlag = true;
              this.notCompareTableFlag = false;
            } else {
              this.isCompareTableFlag = false;
              this.notCompareTableFlag = true;
            }
            // 查询成功后显示表格内容
            this.showTableContent = true;
            this.loading = false;
          })
        }
      });
      console.log('Single Query:', this.singleQuery);
    },
    handleBatchQuery() {
      // this.$refs.batchForm.validate();


      this.$refs["batchForm"].validate(valid => {
        if (valid) {
          this.download('/system/test/batchQuery', {
            ...this.batchQueryParams
          }, `record_${new Date().getTime()}.xlsx`)
        }
      });
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 1025px;
  margin: auto;
  padding: 2rem;
}

h2 {
  margin-bottom: 1rem;
}

.el-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.input-container {
  display: flex;
  align-items: center;
}

/* 根据需要调整间距 */
.input-container span {
  margin-right: 10px;
}

/* 调节输入框的宽度 */
.el-input {
  width: 300px; /* 根据需要调整宽度 */
}

/* 自定义按钮宽度 */
.custom-width {
  width: 150px; /* 根据需要调整宽度 */
}

.box-card {
  margin-bottom: 20px;

}

.table-container {
  /* 其他样式 */
}

.table-spacing {
  height: 20px; /* 你可以根据需要调整这个值 */
}

/* 或者直接在el-table上设置margin */
.el-table {
  margin-bottom: 20px; /* 表格底部的外边距 */
}
</style>
