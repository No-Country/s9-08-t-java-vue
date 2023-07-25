<template>
  <div class="w-4/12 rounded-r-2xl bg-light-blue px-24 py-10 -mv:w-full -mv:rounded-2xl -mv:px-5">
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
        <li>${{ vehicle + crewMembers + insurance }}</li>
      </div>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { VEHICLES_PRICES } from '@/lib/constants'
import { PRICES } from '../constants'
import { useMovingStore } from '@/store/moving'
import { ref } from 'vue'

const moving = useMovingStore()

const vehicle = ref(VEHICLES_PRICES[moving.vehicleType] * 3)
const crewMembers = ref(PRICES.CREW * moving.crewMembers)
const insurance = ref(moving.insurance ? PRICES.INSAURANCE : 0)
</script>
