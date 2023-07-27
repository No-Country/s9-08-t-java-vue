<template>
  <button
    @click.prevent
    :class="
      twMerge(
        'hover:bg-second my-2 w-full rounded-full bg-primary-blue font-medium text-white',
        props.class
      )
    "
  >
    {{ text }}
  </button>
</template>

<script setup lang="ts">
import { twMerge } from 'tailwind-merge'

const props = defineProps({
  text: String,
  class: {
    default: 'h-10',
    type: String
  }
})
</script>

<style lang="scss" scoped></style>
