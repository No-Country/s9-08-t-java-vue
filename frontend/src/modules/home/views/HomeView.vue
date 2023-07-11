<template>
  <main class="container mx-auto font-montserrat">
    <MNNavbar />
    <HeroHome />
    <FleetSection />
    <MNFooter />
  </main>
</template>

<script setup lang="ts">
import HeroHome from '../components/HeroHome.vue'
import FleetSection from '../components/FleetSection.vue'
import MNNavbar from '@/components/common/MNNavbar.vue'
import MNFooter from '@/components/common/MNFooter.vue'
</script>
