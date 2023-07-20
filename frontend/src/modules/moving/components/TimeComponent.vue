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
    <MNDropdownInput
      wrapper-class="w-max"
      text-class="font-semibold text-2xl"
      :text="times"
      :items="['8 a 12hs', '13 a 16 hs', '16 a 20 hs']"
    />
  </section>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import { ref } from 'vue'

const times = ref('Elegir horario de llegada al punto de origen')
</script>
