<template>
  <!-- <form @submit.prevent="formCheck"> -->
  <div class="logo">
    <RouterLink to="/home"><img src="@/assets/logo.png" /></RouterLink>
  </div>
  <div class="centerLine" @keydown.enter="formCheck">
    <div class="mainBox">
      <div>
        <label for="userId" class="form-label">아이디</label>
        <input id="userId" type="text" v-model="formData.userId" />
        <!-- <button @click="idCh">중복확인</button> -->
        <p v-if="!formRealTime.userId">
          아이디는 영문 혹은 숫자를 포함한 4자이상 20자 이내여야 합니다.
        </p>
      </div>
      <div>
        <label for="userPassword" class="form-label">비밀번호</label>
        <input
          id="userPassword"
          type="password"
          v-model="formData.userPassword"
        />
        <p v-if="!formRealTime.userPassword">
          비밀번호는 영문, 숫자, 특수문자를 포함한 8자이상 20자 이내여야 합니다.
        </p>
      </div>
      <div>
        <label for="passwordCheck" class="form-label">비밀번호 확인</label>
        <input id="passwordCheck" type="password" v-model="userPasswordCheck" />
        <p v-if="formData.userPassword !== userPasswordCheck">
          비밀번호가 일치하지 않습니다.
        </p>
      </div>
      <div>
        <label for="userNickname" class="form-label">닉네임</label>
        <input id="userNickname" type="text" v-model="formData.userNickname" />
        <p v-if="!formRealTime.userNickname">
          닉네임은 한글, 영문, 숫자로 구성된 2자이상 10자 이내여야 합니다.
        </p>
      </div>
    </div>
    <div class="mainBox">
      <div>
        <label for="userName" class="form-label">이름</label>
        <input id="userName" type="text" v-model="formData.userName" />
        <p v-if="!formRealTime.userName">
          이름은 한글, 영어로 구성된 2자이상 18자 이내여야 합니다.
        </p>
      </div>
      <div>
        <label for="userPhoneNumber" class="form-label">핸드폰 번호</label>
        <input
          id="userPhoneNumber"
          type="text"
          v-model="formData.userPhoneNumber"
        />
        <p v-if="!formRealTime.userPhoneNumber">
          핸드폰 번호는 11자리의 숫자로만 입력해주세요 (ex : 01012345678)
        </p>
      </div>
      <div>
        <label for="userEmail" class="form-label">이메일</label>
        <input id="userEmail" type="text" v-model="formData.userEmail" />
        <p v-if="!formRealTime.userEmail">
          이메일은 양식을 맞춰 작성해주세요 (ex : ssafy@ssfay.com)
        </p>
      </div>
      <div>
        <label class="form-label">성별</label>
        <input type="radio" value="남성" v-model="formData.userGender" />남성
        <input type="radio" value="여성" v-model="formData.userGender" />여성
        <p v-if="!formRealTime.userGender">성별을 선택해주세요.</p>
      </div>
      <div>
        <label for="userBirthday" class="form-label">생년월일</label>
        <input
          id="userBirthday"
          type="date"
          v-model="formData.userBirthday"
          min="1900-01-01"
          max="2023-01-01"
        />
        <p v-if="!formRealTime.userBirthday">생일을 선택해주세요.</p>
      </div>
    </div>

    <div>
      <button @click="formCheck" class="btn btn-primary">회원가입</button>
      <button @click="back" class="btn btn-primary">뒤로가기</button>
    </div>
  </div>
</template>

<script setup>
// 서버단 유효성 검사
/* id는 서버단 유효성 검사를 통해 동일 아이디가 있는지 확인 */
//

// 프론트단 유효성 검사
// password와 passwordcheck이 동일하다면 유효성 O, password 길이제한 + 영어 + 특문제한
// name은 한글로 작성, 2글자 이상
// 핸드폰 번호는 010으로만 제한
// 이메일 @... 형태로

import { onMounted, ref, watch } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
const store = useUserStore();

const router = useRouter();

const formData = ref({
  userId: "",
  userPassword: "",
  userNickname: "",
  userName: "",
  userPhoneNumber: "",
  userEmail: "",
  userGender: "",
  userBirthday: "",
});

const userPasswordCheck = ref("");

// 이게 최종적으로 쏠 함수
const formCheck = function () {
  console.log(formRealTime.value);
  if (
    formRealTime.value.userId &&
    formRealTime.value.userPassword &&
    formRealTime.value.userNickname &&
    formRealTime.value.userName &&
    formRealTime.value.userPhoneNumber &&
    formRealTime.value.userEmail &&
    formRealTime.value.userGender &&
    formRealTime.value.userBirthday
  ) {
    store.signUp(formData.value);
  } else {
    alert("양식을 확인해주세요");
  }
};

const back = function () {
  router.go(-1);
};

const formRealTime = ref({
  userId: false,
  userPassword: false,
  userNickname: false,
  userName: false,
  userPhoneNumber: false,
  userEmail: false,
  userGender: false,
  userBirthday: false,
});

// 중복확인 아이디, 닉네임, 휴대폰번호, 이메일
const idCh = async function () {
  await store.searchOneUser(formData.value.userId);
  if (formData.value.userId === store.loginUser.userId) {
    formRealTime.value.userId = false;
    console.log(formRealTime.value.userId);
  } else {
    console.log("아닌경우에 else 들어가나요~~");
  }

  await store.searchOneUser(formData.value.userNickname);
  if (formData.value.userNickname === store.loginUser.userNickname) {
    formRealTime.value.userNickname = false;
  }

  await store.searchOneUser(formData.value.userPhoneNumber);
  if (formData.value.userPhoneNumber === store.loginUser.userPhoneNumber) {
    formRealTime.value.userPhoneNumber = false;
  }

  await store.searchOneUser(formData.value.userPhoneNumber);
  if (formData.value.userEmail === store.loginUser.userEmail) {
    formRealTime.value.userEmail = false;
  }
};

