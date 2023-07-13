<template>
  <section class="flex min-h-screen w-full flex-col justify-center">
    <h1 class="text-5xl font-bold text-blue-night">Más opciones para tu mudanza</h1>
    <h3 class="text-3xl font-light text-blue-night">Más opciones para tu mudanza</h3>
    <div class="relative mt-96 h-full w-full rounded-4xl bg-light-blue p-32">
      <div class="absolute -top-3/4 left-16 flex h-full w-full items-center justify-center gap-4">
        <MoreOptionsCard :key="item.id" v-for="item in data" :prop="item" />
        <img src="@/assets/img/options-info/girl-pointing.webp" alt="girl pointing cards" />
      </div>
      <div class="flex w-2/4 flex-col justify-center gap-4">
        <h1 class="text-5xl font-bold text-primary-orange">Experiencias con MoveNow</h1>
        <p class="text-3xl font-light">
          Cuando usted elige Move Now, esta eligiendo eficacia, comodidad y sobre todo seguridad.
        </p>
        <a
          class="w-max rounded-2xl bg-primary-orange px-4 py-2 text-2xl font-semibold text-white"
          href="#"
          >Contratar servicio</a
        >
      </div>
      <div class="absolute -bottom-2/4 left-1/4 -translate-y-1/4">
        <carousel-wrapper />
      </div>
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
