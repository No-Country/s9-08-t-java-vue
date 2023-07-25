<template>
  <section class="flex h-full w-full flex-col flex-wrap items-center justify-center gap-y-28 px-3">
    <h1 class="mt-52 text-center text-5xl uppercase text-primary-orange lg:mt-24">
      Conozca nuestra flota
    </h1>
    <fleet-carousel />
  </section>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import FleetCarousel from '@/components/common/MNFleetCarousel.vue'
import { flota } from '@/lib/data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '@/lib/types'
import { useMovingStore } from '@/store/moving'
import { watch } from 'vue'
import { storeToRefs } from 'pinia'
import { MOVING, SENDING } from '../constants'

const store = storeToRefs(useMovingStore())
const data: Ref<IFleetCard[]> = ref(flota)

watch(store.sendingType, () => {
  if (store.sendingType.value === SENDING) {
    data.value[1].selectable = false
    data.value[2].selectable = false
    data.value[0].selectable = true
  }
  if (store.sendingType.value === MOVING) {
    data.value[1].selectable = true
    data.value[2].selectable = true
    data.value[0].selectable = false
  }
})
</script>
