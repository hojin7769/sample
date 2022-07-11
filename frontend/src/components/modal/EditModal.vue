<template>
  <q-dialog v-model="props.dialog">
    <q-card style="width: 400px">
      <div style="text-align: center">
        <h5>수정 페이지</h5>
      </div>
      <div class="q-pa-md">
        <div class="q-gutter-md" style="max-width: 300px; margin: auto">
          <q-input v-model="props.data.writer" label="글쓴이" clearable />
          <q-input v-model="props.data.title" label="제목" clearable />
          <q-input
            v-model="props.data.content"
            filled
            clearable
            type="textarea"
            color="red-12"
            label="글내용"
          />
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
            @click="$emit('backModal', false)"
          />
        </q-card-section>
      </div>
    </q-card>
  </q-dialog>
</template>
<script>
import emit from 'emit';
import { reactive, ref } from 'vue';
import { callUrl } from '../../assets/js/ui';
import router from '../../router';
export default {
  name: 'ClickButton',
  props: {
    data: {
      seq: {
        type: String,
        default: '0',
      },
      title: {
        type: String,
        default: '0',
      },
      content: {
        type: String,
        default: '0',
      },
      writer: {
        type: String,
        default: '0',
      },
      writeDay: {
        type: String,
        default: '0',
      },
    },
    dialog: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['backModal'],
  setup(props, { emit }) {
    const listback = () => {
      router.addRoute({
        component: () => import('../../views/ListView.vue'),
        name: 'list',
        path: '/list',
        props: true,
      });
      router.push({ name: 'list' });
    };
    const deleteSeq = () => {
      const param = {
        NO_SEQ: props.data.seq,
        DS_TITLE: props.data.title,
        DS_CONTENT: props.data.content,
        ID_USER: props.data.writer,
        MapperId: 'BoardMapper.delete',
      };
      if (!confirm('삭제하시겠습니까?')) return false;
      callUrl('save', param)
        .then(function (response) {
          if (response.status == '200') {
            alert('삭제되었습니다.');
            listback();
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    const save = function () {
      const param = {
        NO_SEQ: props.data.seq,
        ID_USER: props.data.writer,
        DS_TITLE: props.data.title,
        DS_CONTENT: props.data.content,
        DT_INSERT: props.data.writeDay,
        MapperId: 'BoardMapper.update',
      };

      callUrl('save', param)
        .then(function (response) {
          if (response.status == '200') {
            alert('저장이 완료되었습니다.');
            //this.getList;
            //this.dialog = false;
            emit('backModal', false);
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
      listback,
      deleteSeq,
      props,
      save,
    };
  },
};
</script>
