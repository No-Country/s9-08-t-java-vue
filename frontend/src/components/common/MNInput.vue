<template>
  <div class="h-16">
    <div :class="['rounded-full border-[2px] border-zinc-300', props.border]">
      <input
        :class="
          twMerge(
            'bg-low-ligth text-low-dark h-10 w-full rounded-full px-3 outline-none',
            props.class
          )
        "
        :value="modelValue"
        @input="updateValue"
        :type="type"
        :placeholder="placeholder"
      />
    </div>
    <p class="ml-2 text-sm text-red-400" v-show="showError">{{ errorMsg }}</p>
  </div>
</template>

<script setup lang="ts">
import { twMerge } from 'tailwind-merge'

const props = defineProps({
  modelValue: String,
  type: {
    default: 'text',
    type: String
  },
  placeholder: {
    type: String,
    required: true
  },
  errorMsg: {
    type: String,
    default: 'error'
  },
  showError: {
    type: Boolean,
    default: false,
    required: false
  },
  border: {
    type: String,
    default: ''
  },
  class: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['update:modelValue'])

const updateValue = (event: Event) => {
  emit('update:modelValue', (event.target! as HTMLInputElement).value)
}
</script>

<style lang="scss" scoped></style>
