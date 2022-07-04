<template>
  <div class="q-pa-md">
    <q-markup-table separator="horizontal" bordered>
      <thead class="bg-teal">
        <tr>
          <th colspan="5">
            <div class="row no-wrap items-center">
              <div class="text-h4 q-ml-md text-white">게시글 쓰기</div>
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
          <td class="text-left" colspan="4">
            <q-input v-model="data.seq" :dense="dense" disable readonly />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글쓴이</td>
          <td class="text-left" colspan="4">
            <q-input v-model="data.writer" :dense="dense" />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글제목</td>
          <td class="text-left" colspan="4">
            <q-input v-model="data.title" :dense="dense" />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글내용</td>
          <td class="text-left" colspan="4">
            <q-input v-model="data.content" :dense="dense" />
          </td>
        </tr>
      </tbody>
    </q-markup-table>
    <div class="q-pa-md q-gutter-y-md column items-end">
      <q-btn-group push>
        <q-btn push label="목록" @click="listback" />
        <q-btn push label="글쓰기" @click="save" />
      </q-btn-group>
    </div>
  </div>
</template>
<script>
import { reactive, ref } from "vue";
import { callUrl } from "../assets/js/ui";
import router from "../router";

export default {
  setup() {
    const data = reactive({
      seq: "",
      title: "",
      content: "",
      writer: "",
      writeDay: "",
    });

    const listback = () => {
      router.addRoute({
        component: () => import("../views/ListView.vue"),
        name: "list",
        path: "/list",
        props: true,
      });
      router.push({ name: "list" });
    };

    const save = () => {
      const param = {
        NO_SEQ: data.seq,
        ID_USER: data.writer,
        DS_TITLE: data.title,
        DS_CONTENT: data.content,
        DT_INSERT: data.writeDay,
        MapperId: "BoardMapper.insert",
      };

      callUrl("save", param)
        .then((response) => {
          if (response.status == "200") {
            alert("저장이 완료되었습니다.");
            listback();
            this.dialog = false;
          }
        })
        .catch(function (error) {
          console.log(param);
          console.log(error.request);
          console.log(error.message);
          console.log(error.response);
        });
    };

    return {
      ph: ref(""),
      dense: ref(false),
      listback,
      data,
      save,
    };
  },
};
</script>
