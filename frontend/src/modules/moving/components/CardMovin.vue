<template>
  <div class="flex h-96 w-80 flex-col justify-end self-end">
    <div class="relative h-72 w-80 rounded-[40px] border-[1px] border-primary-blue p-4">
      <div class="absolute left-0 right-0 top-[-75px] m-auto flex w-52 justify-center">
        <img class="h-44 w-52 object-cover" src="../../../assets/img//moving//casa.webp" alt="" />
      </div>
      <div class="flex h-full flex-col justify-center">
        <div class="mt-6 flex flex-col justify-center text-center">
          <p class="text-xl font-bold text-zinc-950">Mudanza</p>
          <p class="text-xl font-bold text-zinc-950">{{ size }}</p>
        </div>
        <div class="h-10">
          <p class="mt-16 text-center">{{ info }}</p>
        </div>
      </div>
    </div>
    <div class="mt-5 flex justify-center">
      <MNToggle :state="check" @state="(arg) => $emit('state', arg)"></MNToggle>
    </div>
  </div>
</template>

<script setup lang="ts">
import MNToggle from '@/components/common/MNToggle.vue'
import { ref } from 'vue'

interface CardProp {
  size: string
  info: string
}

defineEmits(['state'])

const props = defineProps<CardProp>()
const check = ref(false)
</script>
