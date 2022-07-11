<template>
  <div class="q-pa-md row items-start q-gutter-md" style="padding: 5em">
    <q-card flat bordered class="my-card">
      <q-card-section>
        <div class="text-h5">
          <span style="padding: 10px">테이블이름:</span>
          <div
            class="q-gutter-md"
            style="max-width: 300px; display: inline-block"
          >
            <q-input v-model="table_data.TABLE_NAME" label="Standard" />
          </div>
        </div>
        <div v-for="(d, i) in list" :key="i">
          <div class="text-h5">
            <span style="padding: 10px">컬럼이름:</span>
            <div
              class="q-gutter-md"
              style="max-width: 300px; display: inline-block"
            >
              <q-input v-model="list[i].colum" label="Standard" />
            </div>
            <span style="padding: 10px">컬럼값:</span>
            <div
              class="q-gutter-md"
              style="max-width: 300px; display: inline-block"
            >
              <!-- <q-input v-model="list[i].value" label="Standard" /> -->
              <q-select
                v-model="list[i].value"
                :options="options"
                label="Standard"
              />
            </div>
            <div
              class="q-pa-md"
              style="max-width: 300px; display: inline-block"
            >
              <q-checkbox v-model="list[i].null_check" label="null값 허용" />
            </div>
            <div
              class="q-pa-md"
              style="max-width: 300px; display: inline-block"
            >
              <q-radio
                v-model="pk"
                :val="list[i].colum"
                :label="list[i].colum"
              />
            </div>
            <a @click="removeRow(i)">Remove</a>
          </div>
        </div>
        <div style="display: inline">
          <button class="button btn-primary" @click="addRow">Add row</button>
        </div>
        <div>
          <button class="button btn-primary" @click="res">sumit</button>
        </div>
        <!-- <div class="content">
        <div style="display: inline;">
          <img src="../assets/pngwing.png" alt="" width="30" height="30"/>
        </div>
        <div style="display: inline; margin-left: 10px; padding: auto; ">글쓴이</div>
        </div>
        <div class="text-caption" style="text-align: right;">
          2011-06-27
        </div> -->
      </q-card-section>

      <!-- <q-card-section class="q-pt-none"> -->

      <!-- <q-separator inset />
      <div class="text-h6">본문</div>
      </q-card-section> -->

      <!-- <q-card-section >
        <q-input
      v-model="text"
      filled
      type="textarea"
      clearable
      label="글 내용 작성"
     />
      </q-card-section> -->
    </q-card>
  </div>
</template>
<script>
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';

export default {
  name: 'SampleData',
  components: {},
  data() {
    return {};
  },
  setup() {
    const table_data = reactive({
      TABLE_NAME: '',
    });

    const list = reactive([{ colum: '', value: '', null_check: false }]);

    const pk = ref('');

    const addRow = () => {
      list.push({
        colum: '',
        value: '',
        null_check: false,
      });
    };

    const removeRow = (i) => {
      list.splice(i, i);
    };
    const res = async () => {
      const param = {
        TABLE_NAME: table_data.TABLE_NAME,
        Table_List: list,
        Table_pk: pk.value,
      };
      await axios.post('http://localhost:8090/menu/createTable', param);
      console.log(res);
    };
    onMounted(() => {});
    return {
      table_data,
      list,
      res,
      addRow,
      removeRow,
      pk,
      options: ['int', 'char', 'bigint', 'date', 'nchar'],
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  height: 60vh
  overflow: auto
.content
  display: flex
  align-items: center
</style>
