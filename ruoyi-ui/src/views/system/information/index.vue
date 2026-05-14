<template>


  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="2" :xs="24">
        <div class="button-container">
          <el-button @click="allhandleRowClick">全部数据</el-button>
        </div>
        <div class="head-container table-container">
          <el-table :data="deptOptions" style="width: 100%" @row-click="handleRowClick">
            <el-table-column prop="categoryName" label="分类名称"  fixed="left">
              <template slot-scope="scope">
                <span>{{ scope.row.categoryName }} ({{ scope.row.count }})</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="90">
              <template slot-scope="scope">
                   <el-button size="mini" icon="el-icon-edit" type="text" @click.stop="handleRowAdd(scope.row)">批量添加</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click.stop="handleRowDelete(scope.row)">批量减少</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="22" :xs="24">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否上传物品图片" prop="hasImage">
        <el-select v-model="queryParams.hasImage" placeholder="请选择是否上传物品图片" clearable>
          <el-option
            v-for="dict in dict.type.sys_yes_no"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="购买价格" prop="purchasePrice">
        <el-input
          v-model="queryParams.purchasePrice"
          placeholder="请输入购买价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购买日期">
        <el-date-picker
          v-model="daterangePurchaseDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="购买的数量" prop="purchaseQuantity">
        <el-input
          v-model="queryParams.purchaseQuantity"
          placeholder="请输入购买的数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购买平台" prop="purchasePlatform">
        <el-select v-model="queryParams.purchasePlatform" placeholder="请选择购买平台" clearable>
          <el-option
            v-for="dict in dict.type.item_infomation"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订单号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生产日期">
        <el-date-picker
          v-model="daterangeProductionDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="保质期" prop="shelfLife">
        <el-input
          v-model="queryParams.shelfLife"
          placeholder="请输入保质期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有效期至">
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
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdatedDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="是否生成二维码" prop="qrCodeGenerated">
        <el-input
          v-model="queryParams.qrCodeGenerated"
          placeholder="请输入是否生成二维码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物品状态" prop="itemStatus">
        <el-select v-model="queryParams.itemStatus" placeholder="请选择物品状态" clearable>
          <el-option
            v-for="dict in dict.type.item_information"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="使用百分率" prop="usageRate">
        <el-input
          v-model="queryParams.usageRate"
          placeholder="请输入使用百分率"
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
          v-hasPermi="['system:information:add']"
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
          v-hasPermi="['system:information:edit']"
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
          v-hasPermi="['system:information:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:information:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="informationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column prop="imageUrl" label="图片" width="110">
        <template slot-scope="scope">
          <img
            v-if="scope.row.itemImageVOList && scope.row.itemImageVOList.length > 0 && scope.row.itemImageVOList[0].imageUrl"
            :src="scope.row.itemImageVOList[0].imageUrl"
            :class="['thumbnail']"
            @click="openViewer(scope.row.itemImageVOList)"
            width="100"
            height="auto"
          />
          <span v-else>暂无图片</span>
          <!-- 大图查看器组件 -->
          <el-dialog :visible.sync="viewerVisible" width="60%" @update:visible="handleViewerClose">
            <div class="viewer">
              <img
                :src="viewerCurrentImage.imageUrl"
                :class="['viewer-image']"
                :style="{ transform: `scale(${viewerScale})` }"
                @wheel="handleViewerZoom"
              />
              <div class="viewer-controls">
                <button class="viewer-control" @click="switchViewerImage(-1)">
                  <i class="el-icon-arrow-left"></i>
                </button>
                <button class="viewer-control" @click="switchViewerImage(1)">
                  <i class="el-icon-arrow-right"></i>
                </button>
                <button class="viewer-control" @click="zoomViewerImage(1)">
                  <i class="el-icon-zoom-in"></i>
                </button>
                <button class="viewer-control" @click="zoomViewerImage(-1)">
                  <i class="el-icon-zoom-out"></i>
                </button>
              </div>
            </div>
          </el-dialog>
        </template>
      </el-table-column>
      <el-table-column label="主键ID" align="center" prop="itemId" width="60"/>
      <el-table-column label="名称" align="center" prop="itemName" width="100"/>
      <el-table-column label="物品的描述信息" align="center" prop="description" width="280"/>
      <el-table-column label="是否上传物品图片" align="center" prop="hasImage">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.hasImage"/>
        </template>
      </el-table-column>
      <el-table-column label="购买价格" align="center" prop="purchasePrice" />
      <el-table-column label="购买日期" align="center" prop="purchaseDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="购买的数量" align="center" prop="purchaseQuantity" />
      <el-table-column label="购买平台" align="center" prop="purchasePlatform">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.item_infomation" :value="scope.row.purchasePlatform"/>
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center" prop="orderId" />
      <el-table-column label="生产日期" align="center" prop="productionDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保质期" align="center" prop="shelfLife" />
      <el-table-column label="有效期至" align="center" prop="expirationDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="物品存放的位置" align="center" prop="storageLocation" width="120"/>
      <el-table-column label="备注信息" align="center" prop="remark" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdDate,  '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="更新时间" align="center" prop="updatedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedDate,  '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否生成二维码" align="center" prop="qrCodeGenerated">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.qrCodeGenerated"/>
        </template>
      </el-table-column>
      <el-table-column label="物品状态" align="center" prop="itemStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.item_information" :value="scope.row.itemStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="使用百分率" align="center" prop="usageRate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"  width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:information:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:information:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handAddCategory(scope.row)"
            v-hasPermi="['system:information:remove']"
          >加入分类</el-button>
          <div>
            <input type="file" multiple ref="fileInput" @change="handleFileSelect">
            <button @click="uploadFiles(scope.row)">上传</button>
          </div>
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
      </el-col>
    </el-row>
    <!-- 对每一行添加或减少分类对话框 -->
    <el-dialog :visible.sync="addCategoryLoading" width="500px" append-to-body>
      <h1 class="bold-center">{{ openItemName }}</h1>
      <div>
        <h3>已加入的分类：</h3>
        <div v-for="category in selectedCategories" :key="category.categoryId">
          <el-checkbox v-model="selectedCategoryIds" :label="category.categoryId">
            {{ category.categoryName }}
          </el-checkbox>
        </div>
      </div>
      <div v-if="unselectedCategories.length">
        <h3>未加入的分类：</h3>
        <div v-for="category in unselectedCategories" :key="category.categoryId">
          <el-checkbox v-model="selectedCategoryIds" :label="category.categoryId">
            {{ category.categoryName }}
          </el-checkbox>
        </div>
      </div>
      <br>
      <br>
      <el-button type="primary" @click="handleAddCategoryConfirm">确定</el-button>
      <el-button @click="dismissConfirm();addCategoryLoading = false">取消</el-button>
    </el-dialog>


    <!-- 添加或修改物品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="itemName" width="100">
          <el-input v-model="form.itemName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="物品的描述信息" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="购买价格" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入购买价格" />
        </el-form-item>
        <el-form-item label="购买日期" prop="purchaseDate">
          <el-date-picker clearable
            v-model="form.purchaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择购买日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购买的数量" prop="purchaseQuantity">
          <el-input v-model="form.purchaseQuantity" placeholder="请输入购买的数量" />
        </el-form-item>
        <el-form-item label="购买平台" prop="purchasePlatform">
          <el-select v-model="form.purchasePlatform" placeholder="请选择购买平台">
            <el-option
              v-for="dict in dict.type.item_infomation"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="生产日期" prop="productionDate">
          <el-date-picker clearable
            v-model="form.productionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生产日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保质期" prop="shelfLife"个月>
          <el-input v-model="form.shelfLife" placeholder="请输入保质期" />
        </el-form-item>
        <el-form-item label="有效期至" prop="expirationDate">
          <el-date-picker clearable
            v-model="form.expirationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择有效期至">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="物品存放的位置" prop="storageLocation" width="120">
          <el-input v-model="form.storageLocation" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="物品状态" prop="itemStatus">
          <el-select v-model="form.itemStatus" placeholder="请选择物品状态">
            <el-option
              v-for="dict in dict.type.item_information"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用百分率" prop="usageRate">
          <el-input v-model="form.usageRate" placeholder="请输入使用百分率" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 分类添加物品弹框 -->
    <el-dialog :visible.sync="dialogVisible" title="分类添加物品信息">
      <span slot="footer" class="dialog-footer" >
    <el-button type="primary" icon="el-icon-check" @click="handleConfirm()">确定添加</el-button>
    <el-button @click="dismissConfirm();dialogVisible = false">取消</el-button>
  </span>
      <el-table v-loading="loading" :data="notInCategoryList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="imageUrl" label="图片">
          <template slot-scope="scope">
            <img
              v-if="scope.row.itemImageVOList && scope.row.itemImageVOList.length > 0 && scope.row.itemImageVOList[0].imageUrl"
              :src="scope.row.itemImageVOList[0].imageUrl"
              :class="['thumbnail']"
              @click="openViewer(scope.row.itemImageVOList)"
              width="100"
              height="auto"
            />
            <span v-else>暂无图片</span>
            <!-- 大图查看器组件 -->
            <el-dialog :visible.sync="viewerVisible" width="60%" @update:visible="handleViewerClose">
              <div class="viewer">
                <img
                  :src="viewerCurrentImage.imageUrl"
                  :class="['viewer-image']"
                  :style="{ transform: `scale(${viewerScale})` }"
                  @wheel="handleViewerZoom"
                />
                <div class="viewer-controls">
                  <button class="viewer-control" @click="switchViewerImage(-1)">
                    <i class="el-icon-arrow-left"></i>
                  </button>
                  <div style="float: right;">
                  <button class="viewer-control" @click="switchViewerImage(1)">
                    <i class="el-icon-arrow-right"></i>
                  </button>
                  </div>
                  <button class="viewer-control" @click="zoomViewerImage(1)">
                    <i class="el-icon-zoom-in"></i>
                  </button>
                  <button class="viewer-control" @click="zoomViewerImage(-1)">
                    <i class="el-icon-zoom-out"></i>
                  </button>
                </div>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
        <el-table-column label="主键ID" align="center" prop="itemId" />
        <el-table-column label="名称" align="center" prop="itemName" width="100"/>
        <el-table-column label="物品的描述信息" align="center" prop="description" width="280"/>
        <el-table-column label="是否上传物品图片" align="center" prop="hasImage">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.hasImage"/>
          </template>
        </el-table-column>
        <el-table-column label="购买价格" align="center" prop="purchasePrice" />
        <el-table-column label="购买日期" align="center" prop="purchaseDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="购买的数量" align="center" prop="purchaseQuantity" />
        <el-table-column label="购买平台" align="center" prop="purchasePlatform">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.item_infomation" :value="scope.row.purchasePlatform"/>
          </template>
        </el-table-column>
        <el-table-column label="订单号" align="center" prop="orderId" />
        <el-table-column label="生产日期" align="center" prop="productionDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.productionDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="保质期" align="center" prop="shelfLife" />
        <el-table-column label="有效期至" align="center" prop="expirationDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="物品存放的位置" align="center" prop="storageLocation" width="120"/>
        <el-table-column label="备注信息" align="center" prop="remark" />
        <el-table-column label="创建人" align="center" prop="createdBy" />
        <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新人" align="center" prop="updatedBy" />
        <el-table-column label="更新时间" align="center" prop="updatedDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updatedDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否生成二维码" align="center" prop="qrCodeGenerated">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.qrCodeGenerated"/>
          </template>
        </el-table-column>
        <el-table-column label="物品状态" align="center" prop="itemStatus">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.item_information" :value="scope.row.itemStatus"/>
          </template>
        </el-table-column>
        <el-table-column label="使用百分率" align="center" prop="usageRate" />
      </el-table>
    </el-dialog>

    <!-- 分类减少物品弹框 -->
    <el-dialog :visible.sync="deleteDialogVisible" title="分类删除物品信息">
      <span slot="footer" class="dialog-footer" >
    <el-button type="primary" icon="el-icon-check" @click="handleDeleteConfirm()">确定减少</el-button>
    <el-button @click="dismissConfirm();deleteDialogVisible = false">取消</el-button>
  </span>
      <el-table v-loading="loading" :data="notInCategoryList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column prop="imageUrl" label="图片">
          <template slot-scope="scope">
            <img
              v-if="scope.row.itemImageVOList && scope.row.itemImageVOList.length > 0 && scope.row.itemImageVOList[0].imageUrl"
              :src="scope.row.itemImageVOList[0].imageUrl"
              :class="['thumbnail']"
              @click="openViewer(scope.row.itemImageVOList)"
              width="100"
              height="auto"
            />
            <span v-else>暂无图片</span>
            <!-- 大图查看器组件 -->
            <el-dialog :visible.sync="viewerVisible" width="60%" @update:visible="handleViewerClose">
              <div class="viewer">
                <img
                  :src="viewerCurrentImage.imageUrl"
                  :class="['viewer-image']"
                  :style="{ transform: `scale(${viewerScale})` }"
                  @wheel="handleViewerZoom"
                />
                <div class="viewer-controls">
                  <button class="viewer-control" @click="switchViewerImage(-1)">
                    <i class="el-icon-arrow-left"></i>
                  </button>
                  <div style="float: right;">
                    <button class="viewer-control" @click="switchViewerImage(1)">
                      <i class="el-icon-arrow-right"></i>
                    </button>
                  </div>
                  <button class="viewer-control" @click="zoomViewerImage(1)">
                    <i class="el-icon-zoom-in"></i>
                  </button>
                  <button class="viewer-control" @click="zoomViewerImage(-1)">
                    <i class="el-icon-zoom-out"></i>
                  </button>
                </div>
              </div>
            </el-dialog>
          </template>
        </el-table-column>
        <el-table-column label="主键ID" align="center" prop="itemId" />
        <el-table-column label="名称" align="center" prop="itemName" width="100"/>
        <el-table-column label="物品的描述信息" align="center" prop="description" />
        <el-table-column label="是否上传物品图片" align="center" prop="hasImage">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.hasImage"/>
          </template>
        </el-table-column>
        <el-table-column label="购买价格" align="center" prop="purchasePrice" />
        <el-table-column label="购买日期" align="center" prop="purchaseDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="购买的数量" align="center" prop="purchaseQuantity" />
        <el-table-column label="购买平台" align="center" prop="purchasePlatform">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.item_infomation" :value="scope.row.purchasePlatform"/>
          </template>
        </el-table-column>
        <el-table-column label="订单号" align="center" prop="orderId" />
        <el-table-column label="生产日期" align="center" prop="productionDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.productionDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="保质期" align="center" prop="shelfLife"/>
        <el-table-column label="有效期至" align="center" prop="expirationDate" width="100">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="物品存放的位置" align="center" prop="storageLocation" width="120"/>
        <el-table-column label="备注信息" align="center" prop="remark" />
        <el-table-column label="创建人" align="center" prop="createdBy" />
        <el-table-column label="创建时间" align="center" prop="createdDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdDate,  '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新人" align="center" prop="updatedBy" />
        <el-table-column label="更新时间" align="center" prop="updatedDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.updatedDate,  '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="是否生成二维码" align="center" prop="qrCodeGenerated">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_yes_no" :value="scope.row.qrCodeGenerated"/>
          </template>
        </el-table-column>
        <el-table-column label="物品状态" align="center" prop="itemStatus">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.item_information" :value="scope.row.itemStatus"/>
          </template>
        </el-table-column>
        <el-table-column label="使用百分率" align="center" prop="usageRate" />
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { listInformation, getInformation, delInformation, addInformation, updateInformation,upload } from "@/api/system/information";
import request from '@/utils/request'
import Viewer from 'viewerjs';
import 'viewerjs/dist/viewer.css';
import { listCategory, getCategory, delCategory, addCategory, updateCategory,addSerachIetmByCategoryId,addIetmByCategoryId,deleteIetmByCategoryId,serachIetmByCategoryId,serachCategoryByIetmId} from "@/api/system/category";
import { listRelation, getRelation, delRelation, addRelation, updateRelation,addCategoryAfterDelete } from "@/api/system/relation";

