import { createRouter, createWebHistory } from 'vue-router';
import MainView from '../views/MainView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
    },
    {
      path: '/about',
      name: 'About',
      component: () =>
        import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
    },
  ],
});

export default router;
