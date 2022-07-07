<template>
  <q-btn-group push>
    <q-btn push label="목록" @click="listback" />
    <q-btn push label="수정" @click="$emit('tt', true)" />
    <q-btn push label="삭제" @click="deleteSeq" />
  </q-btn-group>
</template>
<script>
import { reactive } from "vue";
import { callUrl } from "../../assets/js/ui";
import router from "../../router";
export default {
  name: "ClickButton",
  props: {
    data: {
      seq: {
        type: String,
        default: "0",
      },
      title: {
        type: String,
        default: "0",
      },
      content: {
        type: String,
        default: "0",
      },
      writer: {
        type: String,
        default: "0",
      },
      writeDay: {
        type: String,
        default: "0",
      },
    },
  },
  emits: ["tt"],
  setup(props) {
    const listback = () => {
      router.addRoute({
        component: () => import("../..//views/ListView.vue"),
        name: "list",
        path: "/list",
        props: true,
      });
      router.push({ name: "list" });
    };
    const deleteSeq = () => {
      const param = {
        NO_SEQ: props.data.seq,
        DS_TITLE: props.data.title,
        DS_CONTENT: props.data.content,
        ID_USER: props.data.writer,
        MapperId: "BoardMapper.delete",
      };
      if (!confirm("삭제하시겠습니까?")) return false;
      callUrl("save", param)
        .then(function (response) {
          if (response.status == "200") {
            alert("삭제되었습니다.");
            location.href = "/list";
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    const onEmit = function () {
      this.$emit("print", true);
    };

    return {
      listback,
      deleteSeq,
      onEmit,
    };
  },
};
</script>
