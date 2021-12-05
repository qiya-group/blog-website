<template>
 <el-dropdown>
      <el-icon style="margin-right: 15px"><setting /></el-icon>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item>View</el-dropdown-item>
          <el-dropdown-item>Add</el-dropdown-item>
          <el-dropdown-item>Delete</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
</template>

<script>
import { Message, Menu, Setting } from "@element-plus/icons";

export default {
  name: "page-header",
  components: {
    Message,
    Setting,
    "icon-menu": Menu,
  },
};
</script>

<style scoped>

</style>