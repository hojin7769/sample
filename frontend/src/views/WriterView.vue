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
            <q-input v-model="text" :dense="dense" disable readonly />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글쓴이</td>
          <td class="text-left" colspan="4">
            <q-input v-model="text" :dense="dense" />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글제목</td>
          <td class="text-left" colspan="4">
            <q-input v-model="text" :dense="dense" />
          </td>
        </tr>
        <tr style="height: 100px">
          <td class="text-center title" colspan="1">글내용</td>
          <td class="text-left" colspan="4">
            <q-input v-model="text" :dense="dense" />
          </td>
        </tr>
      </tbody>
    </q-markup-table>
    <div class="q-pa-md q-gutter-y-md column items-end">
      <q-btn-group push>
        <q-btn push label="목록" @click="listback" />
        <q-btn push label="글쓰기" />
      </q-btn-group>
    </div>
  </div>
</template>
<script>
import { ref } from "vue";
import router from "../router";

export default {
  setup() {
    const listback = () => {
      router.addRoute({
        component: () => import("../views/ListView.vue"),
        name: "list",
        path: "/list",
        props: true,
      });
      router.push({ name: "list" });
    };

    return {
      text: ref(""),
      ph: ref(""),
      dense: ref(false),
      listback,
    };
  },
};
</script>
