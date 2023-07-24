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
    <swiper-slide v-for="item in data" :key="item.id" :style="{ flexShrink: 'unset' }">
      <fleet-card :prop="item" />
    </swiper-slide>
  </swiper>
</template>

<script setup lang="ts">
import { Swiper, SwiperSlide } from 'swiper/vue'
import 'swiper/css'
import { ref } from 'vue'
import FleetCard from '@/components/common/MNFleetCard.vue'
import { flota } from '@/lib/data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '@/lib/types'

const data: Ref<IFleetCard[]> = ref(flota)
</script>
