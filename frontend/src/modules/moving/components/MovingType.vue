<template>
  <section class="mt-20">
    <h1 class="mt-52 text-5xl font-bold text-primary-orange lg:mt-24">Elige tu tipo de mudanza</h1>
    <h3 class="text-3xl font-light text-blue-night">¿Cuál es el tamaño de tu mudanza?</h3>
    <SwiperGeneric :data="data" class="mt-10 h-96">
      <template #slide="{ info, size }">
        <CardMovin :info="info" :size="size"></CardMovin>
      </template>
    </SwiperGeneric>
  </section>
</template>

<script lang="ts" setup>
import SwiperGeneric from './SwiperGeneric.vue'
import CardMovin from './CardMovin.vue'
import { ref } from 'vue'

const data = ref([
  { info: 'Persona o parejas', size: 'S' },
  { info: 'Familia o eventos reducidos', size: 'M' },
  { info: 'Familia o eventos grandes', size: 'L' }
])

const test = (arg: boolean) => console.log(arg)
</script>
