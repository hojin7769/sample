<template>
  <div class="text-center" v-if="isLoading">
    <v-progress-circular
      indeterminate
      color="primary"
      :size="70"
      :width="7"
    ></v-progress-circular>
  </div>
  <v-form justify="center" >
    <v-container style="width:50%">
      <div class="container">
        <v-row>
          <v-col cols="12" sm="12">
            <v-text-field v-model="seq" label="글번호" clearable disabled readonly></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="12">
            <v-text-field v-model="id" label="작성자" required clearable></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" sm="12">
            <v-text-field v-model="title" label="제목" required clearable></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="12">
            <v-textarea background-color="light-blue" color="black" label="내용" v-model="content"  required clearable></v-textarea>
          </v-col>
        </v-row>

        <div class="btn_grop">
          <v-btn @click="listback" style="margin-right: 10px">목록</v-btn>
          <v-btn  @click="save" style="margin-right: 10px">저장</v-btn>
          <v-btn
          color="error"
          plain
          @click="deleteSeq" style="margin-right: 10px">삭제</v-btn>
        </div>
      </div>
    </v-container>
  </v-form>
</template>
<script>
import { callUrl } from '../assets/js/ui.js'

export default {
  name: 'listDetail',
  data () {
    return {
      seq: '',
      title: '',
      content: '',
      id: '',
      sneData: {
        NO_SEQ: this.$route.params.NO_SEQ
      }
    }
  },
  created () {
  },
  mounted () {
    this.detail()
  },
  updated () {
  },
  methods: {
    detail () {
      const param = this.$route.params
      callUrl('boardDetail', this.sneData)
        .then(response => {
          this.seq = response.data.data.NO_SEQ
          this.title = response.data.data.DS_TITLE
          this.content = response.data.data.DS_CONTENT
          this.id = response.data.data.ID_USER
        })
        .catch(error => {
          console.log(error)
        })
    },
    save () {
      const param = {
        NO_SEQ: this.seq,
        ID_USER: this.id,
        DS_TITLE: this.title,
        DS_CONTENT: this.content
      }

      callUrl('boardSave', param)
        .then(response => {
          if (response.status == '200') {
            alert('저장이 완료되었습니다.')
            this.listback()
          }
        })
        .catch(function (error) {
          console.log(param)
          console.log(error.request)
          console.log(error.message)
          console.log(error.response)
        })
    },

    listback () {
      location.href = 'http://localhost:8080/list'
    },
    deleteSeq () {
      const param = {
        NO_SEQ: this.seq,
        DS_TITLE: this.title,
        DS_CONTENT: this.content,
        ID_USER: this.id
      }
      if (!confirm('삭제하시겠습니까?')) return false
      callUrl('boardDelete', param)
        .then(function (response) {
          if (response.status == '200') {
            alert('삭제되었습니다.')
            location.href = '/list'
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }

  }

}
</script>
<style scoped>
.row {
  padding: 5px;
}

#middle {
  text-align: center;
}

  .btn_grop {
  text-align: center;
}

  v-btn{
    margin-right: 10px;
  }

input,
textarea {
  text-align: left;
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
