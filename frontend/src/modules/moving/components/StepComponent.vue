<template>
  <div class="flex h-44 items-center justify-center px-16 py-16">
    <div v-for="(i, idx) in values" class="relative flex items-center">
      <div v-if="idx != 0" class="h-1 w-20 bg-orange-500"></div>
      <div class="flex h-6 w-6 items-center justify-center rounded-full border-2 border-orange-500">
        <div v-if="i.status" class="h-3 w-3 rounded-full bg-orange-500"></div>
      </div>
      <div v-if="idx != values.length - 1" class="h-1 w-20 bg-orange-500"></div>
      <div
        :class="[
          'absolute left-0 right-0 top-6 m-auto mt-3 flex w-40 flex-col items-center justify-center',
          { izq: idx == 0, end: idx == values.length - 1 }
        ]"
      >
        <p class="h-14 text-center font-normal text-primary-text">{{ i.name }}</p>
        <button
          v-if="i.status && i.editable"
          @click="() => setCurrent(i.stepNumber)"
          class="font-bold text-primary-text underline"
        >
          Editar
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { type IStepComponent } from '../interfaces/IStepComponent'

interface IStepProp {
  values: IStepComponent[]
}

const { values } = defineProps<IStepProp>()
const emit = defineEmits(['set:current'])
const setCurrent = (num: number) => emit('set:current', num)
</script>

<style scoped>
.izq {
  left: 0;
  transform: translateX(-40%);
}

.end {
  right: 0;
  transform: translateX(5%);
}
</style>
