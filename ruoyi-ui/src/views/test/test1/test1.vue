<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>Basic Information</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8">
          <div>Offer ID/Sequence Number: {{ basicInfo.offerId }}</div>
        </el-col>
        <el-col :span="8">
          <div>Primary Applicant Name: {{ basicInfo.primaryApplicantName }}</div>
        </el-col>
        <el-col :span="8">
          <div>Tier: {{ basicInfo.tier }}</div>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8">
          <div>Dealer: {{ basicInfo.dealer }}</div>
        </el-col>
        <el-col :span="8">
          <div>CO-Applicant Name: {{ basicInfo.coApplicantName }}</div>
        </el-col>
        <el-col :span="8">
          <div>POP: {{ basicInfo.pop }}</div>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8">
          <div>Product: {{ basicInfo.product }}</div>
        </el-col>
        <el-col :span="8">
          <div>Guarantor Name: {{ basicInfo.guarantorName }}</div>
        </el-col>
      </el-row>
    </el-card>


    <el-table :data="modelDisplay" style="width: 100%">
      <el-table-column label="配送信息">
        <el-table-column prop="view" label="view" width="120"align="center"></el-table-column>
        <el-table-column prop="Strategy" label="Strategy" width="120" align="center"></el-table-column>
        <el-table-column prop="Score" label="Score" width="120" align="center"></el-table-column>
        <el-table-column prop="Transfm" label="Transfm" width="120" align="center"></el-table-column>
        <el-table-column prop="Tier" label="Tier" width="120" align="center"></el-table-column>
        <el-table-column prop="Flag" label="Flag" width="120" align="center"></el-table-column>
        <el-table-column prop="Amount" label="Amount" width="120" align="center"></el-table-column>
        <el-table-column prop="province" label="Call Time" width="120" align="center"></el-table-column>
      </el-table-column>
    </el-table>

    <el-table :data="modelCriteria" style="width: 100%">
      <el-table-column prop="date" label="Criteria Name" width="180" align="center"></el-table-column>
      <el-table-column prop="date" label="Criteria Description" width="180" align="center"></el-table-column>
      <el-table-column label="Original" align="center">
        <el-table-column prop="Value" label="Value" width="300" align="center"></el-table-column>
        <el-table-column prop="Points" label="Points" width="300" align="center"></el-table-column>
      </el-table-column>
    </el-table>

    <el-table :data="strategiesDisplay" style="width: 100%">
      <el-table-column prop="date" label="" width="192">
        <el-table-column prop="date" label="Criteria Description" width="192"align="center"></el-table-column>
      </el-table-column>
      <el-table-column prop="date" label="Criteria Name" width="192" align="center">
        <el-table-column prop="date" label="Criteria Description" width="192"></el-table-column>
      </el-table-column>
      <el-table-column prop="date" label="Criteria Name" width="192" align="center">
        <el-table-column prop="date" label="Criteria Description" width="192"></el-table-column>
      </el-table-column>
      <el-table-column prop="date" label="Criteria Name" width="192" align="center">
        <el-table-column prop="date" label="Criteria Description" width="192"></el-table-column>
      </el-table-column>
      <el-table-column prop="date" label="Criteria Name" width="192" align="center">
        <el-table-column prop="date" label="Criteria Description" width="192"></el-table-column>
      </el-table-column>
    </el-table>

  </div>


</template>

<script>
export default {
  data() {
    return {
      basicInfo: {},
      retailPopData: [],
      phcData: []
    };
  },
  mounted() {
    this.fetchBasicInfo();
  },
  methods: {
    fetchBasicInfo() {
      // 假设 DDsearch 是一个函数，它接受一个参数并返回一个 Promise
      DDsearch().then(response => {
        const customDto = response.data.customDto;
        if (customDto && customDto.infoDto) {
          this.basicInfo = customDto.infoDto;
          this.retailPopData = response.data.retailPopData;
          this.phcData = response.data.phcData;
        } else {
          console.error('Invalid response from DDsearch');
        }
      }).catch(error => {
        console.error('Error fetching basic information:', error);
      });
    }
  }
};


</script>
<style scoped>
.box-card {
  margin-bottom: 20px;
}
</style>
