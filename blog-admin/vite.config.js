import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: '127.0.0.1',
    port: 3000,
    proxy: {
      '/server': {
        target: 'http://127.0.0.1:8081/',	//实际请求地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/server/, '')
      },
    }
  }
})
