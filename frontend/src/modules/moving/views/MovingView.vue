<template>
  <main class="container mx-auto overflow-x-hidden p-2 font-montserrat">
    <StepComponent :values="stepComponentData"></StepComponent>
    <!--TODO: Move section to component stepOne-->
    <section v-if="stepForm == 1">
      <MovingType />
      <FleetWrapper />
      <ExtrasComponent />
      <TimeComponent />
    </section>
    <!--TODO: Move section to component stepOne-->
    <section v-if="stepForm == 2"></section>
    <section v-if="stepForm == 3"></section>
    <section v-if="stepForm == 4"></section>

    <section class="flex justify-end gap-1">
      <MNButton
        text="Atras"
        :class="`w-36 cursor-pointer rounded-lg bg-primary-orange py-1 ${
          true ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
        }`"
        @click="prevStep"
      ></MNButton>
      <MNButton
        text="Continuar"
        :class="`w-36 cursor-pointer  rounded-lg bg-primary-orange py-1 ${
          true ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
        }`"
        @click="nextStep"
      ></MNButton>
    </section>
  </main>
</template>

<script setup lang="ts">
import FleetWrapper from '../components/FleetWrapper.vue'
import StepComponent from '../components/StepComponent.vue'
import ExtrasComponent from '../components/ExtrasComponent.vue'
import TimeComponent from '../components/TimeComponent.vue'
import MovingType from '../components/MovingType.vue'
import MNButton from '@/components/common/MNButton.vue'
import { ref } from 'vue'

const stepForm = ref(1)
const stepComponentData = ref([
  { editable: true, name: 'Tipo de envío', status: true, stepNumber: 0 },
  { editable: true, name: 'Especificaciones del pedido', status: false, stepNumber: 1 },
  { editable: true, name: 'Pago', status: false, stepNumber: 2 },
  { editable: true, name: 'Verificación de pedido', status: false, stepNumber: 3 },
  { editable: true, name: 'Pedido Finalizado', status: false, stepNumber: 4 }
])

const nextStep = () => {
  stepComponentData.value[stepForm.value].status = true
  stepForm.value++
}

const prevStep = () => {
  stepForm.value--
  stepComponentData.value[stepForm.value].status = false
}
</script>
