<template>
  <div class="q-pa-md">
    <q-markup-table separator="horizontal" bordered>
      <thead class="bg-teal">
        <tr>
          <th colspan="5">
            <div class="row no-wrap items-center">
              <div class="text-h4 q-ml-md text-white">게시글 상세보기</div>
            </div>
          </th>
        </tr>
        <tr>
          <th class="text-center"></th>
          <th class="text-right"></th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody class="bg-grey-3">
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글번호</td>
          <td class="text-left" colspan="4">{{ data.seq }}</td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글쓴이</td>
          <td class="text-left" colspan="4">{{ data.writer }}</td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글제목</td>
          <td class="text-left" colspan="4">{{ data.title }}</td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글내용</td>
          <td class="text-left" colspan="4">
            <p>{{ data.content }}</p>
          </td>
        </tr>
      </tbody>
    </q-markup-table>
    <div class="q-pa-md q-gutter-y-md column items-end">
      <q-btn-group push>
        <q-btn push label="목록" @click="listback" />
        <q-btn push label="수정" @click="dialog = true" />
        <q-btn push label="삭제" @click="deleteSeq" />
      </q-btn-group>
    </div>

    <q-dialog v-model="dialog">
      <q-card style="width: 400px">
        <div style="text-align: center">
          <h5>수정 페이지</h5>
        </div>
        <div class="q-pa-md">
          <div class="q-gutter-md" style="max-width: 300px; margin: auto">
            <q-input v-model="data.writer" label="글쓴이" clearable />
            <q-input v-model="data.title" label="제목" clearable />
            <q-input v-model="data.content" label="글내용" clearable />
          </div>

          <q-card-section
            class="row q-gutter-sm items-center"
            style="margin: auto; width: 300px"
          >
            <q-btn label="수정" color="primary" icon-right="edit" @click="save">
            </q-btn>
            <q-btn
              label="창 닫기"
              color="primary"
              v-close-popup
              icon-right="close"
              @click="getList"
            />
          </q-card-section>
        </div>
      </q-card>
    </q-dialog>
  </div>
</template>
;

<script>
import { onMounted, reactive, ref } from "vue";
import { callUrl } from "../assets/js/ui.js";
import axios from "axios";
import router from "../router/index.js";

export default {
  name: "listDetail",
  props: {
    NO_SEQ: {
      type: Number,
      default: 0,
    },
  },
  setup(props) {
    const data = reactive({
      seq: "",
      title: "",
      content: "",
      writer: "",
      writeDay: "",
    });
    const getList = () => {
      console.log(props.NO_SEQ);
      axios
        .post("http://localhost:8090/api/boardDetail", props)
        .then((response) => {
          data.seq = response.data.data.NO_SEQ;
          data.title = response.data.data.DS_TITLE;
          data.content = response.data.data.DS_CONTENT;
          data.writer = response.data.data.ID_USER;
          data.writeDay = response.data.data.DT_INSERT;
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    onMounted(() => {
      getList();
    });
    return {
      data,
      getList,
      dialog: ref(false),
    };
  },
  mounted() {},
  updated() {},
  methods: {
    detail() {
      callUrl("boardDetail", this.sneData)
        .then((response) => {
          this.seq = response.data.data.NO_SEQ;
          this.title = response.data.data.DS_TITLE;
          this.content = response.data.data.DS_CONTENT;
          this.id = response.data.data.ID_USER;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    save() {
      const param = {
        NO_SEQ: this.data.seq,
        ID_USER: this.data.writer,
        DS_TITLE: this.data.title,
        DS_CONTENT: this.data.content,
        DT_INSERT: this.data.writeDay,
      };

      callUrl("boardSave", param)
        .then((response) => {
          if (response.status == "200") {
            alert("저장이 완료되었습니다.");
            this.getList;
            this.dialog = false;
          }
        })
        .catch(function (error) {
          console.log(param);
          console.log(error.request);
          console.log(error.message);
          console.log(error.response);
        });
    },

    listback() {
      router.addRoute({
        component: () => import("../views/ListView.vue"),
        name: "list",
        path: "/list",
        props: true,
      });
      router.push({ name: "list" });
    },
    deleteSeq() {
      const param = {
        NO_SEQ: this.data.seq,
        DS_TITLE: this.data.title,
        DS_CONTENT: this.data.content,
        ID_USER: this.data.writer,
      };
      if (!confirm("삭제하시겠습니까?")) return false;
      callUrl("boardDelete", param)
        .then(function (response) {
          if (response.status == "200") {
            alert("삭제되었습니다.");
            location.href = "/list";
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },

    reRoll() {
      router.go();
    },
  },
};
</script>

<style scoped>
.title {
  border-right: 1px solid rgba(0, 0, 0, 0.12);
}
</style>
