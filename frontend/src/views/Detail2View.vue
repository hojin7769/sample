<template>
  <div class="q-pa-md row items-start q-gutter-md"  style="padding: 5em;">
   <q-card flat bordered class="my-card">
      <q-card-section>
        <div class="text-h5">제목</div>
        <div class="content">
        <div style="display: inline;">
          <img src="../assets/pngwing.png" alt="" width="30" height="30"/>
        </div>
        <div style="display: inline; margin-left: 10px; padding: auto; ">글쓴이</div>
        </div>
        <div class="text-caption" style="text-align: right;">
          2011-06-27
        </div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        
      <q-separator inset />
      <div class="text-h6">본문</div>
      </q-card-section>


      <q-card-section >
        <q-input
      v-model="text"
      filled
      type="textarea"
      clearable
      label="글 내용 작성"
     />
      </q-card-section>
    </q-card>
</div> 
</template>
<script>
import { onMounted, ref } from 'vue';
import axios from 'axios';
export default {
  name:'SampleData',
  components:{},
  data() {
    return {
    };
  },
  setup() {
    const text = ref("")
    async function f() {
      const res = await axios
				.post('http://localhost:8090/api/detail', {NO_SEQ:120})
      console.log(res);
    }
    onMounted(() => {
			f();
		});
    return{
      text,
      f,
    }
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
  }
}
</script>
<style lang="sass" scoped>
.my-card
  width: 100%
  height: 60vh
  overflow: auto
.content
  display: flex
  align-items: center

  
</style>