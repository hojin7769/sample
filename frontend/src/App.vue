<template>
  <q-layout view="hhh lpR lFf">
    <header-view></header-view>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>
<script setup>
import { useQuasar } from 'quasar';

const $q = useQuasar();
</script>
;
