<template>
  <button
    :class="[
      'bg-primary-blue w-full text-white font-medium rounded-full hover:bg-second my-2',
      props.class
    ]"
  >
    {{ text }}
  </button>
</template>

<script setup lang="ts">
const props = defineProps({
  text: String,
  class: {
    default: 'w-52 h-10',
    type: String
  }
})
</script>

<style lang="scss" scoped></style>
