<template>
  <div>
    <q-markup-table>
      <thead>
        <tr class="bg-green">
          <th colspan="4">
            <div class="row no-wrap items-center">
              <div class="text-h4 q-ml-md text-white">메뉴 관리</div>
            </div>
          </th>
          <div class="q-pa-md q-gutter-sm" style="text-align: right">
            <q-btn
              padding="md"
              color="green"
              round
              icon="add"
              @click="(dialog = true), (insert = true), reform()"
            />
          </div>
        </tr>

        <tr>
          <th>메뉴라벨</th>
          <th>메뉴아이콘</th>
          <th>메뉴순서</th>
          <th>메뉴선 유무</th>
          <th>메뉴 수정/삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(menuItem, index) in list.menuList" :key="index">
          <td class="text-center">{{ menuItem.MENU_LABEL }}</td>
          <td class="text-center">{{ menuItem.MENU_ICON }}</td>
          <td class="text-center">{{ menuItem.MENU_SEQ }}</td>
          <td class="text-center">{{ menuItem.MENU_SEPARATOR }}</td>
          <td class="text-center">
            <div class="q-pa-md q-gutter-y-md column items-center">
              <q-btn-group push>
                <q-btn
                  push
                  label="수정"
                  @click="(dialog = true), (insert = false), edit(index)"
                />
                <q-btn push label="삭제" @click="deleteM(index)" />
              </q-btn-group>
            </div>
          </td>
        </tr>
      </tbody>
    </q-markup-table>

    <q-dialog v-model="dialog">
      <q-card style="width: 400px">
        <div style="text-align: center">
          <h5>메뉴 생성</h5>
        </div>
        <div class="q-pa-md">
          <div class="q-gutter-md" style="max-width: 300px; margin: auto">
            <q-input
              v-model="menuData.label"
              label="메뉴라벨"
              clearable
              hint="첫글자 대문자"
            />
            <q-input v-model="menuData.icon" label="메뉴아이콘" clearable />
            <q-input
              v-model="menuData.seq"
              label="메뉴순서"
              clearable
              :disable="insert"
            />
            <div class="q-gutter-sm">
              <q-radio v-model="menuData.sep" val="true" label="사용" />
              <q-radio v-model="menuData.sep" val="false" label="미사용" />
            </div>
          </div>

          <q-card-section
            class="row q-gutter-sm"
            style="margin: auto; width: 300px"
          >
            <q-btn
             label="생성"
              color="primary"
              icon-right="edit"
              @click="save"
            >
            </q-btn>
            <q-btn
              label="취소"
              color="primary"
              v-close-popup
              icon-right="close"
            />
          </q-card-section>
        </div>
      </q-card>
    </q-dialog>
  </div>
</template>
<script>
import axios from "axios";
import { onMounted, reactive, ref } from "vue";
import { callUrl } from "../assets/js/menu";
import router from "../router";
export default {
  name: "SampleData",
  components: {},
  setup() {
    const list = reactive({
      menuList: [],
    });
    const menuData = reactive({
      label: "",
      icon: "",
      seq: "",
      sep: "false",
      num: "",
    });

    const reform = () => {
      menuData.label = "";
      menuData.icon = "";
      menuData.sep = "false";
    };

    const getMenu = () => {
      axios
        .post("http://localhost:8090/menu/menuList")
        .then((response) => {
          list.menuList = response.data;
          console.log(list.menuList);
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    const dialog = ref(false);
    const save = () => {
      const param = {
        MENU_LABEL: menuData.label,
        MENU_ICON: menuData.icon,
        MENU_SEQ: menuData.seq,
        MENU_SEPARATOR: menuData.sep,
        MENU_NUM: menuData.num,
      };
      callUrl("/menuSave", param)
        .then((response) => {
          list.menuList = response.data;
          router.go();
          dialog.value = false;
        })
        .catch(function (error) {
          console.log(error);
        });
    };
    const deleteM = (index) => {
      if (!confirm("삭제하시겠습니까?")) return false;
      callUrl("/delete", list.menuList[index])
        .then((response) => {
          alert("삭제되었습니다.");
          router.go();
        })
        .catch(function (error) {
          console.log(error);
        });
    };

    //수정 메서드
    const edit = (index) => {
      menuData.label = list.menuList[index].MENU_LABEL;
      menuData.icon = list.menuList[index].MENU_ICON;
      menuData.seq = list.menuList[index].MENU_SEQ;
      menuData.sep = list.menuList[index].MENU_SEPARATOR;
      menuData.num = list.menuList[index].MENU_NUM;
    };

    onMounted(() => {
      getMenu();
    });
    return {
      getMenu,
      list,
      dialog,
      test: ref("test"),
      menuData,
      save,
      deleteM,
      insert: ref(true),
      reform,
      edit,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style scoped></style>