export default {
  computed: {
    imageSize() {
      return 'image-size';
    },
    isChecked() {
      return (categoryId) => {
        return this.selectedCategoryIds.includes(categoryId);
      };
    }
  },
  name: "Information",
  dicts: ['item_information', 'item_infomation', 'sys_yes_no'],
  data() {


    return {
      openItemName:null,
      uploadDialogVisible: false,
      deptOptions: [],
      categoryName: '', // 分类名称
      fileList: [],
      uploading: false,
      itemId: null,
      id: null,

      viewerVisible: false, // 大图查看器是否可见
      viewerImages: [], // 所有图片对象列表
      viewerCurrentIndex: 0, // 当前展示图片的索引
      viewerCurrentImage: {}, // 当前展示的图片对象
      viewerScale: 1, // 图片缩放比例
      categoryId:null,
      afterDeleteitemId:null,
      // 遮罩层
      loading: true,
      addCategoryLoading: false,
      addCategoryList: [],
      selectedCategories: [], // 存储已选中的分类
      unselectedCategories: [], // 存储未选中的分类
      selectedCategoryIds: [] ,// 存储勾选的categoryId
      // 选中数组
      ids: [],
      rowCategoryId:null,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物品信息表格数据
      informationList: [],
      notInCategoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dialogVisible:false,
      deleteDialogVisible:false,
      // 使用百分率时间范围
      daterangePurchaseDate: [],
      // 使用百分率时间范围
      daterangeProductionDate: [],
      // 使用百分率时间范围
      daterangeExpirationDate: [],
      // 使用百分率时间范围
      daterangeCreatedDate: [],
      // 使用百分率时间范围
      daterangeUpdatedDate: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        itemName: null,
        description: null,
        hasImage: null,
        purchasePrice: null,
        purchaseDate: null,
        purchaseQuantity: null,
        purchasePlatform: null,
        orderId: null,
        productionDate: null,
        shelfLife: null,
        expirationDate: null,
        storageLocation: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        updatedDate: null,
        qrCodeGenerated: null,
        itemStatus: null,
        usageRate: null,
        categoryId:null
      },

      categoryQueryParams: {
        checkFlag:true
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        itemName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        // purchasePrice: [
        //   { required: true, message: "购买价格不能为空", trigger: "blur" }
        // ],
        // purchaseDate: [
        //   { required: true, message: "购买日期不能为空", trigger: "blur" }
        // ],
        purchaseQuantity: [
          { required: true, message: "购买的数量不能为空", trigger: "blur" }
        ],
        purchasePlatform: [
          { required: true, message: "购买平台不能为空", trigger: "change" }
        ],
        // productionDate: [
        //   { required: true, message: "生产日期不能为空", trigger: "blur" }
        // ],
        storageLocation: [
          { required: true, message: "物品存放的位置不能为空", trigger: "blur" }
        ],
        itemStatus: [
          { required: true, message: "物品状态不能为空", trigger: "change" }
        ],
        usageRate: [
          { required: true, message: "使用百分率不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getCategoryList();
    this.getList();
  },


  methods: {
    //添加物品弹框，确定添加
    handleConfirm(){

      const idList = this.ids
      console.log("rowCategoryId"+this.rowCategoryId);
      const categoryId =this.rowCategoryId
      if (idList.length>0) {
        this.$modal.confirm('是否确认对该分类添加物品信息编号为"' + idList + '"的数据项？').then(function () {

          let addIetm= new FormData(); // 创建FormData对象

          addIetm.append('categoryId', categoryId);
          addIetm.append('itemIdList', idList.join(','));

          return addIetmByCategoryId(addIetm);
        }).then(() => {
          this.loading = true;
          this.reset();
          this.dialogVisible = true;
          addSerachIetmByCategoryId(categoryId).then(response => {
            this.notInCategoryList = response.rows;
            this.total = response.total;
            this.loading = false;
          });
          this.$modal.msgSuccess("添加成功");
        }).catch(() => {
        });
      }
    },
//删除物品弹框，确定删除
    handleDeleteConfirm(){
      const idList = this.ids
      const categoryId =this.rowCategoryId
      if (idList.length>0) {
        this.$modal.confirm('是否从该分类中移除物品信息编号为"' + idList + '"的数据项？').then(function () {

          let addIetm= new FormData(); // 创建FormData对象

          addIetm.append('categoryId', categoryId);
          addIetm.append('itemIdList', idList.join(','));

          return deleteIetmByCategoryId(addIetm);
        }).then(() => {
          this.loading = true;
          this.reset();
          this.deleteDialogVisible = true;
          addSerachIetmByCategoryId(categoryId).then(response => {
            this.notInCategoryList = response.rows;
            this.total = response.total;
            this.loading = false;
          });
          this.$modal.msgSuccess("移除成功");
        }).catch(() => {
        });
      }
    },
    //添加进分类中后取消按钮
    dismissConfirm(){
      this.getCategoryList()
    },
    //点击分类时触发的事件
    handleRowClick(row){
      const caId = row.categoryId ;
      this.categoryId=caId
      console.log("categoryId"+this.categoryId);
      this.getList();
    },
    allhandleRowClick(){
      this.categoryId=null
      console.log("categoryIdkkkk"+this.categoryId);
      this.getList();
    },
    //查询该用户不存在于该分类的物品数据,点击添加按钮的时候
    handleRowAdd(row){
      this.loading = true;
      const caId = row.categoryId ;
      this.rowCategoryId=caId
      console.log(typeof caId)
      this.reset();
      this.dialogVisible = true;
      // this.title = "分类添加物品信息";
      // this.queryParams.pageNum = 1;
      // this.queryParams.params = {};
      addSerachIetmByCategoryId(caId).then(response => {
        this.notInCategoryList = response.rows;
        // this.total = response.total;
        this.loading = false;
      });
    },
    //点击删除移除时批量移除触发的事件
    handleRowDelete(row){
      this.loading = true;
      const caId = row.categoryId ;
      this.rowCategoryId=caId
      this.deleteDialogVisible = true;
      let serachIetm= new FormData(); // 创建FormData对象

      serachIetm.append('categoryId', caId);
      serachIetmByCategoryId(serachIetm).then(response => {
        this.notInCategoryList = response.rows;
        this.loading = false;
      });
    },
    handleFileSelect(event) {
      this.files = event.target.files; // 将选中的文件列表存储到data中
    },
    uploadFiles(row) {
      const itemId = row.itemId || this.ids;
      let fd = new FormData(); // 创建FormData对象
      for (let file of this.files) {
        fd.append('files', file); // 将选中的文件添加到FormData中
      }
      fd.append("id", itemId); //传其他参数
       upload(fd).then(response => {
         this.$modal.msgSuccess("上传成功");
         this.files = [];
         this.getList();
       });;

    },
    clearFileInput() {
      this.$refs.fileInput.value = ''; // 重置文件选择框的值
      this.files = []; // 清空文件列表
    },

    openViewer(images) {
      // 初始化大图查看器数据
      this.viewerVisible = true;
      this.viewerImages = images;
      this.viewerCurrentIndex = 0;
      this.viewerCurrentImage = this.viewerImages[this.viewerCurrentIndex];
      this.viewerScale = 1;
    },
    switchViewerImage(step) {
      // 切换大图查看器中的图片
      this.viewerCurrentIndex += step;
      if (this.viewerCurrentIndex < 0) {
        this.viewerCurrentIndex = this.viewerImages.length - 1;
      } else if (this.viewerCurrentIndex >= this.viewerImages.length) {
        this.viewerCurrentIndex = 0;
      }
      this.viewerCurrentImage = this.viewerImages[this.viewerCurrentIndex];
      this.viewerScale = 1;
    },
    zoomViewerImage(scale) {
      // 缩放大图查看器中的图片
      this.viewerScale += scale;
      const minScale = 0.1; // 设置最小缩放比例
      if (this.viewerScale < minScale) {
        this.viewerScale = minScale;
      }
    },
    handleViewerClose() {
      // 关闭大图查看器时重置数据
      this.viewerVisible = false;
      this.viewerImages = [];
      this.viewerCurrentIndex = 0;
      this.viewerCurrentImage = {};
      this.viewerScale = 1;
    },
    handleViewerZoom(event) {
      // 根据滚轮事件缩放图片
      event.preventDefault();
      const scaleDelta = event.deltaY > 0 ? -0.1 : 0.1;
      this.zoomViewerImage(scaleDelta);
    },
    //查询收藏分类
    getCategoryList(){
      this.loading = true;
        this.categoryQueryParams.params = {};
      listCategory(this.categoryQueryParams).then(response => {
        this.deptOptions = response.rows;
        this.loading = false;
        // this.deptOptions = response.data;
      });
    },

    /** 查询物品信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePurchaseDate && '' != this.daterangePurchaseDate) {
        this.queryParams.params["beginPurchaseDate"] = this.daterangePurchaseDate[0];
        this.queryParams.params["endPurchaseDate"] = this.daterangePurchaseDate[1];
      }
      if (null != this.daterangeProductionDate && '' != this.daterangeProductionDate) {
        this.queryParams.params["beginProductionDate"] = this.daterangeProductionDate[0];
        this.queryParams.params["endProductionDate"] = this.daterangeProductionDate[1];
      }
      if (null != this.daterangeExpirationDate && '' != this.daterangeExpirationDate) {
        this.queryParams.params["beginExpirationDate"] = this.daterangeExpirationDate[0];
        this.queryParams.params["endExpirationDate"] = this.daterangeExpirationDate[1];
      }
      if (null != this.daterangeCreatedDate && '' != this.daterangeCreatedDate) {
        this.queryParams.params["beginCreatedDate"] = this.daterangeCreatedDate[0];
        this.queryParams.params["endCreatedDate"] = this.daterangeCreatedDate[1];
      }
      if (null != this.daterangeUpdatedDate && '' != this.daterangeUpdatedDate) {
        this.queryParams.params["beginUpdatedDate"] = this.daterangeUpdatedDate[0];
        this.queryParams.params["endUpdatedDate"] = this.daterangeUpdatedDate[1];
      }

        this.queryParams.categoryId = this.categoryId;

      console.log("getlist"+this.categoryId)
      listInformation(this.queryParams).then(response => {
        this.informationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.dialogVisible = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        itemId: null,
        itemName: null,
        description: null,
        hasImage: null,
        purchasePrice: null,
        purchaseDate: null,
        purchaseQuantity: null,
        purchasePlatform: null,
        orderId: null,
        productionDate: null,
        shelfLife: null,
        expirationDate: null,
        storageLocation: null,
        remark: null,
        createdBy: null,
        createdDate: null,
        updatedBy: null,
        userId: null,
        userName: null,
        updatedDate: null,
        delFlag: null,
        qrCodeGenerated: null,
        itemStatus: null,
        usageRate: null,
        categoryId:null
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
      this.daterangePurchaseDate = [];
      this.daterangeProductionDate = [];
      this.daterangeExpirationDate = [];
      this.daterangeCreatedDate = [];
      this.daterangeUpdatedDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.itemId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 分类增加时的多选框
    // infomationHandleSelectionChange(categorySelection) {
    //   this.ids = categorySelection.map(item => item.itemId)
    //   this.single = categorySelection.length!==1
    //   this.multiple = !categorySelection.length
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const itemId = row.itemId || this.ids
      getInformation(itemId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.itemId != null) {
            updateInformation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInformation(this.form).then(response => {
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
      const itemIds = row.itemId || this.ids;
      this.$modal.confirm('是否确认删除物品信息编号为"' + itemIds + '"的数据项？').then(function() {
        return delInformation(itemIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 根据物品id查询这个绑定了哪些分类 */
    handAddCategory(row){
      const itemIds = row.itemId || this.ids;

      this.afterDeleteitemId=itemIds
      serachCategoryByIetmId(itemIds).then(response => {
        this.openItemName=row.itemName;
        this.addCategoryList = response.rows;
        this.addCategoryLoading = true;
        this.selectedCategories = response.rows.filter(category => category.flag === true);
        this.unselectedCategories = response.rows.filter(category => category.flag !== true);
        this.selectedCategoryIds = this.selectedCategories.map(category => category.categoryId);
      });
    },
    handleAddCategoryConfirm(){
      const selectedCategoryIds = this.selectedCategoryIds;
      let addIetm= new FormData(); // 创建FormData对象
      addIetm.append('itemId', this.afterDeleteitemId);
      addIetm.append('categoryIdList', selectedCategoryIds.join(','));
       addCategoryAfterDelete(addIetm).then(response=>{
         // 关闭弹框
         this.$modal.msgSuccess("修改分类成功！");
         this.addCategoryLoading = false;
         this.getCategoryList()
       });

    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/information/export', {
        ...this.queryParams
      }, `information_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style >

.viewer {
  display: flex; /* 使用 flex 布局 */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 800px; /* 高度占满父容器 */
  overflow: auto; /* 超出部分隐藏 */
}

.viewer-image {
  max-width: 100%; /* 图片最大宽度为父容器的宽度 */
  max-height: 100%; /* 图片最大高度为父容器的高度 */
  transition: transform 0.3s; /* 图片过渡动画效果 */
  background-color: rgba(0, 0, 0, 0); /* 设置图片背景色为透明 */
}

.viewer-controls {
  position: absolute; /* 绝对定位 */
  top: 50%; /* 顶部距离为父容器的一半 */
  left: 10px; /* 左边距离为 10px */
  transform: translateY(-50%); /* 上下居中 */
  display: flex; /* 使用 flex 布局 */
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 水平居中 */
}

.viewer-control {
  margin-bottom: 10px; /* 底部外边距为 10px */
  padding: 5px; /* 内边距为 5px */
  background-color: rgba(0, 0, 0, 0.5); /* 背景颜色为半透明黑色 */
  color: #ffffff; /* 文字颜色为白色 */
  border: none; /* 去除边框 */
  cursor: pointer; /* 鼠标指针样式为手型 */
  transition: background-color 0.3s; /* 背景颜色过渡动画效果 */
}

.viewer-control:hover {
  background-color: rgba(0, 0, 0, 0.8); /* 鼠标悬停时背景颜色加深 */
}
.viewer-preview {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.viewer-preview-image {
  max-width: 100px;
  max-height: 100px;
  margin-right: 10px;
  cursor: pointer;
}

.viewer-preview-image:last-child {
  margin-right: 0;
}
.dialog-footer {
  position: absolute;
  right: 10px; /* 调整按钮距离右侧的间距 */
  top: 10px; /* 调整按钮距离顶部的间距 */
}
.table-container {
  overflow: auto; /* 设置滚动行为 */
  max-height: 800px; /* 设置容器最大高度，可根据需要调整 */
}

#components-layout-demo-fixed-sider .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
.bold-center {
  font-weight: bold; /* 加粗 */
  text-align: center; /* 居中 */
}
</style>

