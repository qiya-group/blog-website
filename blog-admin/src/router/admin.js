import home from '../pages/home.vue'
import PageContainer from '../components/container.vue';

const About = { template: '<div>About</div>' }

const routes = [
    {
        path: '/',
        component: home,
    },
    {
        path: '/home',
        component: home,
    },
    { path: '/about', component: About },
]

export default routes;