<template>
  <swiper
    :slides-per-view="'auto'"
    :space-between="20"
    :center-insufficient-slides="true"
    class="h-full w-3/5"
    @swiper="onSwiper"
    @slideChange="onSlideChange"
    :modules="modules"
  >
    <swiper-slide v-for="item in data" :key="item.id" :style="{ flexShrink: 'unset' }">
      <carousel-card :prop="item" />
    </swiper-slide>
  </swiper>
</template>

<script setup lang="ts">
import { Swiper, SwiperSlide } from 'swiper/vue'
import { Pagination } from 'swiper/modules'
import 'swiper/css'
import 'swiper/css/pagination'
import type { Swiper as TSwiper } from 'swiper/types'

import { ref } from 'vue'
import type { Ref } from 'vue'

import CarouselCard from './CarouselCard.vue'
import { carousel } from '../data/carousel-data'
import type { ICarouselCard } from '../interfaces/ICarouselCard'

const modules = [Pagination]

const onSwiper = (swiper: TSwiper) => {
  console.log(swiper)
}
const onSlideChange = (swiper: TSwiper) => {
  console.log(swiper)
}
const data: Ref<ICarouselCard[]> = ref(carousel)
</script>
