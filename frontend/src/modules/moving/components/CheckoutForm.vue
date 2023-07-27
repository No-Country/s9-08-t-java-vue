<template>
  <form @submit.prevent>
    <div>
      <h2 class="text-lg font-semibold text-primary-orange">Nombre Completo</h2>
      <p class="text-sm font-semibold text-blue-night">Nombre completo del titular de la tarjeta</p>
      <MNInput
        placeholder="Nombre completo"
        type="text"
        class="h-12 w-full rounded-lg border border-primary-orange px-4"
        border="border-none"
        error-msg="Campo requerido"
        v-model="checkout.name.value"
      />
    </div>
    <div>
      <h2 class="text-lg font-semibold text-primary-orange">Numero de tarjeta</h2>
      <p class="text-sm font-semibold text-blue-night">Ingrese todos los digitos de la tarjeta</p>
      <MNInput
        placeholder="Numero de tarjeta"
        type="text"
        class="h-12 w-full rounded-lg border border-primary-orange px-4"
        border="border-none"
        error-msg="Campo requerido"
        v-model="checkout.number.value"
      />
    </div>
    <div class="flex gap-5">
      <div>
        <h2 class="text-lg font-semibold text-primary-orange">Valid</h2>
        <MNInput
          placeholder="Fecha de vencimiento"
          type="date"
          class="h-12 w-full rounded-lg border border-primary-orange px-4"
          border="border-none"
          error-msg="Campo requerido"
          v-model="date"
        />
      </div>
      <div>
        <h2 class="text-lg font-semibold text-primary-orange">CCV</h2>
        <MNInput
          placeholder="CCV"
          type="text"
          class="h-12 w-full rounded-lg border border-primary-orange px-4"
          border="border-none"
          error-msg="Campo requerido"
        />
      </div>
    </div>
  </form>
</template>

<script lang="ts" setup>
import MNInput from '@/components/common/MNInput.vue'
import { useCheckoutStore } from '@/store/checkout'
import { storeToRefs } from 'pinia'
import { ref, watch } from 'vue'

const checkout = storeToRefs(useCheckoutStore())
const date = ref('')

const convertDate = () => {
  const fechaInput = date.value
  const fecha = new Date(fechaInput)

  const month = fecha.getMonth() + 1
  const year = fecha.getFullYear() % 100
  const newDate = `${month.toString().padStart(2, '0')}/${year.toString().padStart(2, '0')}`
  checkout.date.value = newDate
}

watch(
  () => date.value,
  () => convertDate()
)
</script>
