<template>
  <TheHeaderNav />

  <div id="sideSpace">
    <div>
      <!-- <div>전체검색 컴포넌트</div> -->
      <div>
        <popPost style="width: 450px" />
      </div>
    </div>
    <div style="width: 330px">
      <h3>건강 정보</h3>
      <hr />
      <div>단백질</div>
      <p>섭취량 : 체중 1kg당 1.6~2g</p>
      <p>
        흰우유(500ml)를 잘 못먹으면 WPI, 잘 먹으면 WPC, 적당히 잘 먹으면 WPI+WPC
      </p>
      <hr />
      <div>종합비타민</div>
      <p>섭취량 : 1/2 ~ 1서빙</p>
      <p>식사 부실 시 1서빙, 아이허브와 쿠팡 구매 추천</p>
      <hr />
      <div>섬유질</div>
      <p>섭취량 : 25g</p>
      <p>
        소화 능력이 약한 사람에게 추천, 익힌 채소 추천, 섭취량 한번에 늘리지
        말것천
      </p>
      <hr />
      <div>유산균(프로바이오틱스)</div>
      <p>섭취량 : 100억(10 Billion)</p>
      <p>소화 능력이 약한 사람에게 추천</p>
      <hr />
      <div>글루타민</div>
      <p>섭취량 : 5g</p>
      <p>소화 능력이 약한 사람에게 추천</p>
      <hr />
    </div>
  </div>

  <TheFooterNav />
</template>

<script setup>
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";
import TheFooterNav from "@/components/common/TheFooterNav.vue";
import BoardList from "../components/board/BoardList.vue";
import popPost from "@/components/homePage/popPost.vue";
</script>

<style scoped>
#sideSpace {
  margin-left: 20%;
  margin-right: 20%;
  display: flex;
  justify-content: space-between;
}
</style>
