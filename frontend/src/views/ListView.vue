<template>
  <div class="text-center" v-if="isLoading">
    <v-progress-circular
      indeterminate
      color="primary"
      :size="70"
      :width="7"
    ></v-progress-circular>
  </div>
  <div>
    <div style="padding: 30px; background-color: burlywood" >
        <p class="text-h4">게시판</p>
    </div>
    <div  >
      <v-toolbar flat color="white" >
      <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-text-field
          hide-details
          prepend-inner-icon="mdi-magnify"
          single-line
          bg-color="white"
        ></v-text-field>
      </v-toolbar>
    </div>
    <v-table>
      <thead>
        <tr >
          <th class="text-center">글 번호</th>
          <th class="text-center">글 쓴이</th>
          <th class="text-center">글 제목</th>
          <th class="text-center">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(d, i) in data" :key="i" @click="clickParams(d.NO_SEQ)">
          <td>{{ d.NO_SEQ }}</td>
          <td>{{ d.ID_USER }}</td>
          <td class="point">{{d.DS_CONTENT }}</td>
          <td>{{ yyyyMMdd(d.DT_INSERT) }}</td>
        </tr>
      </tbody>
    </v-table>
    <v-btn prepend onclick="location.href='/listDetail'" class="btn">
      <v-icon>mdi-pencil</v-icon>
      글쓰기
    </v-btn>
    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                v-model="page"
                class="my-4"
                :length="1"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>

  </div>
</template>
<script>
import { callUrl } from '../assets/js/ui.js';

export default {
  name: 'app',
  data(){
    return{
      data:[],
      isLoading: true,
      page: 1,
    }
  },
  mounted() {
    this.listcall();
  },
  methods: {

    listcall(){
      callUrl("boardList")
        .then(response => {
          this.data = response.data
          this.isLoading = false
        })
        .catch(error => {
          console.log(error);
        });
    }
    ,clickParams(val) {
      this.$router.push({ name: 'listDetail', params: { NO_SEQ: val } })
    },
    yyyyMMdd(value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == '') return '';

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = '0' + month;
      }

      if (day < 10) {
        day = '0' + day;
      }

      // 최종 포맷 (ex - '2021-10-08')
      return year + '-' + month + '-' + day;
    }
  }
}
</script>
<style scoped>
.btn {
  float: right;
  margin-right: 10px;
  margin-bottom: 10px;
}

td {
  text-align: center;
}

.v-progress-circular {
  margin: 1rem;
  z-index: 2;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  box-shadow: rgba(0, 0, 0, 0.1) 0 0 0 9999px;

}
</style>
