import { createApp } from 'vue';
// import { useUserStore } from "./stores/user";
import { Quasar, Dialog } from 'quasar';
// import pinia from "pinia";
// Import icon libraries
import '@quasar/extras/material-icons/material-icons.css';

// Import Quasar css
import 'quasar/src/css/index.sass';

import App from './App.vue';
import router from './router';
import axios from 'axios';

import headerView from './components/layout/headerView.vue';
// const userStore = useUserStore();
const myApp = createApp(App);
myApp.use(Quasar, {
  plugins: {
    Dialog,
  }, // import Quasar plugins and add here
});
myApp.component(headerView.name, headerView);
myApp.use(router);
// myApp.use(pinia);
myApp.config.globalProperties.$axios = axios;
myApp.mount('#app');
