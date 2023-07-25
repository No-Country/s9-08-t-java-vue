<template>
  <div
    class="absolute -bottom-3/4 left-2/4 flex w-11/12 -translate-x-2/4 flex-col gap-2 rounded-4xl bg-white shadow-2xl sm:-bottom-1/2 lg:-bottom-1/4"
  >
    <form class="relative h-full w-full flex-col rounded-4xl bg-white p-16">
      <div class="h-3">
        <p class="ml-2 text-sm text-red-400" v-show="typeErr">Selecciona el tipo de envio</p>
      </div>
      <MNDropdownInput @selected="setType" :items="[SENDING, MOVING]" :text="typeSelected" />
      <div class="flex w-full flex-wrap items-center justify-between gap-2">
        <div class="relative">
          <MNInput
            placeholder="Origen"
            error-msg="Campo requerido"
            :show-error="originErr"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
            v-model="home.stepOne.value.origin"
          />
          <a href="https://www.google.com/maps/" target="_blank">
            <img
              class="absolute right-4 top-2/4 -translate-y-2/4"
              src="@/assets/img/hero/location-icon.svg"
              alt="location icon"
            />
          </a>
        </div>
        <div class="relative">
          <MNInput
            placeholder="Destino"
            error-msg="Campo requerido"
            :show-error="destinationErr"
            type="text"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
            v-model="home.stepOne.value.destination"
          />
          <a href="https://www.google.com/maps/" target="_blank">
            <img
              class="absolute right-4 top-2/4 -translate-y-2/4"
              src="@/assets/img/hero/location-icon.svg"
              alt="location icon"
            />
          </a>
        </div>
        <div class="relative">
          <MNInput
            placeholder="Fecha"
            type="date"
            class="h-16 w-max rounded-3xl border border-primary-orange px-4"
            border="border-none"
            error-msg="Campo requerido"
            :show-error="dateErr"
            v-model="home.stepOne.value.date"
          />
          <!--

            <img
            class="absolute right-4 top-2/4 -translate-y-2/4"
            src="@/assets/img/hero/calendar-icon.svg"
            alt="location icon"
            />
          -->
        </div>
        <MNButton
          text="Buscar"
          class="m-0 h-16 w-max rounded-2xl bg-primary-orange px-8 text-xl font-semibold"
          @click="search"
        />
      </div>
      <div class="absolute right-0 top-0 w-2/6 -translate-y-[95%]">
        <img src="@/assets/img/hero/camion.png" alt="truck" />
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import MNDropdownInput from '@/components/common/MNDropdownInput.vue'
import MNInput from '@/components/common/MNInput.vue'
import MNButton from '@/components/common/MNButton.vue'
import { useMovingStore } from '@/store/moving'
import { SENDING, MOVING, DEFAULT_TEXT } from '../constants'
import { ref } from 'vue'
import { useAuthStore } from '@/modules/auth/store/auth'
import { useRouter } from 'vue-router'
import type { Ref } from 'vue'
import { storeToRefs } from 'pinia'

const auth = useAuthStore()
const home = storeToRefs(useMovingStore())
const router = useRouter()
const typeSelected = ref(home.stepOne.value.sendingType || DEFAULT_TEXT)

const origin = ref('')
const originErr = ref(false)
const destination = ref('')
const destinationErr = ref(false)
const date = ref('')
const dateErr = ref(false)
const typeErr = ref(false)

const search = () => {
  if (areInputsValid() == false) return
  if (isAnUserLogged() == false) return router.push('/auth')
  //home.setValuesStepOne(origin.value, destination.value, date.value)
  router.push('/moving')
}

const setType = (type: string) => {
  typeSelected.value = type
  home.stepOne.value.sendingType = type
}

const showErrMessage = (input: Ref) => {
  input.value = true
  setTimeout(() => {
    input.value = false
  }, 1000)
}

const areInputsValid = (): boolean => {
  let inputs = true
  if (home.stepOne.value.origin.length < 4) {
    showErrMessage(originErr)
    inputs = false
  }
  if (home.stepOne.value.destination.length < 4) {
    showErrMessage(destinationErr)
    inputs = false
  }
  if (home.stepOne.value.date.length < 4) {
    showErrMessage(dateErr)
    inputs = false
  }
  if (typeSelected.value == DEFAULT_TEXT) {
    showErrMessage(typeErr)
    inputs = false
  }
  return inputs
}

const isAnUserLogged = () => {
  console.log(Boolean(auth.store.profile.token))
  return Boolean(auth.store.profile.token)
}
</script>
