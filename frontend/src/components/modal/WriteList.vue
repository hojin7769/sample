<template>
  <q-dialog @hide="onDialogHide" ref="dialogRef">
    <q-card style="width: 400px">
      <div style="text-align: center">
        <h5>글쓰기</h5>
      </div>
      <div class="q-pa-md">
        <div class="q-gutter-md" style="max-width: 300px; margin: auto">
          <q-input label="Title" clearable v-model="boardData.DS_TITLE" />
          <q-input label="Writer" clearable v-model="boardData.ID_USER" />
          <q-input
            v-model="boardData.DS_CONTENT"
            filled
            clearable
            type="textarea"
            color="red-12"
            label="Content"
            maxlength="500"
            hint="500자이내로 작성해 주세요."
          />
        </div>
      </div>
      <q-card-section
        class="row q-gutter-sm"
        style="margin: auto; width: 300px"
      >
        <q-btn
          label="작성"
          color="primary"
          icon-right="edit"
          @click="onOKClick"
        >
        </q-btn>
        <q-btn label="취소" color="primary" v-close-popup icon-right="close" />
      </q-card-section>
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

const boardData = reactive({
  NO_SEQ: '',
  DT_INSERT: '',
  ID_USER: '',
  DS_TITLE: '',
  DS_CONTENT: '',
  DT_UPDATE: '',
  MapperId: 'BoardMapper.insert',
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
  onDialogOK(boardData);
  // or with payload: onDialogOK({ ... })
  // ...and it will also hide the dialog automatically
}
</script>
