<template>
  <section class="flex h-full w-full flex-col justify-center gap-y-4 px-3">
    <h1 class="mt-52 text-5xl font-bold text-primary-orange lg:mt-24">Elige tu vehículo</h1>
    <h3 class="text-3xl font-light text-blue-night">
      Recuerda que tu paquete debe entrar cómodamente en el vehículo.
    </h3>
    <fleet-carousel />
  </section>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import FleetCarousel from '@/components/common/MNFleetCarousel.vue'
import { flota } from '@/lib/data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '@/lib/types'
import { MOVING } from '@/modules/home/constants'
import { useMovingStore } from '@/store/moving'
import { storeToRefs } from 'pinia'
import { onMounted } from 'vue'

const data: Ref<IFleetCard[]> = ref(flota)
const store = storeToRefs(useMovingStore())

const setAvailable = () => {
  if (store.movingSize.value === 'S') {
    data.value[1].selectable = true
    data.value[2].selectable = false
    data.value[0].selectable = false
  }
  if (store.movingSize.value === 'M') {
    data.value[2].selectable = true
    data.value[0].selectable = false
    data.value[1].selectable = true
  }
  if (store.movingSize.value === 'L') {
    data.value[2].selectable = true
    data.value[0].selectable = false
    data.value[1].selectable = false
  }
}

onMounted(() => {
  setAvailable()
})

watch(
  () => store.movingSize.value,
  () => {
    if (store.sendingType.value === MOVING) {
      //TODO: fix remove magics string
      setAvailable()
    }
  }
)
</script>
