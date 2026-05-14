<template>
  <div class="file-upload-download">
    <el-card shadow="hover">
      <div slot="header">
        <span>计算各维度差评率等数据</span>
      </div>

      <div class="upload-container">
        <!-- 上传区域 -->
        <el-upload
          class="upload-demo"
          drag
          action="javascript:;"
          :auto-upload="false"
          :on-change="handleFileChange"
          :show-file-list="false"
          :multiple="false"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传一个文件，且不超过50MB</div>
        </el-upload>

        <!-- 右侧按钮 -->
<!--        <el-button-->
<!--          type="primary"-->
<!--          class="right-button"-->
<!--          @click="handleButtonClick"-->
<!--        >-->
<!--          操作按钮-->
<!--        </el-button>-->

      </div>

      <div v-if="file" class="file-info">
        <p>已选择文件: {{ file.name }}</p>
        <p>文件大小: {{ (file.size / 1024 / 1024).toFixed(2) }} MB</p>
      </div>

      <div class="action-btns">
        <el-button
          type="primary"
          class="right-button"
          @click="handleButtonClick"
        >
          下载模板
        </el-button>
        <el-button
          type="primary"
          :loading="uploading"
          :disabled="!file"
          @click="handleUpload"
        >
          {{ uploading ? '上传中...' : '开始上传' }}
        </el-button>
      </div>

      <div v-if="errorMsg" class="error-msg">
        <el-alert :title="errorMsg" type="error" show-icon></el-alert>
      </div>
    </el-card>
  </div>
</template>

<script>
import {uploadAndDownloadFile,exportOTAeasyTemplate} from '@/api/test/excel' // 导入上面定义的API方法
export default {
  name: 'FileUploadDownload',
  data() {
    return {
      file: null,
      uploading: false,
      errorMsg: '',
      isDownloading: false
    }
  },
  methods: {
    handleFileChange(file) {
      const isLt50M = file.size / 1024 / 1024 < 50
      if (!isLt50M) {
        this.errorMsg = '上传文件大小不能超过50MB!'
        this.file = null
        return
      }

      this.file = file.raw ? file.raw : file
      this.errorMsg = ''
    },

    handleUpload() {
      if (this.isDownloading) {
        //如果当前正在下载，直接返回阻止重复请求
        return;
      }
      this.isDownloading = true
      uploadAndDownloadFile(this.file).then(response => {
        this.handleDownload(response)
        this.isDownloading = false
      }).catch(error => {
        this.isDownloading = false
      }).finally(() => {
        this.isDownloading = false
      })
    },

    handleButtonClick(){
      exportOTAeasyTemplate().then(response => {
        this.handleDownload(response)
      }).catch(error => {
        console.error('下载失败:', error);
        this.errorMsg = '文件下载失败，请重试';
      })
    },

    handleDownload(response) {
      const blob = new Blob([response.data], {
        type: 'application/file'
      })
      const url = window.URL.createObjectURL(blob)
      const a = document.createElement('a')
      a.href = url
      const patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
      console.log('patt' + patt)
      const contentDisposition = decodeURI(response.headers['content-disposition'])
      console.log('contentDisposition' + contentDisposition)
      const result = patt.exec(contentDisposition)
      const fileName = result[1].replace(/\"/g, '')
      a.download = fileName
      a.click()
    },




    //上面的下载足够，下面的是增强版的
    /*    handleUpload() {
          if (this.isDownloading) return;
          this.isDownloading = true;

          uploadAndDownloadFile(this.file).then(response => {
            // 现在 response 包含完整的 headers
            this.handleDownload({
              data: response.data,   // Blob 数据
              headers: response.headers // 完整的响应头
            });
            this.isDownloading = false;
          }).catch(error => {
            this.isDownloading = false;
            console.error('下载失败:', error);
            this.errorMsg = '文件下载失败，请重试';
          });
        },*/

    /*  handleDownload(response) {
      // 1. 调试输出响应头
      console.log('完整响应头:', response.headers);

      // 2. 获取文件名（添加健壮性处理）
      const disposition = response.headers['content-disposition'] || '';
      let fileName = 'export.xlsx';

      // 尝试从 Content-Disposition 解析文件名
      const match = disposition.match(/filename\*?=['"]?(?:UTF-\d['"]*)?([^;\n"']*)['"]?/i);
      if (match && match[1]) {
        fileName = match[1]
          .replace(/\\"/g, '"')
          .replace(/\+/g, ' ');

        try {
          fileName = decodeURIComponent(fileName);
        } catch (e) {
          console.warn('文件名解码失败，使用原始值', fileName);
        }
      }

      // 3. 创建 Blob
      const blob = new Blob([response.data], {
        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      });

      // 4. 创建下载链接
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = fileName;
      link.style.display = 'none';

      // 5. 触发下载
      document.body.appendChild(link);
      link.click();

      // 6. 清理资源
      setTimeout(() => {
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
      }, 100);
    }*/


  }
}
</script>

<style scoped>
.file-upload-download {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.upload-demo {
  margin-bottom: 20px;
}

/* 修改后生效的样式 */
::v-deep .upload-demo .el-upload-dragger {
  width: 300px;
  height: 180px;
  padding: 20px;
  border: 2px dashed #c0ccda !important;
  border-radius: 8px !important;
  background-color: #f9fafc !important;
  transition: all 0.3s;
}

.file-info {
  margin: 15px 0;
  padding: 10px;
  background-color: #f5f7fa;
  border-radius: 4px;
}

.action-btns {
  margin-top: 20px;
/*  向右移动20*/
  display: flex;
  justify-content: center;
  gap: 10px;


}

.error-msg {
  margin-top: 15px;
}
/*.right-button {
  margin-top: 60px; !* 与上传区域对齐 *!
  white-space: nowrap; !* 防止按钮文字换行 *!
}
.upload-container {
  display: flex;
  align-items: flex-start; !* 顶部对齐 *!
  gap: 20px; !* 间距 *!
}*/
</style>
