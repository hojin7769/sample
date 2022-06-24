<template>
  <div>
    <v-table>
      <thead>
        <tr>
          <th class="text-center">글 번호</th>
          <th class="text-center">글 쓴이</th>
          <th class="text-center">글 제목</th>
          <th class="text-center">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(d, i) in data" :key="i">
          <td>{{ d.NO_SEQ }}</td>
          <td>{{ d.ID_USER }}</td>
          <td class="point" @click="clickParams(d.NO_SEQ)">{{d.DS_CONTENT }}</td>
          <td>{{ yyyyMMdd(d.DT_INSERT) }}</td>
        </tr>
      </tbody>
    </v-table>
    <v-btn prepend onclick="location.href='/boardDetail'" class="btn">
      <v-icon>mdi-pencil</v-icon>
      글쓰기
    </v-btn>

  </div>
</template>
<script>
import { callUrl } from '../assets/js/ui.js';

export default {
  name: 'app',
  data(){
    return{
      sendData : {
        MapperId: "BoardMapper.list"
      },
      data:[],
    }
  },
  mounted() {
    this.listcall();
  },
  methods: {

    listcall(){
      callUrl("list",this.sendData)
        .then(response => {
          this.data = response.data
          console.log(response.data);
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
.point {
  cursor: pointer;
}

.point:hover {
  text-decoration: underline;
  color: blue;
}

.btn {
  float: right;
  margin-right: 10px;
  margin-bottom: 10px;
}

td {
  text-align: center;
}
</style>
