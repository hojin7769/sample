import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import ListDetail from '../views/DetailView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
  },
  {
    path: '/list',
    name: 'list',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "list" */ '../views/ListView.vue'),
  },
  {
    path: '/listDetail',
    name: 'listDetail',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component:ListDetail,
    // true로 설정하면 데이터를 props로도 받습니다.
    props: true,
  },{
    path: '/login',
    name:'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/login/login_main.vue'),
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
