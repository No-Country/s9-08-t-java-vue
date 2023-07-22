<template>
  <label class="relative inline-flex cursor-pointer items-center">
    <input type="checkbox" v-model="localState" class="peer sr-only" />
    <div :class="style"></div>
  </label>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { watch } from 'vue'
import { ref } from 'vue'

const localState = ref()
const style = ref([
  'w-11 h-6 bg-gray-200 peer-focus:outline-none  peer-focus:ring-0 rounded-full peer dark:bg-gray-300 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[""] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-primary-orange'
])

interface PropToogle {
  color?: string
  state: boolean
}

const prop = defineProps<PropToogle>()

const emit = defineEmits(['state'])

watch(prop, () => {
  localState.value = prop.state
})

onMounted(() => (localState.value = prop.state))

watch(localState, () => {
  console.log('WATCH TOGGLE')
  emit('state', localState.value)
})
</script>
