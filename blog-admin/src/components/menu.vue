<template>
  <el-menu
    active-text-color="#ffd04b"
    background-color="#545c64"
    class="page-menu"
    default-active="1"
    text-color="#fff"
  >
    <div class="page-menu-title">
      {{ menus.title }}
    </div>
    <el-menu-item v-for="menu in menus.menu" :index="menu.id" :key="menu.id">
      <el-button type="text" @click="menuClick(menu.path)" size = "medium">
        {{ menu.name }}
      </el-button>
    </el-menu-item>
  </el-menu>
</template>

<script>
import MenuConfig from "../config/menu.config.js";

export default {
  name: "page-menu",
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
  },
};
</script>

<style scoped>
.page-menu {
  height: 100%;
}
.page-menu-title {
  line-height: 40px;
  color: white;
}
</style>