<template>
  <div>
    <nav class="navbar navbar-dark bg-dark">
      <div class="container-fluid">
        <RouterLink to="/home" class="navbar-brand">
          <!-- 김상민그는감히전설이라고할수있다 -->
          <img src="@/assets/blackLogo.png" />
        </RouterLink>

        <div class="navbar-brand">
          <li id="menu__menu">
            마이페이지
            <div id="subwrapper">
              <nav id="subnav">
                <ul id="submenu__list" v-if="logCheck !== null">
                  <RouterLink :to="{ name: 'MyPageInfo' }" class="navbar-brand"
                    >회원정보</RouterLink
                  >
                  <RouterLink :to="{ name: 'friend' }" class="navbar-brand">
                    친구정보
                  </RouterLink>
                  <RouterLink
                    :to="{ name: 'MyPageStatus' }"
                    class="navbar-brand"
                    >상태창</RouterLink
                  >
                  <RouterLink
                    :to="{ name: 'MyPageInfoModify' }"
                    class="navbar-brand"
                    >정보수정</RouterLink
                  >
                </ul>
                <ul id="submenu__list" v-else>
                  <span class="navbar-brand" @click="goLogin">회원정보</span>
                  <span class="navbar-brand" @click="goLogin">친구정보</span>
                  <span class="navbar-brand" @click="goLogin">상태창</span>
                  <span class="navbar-brand" @click="goLogin">정보수정</span>
                </ul>
              </nav>
            </div>
          </li>
        </div>

        <div class="navbar-brand">
          <li id="menu__menu" @click="bringBoardList">
            <RouterLink to="/board"> 공지사항 </RouterLink>
          </li>
        </div>

        <div class="navbar-brand">
          <li id="menu__menu">
            커뮤니티
            <div id="subwrapper">
              <nav id="subnav">
                <ul id="submenu__list">
                  <span @click="bringBoardList1">
                    <RouterLink to="/board" class="navbar-brand"
                      >정보게시판</RouterLink
                    >
                  </span>
                  <span @click="bringBoardList2">
                    <RouterLink to="/board" class="navbar-brand"
                      >자유게시판</RouterLink
                    >
                  </span>
                  <span @click="bringBoardList3">
                    <RouterLink to="/board" class="navbar-brand"
                      >핫딜게시판</RouterLink
                    >
                  </span>
                  <span @click="bringBoardList4">
                    <RouterLink to="/board" class="navbar-brand"
                      >질문게시판</RouterLink
                    >
                  </span>
                </ul>
              </nav>
            </div>
          </li>
        </div>

        <div class="navbar-brand">
          <li id="menu__menu">
            <RouterLink to="/youtube"> 실시간영상 </RouterLink>
          </li>
        </div>

        <div class="navbar-brand">
          <li v-if="logCheck === null" id="menu__sbu__menu">
            <RouterLink to="/login" id="menu__menu"> 로그인 </RouterLink>
            <span id="menu__menu2">|</span>
            <RouterLink to="/signUp" id="menu__menu"> 회원가입 </RouterLink>
          </li>
          <li v-else id="menu__menu">
            <a @click="logout">로그아웃</a>
          </li>
        </div>

        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasDarkNavbar"
          aria-controls="offcanvasDarkNavbar"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div
          class="offcanvas offcanvas-end text-bg-dark"
          tabindex="-1"
          id="offcanvasDarkNavbar"
          aria-labelledby="offcanvasDarkNavbarLabel"
        >
          <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
              환영합니다. {{ loginId }} 님!
            </h5>
            <button
              type="button"
              class="btn-close btn-close-white"
              data-bs-dismiss="offcanvas"
              aria-label="Close"
            ></button>
          </div>
          <div class="offcanvas-body">
            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/home"
                  >홈으로</a
                >
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
              </li>
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Dropdown
                </a>
                <ul class="dropdown-menu dropdown-menu-dark">
                  <li><a class="dropdown-item" href="#">Action</a></li>
                  <li><a class="dropdown-item" href="#">Another action</a></li>
                  <li>
                    <hr class="dropdown-divider" />
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">Something else here</a>
                  </li>
                </ul>
              </li>
            </ul>
            <form class="d-flex mt-3" role="search">
              <input
                class="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button class="btn btn-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </div>
    </nav>
    <!-- 내비게이션 바의 fixed 특징으로 
      인해 아래 클래스에 padding-top을 줘서 해결하자 -->
  </div>
  <!-- <div class="test" style="padding-top: 80px"></div> -->
  <div style="padding-bottom: 30px"></div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { useBoardStore } from "@/stores/board";
import { ref } from "vue";

const storeUser = useUserStore();
const storeBoard = useBoardStore();

const SearchCondition = ref({
  key: "boardClassification",
  word: "공지",
});

const bringBoardList = function () {
  storeBoard.getBoardList({ key: "boardClassification", word: "공지" });
};

const bringBoardList1 = function () {
  storeBoard.getBoardList({ key: "boardClassification", word: "정보" });
};

const bringBoardList2 = function () {
  storeBoard.getBoardList({ key: "boardClassification", word: "자유" });
};

const bringBoardList3 = function () {
  storeBoard.getBoardList({ key: "boardClassification", word: "핫딜" });
};

const bringBoardList4 = function () {
  storeBoard.getBoardList({ key: "boardClassification", word: "질문" });
};

const logCheck = ref(sessionStorage.getItem("access-token"));
const loginId = ref(sessionStorage.getItem("login-usernickname"));

const logout = function () {
  storeUser.userLogout();
};

const goLogin = function () {
  alert("로그인이 필요합니다.");
};
</script>
<style scoped>
a {
  text-decoration: none;
  color: white;
}

#wrapper {
  position: relative;
}

#nav {
  width: 100%;
  max-width: 1024px;

  margin: 0 auto;
}

#menu__list {
  display: table;
  list-style-type: none;

  width: 100%;

  text-align: center;
}

#menu__sbu__menu {
  display: table-row;
  vertical-align: middle;

  color: white;

  padding-left: 20px;
  padding-right: 20px;

  height: 47px;
}

#menu__menu {
  display: table-cell;
  vertical-align: middle;

  color: white;

  padding-left: 20px;
  padding-right: 20px;

  height: 47px;
}

#menu__menu2 {
  display: table-cell;
  vertical-align: middle;

  color: white;

  height: 47px;
}

#menu__menu:hover {
  /* background-color: #deb6ab; */
  background-color: rgb(255, 153, 0);
  background-color: hsla(24, 240, 120);
}

#menu__menu:hover #subwrapper {
  display: block;
}

#menu__menu:hover #submenu__list {
  display: table;
}

#subwrapper {
  display: none;

  /* background-color: #deb6ab; */
  background-color: rgb(255, 153, 0);
  background-color: hsla(24, 240, 120);

  /* hover 시 하단에 나오는 바 길이 */
  /* width: 200px; */

  position: absolute;
  top: 60px;
}

#subnav {
  width: 100%;
  max-width: 1024px;

  margin: 0 auto;
}

#submenu__list {
  display: none;
  list-style-type: none;

  width: 100%;

  text-align: center;
}

#submenu__menu {
  display: table-cell;
  vertical-align: middle;

  height: 50px;

  cursor: pointer;
}

#submenu__menu:hover {
  background-color: #ac7d88;
}

.navbar-brand {
  padding-left: 20px;
  padding-right: 20px;
}

img {
  width: 250px;
}
</style>
