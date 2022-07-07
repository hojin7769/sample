<template>
  <div>
    <q-dialog v-model="props.alert">
      <q-dialog v-model="props.confirm" persistent>
        <q-card>
          <q-card-section class="row items-center">
            <q-avatar
              icon="signal_wifi_off"
              color="primary"
              text-color="white"
            />
            <span class="q-ml-sm"
              >You are currently not connected to any network.</span
            >
          </q-card-section>

          <q-card-actions align="right">
            <q-btn flat label="취소" color="primary" v-close-popup />
            <q-btn flat label="확인" color="primary" v-close-popup />
          </q-card-actions>
        </q-card>
      </q-dialog>
    </q-dialog>
  </div>
</template>
<script>
export default {
  name: "SampleData",
  components: {},
  data() {
    return {};
  },
  props: {
    alert: {
      type: String,
      default: "false",
    },
    confirm: {
      type: String,
      default: "false",
    },
  },
  setup(props, { emit }) {
    return {
      props,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style scoped></style>
