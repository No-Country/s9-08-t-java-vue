<template>
  <div class="my-4 flex h-[409px] w-80 flex-col gap-2 rounded-4xl bg-white px-6 py-2 shadow-3xl">
    <div class="flex h-2/4 w-full items-center justify-center">
      <img class="self-center object-cover object-center" :src="prop.img" alt="prop.img" />
    </div>
    <div class="flex h-2/4 w-full flex-col justify-evenly gap-3 pb-4">
      <h4 class="w-full text-2xl font-semibold text-blue-night">{{ prop.heading }}</h4>
      <p class="font-light">{{ prop.description }}</p>
      <a class="text-2xl font-semibold text-primary-orange" text="Saber más" :href="prop.link" />
    </div>
  </div>
</template>

<script setup lang="ts">
import type { ICarouselCard } from '../interfaces/ICarouselCard'

const props = defineProps<{ prop: ICarouselCard }>()
</script>
