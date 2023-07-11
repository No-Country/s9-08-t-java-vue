<template>
  <div
    class="absolute -bottom-1/4 left-2/4 flex w-11/12 -translate-x-2/4 flex-col gap-2 rounded-4xl bg-white shadow-2xl"
  >
    <form class="relative h-full w-full flex-col rounded-4xl bg-white p-16">
      <MNDropdownInput @selected="setType" :items="['Mudanza']" :text="typeSelected" />
      <div class="flex w-full items-center justify-between gap-2">
        <div class="relative">
          <MNInput
            placeholder="Origen"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
          />
          <img
            class="absolute right-4 top-2/4 -translate-y-2/4"
            src="@/assets/img/hero/location-icon.svg"
            alt="location icon"
          />
        </div>
        <div class="relative">
          <MNInput
            placeholder="Destino"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
          />
          <img
            class="absolute right-4 top-2/4 -translate-y-2/4"
            src="@/assets/img/hero/location-icon.svg"
            alt="location icon"
          />
        </div>
        <div class="relative">
          <MNInput
            placeholder="Fecha"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
          />
          <img
            class="absolute right-4 top-2/4 -translate-y-2/4"
            src="@/assets/img/hero/calendar-icon.svg"
            alt="location icon"
          />
        </div>
        <MNButton
          text="Buscar"
          class="m-0 h-16 w-max rounded-2xl bg-primary-orange px-8 text-xl font-semibold"
        />
      </div>
      <img
        class="fixed bottom-2/4 lg:right-0 xl:right-4"
        src="@/assets/img/hero/camion-02-1.webp"
        alt="truck"
      />
    </form>
  </div>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import MNInput from '@/components/common/MNInput.vue'
import MNButton from '@/components/common/MNButton.vue'

import { ref } from 'vue'

const typeSelected = ref('Tipo de envio')
const setType = (type: string) => (typeSelected.value = type)
</script>
