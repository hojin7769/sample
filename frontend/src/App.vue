<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
        expand-on-hover
        rail
      >
        <v-list>
          <v-list-item
            prepend-avatar="https://cdn.icon-icons.com/icons2/602/PNG/512/Guest_icon-icons.com_55899.png"
            title="guest"
            subtitle=""
          ></v-list-item>
        </v-list>

        <v-divider></v-divider>

        <v-list density="compact" nav>
          <v-list-item prepend-icon="mdi-folder" title="list" value="myfiles" @click="goUrl('list')"></v-list-item>
          <v-list-item prepend-icon="mdi-account-multiple" title="Shared with me" value="shared"></v-list-item>
          <v-list-item prepend-icon="mdi-star" title="Starred" value="starred"></v-list-item>
        </v-list>
      </v-navigation-drawer>

      <v-main style="height: 100vh;">
        <router-view></router-view>
      </v-main>

    </v-layout>
  </v-card>
</template>
<script>

export default {
  name: 'App',

  data: () => ({
    icons: [
      'mdi-facebook',
      'mdi-twitter',
      'mdi-linkedin',
      'mdi-instagram',
    ],
  }),
  methods:{
    goUrl(val){
      location.href='/'+val;
    }

  },
}
</script>
<style>
 body{
   margin: 0;
 }


</style>
