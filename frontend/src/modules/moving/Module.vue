<template>
  <main class="container mx-auto min-h-screen overflow-x-hidden font-montserrat">
    <MNNavbar></MNNavbar>
    <RouterView />
    <MNFooter></MNFooter>
  </main>
</template>

<script lang="ts" setup>
import MNNavbar from '@/components/common/MNNavbar.vue'
import MNFooter from '@/components/common/MNFooter.vue'
</script>
