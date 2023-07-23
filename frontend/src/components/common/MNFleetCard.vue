<template>
  <div class="flex h-96 items-end">
    <div
      class="relative flex h-72 w-80 flex-col items-center justify-center gap-2 rounded-[40px] border-[1px] border-primary-blue p-4"
    >
      <div class="absolute left-0 right-0 top-[-75px] m-auto flex w-52 justify-center">
        <img class="h-28 w-52 object-cover" :src="prop.img" alt="" />
      </div>
      <div class="mt-6 text-center text-blue-night">
        <p class="text-xl font-bold">{{ prop.name }}</p>
        <p>{{ prop.short }}</p>
      </div>
      <div class="mt-3 flex w-full rounded-[15px] bg-blue-100 p-3 text-sm text-zinc-700">
        <div class="flex gap-2">
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
      </div>
      <div class="w-28" color="bg-orange-400">
        <MNButton
          :text="
            prop.type == moving.vehicleType.value
              ? DEFAULT_BTN_NAMES.CONTRATADO
              : DEFAULT_BTN_NAMES.CONTRATAR
          "
          :class="`cursor-pointer rounded-lg bg-primary-orange py-1 ${
            prop.selectable ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
          } ${prop.type == moving.vehicleType.value ? 'bg-green-400' : ''}`"
          :disabled="prop.selectable == false"
          @click="toSelectVehicle"
        ></MNButton>
      </div>
      <div v-if="!prop.selectable" class="text-center text-sm">
        <p>{{ prop.info2 }}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MNButton from '@/components/common/MNButton.vue'
import { type IFleetCard } from '@/lib/types'
import { useMovingStore } from '@/store/moving'
import { storeToRefs } from 'pinia'
import { ref } from 'vue'

interface FleetCardProp {
  prop: IFleetCard
}

const DEFAULT_BTN_NAMES = {
  CONTRATAR: 'Contratar',
  CONTRATADO: 'Contratado'
}

const toSelectVehicle = () => {
  console.log('click')
  moving.vehicleType.value = props.prop.type
}

const btnState = ref('Contratar')
const moving = storeToRefs(useMovingStore())

const props = defineProps<FleetCardProp>()
</script>
