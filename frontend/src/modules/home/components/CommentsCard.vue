<template>
  <div
    class="relative mt-40 flex h-80 w-80 flex-col items-center justify-center gap-3 rounded-4xl bg-light-blue p-3"
  >
    <img
      :src="prop.img"
      alt="profile picture placeholder"
      class="absolute -top-1/4 left-2/4 z-30 -translate-x-2/4"
    />
    <h4 class="text-2xl font-semibold">{{ prop.name }}</h4>
    <div class="flex items-center gap-2">
      <img :key="star" v-for="star in stars" src="@/assets/img/comments/star.svg" alt="star" />
    </div>
    <h6 class="text-lg font-semibold">Testimonio</h6>
    <p class="text-center font-light">{{ prop.comment }}</p>
  </div>
</template>

<script setup lang="ts">
import type { IComments } from '../interfaces/IComments'

const props = defineProps<{ prop: IComments }>()

const stars = Array(props.prop.stars)
</script>
