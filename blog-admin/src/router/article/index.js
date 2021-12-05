import index from '../../pages/article/index.vue';
import list from '../../pages/article/list.vue';
import form from '../../pages/article/form.vue';

const article = [
    {
        path: '/article',
        component: index,
        children: [
            {
                path: '',
                component: list,
            },
            {
                path: 'list',
                component: list,
            },
            {
                path: 'form',
                component: form,
            }
        ]
    },
];

export default article;