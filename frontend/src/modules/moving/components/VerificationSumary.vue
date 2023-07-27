<template>
  <div class="h-full w-[400px] rounded-bl-3xl rounded-tl-3xl bg-light-blue p-11">
    <h2 class="font-semibold text-primary-blue">RESUMEN</h2>

    <div class="mt-3 flex justify-between border-y-2 border-zinc-300 py-4">
      <p>Monto total</p>
      <p>UDS {{ monto }}</p>
    </div>
    <div class="mt-3 flex flex-col gap-5 border-b-2 border-zinc-300 py-4">
      <div>
        <p>Costo de Taxt</p>
      </div>
      <div>
        <p>Costo de IVA</p>
      </div>
      <div></div>
    </div>
    <div
      class="mt-3 flex justify-between border-b-2 border-zinc-300 py-4 text-xl text-primary-orange"
    >
      <p class="font-bold">Pago total</p>
      <p class="font-bold">UDS {{ monto }}</p>
    </div>
    <div class="mt-2">
      <h2 class="font-semibold">Pagar con</h2>
      <div class="flex items-center">
        <img class="' w-14' h-7" src="../../../assets//img/visa.png" alt="" />
        <img class="' w-14' right-4 h-14" src="../../../assets/img/mastercard.png" alt="" />
      </div>
    </div>
    <h2 class="font-semibold">Usted Cuenta con</h2>
    <div class="mt-2 flex flex-col gap-3">
      <div class="flex items-center gap-2">
        <div>
          <img src="../../../assets//img/sumary-icons/frame.webp" alt="" />
        </div>
        <div>
          <p>Envio gratis</p>
        </div>
      </div>
      <div class="flex items-center gap-2">
        <div>
          <img src="../../../assets//img/sumary-icons/frame1.webp" alt="" />
        </div>
        <div>
          <p>Garantía por un año del vendedor</p>
        </div>
      </div>
      <div class="flex items-center gap-2">
        <div>
          <img src="../../../assets//img/sumary-icons/frame2.webp" alt="" />
        </div>
        <div>
          <p>Devolución gratis del producto</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { SCHEDULES_HOURS, VEHICLES_PRICES } from '@/lib/constants'
import { useMovingStore } from '@/store/moving'
import { ref } from 'vue'
import { PRICES } from '../constants'

const moving = useMovingStore()

const vehicle = ref(VEHICLES_PRICES[moving.vehicleType] * SCHEDULES_HOURS)
const crewMembers = ref(PRICES.CREW * moving.crewMembers)
const insurance = ref(moving.insurance ? PRICES.INSAURANCE : 0)
const monto = ref(Number(vehicle.value + crewMembers.value + insurance.value).toFixed(2))
</script>
