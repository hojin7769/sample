<template>
  <div>
    <div v-if="$q.platform.is.win">Gets rendered only on win platform.</div>
  </div>
</template>
<script>
import { useQuasar } from 'quasar';

export default {
  name: 'SampleData',
  components: {},
  data() {
    return {};
  },
  setup() {
    const $q = useQuasar();

    console.log($q.platform.is.win);

    function show() {
      console.log($q.version);
    }
    show();
    return {
      $q,
      show,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style scoped></style>
