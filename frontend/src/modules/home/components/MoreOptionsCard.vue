<template>
  <div
    class="relative flex h-96 w-80 min-w-[290px] flex-col items-end justify-start overflow-hidden rounded-4xl bg-gray-card shadow-3xl"
  >
    <div class="absolute left-0 top-4 z-10 mt-6 flex flex-col items-start gap-4">
      <h4 class="inline rounded-r-lg bg-primary-orange px-3 py-1 text-lg font-semibold text-white">
        {{ prop.name }}
      </h4>
      <p class="px-6 text-2xl font-semibold text-blue-night">{{ prop.info }}</p>
    </div>
    <img
      class="absolute bottom-2 object-cover"
      :class="prop.center ? 'left-2/4 -translate-x-2/4 ' : 'right-0'"
      :src="prop.img"
      alt=""
    />
  </div>
</template>

<script lang="ts" setup>
import type { IMoreOptionsCard } from '../interfaces/IMoreOptionaCard'

interface IMoreOptionsProp {
  prop: IMoreOptionsCard
}

const props = defineProps<IMoreOptionsProp>()
</script>
