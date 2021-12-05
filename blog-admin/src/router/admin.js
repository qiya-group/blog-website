import home from '../pages/home.vue'
import article from './article'

const routes = [
    {
        path: '/',
        component: home,
    },
    {
        path: '/home',
        component: home,
    },
    ...article,
]

export default routes;