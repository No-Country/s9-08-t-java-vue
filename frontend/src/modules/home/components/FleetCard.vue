<template>
  <div class="relative h-64 w-72 rounded-[40px] border-[1px] border-primary-blue p-4">
    <div class="absolute left-0 right-0 top-[-75px] m-auto flex w-52 justify-center">
      <img class="h-28 w-52 object-cover" :src="prop.img" alt="" />
    </div>
    <div class="mt-6 text-center text-blue-night">
      <p class="font-bold">{{ prop.name }}</p>
      <p>{{ prop.short }}</p>
    </div>
    <div class="mt-1 flex w-full rounded-[15px] bg-blue-100 p-3 text-sm text-zinc-700">
      <div class="flex w-1/2">
        <span>
          <svg
            class="inline"
            width="14"
            height="13"
            viewBox="0 0 14 13"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M11.6666 3.25L5.24992 9.20833L2.33325 6.5"
              stroke="black"
              stroke-width="2"
              stroke-linecap="round"
              stroke-linejoin="round"
            />
          </svg>
        </span>
        <p>{{ prop.info1 }}</p>
      </div>
      <div class="flex w-1/2">
        <span>
          <svg
            class="inline"
            width="14"
            height="13"
            viewBox="0 0 14 13"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M11.6666 3.25L5.24992 9.20833L2.33325 6.5"
              stroke="black"
              stroke-width="2"
              stroke-linecap="round"
              stroke-linejoin="round"
            />
          </svg>
        </span>
        <p>{{ prop.info2 }}</p>
      </div>
    </div>
    <div class="m-auto mt-3 w-28" color="bg-orange-400">
      <MNButton
        text="Contratar"
        class="rounded-lg bg-primary-orange py-1 hover:brightness-90"
      ></MNButton>
    </div>
  </div>
</template>

<script setup lang="ts">
import MNButton from '@/components/common/MNButton.vue'
import { type IFleetCard } from '../interfaces/IFleetCard'

interface FleetCardProp {
  prop: IFleetCard
}

const props = defineProps<FleetCardProp>()
</script>
