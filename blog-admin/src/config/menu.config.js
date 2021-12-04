
const MenuConfig = {
    title: '博客后台管理',
    menu: [
        {
            id: 1,
            name: '首页',
            path: '/home',
            direct: '/',
            auth: [
                'admin',
                'user'
            ],       
        },
        {
            id: 2,
            name: '文章管理',
            path: '/article/content',
            auth: [
                'admin',
                'user'
            ],   
        },
    ],
}



export default MenuConfig;