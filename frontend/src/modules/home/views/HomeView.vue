<template>
  <main class="container mx-auto font-montserrat">
    <MNNavbar />
    <HeroHome />
    <FleetWrapper />
    <MoreOptions />
    <MNFooter />
  </main>
</template>

<script setup lang="ts">
import HeroHome from '../components/HeroHome.vue'
import FleetWrapper from '../components/FleetWrapper.vue'
import MoreOptions from '../components/MoreOptions.vue'
import MNNavbar from '@/components/common/MNNavbar.vue'
import MNFooter from '@/components/common/MNFooter.vue'
</script>
