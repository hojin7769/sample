<script setup>
import { RouterView,RouterLink} from "vue-router";
import { ref } from 'vue';
// import TestView from './views/TestVire.vue'
import AboutView from './views/AboutView.vue'
import router  from './router/index.js';
    const name = ref();

    const abc = () => {
      let data = true;
       router.options.routes.forEach(route => {
        console.log(route.name);
        if (route.name == name.value.value) {
          // eslint-disable-next-line no-undef
          console.log(name.value);
          data = false;
        }
        console.log(data);
      });
      if (data) {
        router.addRoute({
          component: AboutView,
          name: "AboutView",
          path: "/about",
        });
        router.push('about');
      }
      
    };
</script>
<template>
<button @click="abc" ref="name" value="About">about</button>
<RouterView/>
</template>

