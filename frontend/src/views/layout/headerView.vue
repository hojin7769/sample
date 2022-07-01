<template>
  <main>
    <q-header reveal bordered class="bg-primary text-white">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="drawer = !drawer" />

        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
          </q-avatar>
          Title
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
              :active="data === menuItem.MENU_LABEL"
              v-ripple
              @click="(data = menuItem.MENU_LABEL), abc(data)"
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
import { onMounted, reactive, ref, toRef } from "vue";
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
    var path = window.location.pathname.substring(1);
    var newStr = path.replace(/^[a-z]/, (char) => char.toUpperCase());
    console.log(newStr);
    const data = ref(newStr);
    const abc = function (data) {
      var repactLink = true;
      router.options.routes.forEach((route) => {
        if (route.name == data) {
          // eslint-disable-next-line no-undef
          repactLink = false;
        }
      });
      if (repactLink) {
        router.addRoute({
          component: () => import("../" + data.trim() + "View.vue"),
          name: data,
          path: "/" + data.toLowerCase(),
        });
      }
      router.push("/" + data.toLowerCase());
    };

    onMounted(() => {
      getMenu();
      abc(newStr);
    });

    return {
      drawer: ref(false),
      list,
      getMenu,
      abc,
      data,
    };
  },
};
</script>
