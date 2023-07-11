<template>
  <main class="flex justify-center items-center h-screen">
    <section class="flex gap-4 flex-wrap">
      <FleetCard v-for="item in data" :prop="item"></FleetCard>
    </section>
  </main>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import FleetCard from '../components/FleetCard.vue'
import {flota} from '../data/flota-info'
import type { Ref } from 'vue';
import type { IFleetCard } from '../interfaces/IFleetCard';


const data: Ref<IFleetCard[]> = ref(flota)
console.log(data.value)

</script>
