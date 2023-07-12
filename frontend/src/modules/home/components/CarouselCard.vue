<template>
  <div class="relative h-64 w-72 rounded-[40px] border-[1px] border-primary-blue p-4">
    <img class="h-28 w-52 object-cover" :src="prop.img" alt="" />
    <h4 class="font-bold">{{ prop.heading }}</h4>
    <a text="Contratar" class="rounded-lg bg-primary-orange py-1 hover:brightness-90" />
  </div>
</template>

<script setup lang="ts">
import MNButton from '@/components/common/MNButton.vue'
import type { ICarouselCard } from '../interfaces/ICarouselCard'

const props = defineProps<{ prop: ICarouselCard }>()
</script>
