<template>
  <main class="container mx-auto overflow-x-hidden font-montserrat">
    <MNNavbar />
    <Hero />
    <Fleet />
    <MoreOptions />
    <Comments />
    <MNFooter />
  </main>
</template>

<script setup lang="ts">
import Hero from '../components/HeroHome.vue'
import Fleet from '../components/FleetWrapper.vue'
import MoreOptions from '../components/MoreOptions.vue'
import Comments from '../components/CommentsWrapper.vue'
import MNNavbar from '@/components/common/MNNavbar.vue'
import MNFooter from '@/components/common/MNFooter.vue'
</script>
