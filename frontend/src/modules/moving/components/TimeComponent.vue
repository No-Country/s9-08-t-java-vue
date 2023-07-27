<template>
  <section class="my-20 flex h-full w-full flex-col justify-center gap-y-10">
    <div class="flex flex-col justify-center gap-y-6">
      <h1 class="text-5xl font-bold text-primary-orange">
        ¿En qué horario necesitas nuestro servicio?
      </h1>
      <h3 class="text-3xl font-light text-blue-night">
        Elige la franja horaria que mejor se adapte a tus necesidades
      </h3>
    </div>
    <!-- TODO: add times with proper format -->
    <div>
      <div class="h-3">
        <p class="ml-2 text-lg text-red-500" v-show="timeError">Selecciona el tipo de envio</p>
      </div>
      <MNDropdownInput
        wrapper-class="w-max"
        text-class="font-semibold text-2xl mt-3"
        :text="times"
        @selected="(arg: string) => (moving.shift.value = SCHEDULES_MAP[arg])"
        :items="Object.keys(SCHEDULES_MAP)"
      />
      <h3 class="mt-3 font-semibold text-blue-night">{{ moving.shift.value }}</h3>
    </div>
  </section>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import { ref } from 'vue'
import { SCHEDULES_MAP } from '../constants'
import { useMovingStore } from '@/store/moving'
import { storeToRefs } from 'pinia'

const moving = storeToRefs(useMovingStore())
const times = ref('Elegir horario de llegada al punto de origen')
defineProps(['timeError'])
</script>
