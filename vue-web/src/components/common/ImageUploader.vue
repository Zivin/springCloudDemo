<!--图片上传控件-->
<template>
  <el-upload class="image-uploader" :action="this.fileUploadUrl" :show-file-list="false"
             :headers="this.fileUploadHeader" :data="fileUploadData" :on-success="handleImageSuccess"
             :on-error="handleImageError" :before-upload="beforeImageUpload">
    <default-image :src="this.imageUrl" class="image-box"></default-image>
    <el-button class="url-button" type="primary" size="mini" @click.stop="setUrl">URL</el-button>
  </el-upload>
</template>

<script>
  import * as constant from 'utils/constant'

  export default {
    data() {
      return {
        imageUrl: this.initUrl
      }
    },
    props: [
      'initUrl'
    ],
    watch: {
      initUrl(val) {
        this.imageUrl = val;
      }
    },
    computed: {
      fileUploadData: function () {
        let param = {};
        param[constant.FILE_UPLOAD_PARAM] = this.imageUrl;
        return param;
      }
    },
    methods: {
      handleImageSuccess(response) {
        if (response.code == 0) {
          this.imageUrl = response.data[0];
          this.$emit('success', response.data[0]);
        } else {
          this.$message.error('文件上传失败!');
        }
      },
      handleImageError(err) {
        this.$message.error('文件上传失败!');
        this.$emit('error', err);
      },
      beforeImageUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 < 200;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 200KB!');
        }
        return isJPG && isLt2M;
      },
      setUrl() {
        this.$prompt('请输入图片地址', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?$/,
          inputErrorMessage: '图片地址格式不正确'
        }).then(({value}) => {
          this.$emit('success', value);
        }).catch(() => {
        });
      }
    }
  }
</script>

<style scoped>
  .image-uploader {
    width: 200px;
    height: 200px;
    border: 1px dashed #20a0ff;
    border-radius: 10px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .image-box {
    width: 200px;
    height: 200px;
    display: block;
  }

  .url-button {
    position: absolute;
    bottom: 0;
    right: 0;
  }
</style>
