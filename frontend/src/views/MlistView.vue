<template>
  <div>
    <q-markup-table>
      <thead>
        <tr class="bg-green">
          <th colspan="5">
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
          <th>메뉴 경로</th>
          <th>메뉴 수정/삭제</th>
        </tr>
      </thead>
      <draggable tag="tbody" v-model="list.menuList" @change="log">
        <tr v-for="(menuItem, index) in list.menuList" :key="index">
          <td class="text-center">{{ menuItem.MENU_LABEL }}</td>
          <td class="text-center">{{ menuItem.MENU_ICON }}</td>
          <td class="text-center">{{ menuItem.MENU_SEQ }}</td>
          <td class="text-center">{{ menuItem.MENU_SEPARATOR }}</td>
          <td class="text-center">{{ menuItem.MENU_PATH }}</td>
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
      </draggable>
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
            <q-input label="경로" clearable v-model="menuData.path" />
            <div class="q-gutter-sm">
              <q-radio v-model="menuData.sep" val="true" label="사용" />
              <q-radio v-model="menuData.sep" val="false" label="미사용" />
            </div>
          </div>
          <q-card-section
            class="row q-gutter-sm"
            style="margin: auto; width: 300px"
          >
            <q-btn label="생성" color="primary" icon-right="edit" @click="save">
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
    <!-- <uploadCom :alert="alert"  :confirm="confirm" ></uploadCom> -->
  </div>
</template>
<script>
import axios from "axios";
import { onMounted, reactive, ref } from "vue";
import { callUrl } from "../assets/js/menu";
import router from "../router";
import { VueDraggableNext } from "vue-draggable-next";
import UploadCompVue from "../components/alert/UploadComp.vue";

export default {
  name: "SampleData",
  components: {
    draggable: VueDraggableNext,
    // uploadCom : UploadCompVue,
  },
  setup() {
    const list = reactive({
      menuList: [],
    });

    // const alert = ref(false);
    // const confirm = ref(false);
    const menuData = reactive({
      label: "",
      icon: "",
      seq: "",
      sep: "false",
      num: "",
      path: "",
    });
    const reform = () => {
      menuData.label = "";
      menuData.icon = "";
      menuData.sep = "false";
      menuData.seq = "";
      menuData.num = "";
      menuData.path = "";
    };
    const getMenu = async () => {
      await axios
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
        MENU_PATH: menuData.path,
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

    const saveparam = async (param) => {
      await callUrl("/menuSave", param)
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
      // alert.value = true;
      // confirm.value = true;
      // console.log(alert);
      callUrl("/delete", list.menuList[index])
        .then((response) => {
          alert("삭제되었습니다.");
          router.go();
        })
        .catch(function (error) {
          console.log(error);
        });
    };

    const log = ({ moved }) => {
      console.log(moved.element);
      moved.element.MENU_SEQ = moved.newIndex + 1;
      if (moved.oldIndex > moved.newIndex) {
        for (let i = moved.oldIndex; i >= 0; i--) {
          list.menuList[moved.oldIndex - i].MENU_SEQ = moved.oldIndex - (i - 1);
        }
      } else {
        for (let i = 0; i < moved.newIndex; i++) {
          list.menuList[moved.oldIndex + i].MENU_SEQ = moved.oldIndex + 1 + i;
        }
      }
      for (let i = 0; i < list.menuList.length; i++) {
        saveparam(list.menuList[i]);
      }
    };
    //수정 메서드
    const edit = (index) => {
      menuData.label = list.menuList[index].MENU_LABEL;
      menuData.icon = list.menuList[index].MENU_ICON.trim();
      menuData.seq = list.menuList[index].MENU_SEQ;
      menuData.sep = (list.menuList[index].MENU_SEPARATOR + "").trim();
      menuData.num = list.menuList[index].MENU_NUM;
      menuData.path = list.menuList[index].MENU_PATH;
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
      log,
      saveparam,
      alert,
      confirm,
    };
  },
};
</script>
<style scoped></style>
