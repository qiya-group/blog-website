<template>
  <el-menu
    active-text-color="#ffd04b"
    background-color="#545c64"
    class="page-menu"
    default-active="1"
    text-color="#fff"
  >
    <!-- <div class="page-menu-title">
      {{ menus.title }}
    </div> -->
    <el-menu-item index="1" key="1">
      <el-button
        type="text"
        @click="menuClick('/home')"
        size="medium"
        class="page-menu-btn"
      >
        <el-icon><HomeFilled /></el-icon>首页
      </el-button>
    </el-menu-item>

    <el-menu-item index="2" key="2">
      <el-button
        type="text"
        @click="menuClick('/article')"
        size="medium"
        class="page-menu-btn"
      >
        <el-icon><DocumentCopy /></el-icon>文章管理
      </el-button>
    </el-menu-item>
    <el-menu-item index="3" key="3">
      <el-button
        type="text"
        @click="menuClick('/classic')"
        size="medium"
        class="page-menu-btn"
      >
        <el-icon><Notebook /></el-icon>类别
      </el-button>
    </el-menu-item>
    <el-menu-item index="4" key="4">
      <el-button
        type="text"
        @click="menuClick('/config')"
        size="medium"
        class="page-menu-btn"
      >
        <el-icon><SetUp /></el-icon>配置系统
      </el-button>
    </el-menu-item>
    <el-menu-item index="5" key="5">
      <el-button
        type="text"
        @click="menuClick('/system')"
        size="medium"
        class="page-menu-btn"
      >
        <el-icon><Setting /></el-icon>运维管理
      </el-button>
    </el-menu-item>
  </el-menu>
</template>

<script>
import { HomeFilled, Notebook, DocumentCopy, SetUp, Setting } from "@element-plus/icons";
import MenuConfig from "../config/menu.config.js";

export default {
  name: "page-menu",
  components: {
    HomeFilled,
    Notebook,
    DocumentCopy,
    SetUp,
    Setting,
  },
  data() {
    return {
      menus: MenuConfig,
    };
  },
  methods: {
    menuClick(path) {
      // 处理路由
      this.$router.push(path);
    },
    renderIcon(icon) {
      return `
        <template>
          <el-icon>
            ${icon}
          </el-icon>
        </template>
      `;
    },
  },
};
</script>

<style scoped>
.page-menu {
  height: 100%;
}
.page-menu-title {
  line-height: 50px;
  color: white;
}
.page-menu-btn {
  color: white;
}
</style>