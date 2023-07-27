<template>
  <div
    class="rounded-r-2xl bg-light-blue px-24 py-10 sm:w-full lg:w-4/6 xl:w-4/12 -mv:w-full -mv:rounded-2xl -mv:px-5"
  >
    <p>Resumen de pedido</p>
    <ul class="py-4">
      <div class="flex justify-between">
        <li><p class="font-bold text-primary-orange">Vehiculo</p></li>
        <li>
          <p>${{ vehicle }} x hora</p>
        </li>
      </div>
      <div class="flex justify-between">
        <li>
          <p class="font-bold text-primary-orange">Ayudantes ({{ moving.crewMembers }})</p>
        </li>
        <li>${{ crewMembers }}</li>
      </div>
      <div class="flex justify-between">
        <li><p class="font-bold text-primary-orange">Seguro</p></li>
        <li>${{ insurance }}</li>
      </div>
      <div class="mt-2 flex justify-between">
        <li class="text-2xl">Total</li>
        <li>${{ Number(vehicle + crewMembers + insurance).toFixed(2) }}</li>
      </div>
    </ul>
  </div>
  <div>
    <h1 class="mt-32 text-3xl font-bold text-primary-orange">Seleccione un método de pago</h1>
    <h3 class="text-3xl font-light text-blue-night">
      Seleccione la tarjeta con la que va a realizar la compra.
    </h3>
    <div class="mt-10 flex flex-wrap">
      <div class="w-1/2 -lg:w-full">
        <CheckoutForm class="lg:w-96"></CheckoutForm>
      </div>
      <div class="flex w-1/2 flex-col items-center justify-end -mv:justify-center -lg:w-full">
        <div class="lg:w-1/2">
          <div class="flex gap-3">
            <input type="radio" v-model="checkout.isVisa.value" :value="true" />
            <label class="flex items-center" for="">
              <img class="w-14" :src="visa" alt="" />
              <p>Opcion de pago con Visa</p>
            </label>
          </div>
          <div class="flex gap-3">
            <input type="radio" v-model="checkout.isVisa.value" :value="false" />
            <label class="flex items-center" for="">
              <img class="w-14" :src="master" alt="" />
              <p>Opcion de pago con Master Card</p>
            </label>
          </div>
        </div>
        <div class="lg:w-1/2">
          <CreditCard :is-visa="isVisa"></CreditCard>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { VEHICLES_PRICES } from '@/lib/constants'
import { PRICES } from '../constants'
import { useMovingStore } from '@/store/moving'
import { ref } from 'vue'
import CreditCard from './CreditCard.vue'
import CheckoutForm from './CheckoutForm.vue'
import visa from '@/assets/img/visa.png'
import master from '@/assets/img/mastercard.png'
import { storeToRefs } from 'pinia'
import { useCheckoutStore } from '@/store/checkout'

const moving = useMovingStore()
const checkout = storeToRefs(useCheckoutStore())
const vehicle = ref(VEHICLES_PRICES[moving.vehicleType] * 3)
const crewMembers = ref(PRICES.CREW * moving.crewMembers)
const insurance = ref(moving.insurance ? PRICES.INSAURANCE : 0)

const isVisa = ref(false)
</script>
