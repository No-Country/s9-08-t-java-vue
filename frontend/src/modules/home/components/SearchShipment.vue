<template>
  <div
    class="absolute -bottom-3/4 left-2/4 flex w-11/12 -translate-x-2/4 flex-col gap-2 rounded-4xl bg-white shadow-2xl sm:-bottom-1/2 lg:-bottom-1/4"
  >
    <form class="relative h-full w-full flex-col rounded-4xl bg-white p-16">
      <MNDropdownInput @selected="setType" :items="[SENDING, MOVING]" :text="typeSelected" />
      <div class="flex w-full flex-wrap items-center justify-between gap-2">
        <div class="relative">
          <MNInput
            placeholder="Origen"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
          />
          <a href="https://www.google.com/maps/" target="_blank">
            <img
              class="absolute right-4 top-2/4 -translate-y-2/4"
              src="@/assets/img/hero/location-icon.svg"
              alt="location icon"
            />
          </a>
        </div>
        <div class="relative">
          <MNInput
            placeholder="Destino"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
          />
          <a href="https://www.google.com/maps/" target="_blank">
            <img
              class="absolute right-4 top-2/4 -translate-y-2/4"
              src="@/assets/img/hero/location-icon.svg"
              alt="location icon"
            />
          </a>
        </div>
        <div class="relative">
          <MNInput
            placeholder="Fecha"
            type="date"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
          />
          <!--

            <img
            class="absolute right-4 top-2/4 -translate-y-2/4"
            src="@/assets/img/hero/calendar-icon.svg"
            alt="location icon"
            />
          -->
        </div>
        <MNButton
          text="Buscar"
          class="m-0 h-16 w-max rounded-2xl bg-primary-orange px-8 text-xl font-semibold"
        />
      </div>
      <div class="absolute right-0 top-0 w-2/6 -translate-y-[95%]">
        <img src="@/assets/img/hero/camion.png" alt="truck" />
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import MNInput from '@/components/common/MNInput.vue'
import MNButton from '@/components/common/MNButton.vue'
import { useMovingStore } from '@/store/moving'
import { SENDING, MOVING } from '../constants'
import { ref } from 'vue'

const home = useMovingStore()
const typeSelected = ref('Tipo de envio')
const setType = (type: string) => {
  typeSelected.value = type
  home.setSendinType(type)
}
</script>
