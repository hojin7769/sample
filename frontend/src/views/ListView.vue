<template>
  <div class="q-pa-md">
    <q-table
      title="게시판"
      :rows="data.boardList"
      :columns="columns"
      row-key="name"
      @row-click="textPrint"
      pagination="page:6"
      v-model="pagination"
      :rows-per-page-options="[10]"
      flat
      bordered
    >
      <template v-slot:top-right>
        <q-btn color="primary" label="글쓰기" @click="writePage"/>
      </template>
    </q-table>
  </div>
</template>
<script>
import { reactive, ref } from "vue";
import { onMounted } from "vue";
import axios from "axios";
import { date } from "quasar";
import router from "../router";



export default {
  name: "SampleData",
  setup() {


    const columns = [
  {
    name: "NO_SEQ",
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
    format :(val)=> betweenDays(val) + '일전' ,
    //-,
  },
];

    const data = reactive({
      boardList: [],
    });
    //list를 받아오는 함수
    const getList = () => {
      const sndData = {
        MapperId: "BoardMapper.list",
      };
      axios
        .post("http://localhost:8090/api/list", sndData)
        .then((response) => {
          data.boardList = response.data;
          console.log(data.boardList);
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    //row를 클릭했을때 디테일 페이지로 router를 만들어서 보내는 함수
    const textPrint = (event, row, index) => {
      var seq = row.NO_SEQ;
      router.addRoute({
        component: () => import("../views/DetailView.vue"),
        name: "listDetail",
        path: "/listDetail/"+seq,
        props: true,
      });
      router.push({ name: "listDetail", params: { NO_SEQ: seq } });
    };
    //글쓰기를 눌렀을때 글쓰는 페이지로 보내주는 함수
    const writePage = () => {
      router.addRoute({
        component: () => import("../views/WriterView.vue"),
        name: "writer",
        path: "/writer",
        props: true,
      });
      router.push({ name: "writer" });
    };

    const betweenDays = (val) => {
      let now = new Date();
      
      let year = now.getFullYear();
      let month = now.getMonth()+1;
      let day = now.getDate();

      let stDay = new Date(val);
      let endDay = new Date(year,month,day);
      
      let btMs = endDay.getTime() - stDay.getTime();

      let btDay = btMs/(1000*60*60*24);

      return Math.round(btDay);
      
    }
    //셋업에서 정의를 내리고 마운트됐을때 실행한다.
    onMounted(() => {
      getList();
    });
    //다른곳에서 사용할 수 있게 끔 리턴해서 저장한다.
    return {
      data,
      getList,
      columns,
      textPrint,
      writePage,
      betweenDays,
      columns,
      pagination: ref({
        rowsPerPage: 10,
      }),
    };
  },
};
</script>
<style scoped></style>
