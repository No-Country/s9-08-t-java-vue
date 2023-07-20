<template>
  <swiper
    :slides-per-view="'auto'"
    :space-between="10"
    :breakpoints="{
      '1024': {
        slidesPerView: 3,
        spaceBetween: 20
      }
    }"
    :center-insufficient-slides="true"
    class="h-full w-full"
  >
    <swiper-slide v-for="item in data" :key="JSON.stringify(item)" :style="{ flexShrink: 'unset' }">
      <slot name="slide" v-bind="item"> </slot>
    </swiper-slide>
  </swiper>
</template>

<script setup lang="ts">
import { Swiper, SwiperSlide } from 'swiper/vue'
import 'swiper/css'

defineProps({
  data: Array as () => unknown[]
})
</script>
