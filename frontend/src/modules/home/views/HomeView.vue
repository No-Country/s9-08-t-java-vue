<template>
  <main class="font-montserrat">
    <HeroHome />
    <SearchShipment />
  </main>
</template>

<script setup lang="ts">
import MNToggle from '@/components/common/MNToggle.vue'
import HeroHome from '../components/HeroHome.vue'
import SearchShipment from '../components/SearchShipment.vue'

const isChecked = (value: boolean) => console.log(value)
</script>
