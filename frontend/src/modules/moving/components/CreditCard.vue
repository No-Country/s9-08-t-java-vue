<template>
  <div class="space-y-16">
    <div
      class="relative m-auto h-56 transform rounded-xl bg-red-100 text-white shadow-2xl transition-transform sm:w-96"
    >
      <img class="relative h-full w-full rounded-xl object-cover" :src="cover" />

      <div class="absolute top-8 w-full px-8">
        <div class="flex justify-between">
          <div class="">
            <p class="font-light">Name</p>
            <p class="font-medium tracking-widest">{{ checkout.name.value }}</p>
          </div>
          <img v-if="checkout.isVisa.value" :class="'absolute right-4 h-7 w-14'" :src="visa" />
          <img
            v-if="!checkout.isVisa.value"
            :class="'absolute -top-3 right-4 h-14 w-14'"
            :src="master"
          />
        </div>
        <div class="pt-1">
          <p class="font-light">Card Number</p>
          <p class="tracking-more-wider font-medium">{{ checkout.number.value }}</p>
        </div>
        <div class="pr-6 pt-6">
          <div class="flex justify-between">
            <div class="">
              <p class="text-xs font-light">Valid</p>
              <p class="text-sm font-medium tracking-wider">{{ checkout.date.value }}</p>
            </div>
            <div class="">
              <p class="text-xs font-light">CVV</p>
              <p class="tracking-more-wider text-sm font-bold">···</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import visa from '@/assets/img/visa.png'
import master from '@/assets/img/mastercard.png'
import cover from '@/assets/img/cover-card.png'

import { useCheckoutStore } from '@/store/checkout'
import { storeToRefs } from 'pinia'

const checkout = storeToRefs(useCheckoutStore())
</script>
