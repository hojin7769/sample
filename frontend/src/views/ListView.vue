<template>
  <div class="q-pa-md">
    <div class="items-end" style="text-align: end" @click="writePage">
      <q-btn color="primary" label="글쓰기" />
    </div>
    <q-table
      title="게시판"
      :rows="data.boardList"
      :columns="columns"
      row-key="name"
      @row-click="textPrint"
      pagination="page:6"
      v-model="pagination"
      :rows-per-page-options="[10]"
    />
  </div>
</template>
<script>
import { reactive, ref } from "vue";
import { onMounted } from "vue";
import axios from "axios";
import { date } from "quasar";
import router from "../router";

const columns = [
  {
    name: "NO_SEQ",
    required: true,
    label: "글번호",
    align: "center",
    field: "NO_SEQ",
    sortable: true,
  },
  { name: "ID_USER", align: "center", label: "글쓴이", field: "ID_USER" },
  { name: "DS_TITLE", align: "center", label: "글제목", field: "DS_TITLE" },
  {
    name: "DT_INSERT",
    label: "작성일자",
    align: "center",
    field: "DT_INSERT",
    format: (val) => date.formatDate(val, "YYYY-MM-DD"),
  },
];

export default {
  name: "SampleData",
  setup() {
    const data = reactive({
      boardList: [],
    });
    const getList = () => {
      axios
        .post("http://localhost:8090/api/boardList")
        .then((response) => {
          data.boardList = response.data;
          console.log(data.boardList);
        })
        .catch(function (error) {
          console.log(error);
        });
    };

    const yyyyMMdd = (value) => {
      if (value == "") return "";

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = "0" + month;
      }

      if (day < 10) {
        day = "0" + day;
      }

      // 최종 포맷 (ex - '2021-10-08')
      return year + "-" + month + "-" + day;
    };
    const textPrint = (event, row, index) => {
      var seq = row.NO_SEQ;
      router.addRoute({
        component: () => import("../views/DetailView.vue"),
        name: "listDetail",
        path: "/listDetail",
        props: true,
      });
      router.push({ name: "listDetail", params: { NO_SEQ: seq } });
    };

    const writePage = () => {
      router.addRoute({
        component: () => import("../views/WriterView.vue"),
        name: "writer",
        path: "/writer",
        props: true,
      });
      router.push({ name: "writer" });
    };
    onMounted(() => {
      getList();
    });
    return {
      data,
      getList,
      columns,
      yyyyMMdd,
      textPrint,
      writePage,
      pagination: ref({
        rowsPerPage: 10,
      }),
    };
  },
};
</script>
<style scoped></style>
