<template>
  <main>
    <q-header reveal bordered class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="drawer = !drawer" />

        <q-toolbar-title @click="goHome">
          <q-avatar>
            <img src="https://w7.pngwing.com/pngs/739/102/png-transparent-google-chrome-computer-icons-chrome-web-store-web-browser-world-wide-web-logo-internet-google-chrome-thumbnail.png"  />
          </q-avatar>
          TESTWEB-APP   
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="drawer"
      show-if-above
      :width="200"
      :breakpoint="500"
      bordered
      class="bg-grey-3"
    >

      <!-- drawer content -->
      <q-scroll-area class="fit">
        <q-list>
          <template v-for="(menuItem, index) in list.menuList" :key="index">
            <q-item
              clickable
              :active= "data === menuItem.MENU_PATH"
              v-ripple
              @click="(data = menuItem.MENU_PATH), abc(data,menuItem.MENU_LABEL)"
            >
              <q-item-section avatar>
                <q-icon :name="menuItem.MENU_ICON" />
              </q-item-section>
              <q-item-section>
                {{ menuItem.MENU_LABEL }}
              </q-item-section>
            </q-item>
            <q-separator :key="'sep' + index" v-if="menuItem.MENU_SEPARATOR" />
          </template>
        </q-list>
      </q-scroll-area>
    </q-drawer>
  </main>
</template>

<script>
import { onMounted, reactive, ref } from "vue";
import router from "../../router/index.js";
import axios from "axios";

export default {
  setup() {
    const list = reactive({
      menuList: [],
    });
    const getMenu = () => {
      axios
        .post("http://localhost:8090/menu/menuList")
        .then((response) => {
          list.menuList = response.data;
          console.log(list.menuList);
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    const path = window.location.pathname.substring(1);
    const data = ref(path);
    const abc = function (data , label) {
      var repactLink = true;
      router.options.routes.forEach((route) => {
        if (route.name == label) {
          // eslint-disable-next-line no-undef
          repactLink = false;
        }
      });
      if (repactLink) {
        router.addRoute({
          component: () => import("../" + label.trim() + "View.vue"),
          name: label,
          path: "/" + data,
        });
      }
      router.push("/" + data);
    };

    const goRef = () =>{
       var path = window.location.pathname.substring(1);
       var newStr = path.replace(/^[a-z]/, (char) => char.toUpperCase());
       var repactLink = true;
      router.options.routes.forEach((route) => {
        if (route.name == newStr ) {
          // eslint-disable-next-line no-undef
          repactLink = false;
        }
      });
      if (repactLink) {
        router.addRoute({
          component: () => import("../" + newStr.trim() + "View.vue"),
          name: newStr,
          path: "/" + path,
        });
      }
      router.push("/" + path);

    }
    const goHome = () => {
      location.href="/"
    }
    onMounted(() => {
      getMenu();
      goRef();

    });

    return {
      drawer: ref(false),
      list,
      getMenu,
      abc,
      data,
      goRef,
      goHome,
    };
  },
};
</script>
