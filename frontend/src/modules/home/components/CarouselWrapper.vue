<template>
  <div class="absolute -bottom-2/4 left-2/4 -translate-y-2/4 translate-x-40">
    <swiper
      :centered-slides="true"
      :slides-per-view="'auto'"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
    >
      <swiper-slide v-for="item in data" :key="item.id">
        <carousel-card :prop="item" />
      </swiper-slide>
    </swiper>
  </div>
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
