import { createRouter, createWebHashHistory } from 'vue-router';

// 后台管理路由
import routes from './admin';

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})
  

export default router;