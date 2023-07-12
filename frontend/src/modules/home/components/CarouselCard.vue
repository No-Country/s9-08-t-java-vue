<template>
  <div class="flex h-[409px] w-[309px] flex-col gap-4 rounded-4xl bg-white px-6 py-2 shadow-xl">
    <img class="self-center object-cover object-center" :src="prop.img" alt="prop.img" />
    <h4 class="w-full text-2xl font-semibold text-blue-night">{{ prop.heading }}</h4>
    <p class="font-light">{{ prop.description }}</p>
    <a class="text-2xl font-semibold text-primary-orange" text="Saber más" :href="prop.link" />
  </div>
</template>

<script setup lang="ts">
import type { ICarouselCard } from '../interfaces/ICarouselCard'

const props = defineProps<{ prop: ICarouselCard }>()
</script>
