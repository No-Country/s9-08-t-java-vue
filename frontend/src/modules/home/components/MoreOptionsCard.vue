<template>
  <div class="relative flex w-56 flex-col rounded-3xl bg-gray-card shadow-2xl">
    <div class="absolute z-10 mt-6 inline rounded-r-lg bg-primary-orange px-3">
      <p class="inline py-1 text-sm text-white">{{ prop.name }}</p>
    </div>
    <div class="mt-14 h-16 px-5">
      <p class="font-bold text-blue-night">{{ prop.info }}</p>
    </div>
    <div class="self-end">
      <img class="h-40 w-36 object-cover" :src="prop.img" alt="" />
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { IMoreOptionsCard } from '../interfaces/IMoreOptionaCard'

interface IMoreOptionsProp {
  prop: IMoreOptionsCard
}

const { prop } = defineProps<IMoreOptionsProp>()
</script>
