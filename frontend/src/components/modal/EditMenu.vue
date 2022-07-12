<template>
  <q-dialog @hide="onDialogHide" ref="dialogRef">
    <q-card style="width: 400px">
      <div style="text-align: center">
        <h5>메뉴 생성</h5>
      </div>
      <div class="q-pa-md">
        <div class="q-gutter-md" style="max-width: 300px; margin: auto">
          <q-input
            v-model="$props.menuData.name"
            label="메뉴라벨"
            clearable
            hint="첫글자 대문자"
          />
          <q-input
            v-model="$props.menuData.icon"
            label="메뉴아이콘"
            clearable
          />
          <q-input label="경로" clearable v-model="$props.menuData.path" />
          <q-input
            label="비교경로"
            clearable
            v-model="$props.menuData.com_path"
          />
          <q-input
            label="파일경로"
            clearable
            v-model="$props.menuData.file_path"
          />
          <div class="q-gutter-sm">
            <q-radio v-model="$props.menuData.sep" val="true" label="사용" />
            <q-radio v-model="$props.menuData.sep" val="false" label="미사용" />
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
import { useDialogPluginComponent } from 'quasar';

const props = defineProps({
  com: {
    module: 'false',
    type: Boolean,
  },
  menuData: {
    module: Object,
  },
});
defineExpose({
  props,
});
defineEmits([
  // REQUIRED; need to specify some events that your
  // component will emit through useDialogPluginComponent()
  ...useDialogPluginComponent.emits,
]);

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
  onDialogOK(props.menuData);
  // or with payload: onDialogOK({ ... })
  // ...and it will also hide the dialog automatically
}
</script>
