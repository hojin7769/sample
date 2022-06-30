<script setup>
import Hader from "./views/layout/headerView.vue";
</script>
<template>
  <q-layout view="hhh lpR lFf">
    <Hader />
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>
