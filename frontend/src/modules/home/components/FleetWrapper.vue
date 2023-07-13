<template>
  <section class="my-24 flex h-full w-full flex-col flex-wrap items-center justify-center gap-y-28">
    <h1 class="mt-24 text-5xl uppercase text-primary-orange">Conozca nuestra flota</h1>
    <div class="flex flex-wrap items-center justify-center gap-8">
      <FleetCard :key="item.id" v-for="item in data" :prop="item" />
    </div>
  </section>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import FleetCard from './FleetCard.vue'
import { flota } from '../data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '../interfaces/IFleetCard'
import { useHomeStore } from '../store/home'
import { watch } from 'vue'
import { storeToRefs } from 'pinia'
import { MOVING, SENDING } from '../constants'

const home = storeToRefs(useHomeStore())
const data: Ref<IFleetCard[]> = ref(flota)

watch(home.sendingType, () => {
  if (home.sendingType.value === SENDING) {
    data.value[1].selectable = false
    data.value[2].selectable = false
    data.value[0].selectable = true
  }
  if (home.sendingType.value === MOVING) {
    data.value[1].selectable = true
    data.value[2].selectable = true
    data.value[0].selectable = false
  }
})
</script>