watch(formData.value, (newValue, oldValue) => {
  store.searchOneUser(newValue.userId);

  if (onlyNumberAndEnglish(newValue.userId)) {
    if (newValue.userId.length >= 4 && newValue.userId.length <= 20) {
      formRealTime.value.userId = true;
    } else {
      formRealTime.value.userId = false;
    }
  }

  // pw와 pwcheck이 동일하고, 영어, 숫자, 특문만 들어오며
  // 8~20글자 사이인 경우에만 조건 성공
  if (strongPassword(newValue.userPassword)) {
    formRealTime.value.userPassword = true;
  } else {
    formRealTime.value.userPassword = false;
  }
  // 닉네임이 한글, 영어, 숫자가 들어오고 2글자~10글자 사이만 작성 가능
  if (strongNickname(newValue.userNickname)) {
    if (
      newValue.userNickname.length >= 2 &&
      newValue.userNickname.length <= 10
    ) {
      formRealTime.value.userNickname = true;
    } else {
      formRealTime.value.userNickname = false;
    }
  }
  // 이름은 2글자~18글자 사이이며, 한글과 영어만 입력 가능
  if (strongName(newValue.userName)) {
    if (newValue.userName.length >= 2 && newValue.userName.length <= 18) {
      formRealTime.value.userName = true;
    } else {
      formRealTime.value.userName = false;
    }
  }

  // 핸드폰 번호는 11글자이며, 숫자만 입력가능
  if (/^[0-9]+$/.test(newValue.userPhoneNumber)) {
    if (newValue.userPhoneNumber.length === 11) {
      formRealTime.value.userPhoneNumber = true;
    } else {
      formRealTime.value.userPhoneNumber = false;
    }
  }

  // 이메일은 ssafy@ssfay.com과 같은 형식만 받을 수 있게 처리
  if (strongEmail(newValue.userEmail)) {
    formRealTime.value.userEmail = true;
  } else {
    formRealTime.value.userEmail = false;
  }

  // 성별 선택
  if (newValue.userGender !== null) {
    formRealTime.value.userGender = true;
  } else {
    formRealTime.value.userGender = false;
  }

  // 생일 선택
  if (newValue.userBirthday !== null) {
    formRealTime.value.userBirthday = true;
  } else {
    formRealTime.value.userBirthday = false;
  }
});

const testb = function () {
  // id에 영어, 숫자만 들어오며 4~20글자 사이인 경우에만 조건 성공
  if (onlyNumberAndEnglish(formData.value.userId)) {
    if (
      formData.value.userId.length >= 4 &&
      formData.value.userId.length <= 20
    ) {
      console.log("정상적인 아이디 작동");
      console.log(formEffectiveness.value.userId);
      formEffectiveness.value.userId = true;
      console.log(formEffectiveness.value.userId);
    }
  }

  // pw와 pwcheck이 동일하고, 영어, 숫자, 특문만 들어오며
  // 8~20글자 사이인 경우에만 조건 성공
  if (strongPassword(formData.value.userPassword)) {
    if (formData.value.userPassword === userPasswordCheck.value) {
      console.log("정상적인 패스워드 작동");
    }
  }
  // 닉네임이 한글, 영어, 숫자가 들어오고 1글자~10글자 사이만 작성 가능
  if (strongNickname(formData.value.userNickname)) {
    if (
      formData.value.userNickname.length >= 1 &&
      formData.value.userNickname.length <= 10
    ) {
      console.log("정상적인 닉네임 작동");
    }
  }
  // 이름은 2글자~18글자 사이이며, 한글과 영어만 입력 가능
  if (strongName(formData.value.userName)) {
    if (
      formData.value.userName.length >= 2 &&
      formData.value.userName.length <= 18
    ) {
      console.log("정상적인 이름 작동");
    }
  }

  // 핸드폰 번호는 11글자이며, 숫자만 입력가능
  if (/^[0-9]+$/.test(formData.value.userPhoneNumber)) {
    if (formData.value.userPhoneNumber.length === 11) {
      console.log("정상적인 폰번호 작동");
    }
  }

  // 이메일은 ssafy@ssfay.com과 같은 형식만 받을 수 있게 처리
  if (strongEmail(formData.value.userEmail)) {
    console.log("정상적인 이메일 작동");
  }

  console.log(formData.value);
};

const onlyNumberAndEnglish = function (str) {
  return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
};

const strongPassword = function (str) {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/.test(
    str
  );
};

const strongNickname = function (str) {
  return /^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$/.test(str);
};

const strongName = function (str) {
  return /^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$/.test(str);
};

const strongEmail = function (str) {
  return /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/.test(str);
};
</script>

<style scoped>
.logo {
  text-align: center;
  margin-top: 100px;
}
.centerLine {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.form-label {
  font-weight: bold;
}
.mainBox {
  border: 1px solid gray;
  text-align: center;
  width: 700px;

  padding-top: 30px;
  padding-bottom: 14px;
  margin-bottom: 20px;
}

p {
  font-size: 13px;
  color: gray;
}

button {
  background-color: rgb(255, 153, 0);
  background-color: hsla(24, 240, 120);
}

button:hover {
  background-color: red;
  background-color: hsla(24, 240, 120);
}
</style>
