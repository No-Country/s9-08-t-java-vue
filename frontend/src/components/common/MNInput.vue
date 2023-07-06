<template>
  <div class="h-16">
    <div :class="['border-zinc-300 border-[2px] rounded-full', props.border]">
      <input
        :class="[
          'h-10 px-3 bg-low-ligth text-low-dark w-full outline-none rounded-full',
          props.class
        ]"
        :value="modelValue"
        @input="updateValue"
        :type="type"
        :placeholder="placeholder"
      />
    </div>
    <p class="text-sm ml-2 text-red-400" v-show="showError">{{ errorMsg }}</p>
  </div>
</template>

<script setup lang="ts">
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
