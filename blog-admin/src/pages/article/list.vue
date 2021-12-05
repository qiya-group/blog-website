<template>
  <div style="height: 100%">
      <el-header>
        <el-row>
          <el-col :span="10">
            <el-input
              v-model="input"
              placeholder="搜索内容"
              style="width: 50%; margin-right: 10px"
            />
            <el-button type="primary">
              <el-icon>
                <Search></Search>
              </el-icon>
              搜索</el-button
            >
          </el-col>
          <el-col :span="14" style="text-align: right">
            <el-button type="primary" @click="add()">新增</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="50" align="center">
        </el-table-column>
        <el-table-column prop="title" label="标题" width="150" align="center">
        </el-table-column>
        <el-table-column
          prop="subTitle"
          label="子标题"
          width="150"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="tag" label="类别" width="80" align="center">
        </el-table-column>
        <el-table-column label="发布时间" align="center">
          <template #default="scope">
            <div>
              {{ formatTime(scope.row.publishTime) }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="文章状态" align="center">
          <template #default="scope">
            <el-tag style="margin-left: 10px" v-if="scope.row.status === 1"
              >已发布</el-tag
            >
            <el-tag style="margin-left: 10px" type="danger" v-else>草稿中</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="viewTime" label="访问次数" align="center">
        </el-table-column>
        <el-table-column fixed="right" label="操作" align="center">
          <template #default="scope">
            <el-button
              v-show="scope.row.status === 0"
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              type="text"
              >编辑</el-button
            >
            <el-button
              v-show="scope.row.status === 0"
              type="text"
              size="mini"
              @click="handleDelete(scope.$index, scope.row)"
              style="color: red"
              >删除</el-button
            >
            <el-button
              v-show="scope.row.status === 0"
              type="text"
              size="mini"
              @click="handleDelete(scope.$index, scope.row)"
              style="color: red"
              >发布</el-button
            >
            <el-button
              v-show="scope.row.status === 1"
              type="text"
              size="mini"
              @click="handleDelete(scope.$index, scope.row)"
              style="color: red"
              >查看文章</el-button>
            <el-button
              v-show="scope.row.status === 1"
              type="text"
              size="mini"
              @click="handleDelete(scope.$index, scope.row)"
              style="color: red"
              >撤回文章</el-button
            >
          </template>
        </el-table-column>
      </el-table>
       <el-pagination class = "page-list-footer" background layout="prev, pager, next" :total="tableData.length">
      </el-pagination>
  </div>
</template>

<script>
import axios from "axios";
import { ref } from "vue";
import { Search } from "@element-plus/icons";
import { formatTime } from "../../common/util";

export default {
  name: "ArticleList",
  components: {
    Search,
  },
  data() {
    return {
      tableData: [],
      formatTime,
      input: ref(""),
    };
  },
  methods: {
    initData() {
      axios
        .get("/server/article/selectByPrimaryKey?id=1")
        .then((res) => {
          this.tableData = [res.data.data];
        })
        .catch((err) => {});
    },
    handleEdit(index, row) {},
    handleDelete(index, row) {},
    add()  {
      this.$router.push('/article/form');
    }
  },
  created() {
    this.initData();
  },
};
</script>

<style scoped>
.el-header {
  padding: 0px;
}
.box-card {
  height: 100%;
}
.page-list-footer {
  line-height: 50px;
  text-align: right;
  padding-top: 10px;
}
</style>