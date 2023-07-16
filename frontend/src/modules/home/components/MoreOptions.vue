<template>
  <section class="relative h-auto p-4">
    <div>
      <h1 class="text-5xl font-bold text-blue-night">Más opciones para tu mudanza</h1>
      <h3 class="text-3xl font-light text-blue-night">Más opciones para tu mudanza</h3>
    </div>
    <div
      class="left-0 z-10 flex w-full flex-wrap justify-center gap-4 py-10 lg:absolute lg:top-20 lg:flex-nowrap lg:justify-start"
    >
      <MoreOptionsCard :key="item.id" v-for="item in data" :prop="item" />
      <img
        class="absolute -right-52 -top-36 z-10 hidden -translate-x-1/2 lg:block"
        src="@/assets/img/options-info/girl-pointing.webp"
        alt="girl pointing cards"
      />
    </div>
    <div class="relative h-full w-full rounded-4xl bg-light-blue p-7 lg:mt-80 lg:p-32">
      <div class="flex flex-col justify-center gap-4 lg:w-2/4">
        <h1 class="text-3xl font-bold text-primary-orange lg:text-5xl">Experiencias con MoveNow</h1>
        <p class="text-2xl font-light lg:text-3xl">
          Cuando usted elige Move Now, esta eligiendo eficacia, comodidad y sobre todo seguridad.
        </p>
        <a
          class="w-max rounded-2xl bg-primary-orange px-4 py-2 text-2xl font-semibold text-white"
          href="#"
          >Contratar servicio</a
        >
      </div>
    </div>
    <div class="-bottom-0 left-1/4 lg:absolute lg:translate-x-7 lg:translate-y-1/2">
      <carousel-wrapper />
    </div>
  </section>
</template>

<script setup lang="ts">
import CarouselWrapper from '../components/CarouselWrapper.vue'
import MoreOptionsCard from './MoreOptionsCard.vue'
import { ref } from 'vue'
import { options } from '../data/options-info'
import type { Ref } from 'vue'
import type { IMoreOptionsCard } from '../interfaces/IMoreOptionaCard'

const data: Ref<IMoreOptionsCard[]> = ref(options)
</script>
