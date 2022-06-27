import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import MyGlobalComponent from './views/login/login_main.vue';

loadFonts()

createApp(App)
  .use(router)
  .use(store)
  .use(vuetify)
  .component('MyGlobalComponent',MyGlobalComponent)
  .mount('#app')
