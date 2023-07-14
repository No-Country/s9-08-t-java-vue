<template>
  <section class="mt-72 flex h-full w-full flex-col items-center justify-center gap-8">
    <h2 class="text-4xl font-normal uppercase text-primary-orange">
      Comentarios de nuestros clientes
    </h2>
    <swiper
      :slides-per-view="'auto'"
      :space-between="20"
      :center-insufficient-slides="true"
      class="h-full w-full"
    >
      <swiper-slide
        class="flex items-center"
        v-for="item in data"
        :key="item.id"
        :style="{ flexShrink: 'unset' }"
      >
        <comments-card :prop="item" />
      </swiper-slide>
    </swiper>
  </section>
</template>

<script setup lang="ts">
import { Swiper, SwiperSlide } from 'swiper/vue'
// import { Pagination } from 'swiper/modules'
import 'swiper/css'
// import type { Swiper as TSwiper } from 'swiper/types'

import { ref } from 'vue'
import { comments } from '../data/comments'
import type { Ref } from 'vue'
import type { IComments } from '../interfaces/IComments'
import CommentsCard from './CommentsCard.vue'

// const modules = [Pagination]

const data: Ref<IComments[]> = ref(comments)
</script>
