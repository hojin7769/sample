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
              :active="data === menuItem.MENU_LABLE"
              v-ripple
              @click="(data = menuItem.MENU_LABLE), abc(data)"
            >
              <q-item-section avatar>
                <q-icon :name="menuItem.MENU_ICON" />
              </q-item-section>
              <q-item-section>
                {{ menuItem.MENU_LABLE }}
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

// const menuList = [
//   {
//     icon: 'list',
//     label: 'List',
//     separator: true
//   },
//   {
//     icon: 'send',
//     label: 'Outbox',
//     separator: false
//   },
//   {
//     icon: 'delete',
//     label: 'Trash',
//     separator: false
//   },
//   {
//     icon: 'error',
//     label: 'Spam',
//     separator: true
//   },
//   {
//     icon: 'settings',
//     label: 'Settings',
//     separator: false
//   },
//   {
//     icon: 'feedback',
//     label: 'Send Feedback',
//     separator: false
//   },
//   {
//     icon: 'help',
//     iconColor: 'primary',
//     label: 'Test',
//     separator: false
//   }
// ]
export default {
  setup() {
    var path = window.location.pathname.substring(1);
    let newStr = path.replace(/^[a-z]/, (char) => char.toUpperCase());
    console.log(newStr);
    const data = ref(newStr);
    const abc = function (data) {
      var repactLink = true;
      router.options.routes.forEach((route) => {
        console.log(route.name);
        console.log(data);
        if (route.name == data) {
          // eslint-disable-next-line no-undef
          console.log(data);
          repactLink = false;
        }
        console.log(repactLink);
      });
      if (repactLink) {
        console.log(data.toLowerCase());
        router.addRoute({
          component: () => import("../" + data.trim() + "View.vue"),
          name: data,
          path: "/" + data.toLowerCase(),
        });
      }
      router.push(data.toLowerCase());
    };
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

    onMounted(() => {
      abc(newStr);
      getMenu();
    });

    return {
      drawer: ref(false),
      list,
      abc,
      data,
      getMenu,
    };
  },
};
</script>
