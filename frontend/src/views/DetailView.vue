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
            <p style="white-space: pre">{{ data.content }}</p>
          </td>
        </tr>
      </tbody>
    </q-markup-table>
    <div class="q-pa-md q-gutter-y-md column items-end">
      <click-button
        :data="data"
        v-on:tt="(val) => (dialog = val)"
      ></click-button>
    </div>
    <edit-modal
      :dialog="dialog"
      :data="data"
      v-on:backModal="(val) => (dialog = val)"
    ></edit-modal>
  </div>
</template>
;

<script>
import { onMounted, reactive, ref } from 'vue';
import { callUrl } from '../assets/js/ui.js';
import axios from 'axios';
import router from '../router/index.js';
import ClickButton from '../components/button/ClickButton.vue';
import EditModal from '../components/modal/EditModal.vue';

export default {
  name: 'listDetail',
  props: {
    NO_SEQ: {
      type: Number,
      default: 0,
    },
  },
  components: {
    'click-button': ClickButton,
    'edit-modal': EditModal,
  },
  setup(props) {
    const data = reactive({
      seq: '',
      title: '',
      content: '',
      writer: '',
      writeDay: '',
    });
    const dialog = ref(false);
    const getList = () => {
      const params = reactive({
        NO_SEQ: props.NO_SEQ,
        MapperId: 'BoardMapper.detail',
      });
      axios
        .post('http://localhost:8090/api/detail', params)
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
    getList();

    return {
      data,
      getList,
      dialog,
    };
  },
  mounted() {},
  updated() {},
  methods: {
    // save() {
    // 	const param = {
    // 		NO_SEQ: this.data.seq,
    // 		ID_USER: this.data.writer,
    // 		DS_TITLE: this.data.title,
    // 		DS_CONTENT: this.data.content,
    // 		DT_INSERT: this.data.writeDay,
    // 		MapperId: 'BoardMapper.update',
    // 	};

    // 	callUrl('save', param)
    // 		.then(response => {
    // 			if (response.status == '200') {
    // 				alert('저장이 완료되었습니다.');
    // 				this.getList;
    // 				this.dialog = false;
    // 			}
    // 		})
    // 		.catch(function (error) {
    // 			console.log(param);
    // 			console.log(error.request);
    // 			console.log(error.message);
    // 			console.log(error.response);
    // 		});
    // },

    // listback() {
    // 	router.addRoute({
    // 		component: () => import('../views/ListView.vue'),
    // 		name: 'list',
    // 		path: '/list',
    // 		props: true,
    // 	});
    // 	router.push({ name: 'list' });
    // },
    // deleteSeq() {
    // 	const param = {
    // 		NO_SEQ: this.data.seq,
    // 		DS_TITLE: this.data.title,
    // 		DS_CONTENT: this.data.content,
    // 		ID_USER: this.data.writer,
    // 		MapperId: 'BoardMapper.delete',
    // 	};
    // 	if (!confirm('삭제하시겠습니까?')) return false;
    // 	callUrl('save', param)
    // 		.then(function (response) {
    // 			if (response.status == '200') {
    // 				alert('삭제되었습니다.');
    // 				location.href = '/list';
    // 			}
    // 		})
    // 		.catch(function (error) {
    // 			console.log(error);
    // 		});
    // },

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
