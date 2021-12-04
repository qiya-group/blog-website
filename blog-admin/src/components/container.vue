<template>
  <el-container id = "page-container" :style="{ height: height }">
    <el-aside width="200px">
      <PageMenu />
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <PageHeader />
        <span>Tom</span>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import PageMenu from "./menu.vue";
import PageHeader from "./header.vue";

export default {
  name: "page-container",
  components: {
    PageMenu,
    PageHeader,
  },

  computed: {
    height() {
      return `${window.innerHeight}px`; // 动态计算宽度，避免超出产生滚动条
    }
  },
};
</script>


<style scoped>
</style>