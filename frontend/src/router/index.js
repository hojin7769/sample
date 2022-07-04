import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/MainView.vue';

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/',
			name: 'main',
			component: HomeView,
		},
		{
			path: '/about',
			name: 'About',
			component: () =>
				import(/* webpackChunkName: "about" */ '../views/AboutView.vue'),
		},
		{
			path: '/mlist',
			name: 'Mlist',
			component: () => import('../views/MlistView.vue'),
		},
	],
});

export default router;
