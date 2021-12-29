<template>
  <el-form ref="form" :model="form" label-width="120px" style="width: 70%">
    <el-form-item label="标题：">
      <el-input v-model="form.title"></el-input>
    </el-form-item>
    <el-form-item label="子标题：">
      <el-input v-model="form.suTitle"></el-input>
    </el-form-item>
    <el-form-item label="类别：">
      <el-input v-model="form.tag"></el-input>
    </el-form-item>
    <el-form-item label="内容：">
      <div id="toolbar-container" class="toolbar"></div>
      <div id="text-container" class="text"></div>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加</el-button>
      <el-button type="danger">取消</el-button>
      <el-button>返回上一层</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import E from "wangeditor";
import axios from "axios";

export default {
  name: "ArticleForm",
  data() {
    return {
      editor: null,
      form: {
        title: "",
        suTitle: "",
        content: "",
        tag: "",
      },
      data: 0,
    };
  },
  methods: {
    registerEditor() {
      this.editor = new E('#toolbar-container', '#text-container'); // 传入两个元素
      this.editor.create();
    },
    onSubmit() {

      // 编辑器赋值
      this.form.content = this.editor.txt.html();
      axios.post("/server/article/insert", this.form, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then((res) => {
          this.data = [res.data.data];
        })
        .catch((err) => {});
      console.log(this.form)
    }
  },
  mounted() {
    this.registerEditor();
  },
};
</script>

<style scoped>
.toolbar {
  border: 1px solid #ccc;
}
.text {
  border: 1px solid #ccc;
  min-height: 400px;
}
</style>