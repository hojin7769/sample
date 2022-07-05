import { createApp } from 'vue';
import { Quasar } from 'quasar';
// Import icon libraries
import '@quasar/extras/material-icons/material-icons.css';

// Import Quasar css
import 'quasar/src/css/index.sass';

import App from './App.vue';
import router from './router';
import axios from 'axios';

const myApp = createApp(App);
myApp.use(Quasar, {
	plugins: {}, // import Quasar plugins and add here
});

myApp.component('ClickButton', {
	/* ... */
});
myApp.use(router);
myApp.config.globalProperties.$axios = axios;
myApp.mount('#app');
