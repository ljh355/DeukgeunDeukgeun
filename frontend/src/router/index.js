import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import LogoView from "@/views/LogoView.vue";
import MyPageView from "@/views/MyPageView.vue";
import BoardView from "@/views/BoardView.vue";
import YoutubeView from "@/views/YoutubeView.vue";

import BoardList from "@/components/board/BoardList.vue";
import BoardCreate from "@/components/board/BoardCreate.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

import UserLogin from "@/components/user/UserLogin.vue";
import UserSignUp from "@/components/user/UserSignUp.vue";

import MyPageInfo from "@/components/myPage/MyPageInfo.vue";
import MyPageStatus from "@/components/myPage/MyPageStatus.vue";
import MyPageInfoModify from "@/components/myPage/MyPageInfoModify.vue";
import MyPageFriend from "@/components/myPage/MyPageFriend.vue";

import popPost from "@/components/homePage/popPost.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "logo",
      component: LogoView,
    },
    {
      path: "/home",
      name: "home",
      component: HomeView,
      children: [
        {
          path: "popPost",
          name: "popPost",
          component: popPost,
        },
      ],
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPageView,
      children: [
        {
          path: "info",
          name: "MyPageInfo",
          component: MyPageInfo,
        },
        {
          path: "status",
          name: "MyPageStatus",
          component: MyPageStatus,
        },
        {
          path: "modify",
          name: "MyPageInfoModify",
          component: MyPageInfoModify,
        },
        {
          path: "friend",
          name: "friend",
          component: MyPageFriend,
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList,
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate,
        },
        {
          path: ":boardNo",
          name: "boardDetail",
          component: BoardDetail,
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate,
        },
      ],
    },
    {
      path: "/youtube",
      name: "youtube",
      component: YoutubeView,
    },
    {
      path: "/login",
      name: "login",
      component: UserLogin,
    },
    {
      path: "/signUp",
      name: "UserSignUp",
      component: UserSignUp,
    },
  ],
});

export default router;
