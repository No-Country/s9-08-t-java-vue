<template>
  <section class="my-24 flex h-full w-full flex-col items-center justify-center gap-y-28">
    <h1 class="mt-24 text-5xl uppercase text-primary-orange">Conozca nuestra flota</h1>
    <div class="flex items-center justify-center gap-8">
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

const data: Ref<IFleetCard[]> = ref(flota)
</script>
