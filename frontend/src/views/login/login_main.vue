<template>
  <div class="outer">
    <div class="inner">
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-sheet
            elevation="18"
            class="mx-auto"
          >
          <v-card class="elevation-12" width="80vmin">
            <v-toolbar dark color="primary">
              <v-toolbar-title>로그인</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-card-text>
              <form>
                <v-text-field
                  label="아이디"
                  data-vv-name="id"
                  required
                  clearable
                ></v-text-field>
                <v-text-field
                  label="비밀번호"
                  data-vv-name="pwd"
                  required
                  type="password"
                  clearable
                ></v-text-field>
                <v-checkbox
                  value="1"
                  label="자동로그인"
                  data-vv-name="agree"
                  type="checkbox"
                  required
                ></v-checkbox>
                <div style="text-align: center">
                  <v-btn variant="tonal" class="btnGroup">로그인</v-btn>
                  <v-btn variant="tonal" class="btnGroup">회원가입</v-btn>
                </div>
              </form>
            </v-card-text>
          </v-card>
          </v-sheet>
        </v-layout>
        <v-snackbar
        >
          <v-btn>
            닫기
          </v-btn>
        </v-snackbar>
      </v-container>
    </div>
  </div>
</template>

<style scoped>
/* 백그라운드 색깔 및 크기 설정 */
.outer {
  width : 100vw;
  height : 100vh;
  background-color: antiquewhite
}

.inner {
  width: auto;
  height: auto;
}


/* 가운데 정렬 */
.outer {
  display: flex;
}

.inner {
  margin: auto;
}


/* absolute */
.outer, .inner {
  position: relative
}

.absolute {
  position: absolute;
  bottom: 0;
  right: 0
}


.btnGroup {
  margin-right: 5px;
}
</style>

