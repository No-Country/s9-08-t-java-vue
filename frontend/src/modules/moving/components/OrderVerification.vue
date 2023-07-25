<template>
  <section class="mt-16 flex justify-between py-14">
    <div class="text-blue-night lg:w-1/2">
      <h1 class="mt-32 text-3xl font-bold text-primary-orange">Verificación del pedido</h1>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Cuenta</p>
        <p>{{ checkout.name }}</p>
        <p>{{ profile.store.profile.email }}</p>
      </div>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Dirección de Envío</p>

        <p class="">Origen: {{ moving.stepOne.origin }}</p>
        <p>Destino: {{ moving.stepOne.destination }}</p>
      </div>
      <div class="w-1/2 border-b-2 py-5">
        <p class="mt-5 text-xl font-bold text-primary-orange">Información de pago</p>
        <img v-if="checkout.isVisa" :class="'  h-7 w-14'" :src="visa" />
        <img v-if="!checkout.isVisa" :class="'  right-4 h-14 w-14'" :src="master" />
        <p>
          Tarjeta finalizada en
          {{ checkout.number.split(' ')[checkout.number.split(' ').length - 1] }}
        </p>
      </div>
    </div>
    <div class="flex justify-end lg:w-1/2">
      <VerificationSumary></VerificationSumary>
    </div>
  </section>
</template>

<script lang="ts" setup>
import { useMovingStore } from '@/store/moving'
import { useAuthStore } from '@/modules/auth/store/auth'
import { useCheckoutStore } from '@/store/checkout'
import VerificationSumary from './VerificationSumary.vue'
import visa from '@/assets/img/visa.png'
import master from '@/assets/img/mastercard.png'

const checkout = useCheckoutStore()
const profile = useAuthStore()
const moving = useMovingStore()
</script>
