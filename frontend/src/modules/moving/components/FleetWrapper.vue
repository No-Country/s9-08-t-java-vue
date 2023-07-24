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
import { ref } from 'vue'
import FleetCarousel from '@/components/common/MNFleetCarousel.vue'
import { flota } from '@/lib/data/flota-info'
import type { Ref } from 'vue'
import type { IFleetCard } from '@/lib/types'

const data: Ref<IFleetCard[]> = ref(flota)
</script>
