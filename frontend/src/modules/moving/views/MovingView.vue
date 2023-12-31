<template>
  <main class="container mx-auto overflow-x-hidden p-2 font-montserrat">
    <StepComponent :values="stepComponentData" @set:current="onEdit"></StepComponent>
    <!--TODO: Move section to component stepOne-->
    <section v-if="mySteps == 2">
      <MovingType />
      <FleetWrapper />
      <ExtrasComponent @crew:quantity="" />
      <TimeComponent :timeError="timeError" />
    </section>
    <!--TODO: Move section to component stepOne-->
    <section v-if="mySteps == 4">
      <OrderVerification></OrderVerification>
    </section>
    <section class="mb-8" v-if="mySteps == 3">
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
        v-if="(mySteps == 4) == false"
        text="Continuar"
        :class="`w-36 cursor-pointer  rounded-lg bg-primary-orange py-1 ${
          true ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
        }`"
        @click="nextStep"
      ></MNButton>
      <MNButton
        v-if="mySteps == 4"
        text="Comprar"
        :class="`w-36 cursor-pointer  rounded-lg bg-primary-orange py-1 ${
          true ? 'hover:brightness-90' : ' cursor-default bg-zinc-600 opacity-20'
        }`"
        @click="handleSummitMoving"
      ></MNButton>
    </section>
  </main>
  <div class="h-4 w-full">
    <p class="ml-2 text-lg text-red-500" v-show="formError">
      Completa los campos <span class="font-semibold">{{ errorMsg }}</span>
    </p>
  </div>
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
import { storeToRefs } from 'pinia'
import { useMovingStore } from '@/store/moving'
import type { IMoving } from '../interfaces/IMoving'
import { useAuthStore } from '@/modules/auth/store/auth'
import { saveMoving } from '../services/movingServices'

const router = useRouter()
const formError = ref(false)
const errorMsg = ref('')
const mySteps = ref(2)
const timeError = ref(false)
const moving = storeToRefs(useMovingStore())
const auth = useAuthStore()

const stepComponentData = ref([
  { editable: false, name: 'Tipo de envío', status: true, stepNumber: 1 },
  { editable: true, name: 'Especificaciones del pedido', status: true, stepNumber: 2 },
  { editable: true, name: 'Pago', status: false, stepNumber: 3 },
  { editable: true, name: 'Verificación de pedido', status: false, stepNumber: 4 },
  { editable: false, name: 'Pedido Finalizado', status: false, stepNumber: 5 }
])

const isValidMovinForm = () => {
  errorMsg.value = ''
  let flag = true
  if (moving.shift.value.length == 0) {
    timeError.value = true
    errorMsg.value += ' Horario de llegada'
    flag = false
    setTimeout(() => {
      timeError.value = false
    }, 1000)
  }

  if (moving.movingSize.value.length == 0) {
    formError.value = true
    errorMsg.value += ' Tamaño de mudanza'
    flag = false
    setTimeout(() => {
      formError.value = false
    }, 1000)
  }

  if (moving.vehicleType.value.length == 0) {
    formError.value = true
    errorMsg.value += ' Tipo de vehiculo'
    flag = false
    setTimeout(() => {
      formError.value = false
    }, 1000)
  }

  return flag
}

const nextStep = () => {
  if (isValidMovinForm() == false) return

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

const buildMoving = (): IMoving => {
  return {
    crewMembersNumber: moving.crewMembers.value,
    date: moving.stepOne.value.date,
    destinationPoint: moving.stepOne.value.destination,
    idUser: auth.store.profile.id,
    insurance: moving.insurance.value,
    loadingPoint: moving.stepOne.value.origin,
    shift: moving.shift.value,
    vehicleType: moving.vehicleType.value
  }
}

const handleSummitMoving = async () => {
  const res = await saveMoving(buildMoving())
  console.log(res)
  nextStep()
}
</script>
