import { createApp } from "vue";
import { Quasar, Dialog } from "quasar";
// Import icon libraries
import "@quasar/extras/material-icons/material-icons.css";

// Import Quasar css
import "quasar/src/css/index.sass";

import App from "./App.vue";
import router from "./router";
import axios from "axios";

import headerView from "./components/layout/headerView.vue";

const myApp = createApp(App);
myApp.use(Quasar, {
  plugins: {
    Dialog,
  }, // import Quasar plugins and add here
});
myApp.component(headerView.name, headerView);
myApp.use(router);
myApp.config.globalProperties.$axios = axios;
myApp.mount("#app");
