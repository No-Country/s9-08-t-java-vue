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

import { useMovingStore } from '@/store/moving'
import { useRouter } from 'vue-router'
import { onBeforeMount } from 'vue'

const router = useRouter()
const moving = useMovingStore()
onBeforeMount(() => {
  if (!moving.sendingType) router.push('/')
})
</script>
