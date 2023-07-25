<template>
  <main class="container mx-auto overflow-x-hidden p-2 font-montserrat">
    <StepComponent :values="stepComponentData" @set:current="onEdit"></StepComponent>
    <!--TODO: Move section to component stepOne-->
    <section v-if="mySteps == 2">
      <MovingType />
      <FleetWrapper />
      <ExtrasComponent @crew:quantity="" />
      <TimeComponent :timeError="true" />
    </section>
    <!--TODO: Move section to component stepOne-->
    <section v-if="mySteps == 4">
      <OrderVerification></OrderVerification>
    </section>
    <section v-if="mySteps == 3">
      <Payment></Payment>
    </section>
    <section v-if="mySteps == 5">
      <OrderFinished />
    </section>

    <section v-show="(mySteps == 5) == false" class="flex justify-end gap-1">
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
import OrderFinished from '../components/OrderFinished.vue'
import OrderVerification from '../components/OrderVerification.vue'
import Payment from '../components/Payment.vue'

import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const mySteps = ref(2)

const stepComponentData = ref([
  { editable: true, name: 'Tipo de envío', status: true, stepNumber: 1 },
  { editable: true, name: 'Especificaciones del pedido', status: true, stepNumber: 2 },
  { editable: true, name: 'Pago', status: false, stepNumber: 3 },
  { editable: true, name: 'Verificación de pedido', status: false, stepNumber: 4 },
  { editable: false, name: 'Pedido Finalizado', status: false, stepNumber: 5 }
])

const nextStep = () => {
  stepComponentData.value[mySteps.value].status = true
  mySteps.value++
}

const prevStep = () => {
  mySteps.value--
  stepComponentData.value[mySteps.value].status = false
}

const onEdit = (index: number) => {
  if (index == 0) router.push('/')
  stepComponentData.value[index].status = false
  mySteps.value = index
}
</script>
