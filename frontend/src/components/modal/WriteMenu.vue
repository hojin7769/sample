<template>
  <q-dialog @hide="onDialogHide" ref="dialogRef">
    <q-card style="width: 400px">
      <div style="text-align: center">
        <h5>메뉴 생성</h5>
      </div>
      <div class="q-pa-md">
        <div class="q-gutter-md" style="max-width: 300px; margin: auto">
          <q-input
            v-model="menuData.MENU_NAME"
            label="메뉴라벨"
            clearable
            hint="첫글자 대문자"
          />
          <q-input v-model="menuData.MENU_ICON" label="메뉴아이콘" clearable />
          <q-input label="경로" clearable v-model="menuData.MENU_PATH" />
          <q-input
            label="파일실제경로"
            clearable
            v-model="menuData.MENU_FILE_PATH"
          />
          <q-input
            label="비교경로"
            clearable
            v-model="menuData.MENU_COM_PATH"
            hint="경로와 똑같이 입력해 주세여"
          />
          <div class="q-gutter-sm">
            <q-radio
              v-model="menuData.MENU_SEPARATOR"
              val="true"
              label="사용"
            />
            <q-radio
              v-model="menuData.MENU_SEPARATOR"
              val="false"
              label="미사용"
            />
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
            @click="onOKClick"
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
</template>
<script setup>
import { reactive } from 'vue';
import { useDialogPluginComponent } from 'quasar';

defineEmits([
  // REQUIRED; need to specify some events that your
  // component will emit through useDialogPluginComponent()
  ...useDialogPluginComponent.emits,
]);

const menuData = reactive({
  MENU_NAME: '',
  MENU_ICON: '',
  MENU_SEQ: '',
  MENU_SEPARATOR: '',
  MENU_NUM: '',
  MENU_PATH: '',
  MENU_FILE_PATH: '',
  MENU_COM_PATH: '',
});
const { dialogRef, onDialogHide, onDialogOK, onDialogCancel } =
  useDialogPluginComponent();
// dialogRef      - Vue ref to be applied to QDialog
// onDialogHide   - Function to be used as handler for @hide on QDialog
// onDialogOK     - Function to call to settle dialog with "ok" outcome
//                    example: onDialogOK() - no payload
//                    example: onDialogOK({ /*...*/ }) - with payload
// onDialogCancel - Function to call to settle dialog with "cancel" outcome

// this is part of our example (so not required)
function onOKClick() {
  // on OK, it is REQUIRED to
  // call onDialogOK (with optional payload)
  onDialogOK(menuData);
  // or with payload: onDialogOK({ ... })
  // ...and it will also hide the dialog automatically
}
</script>
