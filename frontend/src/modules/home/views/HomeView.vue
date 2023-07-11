<template>
  <main class="container mx-auto font-montserrat">
    <MNNavbar />
    <section class="flex flex-wrap gap-4">
      <HeroHome />
      <!-- <FleetCard v-for="item in data" :prop="item"></FleetCard> -->
    </section>
  </main>
</template>

<script setup lang="ts">
import HeroHome from '../components/HeroHome.vue'
import { ref } from 'vue'
import FleetCard from '../components/FleetCard.vue'
import MNNavbar from '@/components/common/MNNavbar.vue'
import { flota } from '../data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '../interfaces/IFleetCard'

const data: Ref<IFleetCard[]> = ref(flota)
console.log(data.value)
</script>
