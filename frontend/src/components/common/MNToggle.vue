<template>
  <label class="relative inline-flex cursor-pointer items-center">
    <input type="checkbox" v-model="check" class="peer sr-only" />
    <div :class="style"></div>
  </label>
</template>

<script lang="ts" setup>
import { watch } from 'vue'
import { onMounted } from 'vue'
import { ref } from 'vue'

const check = ref(false)
const style = ref([
  'w-11 h-6 bg-gray-200 peer-focus:outline-none  peer-focus:ring-0 rounded-full peer dark:bg-gray-300 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[""] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 '
])

const { color } = defineProps({
  color: {
    type: String,
    default: 'bg-blue-600'
  }
})

const emit = defineEmits(['state'])

onMounted(() => {
  const beforeColor = 'peer-checked:' + color
  style.value.push(beforeColor)
})

watch(check, () => {
  emit('state', check.value)
})
</script>
