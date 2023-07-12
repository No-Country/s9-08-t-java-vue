<template>
  <swiper :slides-per-view="3" :space-between="50" @swiper="onSwiper" @slideChange="onSlideChange">
    <swiper-slide>
      <carousel-card v-for="item in data" :prop="item" :key="item.id" />
    </swiper-slide>
  </swiper>
</template>
<script setup lang="ts">
import { Swiper, SwiperSlide } from 'swiper/vue'
import CarouselCard from './CarouselCard.vue'
import 'swiper/css'
import type { Swiper as TSwiper } from 'swiper/types'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { carousel } from '../data/carousel-data'
import type { ICarouselCard } from '../interfaces/ICarouselCard'

const onSwiper = (swiper: TSwiper) => {
  console.log(swiper)
}
const onSlideChange = () => {
  console.log('slide change')
}
const data: Ref<ICarouselCard[]> = ref(carousel)
</script>
